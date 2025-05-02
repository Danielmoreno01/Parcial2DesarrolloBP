package com.example.parcial2.ui.theme
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.parcial2.Producto
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroProductos(
    productos: MutableList<Producto>,
    onBack: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagenUrl by remember { mutableStateOf("") }
    val ctx = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFDE7))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))
        Text(
            "Registrar Producto",
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFF33691E)
        )
        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(0xFF2E7D32))
        )
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = precio,
            onValueChange = {
                if (it.all { c -> c.isDigit() || c == '.' }) precio = it
            },
            label = { Text("Precio") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(0xFF2E7D32))
        )
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(0xFF2E7D32))
        )
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = imagenUrl,
            onValueChange = { imagenUrl = it },
            label = { Text("URL Imagen") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(0xFF2E7D32))
        )
        Spacer(Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    if (nombre.isNotBlank() &&
                        precio.isNotBlank() &&
                        descripcion.isNotBlank() &&
                        imagenUrl.isNotBlank()
                    ) {
                        productos.add(
                            Producto(
                                id = productos.size + 1,
                                nombre = nombre,
                                precio = precio.toDoubleOrNull() ?: 0.0,
                                descripcion = descripcion,
                                imagenUrl = imagenUrl
                            )
                        )
                        onBack()
                    } else {
                        Toast.makeText(ctx, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784))
            ) {
                Text("Guardar", color = Color.White)
            }

            Spacer(Modifier.width(16.dp))

            Button(
                onClick = onBack,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = CircleShape
            ) {
                Text("Cancelar")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRegistroProductos() {
    Parcial2Theme {
        val productos = remember { mutableStateListOf<Producto>() }
        RegistroProductos(productos = productos, onBack = {})
    }
}
