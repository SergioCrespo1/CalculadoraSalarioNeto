package com.sergiocrespo.calculadorasalarioneto

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sergiocrespo.calculadorasalarioneto.ui.theme.CalculadoraSalarioNetoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CalculadoraSalarioNetoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FormularioSalarioScreen { resultado ->
                        val intent = Intent(this, ResultadoActivity::class.java).apply {
                            putExtra("EXTRA_RESULTADO", resultado)
                        }
                        startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun FormularioSalarioScreen(onCalcularClick: (ResultadoSalario) -> Unit) {

    val context = LocalContext.current

    var bruto by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var hijos by remember { mutableStateOf("0") }
    var discapacidad by remember { mutableStateOf("0") }
    var pagas14 by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Calculadora Salario Neto",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        OutlinedTextField(
            value = bruto,
            onValueChange = { bruto = it },
            label = { Text("Salario bruto anual (€)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = hijos,
            onValueChange = { hijos = it },
            label = { Text("Hijos a cargo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = discapacidad,
            onValueChange = { discapacidad = it },
            label = { Text("Discapacidad (%)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Pagas anuales:", modifier = Modifier.weight(1f))

            FilterChip(
                selected = !pagas14,
                onClick = { pagas14 = false },
                label = { Text("12") }
            )

            Spacer(modifier = Modifier.width(8.dp))

            FilterChip(
                selected = pagas14,
                onClick = { pagas14 = true },
                label = { Text("14") }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val b = bruto.toDoubleOrNull()
                val e = edad.toIntOrNull()

                if (b == null || b <= 0 || e == null || e < 16) {
                    Toast.makeText(
                        context,
                        "Revisa el sueldo bruto y la edad",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    onCalcularClick(
                        calcularSalarioNeto(
                            brutoAnual = b,
                            edad = e,
                            hijos = hijos.toIntOrNull() ?: 0,
                            gradoDiscapacidad = discapacidad.toIntOrNull() ?: 0,
                            pagas = if (pagas14) 14 else 12
                        )
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular", fontSize = 16.sp)
        }
    }
}
