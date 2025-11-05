package com.calculadora

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping()
class CalculadoraController(
    private val calculadoraService: CalculadoraService
    val n1: Double? = null
    val n2: Double? = null
){
    @PostMapping("/somar")
    fun somar(
        @RequestBody request: CalculadoraRequest
    ): Double {
        return calculadoraService.somar(n1,n2)
    }

    @PostMapping("/subtrair")
    fun subtrair(
        @RequestBody request: CalculadoraRequest
    ): Double {
        return calculadoraService.subtrair(n1,n2)
    }

    @PostMapping("/multiplicar")
    fun multiplicar(
        @RequestBody request: CalculadoraRequest
    ): Double {
        return calculadoraService.multiplicar(n1,n2)
    }

    @PostMapping("/dividir")
    fun dividir(
        @RequestBody request: CalculadoraRequest
    ): ResponseEntity<Any> {

        if (request.n2 == 0.0) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Erro: Divisão por zero não é permitida.")
        }

        val result = request.n1 / request.n2
        return ResponseEntity.ok(result) // Agora o retorno é consistente com a assinatura
    }
}



