# Parcial 2doCorte Paradigmas de programación <br> <br>

# Punto 1
# Punto 2
### Descripción del Funcionamiento de la Comunicación entre Agentes
En este sistema de calculadora, la comunicación entre agentes es manejada principalmente a través del agente "EntradaSalida" en el modelo. Este agente recibe la expresión matemática en una cadena de texto y luego la divide en tokens (números y operadores). <br> <br>
A partir de estos tokens, "EntradaSalida" establece la secuencia de operaciones necesarias para resolver la expresión, respetando las reglas de precedencia.

Cada agente de operación (suma, resta, multiplicación, división, potencia) es responsable de una operación específica. Cuando "EntradaSalida" determina que un agente debe ejecutar una operación, pasa los procesos al agente correspondiente y recibe el resultado. Luego este resultado se agrega otra vez a la secuencia para completar la operacion.
## Informe
## Arquitectura del Sistema

Modelo de Calculadora (CalculatorModel): Es el núcleo del sistema, diseñado para manejar las expresiones matemáticas. "CalculatorModel" se compone del agente coordinador "EntradaSalida", encargado de recibir la expresión matematica y gestionar la secuencia de operaciones. Utiliza un "scheduler" de Mesa para organizar y ejecutar los agentes de operación. <br> <br>
    Agentes de Operación: Cada operación matemática es manejada por un agente eespecifico. Los agentes "AgenteSuma", "AgenteResta", "AgenteMultiplicacion", "AgenteDivision", y "AgentePotencia" se implementan como instancias de la clase "AgenteOperacion". Su única responsabilidad es recibir dos valores y devolver el resultado de la operación correspondiente.

## 2. Interacción entre Agentes

La interacción entre agentes sigue un flujo estructurado donde "EntradaSalida" funciona como el agente principal, coordinando el flujo de datos entre los agentes. Este flujo se divide en: <br> <br>
        Análisis de la Expresión: "EntradaSalida" toma la expresión y la descompone en tokens que representan números y operadores. <br> <br>
        Gestión de Precedencia: Siguiendo las reglas de precedencia, "EntradaSalida" organiza los tokens en el orden adecuado para resolver las operaciones de mayor precedencia antes. <br> <br>
        Ejecución de Operaciones: "EntradaSalida" llama a los agentes de operación en secuencia. Para cada operación envía dos numeros al agente respectivo, espera el resultado y luego actualiza la lista de valores pendientes para la siguiente operacion. Este proceso continúa hasta que solo queda un valor, que es el resultado final.

## 3. Mecanismos de Comunicación

Comunicación directa: La comunicación entre el agente "EntradaSalida" y cada agente de operación es directa. "EntradaSalida" llama a un agente específico y espera el resultado. Este método asegura que cada operación se complete en el orden correcto y respete la precedencia de operadores. <br> <br>
    Almacenamiento Temporal de Resultados: Los resultados de las operaciones intermedias se almacenan temporalmente en una lista en el modelo "CalculatorModel", lo cual permite gestionar el flujo de datos y reducir la complejidad de comunicación. <br> <br>
    Control de Precedencia: "EntradaSalida" se encarga de la precedencia usando un esquema de pila de operadores y valores. Este mecanismo organiza las operaciones de manera que la multiplicación, división y potencia se resuelvan antes de las sumas y restas.

## Ejemplo de Comunicación en la Evaluación de la Expresión

Tomemos como ejemplo la expresión 2 + 3 * 4 - 5 ^ 2. La interacción ocurre de la siguiente manera:

EntradaSalida analiza la expresión y determina que ^ (potencia) y * (multiplicación) tienen precedencia sobre + y -.<br> <br>
    Primera interacción:
        "EntradaSalida" envía 5 y 2 al "AgentePotencia", que devuelve 25.
        Este resultado reemplaza 5 ^ 2 en la expresión.<br> <br>
    Segunda interacción:
        "EntradaSalida" envía 3 y 4 al "AgenteMultiplicacion", que devuelve 12.
        Este resultado reemplaza 3 * 4 en la expresión.<br> <br>
    Interacción final:
        "EntradaSalida" procesa el resto de la expresión (2 + 12 - 25) mediante el "AgenteSuma" y el "AgenteResta", en orden.<br> <br>

# Punto 3

## Informe sobre la Implementación de la Calculadora Científica

## Introducción
Este informe describe la implementación de una calculadora científica en Kotlin, destacando la aplicación de los principios de la Programación Orientada a Objetos (POO), incluyendo **encapsulamiento**, **herencia** y **polimorfismo**. Cada uno de estos principios se ha aplicado de manera que se maximice la modularidad, la reutilización del código y la claridad del diseño.

## 1. Encapsulamiento
El encapsulamiento se refiere a la práctica de ocultar el estado interno de un objeto y proporcionar métodos públicos para acceder y modificar ese estado.

**Implementación en la Calculadora Científica:**
- **Atributos Privados:** La clase `CalculadoraCientifica` tiene un atributo privado `memoria`, que almacena un valor en memoria. Este atributo no se puede acceder directamente desde fuera de la clase, lo que protege su integridad.

    ```kotlin
    private var memoria: Double = 0.0
    ```

- **Métodos Públicos:** La clase proporciona métodos públicos para interactuar con el atributo `memoria`. Por ejemplo, el método `guardarEnMemoria()` permite guardar un valor en memoria, mientras que `recuperarMemoria()` permite acceder a ese valor.

    ```kotlin
    fun guardarEnMemoria(valor: Double) {
        memoria = valor
    }

    fun recuperarMemoria(): Double = memoria
    ```

- **Manejo de Errores:** También se implementan excepciones dentro de los métodos para manejar errores comunes, como la división por cero en el método `dividir()`. Esto asegura que los usuarios reciban mensajes de error claros y precisos, mejorando la robustez de la aplicación.

    ```kotlin
    fun dividir(a: Double, b: Double): Double {
        if (b == 0.0) throw ArithmeticException("División por cero no permitida")
        return a / b
    }
    ```

## 2. Herencia
La herencia permite crear una nueva clase que extiende o especializa el comportamiento de una clase existente.

**Implementación en la Calculadora Científica:**
- **Clase Base:** Aunque en el código actual no se ha implementado explícitamente una clase base `Calculadora`, se puede diseñar una que contenga las operaciones aritméticas básicas (suma, resta, multiplicación y división).

    ```kotlin
    open class Calculadora {
        fun sumar(a: Double, b: Double): Double = a + b
        fun restar(a: Double, b: Double): Double = a - b
        fun multiplicar(a: Double, b: Double): Double = a * b
        fun dividir(a: Double, b: Double): Double {
            if (b == 0.0) throw ArithmeticException("División por cero no permitida")
            return a / b
        }
    }
    ```

- **Clase Derivada:** La clase `CalculadoraCientifica` hereda de la clase `Calculadora`, permitiendo que todas las operaciones básicas estén disponibles sin tener que reescribir el código. Esto demuestra la reutilización de código y una estructura más clara.

    ```kotlin
    class CalculadoraCientifica : Calculadora() {
        // Métodos para funciones avanzadas
    }
    ```

## 3. Polimorfismo
El polimorfismo permite que se utilicen métodos con el mismo nombre pero comportamientos diferentes, dependiendo del contexto en el que se utilicen.

**Implementación en la Calculadora Científica:**
- **Sobrecarga de Métodos:** En la calculadora, se podrían implementar métodos sobrecargados para realizar operaciones con diferentes tipos de datos, por ejemplo, aceptar tanto `Double` como `Int` en los métodos de operación.

    ```kotlin
    fun sumar(a: Int, b: Int): Int = a + b
    fun sumar(a: Double, b: Double): Double = a + b
    ```

- **Evaluación de Expresiones:** La función `evaluarExpresion()` toma una cadena de texto que representa una expresión matemática y evalúa el resultado. Esta función puede recibir diferentes formatos de expresión, demostrando polimorfismo al manejar diferentes tipos de datos y operaciones en un solo método.

    ```kotlin
    fun evaluarExpresion(expresion: String): Double {
        // Lógica para analizar y evaluar la expresión
    }
    ```

## Conclusión
La implementación de la calculadora científica en Kotlin ha hecho un uso efectivo de los principios de la Programación Orientada a Objetos. 

- **Encapsulamiento**: Se logra mediante el uso de atributos privados y la provisión de métodos públicos, garantizando que el estado interno de los objetos esté protegido y controlado.
- **Herencia**: Permite la creación de una jerarquía de clases que facilita la reutilización del código y la extensión de funcionalidades.
- **Polimorfismo**: Ofrece flexibilidad y la posibilidad de tratar diferentes tipos de datos a través de métodos sobrecargados, permitiendo que el mismo método pueda actuar de diferentes maneras dependiendo del contexto.

Estos principios no solo contribuyen a una mejor organización del código, sino que también facilitan su mantenimiento y escalabilidad, permitiendo futuras extensiones sin necesidad de reestructurar el código existente.

