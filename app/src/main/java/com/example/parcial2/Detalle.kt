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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color

@Composable
fun Detalle(
    producto: Producto,
    carrito: MutableList<Producto>,
    onBack: () -> Unit
) {
    val ctx = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFDE7))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(24.dp))
        Text(
            "Detalle del Producto",
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFF33691E)
        )

        Image(
            painter = rememberImagePainter(
                data = producto.imagenUrl.ifBlank { "https://via.placeholder.com/200" },
                builder = {
                    placeholder(android.R.drawable.ic_menu_gallery)
                    error(android.R.drawable.ic_delete)
                }
            ),
            contentDescription = "Imagen del producto",
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(12.dp))
                .shadow(4.dp, RoundedCornerShape(12.dp))
        )

        Text(producto.nombre, style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF2E7D32)))
        Text("$${producto.precio}", style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF388E3C)))
        Text(producto.descripcion, style = MaterialTheme.typography.bodyMedium)

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    carrito.add(producto)
                    Toast.makeText(ctx, "Agregado al carrito", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784))
            ) {
                Text("Agregar al Carrito", color = Color.White)
            }

            Spacer(Modifier.width(16.dp))

            Button(
                onClick = onBack,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = CircleShape
            ) {
                Text("Volver")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDetalle() {
    Parcial2Theme {
        val carrito = remember { mutableStateListOf<Producto>() }
        val producto = Producto(
            1, "Flor Silvestre", 12.0, "Una hermosa flor de montaña.",
            "https://via.placeholder.com/150"
        )
        Detalle(producto = producto, carrito = carrito, onBack = {})
    }
}
