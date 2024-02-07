package com.cradlecare.network_module.network_models.home_module.chat_bot_feature

data class ChatBotRequestBody(
    val model: String = "gpt-3.5-turbo-instruct",
    val prompt: String,
    val maxTokens: Int = 4000,
    val temperature: Int = 0
)
