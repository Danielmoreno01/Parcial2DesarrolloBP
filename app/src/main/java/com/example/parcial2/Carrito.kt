package com.example.parcial2.ui.theme

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.parcial2.Producto

@Composable
fun Carrito(
    carrito: MutableList<Producto>,
    onBack: () -> Unit
) {
    val ctx = LocalContext.current
    val isEmpty = carrito.isEmpty()

    Column(Modifier.padding(16.dp)) {
        Text("Carrito de Compras", style = MaterialTheme.typography.headlineSmall.copy(color = Color.Red))
        Spacer(Modifier.height(8.dp))

        if (isEmpty) {
            Text("El carrito está vacío", color = MaterialTheme.colorScheme.error)
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(carrito) { producto ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("${producto.nombre} - $${producto.precio}", modifier = Modifier.weight(1f))
                        IconButton(onClick = { carrito.remove(producto) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(8.dp))
        Text("Total: $${carrito.sumOf { it.precio }}", style = MaterialTheme.typography.bodyLarge.copy(color = Color.White))
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                if (isEmpty) {
                    Toast.makeText(ctx, "No hay productos en el carrito", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(ctx, "Compra Finalizada", Toast.LENGTH_SHORT).show()
                    carrito.clear()
                }
            },
            enabled = !isEmpty,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Finalizar Compra", color = Color.White)
        }

        Spacer(Modifier.height(8.dp))
        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Volver")
        }
    }
}
