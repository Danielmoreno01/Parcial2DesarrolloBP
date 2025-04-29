package com.example.parcial2.ui.theme

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.parcial2.Producto

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

    Column(Modifier.padding(16.dp)) {
        Text("Registrar Producto", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = precio,
            onValueChange = {
                if (it.all { ch -> ch.isDigit() || ch == '.' }) precio = it
            },
            label = { Text("Precio") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = imagenUrl,
            onValueChange = { imagenUrl = it },
            label = { Text("URL Imagen") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (
                        nombre.isNotBlank() &&
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
                        Toast.makeText(
                            ctx,
                            "Por favor complete todos los campos correctamente",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Guardar", color = MaterialTheme.colorScheme.onPrimary)
            }

            Button(onClick = onBack) {
                Text("Cancelar")
            }
        }
    }
}
