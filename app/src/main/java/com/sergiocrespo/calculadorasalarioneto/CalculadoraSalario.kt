package com.sergiocrespo.calculadorasalarioneto

import java.io.Serializable

data class ResultadoSalario(
    val salarioBrutoAnual: Double,
    val porcentajeIrpf: Double,
    val importeIrpfAnual: Double,
    val deduccionFamiliar: Double,
    val salarioNetoAnual: Double,
    val salarioNetoMensual: Double,
    val pagas: Int
) : Serializable

fun calcularSalarioNeto(
    brutoAnual: Double,
    edad: Int,
    hijos: Int,
    gradoDiscapacidad: Int,
    pagas: Int = 14
): ResultadoSalario {
    require(brutoAnual >= 0.0) {"El salario bruto no puede ser negativo"}
    require(edad >= 0) {"La edad no puede ser negativa"}
    require(hijos >= 0) {"El número de hijos no puede ser negativo"}
    require(gradoDiscapacidad in 0..100) { "El grado de discapacidad debe estar entre 0 y 100" }
    require(pagas > 0) {"El número de pagas debe ser superior a 0"}

    val irpfBase = when {

        brutoAnual <= 12450.00 -> 19.00
        brutoAnual <= 20200.00 -> 24.00
        brutoAnual <= 35200.00 -> 30.00
        brutoAnual <= 60000.00 -> 37.00
        brutoAnual <= 300000.00 -> 45.00
        else -> 47.00
    }
    var deduccionPuntos = hijos * 1.5

    if (gradoDiscapacidad >= 33) {
        deduccionPuntos += 2.0
    }
    if (edad >= 65) {
        deduccionPuntos += 1.0
    }
    val irpfFinal = (irpfBase - deduccionPuntos).coerceAtLeast(2.0)
    val importeIrpf = brutoAnual * (irpfFinal / 100.00)
    val deduccionFamiliar = brutoAnual * (deduccionPuntos / 100.0)


    val salarioNetoAnual = brutoAnual - importeIrpf
    val salarioNetoMensual = salarioNetoAnual / pagas

    return ResultadoSalario(
        salarioBrutoAnual = brutoAnual,
        porcentajeIrpf = irpfFinal,
        importeIrpfAnual = importeIrpf,
        deduccionFamiliar = deduccionFamiliar,
        salarioNetoAnual = salarioNetoAnual,
        salarioNetoMensual = salarioNetoMensual,
        pagas = pagas

    )
}