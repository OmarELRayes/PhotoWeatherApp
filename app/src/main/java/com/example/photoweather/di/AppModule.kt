/*
 *  Created by Omar ELRayes@CresignZone on 3/21/22, 1:18 PM
 *  Copyright (c) 2022 . All rights reserved.
 *  Last modified 3/20/22, 10:28 PM
 */

package com.example.photoweather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    companion object {
        @Provides
        @Singleton
        fun provideCoroutineContext(): CoroutineDispatcher {
            return Dispatchers.IO
        }
    }
}
