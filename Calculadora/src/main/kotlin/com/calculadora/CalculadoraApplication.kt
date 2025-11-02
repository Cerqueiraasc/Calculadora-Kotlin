package com.calculadora

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.calculadora"])
class CalculadoraApplication

fun main(args: Array<String>) {
    runApplication<CalculadoraApplication>(*args)
}
