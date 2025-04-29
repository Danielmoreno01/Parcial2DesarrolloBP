package com.example.parcial2.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            "Catálogo de Productos",
            style = MaterialTheme.typography.headlineSmall.copy(color = Color.Red),
        )
        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(productos) { producto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { onNavigateToDetalle(producto.id) },

                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF333333))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = producto.imagenUrl,
                                builder = {
                                    placeholder(android.R.drawable.ic_menu_gallery)
                                    error(android.R.drawable.ic_delete)
                                }
                            ),
                            contentDescription = "Imagen del Producto",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(MaterialTheme.shapes.small)
                        )

                        Spacer(Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                producto.nombre,
                                style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                            )
                            Text(
                                "$${producto.precio}",
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                            )
                        }

                        IconButton(onClick = { onNavigateToDetalle(producto.id) }) {
                            Icon(Icons.Default.Info, contentDescription = "Ver detalle", tint = Color.White)
                        }

                        IconButton(onClick = {
                            productoAEliminar = producto
                            showDialog = true
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar producto", tint = Color.White)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            "Total Carrito: $${carrito.sumOf { it.precio }}",
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onNavigateToRegistro,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Agregar Producto", color = Color.White)
            }
            Button(
                onClick = onNavigateToCarrito,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Ver Carrito", color = Color.White)
            }
        }
    }

    if (showDialog && productoAEliminar != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Eliminar producto", color = Color.Red) },
            text = { Text("¿Seguro que deseas eliminar '${productoAEliminar?.nombre}'?", color = Color.White) },
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
                    Text("Cancelar", color = Color.White)
                }
            }
        )
    }
}
