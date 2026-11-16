package com.sergiocrespo.calculadorasalarioneto

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sergiocrespo.calculadorasalarioneto.ui.theme.CalculadoraSalarioNetoTheme
import com.sergiocrespo.calculadorasalarioneto.ui.theme.FondoVerdeSuave
import com.sergiocrespo.calculadorasalarioneto.ui.theme.VerdeNeto
import java.util.Locale

class ResultadoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val resultado = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(
                "EXTRA_RESULTADO",
                ResultadoSalario::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("EXTRA_RESULTADO") as? ResultadoSalario
        }

        setContent {
            CalculadoraSalarioNetoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (resultado != null) {
                        ResultadoScreen(
                            resultado = resultado,
                            onVolver = { finish() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ResultadoScreen(
    resultado: ResultadoSalario,
    onVolver: () -> Unit
) {

    fun euro(valor: Double): String =
        String.format(Locale.GERMANY, "%,.2f €", valor)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Desglose Salarial",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = FondoVerdeSuave
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "NETO MENSUAL (${resultado.pagas} pagas)",
                    fontWeight = FontWeight.Bold,
                    color = VerdeNeto
                )

                Text(
                    euro(resultado.salarioNetoMensual),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = VerdeNeto
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    "Neto anual: ${euro(resultado.salarioNetoAnual)}",
                    color = VerdeNeto
                )
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                FilaDetalle(
                    "Bruto anual:",
                    euro(resultado.salarioBrutoAnual)
                )

                FilaDetalle(
                    "Retención IRPF (${String.format(Locale.GERMANY, "%.1f", resultado.porcentajeIrpf)}%):",
                    "- ${euro(resultado.importeIrpfAnual)}"
                )

                FilaDetalle(
                    "Deducción familiar:",
                    euro(resultado.deduccionFamiliar)
                )
            }
        }

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Modificar datos")
        }
    }
}

@Composable
fun FilaDetalle(titulo: String, valor: String) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            titulo,
            modifier = Modifier.weight(1f)
        )

        Text(
            valor,
            fontWeight = FontWeight.SemiBold
        )
    }
}
