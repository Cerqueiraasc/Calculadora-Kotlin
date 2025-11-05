package com.calculadora

import org.springframework.stereotype.Service

@Service
class CalculadoraService {
    fun somar (n1: Double?, n2: Double): Double {
        return n1 + n2
    }

    fun subtrair (n1: Double, n2: Double): Double {
        return n1 - n2
    }

    fun multiplicar (n1: Double, n2: Double): Double {
        return n1 * n2
    }

    fun dividir (n1: Double, n2: Double): Double {
        return n1 / n2
    }
}
