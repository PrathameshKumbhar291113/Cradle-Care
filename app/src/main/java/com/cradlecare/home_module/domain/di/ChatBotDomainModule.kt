package com.cradlecare.home_module.domain.di

import com.cradlecare.home_module.domain.repository.ChatBotRepository
import com.cradlecare.home_module.domain.use_case.ChatBotUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatBotDomainModule {

    @Provides
    @Singleton
    fun providesChatBotRepository(
        chatBotRepository: ChatBotRepository
    ): ChatBotUseCase {
        return ChatBotUseCase(chatBotRepository)
    }
}