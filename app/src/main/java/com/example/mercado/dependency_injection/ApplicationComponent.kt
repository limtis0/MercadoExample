package com.example.mercado.dependency_injection

import com.example.mercado.retrofit.domain.RetrofitDIModule
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [
        RetrofitDIModule::class
    ]
)
@Singleton
interface ApplicationComponent {
    // fun inject(application: SomeApplication)
}