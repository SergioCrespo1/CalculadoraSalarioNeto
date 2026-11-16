package com.sergiocrespo.calculadorasalarioneto.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = AzulBoton,
    background = FondoLimpio,
    surface = Color.White,
    onBackground = TextoOscuro,
    onSurface = TextoOscuro
)

@Composable
fun CalculadoraSalarioNetoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}
