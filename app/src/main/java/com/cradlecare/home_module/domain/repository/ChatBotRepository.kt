package com.cradlecare.home_module.domain.repository

import com.cradlecare.network_module.network_models.home_module.chat_bot_feature.ChatBotPromptResponse
import com.google.gson.JsonObject
import retrofit2.Response

interface ChatBotRepository {
    suspend fun postChatGpPrompt(requestBody: JsonObject): Response<ChatBotPromptResponse>
}