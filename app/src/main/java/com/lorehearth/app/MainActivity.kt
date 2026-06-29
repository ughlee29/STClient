package com.lorehearth.app

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.charset.Charset
import java.util.UUID
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    private val prefs by lazy { getSharedPreferences("lorehearth", Context.MODE_PRIVATE) }
    private val client by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoreHearthApp(
                loadSettings = ::loadSettings,
                saveSettings = ::saveSettings,
                loadCharacter = ::loadCharacter,
                saveCharacter = ::saveCharacter,
                loadMessages = ::loadMessages,
                saveMessages = ::saveMessages,
                clearMessages = ::clearMessages,
                readUriBytes = ::readUriBytes,
                generateCloudReply = ::generateCloudReply
            )
        }
    }

    private fun loadSettings(): AppSettings = AppSettings(
        baseUrl = prefs.getString("baseUrl", "https://api.openai.com/v1") ?: "https://api.openai.com/v1",
        apiKey = prefs.getString("apiKey", "") ?: "",
        model = prefs.getString("model", "") ?: "",
        temperature = prefs.getString("temperature", "0.8") ?: "0.8",
        maxTokens = prefs.getString("maxTokens", "1024") ?: "1024",
        useCloud = prefs.getBoolean("useCloud", true)
    )

    private fun saveSettings(settings: AppSettings) {
        prefs.edit()
            .putString("baseUrl", settings.baseUrl.trim())
            .putString("apiKey", settings.apiKey.trim())
            .putString("model", settings.model.trim())
            .putString("temperature", settings.temperature.trim())
            .putString("maxTokens", settings.maxTokens.trim())
            .putBoolean("useCloud", settings.useCloud)
            .apply()
    }

    private fun loadCharacter(): LoreCharacter? {
        val raw = prefs.getString("activeCharacter", null) ?: return null
        return runCatching { LoreCharacter.fromJson(JSONObject(raw)) }.getOrNull()
    }

    private fun saveCharacter(character: LoreCharacter) {
        prefs.edit().putString("activeCharacter", character.toJson().toString()).apply()
    }

    private fun loadMessages(): List<ChatTurn> {
        val raw = prefs.getString("messages", "[]") ?: "[]"
        return runCatching {
            val array = JSONArray(raw)
            buildList {
                for (index in 0 until array.length()) {
                    val item = array.getJSONObject(index)
                    add(ChatTurn(role = item.optString("role"), content = item.optString("content")))
                }
            }
        }.getOrDefault(emptyList())
    }

    private fun saveMessages(messages: List<ChatTurn>) {
        val array = JSONArray()
        messages.forEach { turn -> array.put(JSONObject().put("role", turn.role).put("content", turn.content)) }
        prefs.edit().putString("messages", array.toString()).apply()
    }

    private fun clearMessages() {
        prefs.edit().remove("messages").apply()
    }

    private fun readUriBytes(uri: Uri): ByteArray {
        contentResolver.openInputStream(uri)?.use { return it.readBytes() }
        throw IOException("Could not open selected file.")
    }

    private suspend fun generateCloudReply(settings: AppSettings, character: LoreCharacter?, messages: List<ChatTurn>): String =
        withContext(Dispatchers.IO) {
            if (!settings.useCloud) return@withContext "Campfire Mode is planned for v0.2. Offline GGUF support is not wired yet."
            if (settings.apiKey.isBlank()) throw IllegalStateException("API key is missing.")
            if (settings.model.isBlank()) throw IllegalStateException("Model ID is missing.")

            val request = Request.Builder()
                .url(normalizeChatEndpoint(settings.baseUrl))
                .addHeader("Authorization", "Bearer ${settings.apiKey}")
                .addHeader("Content-Type", "application/json")
                .addHeader("HTTP-Referer", "https://github.com/ughlee29/STClient")
                .addHeader("X-Title", "LoreHearth")
                .post(buildChatCompletionBody(settings, character, messages).toString().toRequestBody("application/json".toMediaType()))
                .build()

            client.newCall(request).execute().use { response ->
                val responseText = response.body?.string().orEmpty()
                if (!response.isSuccessful) {
                    val message = runCatching { JSONObject(responseText).optJSONObject("error")?.optString("message") }.getOrNull().orEmpty()
                    throw IOException("Cloud request failed ${response.code}: ${message.ifBlank { responseText.take(500) }}")
                }
                val json = JSONObject(responseText)
                val choice = json.getJSONArray("choices").getJSONObject(0)
                choice.getJSONObject("message").optString("content").ifBlank {
                    throw IOException("Cloud response did not include message content.")
                }
            }
        }
}

data class AppSettings(
    val baseUrl: String,
    val apiKey: String,
    val model: String,
    val temperature: String,
    val maxTokens: String,
    val useCloud: Boolean
)

data class LoreCharacter(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String = "",
    val personality: String = "",
    val scenario: String = "",
    val firstMessage: String = "",
    val exampleDialogue: String = "",
    val creatorNotes: String = "",
    val systemPrompt: String = ""
) {
    fun toJson(): JSONObject = JSONObject()
        .put("id", id)
        .put("name", name)
        .put("description", description)
        .put("personality", personality)
        .put("scenario", scenario)
        .put("firstMessage", firstMessage)
        .put("exampleDialogue", exampleDialogue)
        .put("creatorNotes", creatorNotes)
        .put("systemPrompt", systemPrompt)

    companion object {
        fun fromJson(json: JSONObject): LoreCharacter = LoreCharacter(
            id = json.optString("id").ifBlank { UUID.randomUUID().toString() },
            name = json.optString("name").ifBlank { "Imported Character" },
            description = json.optString("description"),
            personality = json.optString("personality"),
            scenario = json.optString("scenario"),
            firstMessage = json.optString("firstMessage"),
            exampleDialogue = json.optString("exampleDialogue"),
            creatorNotes = json.optString("creatorNotes"),
            systemPrompt = json.optString("systemPrompt")
        )
    }
}

data class ChatTurn(val role: String, val content: String)

private enum class AppTab { Hearth, Import, Settings, Campfire }

@Composable
private fun LoreHearthApp(
    loadSettings: () -> AppSettings,
    saveSettings: (AppSettings) -> Unit,
    loadCharacter: () -> LoreCharacter?,
    saveCharacter: (LoreCharacter) -> Unit,
    loadMessages: () -> List<ChatTurn>,
    saveMessages: (List<ChatTurn>) -> Unit,
    clearMessages: () -> Unit,
    readUriBytes: (Uri) -> ByteArray,
    generateCloudReply: suspend (AppSettings, LoreCharacter?, List<ChatTurn>) -> String
) {
    var activeTab by remember { mutableStateOf(AppTab.Hearth) }
    var settings by remember { mutableStateOf(loadSettings()) }
    var character by remember { mutableStateOf(loadCharacter()) }
    var messages by remember { mutableStateOf(loadMessages()) }
    var status by remember { mutableStateOf("Ready.") }
    val scope = rememberCoroutineScope()

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text("LoreHearth", style = MaterialTheme.typography.headlineMedium)
                Text("v0.1 Hearth Spine — cloud chat first, offline later.")
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    AppTab.entries.forEach { tab -> TextButton(onClick = { activeTab = tab }) { Text(tab.name) } }
                }
                Divider()
                Spacer(Modifier.height(8.dp))
                when (activeTab) {
                    AppTab.Hearth -> HearthScreen(character, messages, status, onSend = { userText ->
                        if (userText.isBlank()) return@HearthScreen
                        val pending = messages + ChatTurn("user", userText.trim())
                        messages = pending
                        saveMessages(pending)
                        status = "Sending..."
                        scope.launch {
                            runCatching {
                                val reply = generateCloudReply(settings, character, pending)
                                messages = pending + ChatTurn("assistant", reply)
                                saveMessages(messages)
                                status = "Reply received."
                            }.onFailure { error -> status = error.message ?: "Unknown error." }
                        }
                    }, onClear = {
                        messages = emptyList()
                        clearMessages()
                        status = "Chat cleared."
                    })
                    AppTab.Import -> ImportScreen(status, readUriBytes, onImported = { imported ->
                        character = imported
                        saveCharacter(imported)
                        messages = if (imported.firstMessage.isNotBlank()) listOf(ChatTurn("assistant", imported.firstMessage)) else emptyList()
                        saveMessages(messages)
                        status = "Imported ${imported.name}."
                        activeTab = AppTab.Hearth
                    }, onError = { status = it })
                    AppTab.Settings -> SettingsScreen(settings) { updated ->
                        settings = updated
                        saveSettings(updated)
                        status = "Settings saved."
                    }
                    AppTab.Campfire -> CampfireScreen()
                }
            }
        }
    }
}

@Composable
private fun HearthScreen(character: LoreCharacter?, messages: List<ChatTurn>, status: String, onSend: (String) -> Unit, onClear: () -> Unit) {
    var input by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(character?.let { "Active character: ${it.name}" } ?: "No character imported yet.")
        Text("Status: $status")
        Spacer(Modifier.height(8.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(messages) { message ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(message.role.uppercase(), style = MaterialTheme.typography.labelMedium)
                        Text(message.content)
                    }
                }
            }
        }
        OutlinedTextField(value = input, onValueChange = { input = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Message") }, minLines = 2)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { onSend(input); input = "" }) { Text("Send") }
            TextButton(onClick = onClear) { Text("Clear chat") }
        }
    }
}

@Composable
private fun ImportScreen(status: String, readUriBytes: (Uri) -> ByteArray, onImported: (LoreCharacter) -> Unit, onError: (String) -> Unit) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        if (uri == null) return@rememberLauncherForActivityResult
        runCatching { parseCharacterCard(readUriBytes(uri)) }
            .onSuccess(onImported)
            .onFailure { error -> onError(error.message ?: "Import failed.") }
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Lore Vault")
        Text("Import one JSON card or PNG card. Phone scanning is intentionally disabled in v0.1; file picker first.")
        Text("Status: $status")
        Button(onClick = { launcher.launch(arrayOf("application/json", "image/png", "text/*", "*/*")) }) { Text("Import character card") }
    }
}

@Composable
private fun SettingsScreen(settings: AppSettings, onSave: (AppSettings) -> Unit) {
    var baseUrl by remember { mutableStateOf(settings.baseUrl) }
    var apiKey by remember { mutableStateOf(settings.apiKey) }
    var model by remember { mutableStateOf(settings.model) }
    var temperature by remember { mutableStateOf(settings.temperature) }
    var maxTokens by remember { mutableStateOf(settings.maxTokens) }
    var useCloud by remember { mutableStateOf(settings.useCloud) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Cloud Route Settings")
        Row { Text("Use cloud API"); Spacer(Modifier.weight(1f)); Switch(checked = useCloud, onCheckedChange = { useCloud = it }) }
        OutlinedTextField(value = baseUrl, onValueChange = { baseUrl = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Base URL") })
        OutlinedTextField(value = apiKey, onValueChange = { apiKey = it }, modifier = Modifier.fillMaxWidth(), label = { Text("API key") }, visualTransformation = PasswordVisualTransformation())
        OutlinedTextField(value = model, onValueChange = { model = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Model ID") })
        OutlinedTextField(value = temperature, onValueChange = { temperature = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Temperature") })
        OutlinedTextField(value = maxTokens, onValueChange = { maxTokens = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Max output tokens") })
        Button(onClick = { onSave(AppSettings(baseUrl, apiKey, model, temperature, maxTokens, useCloud)) }) { Text("Save settings") }
    }
}

@Composable
private fun CampfireScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Campfire Mode")
        Text("Offline local LLM support is planned for v0.2. It is not faked in v0.1.")
        Text("Target path: GGUF model picker/import, llama.cpp JNI wrapper, lightweight RP model, compact prompt harness.")
    }
}

private fun normalizeChatEndpoint(baseUrl: String): String {
    val trimmed = baseUrl.trim().trimEnd('/')
    return if (trimmed.endsWith("/chat/completions")) trimmed else "$trimmed/chat/completions"
}

private fun buildChatCompletionBody(settings: AppSettings, character: LoreCharacter?, messages: List<ChatTurn>): JSONObject {
    val chatMessages = JSONArray()
    chatMessages.put(JSONObject().put("role", "system").put("content", buildSystemPrompt(character)))
    messages.takeLast(40).forEach { turn ->
        val role = if (turn.role == "assistant") "assistant" else "user"
        chatMessages.put(JSONObject().put("role", role).put("content", turn.content))
    }
    return JSONObject()
        .put("model", settings.model.trim())
        .put("messages", chatMessages)
        .put("temperature", settings.temperature.toDoubleOrNull() ?: 0.8)
        .put("max_tokens", settings.maxTokens.toIntOrNull() ?: 1024)
}

private fun buildSystemPrompt(character: LoreCharacter?): String = buildString {
    appendLine("You are powering a character roleplay chat inside LoreHearth.")
    appendLine("Stay in character. Continue the scene through dialogue, action, and sensory detail when appropriate.")
    appendLine("Do not mention being an AI unless the user explicitly asks out of character.")
    appendLine("Respect consent, user agency, and established character facts.")
    if (character != null) {
        appendLine()
        appendLine("Character name: ${character.name}")
        if (character.description.isNotBlank()) appendLine("Description: ${character.description}")
        if (character.personality.isNotBlank()) appendLine("Personality: ${character.personality}")
        if (character.scenario.isNotBlank()) appendLine("Scenario: ${character.scenario}")
        if (character.exampleDialogue.isNotBlank()) appendLine("Example dialogue: ${character.exampleDialogue}")
        if (character.creatorNotes.isNotBlank()) appendLine("Creator notes: ${character.creatorNotes}")
        if (character.systemPrompt.isNotBlank()) appendLine("Character system prompt: ${character.systemPrompt}")
    }
}

private fun parseCharacterCard(bytes: ByteArray): LoreCharacter {
    val rawJson = extractPngCharaJson(bytes) ?: String(bytes, Charset.forName("UTF-8"))
    val root = JSONObject(rawJson.trim())
    val data = root.optJSONObject("data") ?: root
    return LoreCharacter(
        name = data.firstText("name").ifBlank { "Imported Character" },
        description = data.firstText("description", "char_persona"),
        personality = data.firstText("personality"),
        scenario = data.firstText("scenario"),
        firstMessage = data.firstText("first_mes", "firstMessage", "first_message"),
        exampleDialogue = data.firstText("mes_example", "example_dialogue"),
        creatorNotes = data.firstText("creator_notes", "creatorNotes"),
        systemPrompt = data.firstText("system_prompt", "systemPrompt")
    )
}

private fun JSONObject.firstText(vararg keys: String): String {
    for (key in keys) {
        val value = optString(key, "")
        if (value.isNotBlank()) return value
    }
    return ""
}

private fun extractPngCharaJson(bytes: ByteArray): String? {
    val pngHeader = byteArrayOf(0x89.toByte(), 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A)
    if (bytes.size < 8 || !bytes.copyOfRange(0, 8).contentEquals(pngHeader)) return null
    var offset = 8
    while (offset + 8 <= bytes.size) {
        val length = ByteBuffer.wrap(bytes, offset, 4).int
        val typeStart = offset + 4
        val dataStart = offset + 8
        val dataEnd = dataStart + length
        if (length < 0 || dataEnd + 4 > bytes.size) return null
        val type = String(bytes, typeStart, 4, Charsets.US_ASCII)
        if (type == "tEXt") {
            val chunk = bytes.copyOfRange(dataStart, dataEnd)
            val separator = chunk.indexOf(0)
            if (separator > 0) {
                val keyword = String(chunk, 0, separator, Charsets.ISO_8859_1)
                val text = String(chunk, separator + 1, chunk.size - separator - 1, Charsets.ISO_8859_1)
                if (keyword == "chara") return String(Base64.decode(text, Base64.DEFAULT), Charsets.UTF_8)
            }
        }
        if (type == "IEND") return null
        offset = dataEnd + 4
    }
    return null
}

private fun ByteArray.indexOf(value: Int): Int {
    for (index in indices) if (this[index].toInt() == value) return index
    return -1
}
