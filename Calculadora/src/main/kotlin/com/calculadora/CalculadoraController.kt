package com.calculadora

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping()
class CalculadoraController{

    @GetMapping("/somar")
    fun somar(
        @RequestParam("n1") n1: Double,
        @RequestParam("n2") n2: Double
    ): Double {
        return n1 + n2
    }

    @GetMapping("/subtrair")
    fun subtrair(
        @RequestParam("n1") n1: Double,
        @RequestParam("n2") n2: Double
    ): Double {
        return n1 - n2
    }

    @GetMapping("/multiplicar")
    fun multiplicar(
        @RequestParam("n1") n1: Double,
        @RequestParam("n2") n2: Double
    ): Double {
        return n1 * n2
    }

    @GetMapping("/dividir")
    fun dividir(
        @RequestParam("n1") n1: Double,
        @RequestParam("n2") n2: Double
    ): ResponseEntity<Any> { // <--- Mude aqui
        if (n2 == 0.0) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Erro: Divisão por zero não é permitida.")
        }

        val result = n1 / n2
        return ResponseEntity.ok(result) // Agora o retorno é consistente com a assinatura
    }

    @GetMapping
    fun teste(): ResponseEntity<String> {
        return ResponseEntity.ok("Media Service is running")
    }
}



