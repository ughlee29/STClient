package com.sillytavern.kotlin.config

object InferenceSetupUsage {
    val quickStart = listOf(
        "Choose a cloud or local LLM provider.",
        "Enter API keys through ApiKeyManager, not hardcoded source.",
        "Load character cards through STCardParser.",
        "Build prompts through STPromptBuilder.",
        "Watch context pressure through ContextBudgetManager and ContextHealthIndicator."
    )
}
