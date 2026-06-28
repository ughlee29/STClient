package com.sillytavern.kotlin.config

interface ChatDao {
    suspend fun messages(chatId: String): List<ChatMessage>
    suspend fun message(id: String): ChatMessage?
    suspend fun insert(message: ChatMessage)
    suspend fun update(message: ChatMessage)
    suspend fun delete(messageId: String)
    suspend fun siblings(chatId: String, parentId: String?, branchGroup: String): List<ChatMessage>
}

class InMemoryChatDao : ChatDao {
    private val rows = linkedMapOf<String, ChatMessage>()

    override suspend fun messages(chatId: String): List<ChatMessage> =
        rows.values.filter { it.chatId == chatId }.sortedBy { it.timestamp }

    override suspend fun message(id: String): ChatMessage? = rows[id]
    override suspend fun insert(message: ChatMessage) { rows[message.id] = message }
    override suspend fun update(message: ChatMessage) { rows[message.id] = message }
    override suspend fun delete(messageId: String) { rows.remove(messageId) }

    override suspend fun siblings(chatId: String, parentId: String?, branchGroup: String): List<ChatMessage> =
        rows.values.filter { it.chatId == chatId && it.parentId == parentId && it.branchGroup == branchGroup }
            .sortedBy { it.branchIndex }
}
