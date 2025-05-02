package com.example.parcial2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.parcial2.navigation.AppNavigation
import com.example.parcial2.ui.theme.Parcial2Theme
import androidx.compose.ui.tooling.preview.Preview


class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Parcial2Theme {
                val productos = mutableStateListOf<Producto>()
                val carrito = mutableStateListOf<Producto>()
                val navController = rememberNavController()
                AppNavigation(navController, productos, carrito)
            }
        }
    }
}
