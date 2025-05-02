package com.example.parcial2.ui.theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import coil.compose.rememberImagePainter
import com.example.parcial2.Producto
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun Catalogo(
    productos: MutableList<Producto>,
    carrito: List<Producto>,
    onNavigateToRegistro: () -> Unit,
    onNavigateToCarrito: () -> Unit,
    onNavigateToDetalle: (Int) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var productoAEliminar by remember { mutableStateOf<Producto?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFDE7))
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            "Catálogo de Productos",
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFF33691E),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(productos) { producto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .shadow(4.dp, shape = RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = producto.imagenUrl.ifBlank { "https://via.placeholder.com/150" },
                                builder = {
                                    placeholder(android.R.drawable.ic_menu_gallery)
                                    error(android.R.drawable.ic_delete)
                                }
                            ),
                            contentDescription = "Imagen del producto",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )

                        Spacer(Modifier.width(16.dp))

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

                        Column(horizontalAlignment = Alignment.End) {
                            IconButton(onClick = { onNavigateToDetalle(producto.id) }) {
                                Icon(Icons.Default.Info, contentDescription = "Detalle", tint = Color(0xFF33691E))
                            }
                            IconButton(onClick = {
                                productoAEliminar = producto
                                showDialog = true
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color(0xFFB71C1C))
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            "Total Carrito: $${carrito.sumOf { it.precio }}",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF33691E)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onNavigateToRegistro,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784))
            ) {
                Text("Agregar Producto", color = Color.White)
            }
            Button(
                onClick = onNavigateToCarrito,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF66BB6A))
            ) {
                Text("Ver Carrito", color = Color.White)
            }
        }
    }

    if (showDialog && productoAEliminar != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("¿Eliminar producto?") },
            text = { Text("¿Seguro que deseas eliminar '${productoAEliminar?.nombre}' del catálogo?") },
            confirmButton = {
                TextButton(onClick = {
                    productos.remove(productoAEliminar)
                    showDialog = false
                    productoAEliminar = null
                }) {
                    Text("Eliminar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                    productoAEliminar = null
                }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewCatalogo() {
    Parcial2Theme {
        val productos = remember {
            mutableStateListOf(
                Producto(1, "Flor silvestre", 10.0, "Hermosa flor", ""),
                Producto(2, "Maceta ecológica", 25.0, "Ideal para plantas", "")
            )
        }
        val carrito = remember { mutableStateListOf<Producto>() }
        Catalogo(
            productos = productos,
            carrito = carrito,
            onNavigateToRegistro = {},
            onNavigateToCarrito = {},
            onNavigateToDetalle = {}
        )
    }
}
