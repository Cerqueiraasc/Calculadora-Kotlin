package com.calculadora

import org.springframework.stereotype.Service

enum class OperacaoTipo {
    SOMA,
    SUBTRACAO,
    MULTIPLICACAO,
    DIVISAO
}

data class Operacao(
    val tipo: OperacaoTipo,
    val numeros: List<Double>
)

@Service
class CalculadoraService {

    fun somar(vararg numeros: Double): Double {
        return numeros.sum()
    }

    fun subtrair(vararg numeros: Double): Double {
        if (numeros.isEmpty()) return 0.0
        var resultado = numeros.first()
        for (i in 1 until numeros.size) {
            resultado -= numeros[i]
        }
        return resultado
    }

    fun multiplicar(vararg numeros: Double): Double {
        if (numeros.isEmpty()) return 0.0
        var resultado = numeros.first()
        for (i in 1 until numeros.size) {
            resultado *= numeros[i]
        }
        return resultado
    }

    fun dividir(vararg numeros: Double): Double {
        if (numeros.isEmpty()) return 0.0
        var resultado = numeros.first()
        for (i in 1 until numeros.size) {
            val divisor = numeros[i]
            if (divisor == 0.0) {

                throw IllegalArgumentException("Divisão por zero não permitida.")
            }
            resultado /= divisor
        }
        return resultado
    }

    fun calcularEmCadeia(operacoes: List<Operacao>): Double {
        if (operacoes.isEmpty()) return 0.0

        val primeiraOp = operacoes.first()
        var acumulador = executarOperacao(primeiraOp.tipo, primeiraOp.numeros)

        for (op in operacoes.drop(1)) {

            val numerosParaOperar = listOf(acumulador) + op.numeros


            acumulador = executarOperacao(op.tipo, numerosParaOperar)
        }

        return acumulador
    }

    private fun executarOperacao(tipo: OperacaoTipo, numeros: List<Double>): Double {

        val numerosVararg = numeros.toDoubleArray()

        return when (tipo) {
            OperacaoTipo.SOMA -> somar(*numerosVararg)
            OperacaoTipo.SUBTRACAO -> subtrair(*numerosVararg)
            OperacaoTipo.MULTIPLICACAO -> multiplicar(*numerosVararg)
            OperacaoTipo.DIVISAO -> dividir(*numerosVararg)
        }
    }
}