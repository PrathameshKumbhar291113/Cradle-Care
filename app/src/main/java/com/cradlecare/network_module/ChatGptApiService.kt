package com.cradlecare.network_module

import com.cradlecare.network_module.models.chat_gpt_module.ChatGptPromptResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatGptApiService {
    @POST("v1/completions")
    fun postChatGpPrompt(@Body requestBody: Map<String, Any>): Response<ChatGptPromptResponse>
}