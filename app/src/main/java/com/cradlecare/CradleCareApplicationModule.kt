package com.cradlecare

import com.cradlecare.network_module.ApiCommunicator
import com.cradlecare.network_module.ChatBotApiService
import com.cradlecare.utils.ApiConstantsCradleCare
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CradleCareApplicationModule {
    @Provides
    @Singleton
    fun provideApiCommunicator(): ApiCommunicator {
        return Retrofit.Builder()
            .baseUrl(ApiConstantsCradleCare.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiCommunicator::class.java)
    }

    @Provides
    @Singleton
    fun provideChatGptApiService() : ChatBotApiService {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", "Bearer ${ApiConstantsCradleCare.OPEN_AI_CHAT_GPT_API_KEY}") // Replace YOUR_API_KEY with your actual API key
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiConstantsCradleCare.CHAT_BOT_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatBotApiService::class.java)
    }
}