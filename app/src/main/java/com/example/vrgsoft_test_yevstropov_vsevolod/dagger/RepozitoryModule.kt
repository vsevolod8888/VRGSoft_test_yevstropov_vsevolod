package com.example.vrgsoft_test_yevstropov_vsevolod.dagger

import android.content.Context
import com.example.vrgsoft_test_yevstropov_vsevolod.Repozitory
import com.example.vrgsoft_test_yevstropov_vsevolod.api.RedditApiService
import com.example.vrgsoft_test_yevstropov_vsevolod.api.retrofit
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepozitoryModule(var context: Context) {
    @Provides
    @Singleton
    fun providesApiService():RedditApiService{
        val ourApiService: RedditApiService = retrofit.create(RedditApiService::class.java)
        return ourApiService
    }

    @Provides
    @Singleton
    fun providesRepozitory(service: RedditApiService): Repozitory {
        return Repozitory(service) //в модуле создаются зависимости
    }
}