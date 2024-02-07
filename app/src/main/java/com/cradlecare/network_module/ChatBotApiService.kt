package com.cradlecare.network_module

import com.cradlecare.network_module.network_models.home_module.chat_bot_feature.ChatBotPromptResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatBotApiService {
    @POST("v1/completions")
    suspend fun postChatGpPrompt(@Body requestBody: JsonObject): Response<ChatBotPromptResponse>
}