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
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*


@Composable
fun Carrito(
    carrito: MutableList<Producto>,
    onBack: () -> Unit
) {
    val ctx = LocalContext.current
    val isEmpty = carrito.isEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFDE7))
            .padding(16.dp)
    ) {
        Spacer(Modifier.height(32.dp))
        Text(
            "Carrito de Compras",
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFF33691E),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(12.dp))

        if (isEmpty) {
            Text(
                "El carrito está vacío",
                color = Color(0xFFD32F2F),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(carrito) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .shadow(2.dp, RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    producto.nombre,
                                    style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF2E7D32))
                                )
                                Text(
                                    "$${producto.precio}",
                                    style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF388E3C))
                                )
                            }
                            IconButton(onClick = { carrito.remove(producto) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color(0xFFD32F2F))
                            }
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))
        Text(
            "Total: $${carrito.sumOf { it.precio }}",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF33691E)
        )
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
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784))
        ) {
            Text("Finalizar Compra", color = Color.White)
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = CircleShape
        ) {
            Text("Volver")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCarrito() {
    Parcial2Theme {
        val carrito = remember {
            mutableStateListOf(
                Producto(1, "Café orgánico", 15.0, "Café colombiano natural", ""),
                Producto(2, "Té verde", 8.0, "Té de hojas naturales", "")
            )
        }
        Carrito(carrito = carrito, onBack = {})
    }
}
