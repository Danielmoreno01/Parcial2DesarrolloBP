package com.example.parcial2.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.parcial2.Producto
import com.example.parcial2.ui.theme.*

@Composable
fun AppNavigation(
    navController: NavHostController,
    productos: MutableList<Producto>,
    carrito:   MutableList<Producto>
) {
    NavHost(navController = navController, startDestination = "catalogo") {
        composable("catalogo") {
            Catalogo(
                productos, carrito,
                onNavigateToRegistro = { navController.navigate("registro") },
                onNavigateToCarrito = { navController.navigate("carrito") },
                onNavigateToDetalle = { id -> navController.navigate("detalle/$id") }
            )
        }
        composable("registro") {
            RegistroProductos(productos) { navController.popBackStack() }
        }
        composable("carrito") {
            Carrito(carrito) { navController.popBackStack() }
        }
        composable("detalle/{id}") { back ->
            val id = back.arguments?.getString("id")?.toIntOrNull()
            val p  = productos.find { it.id == id }
            if (p != null) {
                Detalle(p, carrito) { navController.popBackStack() }
            } else {
                Text("Producto no encontrado")
            }
        }
    }
}
