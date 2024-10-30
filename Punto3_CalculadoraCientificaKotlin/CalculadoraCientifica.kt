import kotlin.math.*

class CalculadoraCientifica {
    private var memoria: Double = 0.0

    // Operaciones básicas
    fun sumar(a: Double, b: Double): Double = a + b
    fun restar(a: Double, b: Double): Double = a - b
    fun multiplicar(a: Double, b: Double): Double = a * b
    fun dividir(a: Double, b: Double): Double {
        if (b == 0.0) throw ArithmeticException("División por cero no permitida")
        return a / b
    }

    // Funciones trigonométricas (en grados)
    fun seno(angulo: Double): Double = sin(Math.toRadians(angulo))
    fun coseno(angulo: Double): Double = cos(Math.toRadians(angulo))
    fun tangente(angulo: Double): Double = tan(Math.toRadians(angulo))

    // Potencias y raíces
    fun potencia(base: Double, exponente: Double): Double = base.pow(exponente)
    fun raiz(n: Double): Double {
        if (n < 0) throw ArithmeticException("Raíz de número negativo no permitida")
        return sqrt(n)
    }

    // Logaritmos
    fun logaritmoBase10(n: Double): Double {
        if (n <= 0) throw ArithmeticException("Logaritmo indefinido para números no positivos")
        return log10(n)
    }
    fun logaritmoNatural(n: Double): Double {
        if (n <= 0) throw ArithmeticException("Logaritmo indefinido para números no positivos")
        return ln(n)
    }

    // Función exponencial
    fun exponencial(n: Double): Double = exp(n)

    // Conversión de grados a radianes y viceversa
    fun gradosARadianes(grados: Double): Double = Math.toRadians(grados)
    fun radianesAGrados(radianes: Double): Double = Math.toDegrees(radianes)

    // Funciones de memoria
    fun guardarEnMemoria(valor: Double) {
        memoria = valor
    }

    fun sumarMemoria(valor: Double) {
        memoria += valor
    }

    fun restarMemoria(valor: Double) {
        memoria -= valor
    }

    fun recuperarMemoria(): Double = memoria

    fun limpiarMemoria() {
        memoria = 0.0
    }

    // Evaluación de expresiones matemáticas simples
    fun evaluarExpresion(expresion: String): Double {
        val tokens = expresion.split(" ")
        val operadores = mutableListOf<String>()
        val valores = mutableListOf<Double>()

        for (token in tokens) {
            when {
                token.toDoubleOrNull() != null -> valores.add(token.toDouble())
                token == "+" || token == "-" || token == "*" || token == "/" -> operadores.add(token)
                else -> throw IllegalArgumentException("Operador no válido: $token")
            }
        }

        // Ejecutar las operaciones de acuerdo con la precedencia
        while (operadores.isNotEmpty()) {
            val operador = operadores.removeAt(0)
            val valor1 = valores.removeAt(0)
            val valor2 = valores.removeAt(0)

            val resultado = when (operador) {
                "+" -> sumar(valor1, valor2)
                "-" -> restar(valor1, valor2)
                "*" -> multiplicar(valor1, valor2)
                "/" -> dividir(valor1, valor2)
                else -> throw IllegalArgumentException("Operador no soportado")
            }
            valores.add(0, resultado)
        }

        return valores[0]
    }
}

fun main() {
    val calculadora = CalculadoraCientifica()

    // Ejemplos de todas las operaciones
    println("Ejemplos de operaciones:")
    println("Suma: 12 + 8 = ${calculadora.sumar(12.0, 8.0)}")
    println("Resta: 10 - 4 = ${calculadora.restar(10.0, 4.0)}")
    println("Multiplicación: 6 * 7 = ${calculadora.multiplicar(6.0, 7.0)}")
    println("División: 20 / 4 = ${calculadora.dividir(20.0, 4.0)}")

    println("Seno de 30 grados = ${calculadora.seno(30.0)}")
    println("Coseno de 45 grados = ${calculadora.coseno(45.0)}")
    println("Tangente de 60 grados = ${calculadora.tangente(60.0)}")

    println("Potencia: 2^5 = ${calculadora.potencia(2.0, 5.0)}")
    println("Raíz cuadrada de 16 = ${calculadora.raiz(16.0)}")

    println("Logaritmo base 10 de 100 = ${calculadora.logaritmoBase10(100.0)}")
    println("Logaritmo natural de e = ${calculadora.logaritmoNatural(Math.E)}")

    println("Exponencial de 2 = ${calculadora.exponencial(2.0)}")

    println("Convertir 180 grados a radianes = ${calculadora.gradosARadianes(180.0)}")
    println("Convertir PI radianes a grados = ${calculadora.radianesAGrados(Math.PI)}")

    calculadora.guardarEnMemoria(50.0)
    println("Memoria guardada: 50.0")
    calculadora.sumarMemoria(25.0)
    println("Memoria después de sumar 25: ${calculadora.recuperarMemoria()}")
    calculadora.restarMemoria(10.0)
    println("Memoria después de restar 10: ${calculadora.recuperarMemoria()}")
    calculadora.limpiarMemoria()
    println("Memoria después de limpiar: ${calculadora.recuperarMemoria()}")

    // Ejemplo de evaluación de expresión
    val expresion = "2 + 3 * 4 - 5"
    println("Resultado de la expresión larga '$expresion': ${calculadora.evaluarExpresion(expresion)}")
}

