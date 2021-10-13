package com.example.vrgsoft_test_yevstropov_vsevolod.dagger
import com.example.vrgsoft_test_yevstropov_vsevolod.FragmentPicture
import com.example.vrgsoft_test_yevstropov_vsevolod.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepozitoryModule::class, ViewModelsModule::class]) //
interface Component {
    fun inject(mainFragment: MainFragment)
    fun inject(fragmentPicture: FragmentPicture)

}