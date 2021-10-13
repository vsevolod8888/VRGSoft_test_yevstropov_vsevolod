package com.example.uklontestzadanie.dagger

import android.content.Context
import com.example.vrgsoft_test_yevstropov_vsevolod.Repozitory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepozitoryModule(var context: Context) {


    @Provides
    @Singleton
    fun providesRepozitory(): Repozitory {
        return Repozitory() //в модуле создаются зависимости
    }
}