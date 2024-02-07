package com.cradlecare.home_module.data.repository

import com.cradlecare.home_module.domain.repository.ChatBotRepository
import com.cradlecare.network_module.ChatBotApiService
import com.cradlecare.network_module.network_models.home_module.chat_bot_feature.ChatBotPromptResponse
import com.google.gson.JsonObject
import retrofit2.Response
import javax.inject.Inject

class ChatBotRepoImpl @Inject constructor(private val apiService: ChatBotApiService) :
    ChatBotRepository {
    override suspend fun postChatGpPrompt(requestBody: JsonObject): Response<ChatBotPromptResponse> {
        return apiService.postChatGpPrompt(requestBody)
    }

}