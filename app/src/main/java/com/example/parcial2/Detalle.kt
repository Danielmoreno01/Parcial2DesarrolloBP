package com.example.parcial2.ui.theme

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.parcial2.Producto

@Composable
fun Detalle(
    producto: Producto,
    carrito: MutableList<Producto>,
    onBack: () -> Unit
) {
    val ctx = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Detalle del Producto", style = MaterialTheme.typography.headlineSmall)

        Image(
            painter = rememberImagePainter(
                data = producto.imagenUrl,
                builder = {
                    placeholder(android.R.drawable.ic_menu_gallery)
                    error(android.R.drawable.ic_delete)
                }
            ),
            contentDescription = "Imagen del producto",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
        Text("$${producto.precio}", style = MaterialTheme.typography.bodyLarge)
        Text(producto.descripcion, style = MaterialTheme.typography.bodyMedium)

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    carrito.add(producto)
                    Toast.makeText(ctx, "Agregado al carrito", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Agregar al Carrito", color = MaterialTheme.colorScheme.onPrimary)
            }
            Button(
                onClick = onBack,
                modifier = Modifier.weight(1f)
            ) {
                Text("Volver")
            }
        }
    }
}
