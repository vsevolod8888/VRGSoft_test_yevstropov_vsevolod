package com.example.vrgsoft_test_yevstropov_vsevolod.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vrgsoft_test_yevstropov_vsevolod.MainViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelsModule { // возвращает фабрику, чтобы можно было инжектить во вью модел

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindFragmentCoordViewModel(viewModel: MainViewModel?): ViewModel?


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory?): ViewModelProvider.Factory? //должно провайдится только один раз для всех вью моделей

}