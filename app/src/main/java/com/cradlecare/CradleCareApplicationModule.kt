package com.cradlecare

import com.cradlecare.network_module.ApiCommunicator
import com.cradlecare.network_module.ChatGptApiService
import com.cradlecare.utils.ApiConstantsCradleCare
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideChatGptApiService() : ChatGptApiService{
        return Retrofit.Builder()
            .baseUrl(ApiConstantsCradleCare.CHAT_GPT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatGptApiService::class.java)
    }
}