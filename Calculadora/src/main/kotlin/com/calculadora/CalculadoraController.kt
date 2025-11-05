package com.calculadora

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class CalculadoraController(
    private val calculadoraService: CalculadoraService
){
    @PostMapping("/somar")
    fun somar(
        @RequestBody request: CalculadoraRequest
    ): Double {
        return calculadoraService.somar(
            n1 = request.n1,
            n2 = request.n2,
        )
    }

    @PostMapping("/subtrair")
    fun subtrair(
        @RequestBody request: CalculadoraRequest
    ): Double {
        return calculadoraService.subtrair(
            n1 = request.n1,
            n2 = request.n2,
        )

    }

    @PostMapping("/multiplicar")
    fun multiplicar(
        @RequestBody request: CalculadoraRequest
    ): Double {
        return calculadoraService.multiplicar(
            n1 = request.n1,
            n2 = request.n2,
        )
    }

    @PostMapping("/dividir")
    fun dividir(
        @RequestBody request: CalculadoraRequest
    ): Double {
        return calculadoraService.dividir(
            n1 = request.n1,
            n2 = request.n2,
        )
    }
}



