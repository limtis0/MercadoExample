package com.example.mercado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mercado.dependency_injection.ApplicationComponent
import com.example.mercado.dependency_injection.DaggerApplicationComponent
import com.example.mercado.ui.screens.MainScreen
import com.example.mercado.ui.theme.MercadoExampleTheme

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent = DaggerApplicationComponent.create()

        setContent {
            MercadoExampleTheme {
                MainScreen()
            }
        }
    }
}
