package com.cradlecare.home_module.data.di

import com.cradlecare.home_module.data.repository.ChatBotRepoImpl
import com.cradlecare.home_module.domain.repository.ChatBotRepository
import com.cradlecare.network_module.ChatBotApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatBotDataModule {

    @Provides
    @Singleton
    fun providesChatBotRepositoryImpl(apiService: ChatBotApiService): ChatBotRepository{
        return ChatBotRepoImpl(apiService)
    }
}