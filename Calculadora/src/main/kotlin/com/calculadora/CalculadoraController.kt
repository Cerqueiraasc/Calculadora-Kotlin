package com.calculadora

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping
class CalculadoraController(
    private val calculadoraService: CalculadoraService
) {

    @PostMapping("/somar")
    fun somar(@RequestBody request: CalculadoraRequest): Double {
        return calculadoraService.somar(*request.numeros.toDoubleArray())
    }

    @PostMapping("/subtrair")
    fun subtrair(@RequestBody request: CalculadoraRequest): Double {
        return calculadoraService.subtrair(*request.numeros.toDoubleArray())
    }

    @PostMapping("/multiplicar")
    fun multiplicar(@RequestBody request: CalculadoraRequest): Double {
        return calculadoraService.multiplicar(*request.numeros.toDoubleArray())
    }

    @PostMapping("/dividir")
    fun dividir(@RequestBody request: CalculadoraRequest): Double {
        return calculadoraService.dividir(*request.numeros.toDoubleArray())
    }

    @PostMapping("/calcular")
    fun calcular(@RequestBody request: Map<String, List<Operacao>>): Double {

        val operacoes = request["operacoes"] ?: emptyList()
        return calculadoraService.calcularEmCadeia(operacoes)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(ex: IllegalArgumentException): ResponseEntity<Map<String, String>> {
        val erro = mapOf(
            "status" to "400",
            "mensagem" to (ex.message ?: "Requisição inválida")
        )
        return ResponseEntity(erro, HttpStatus.BAD_REQUEST)
    }
}