# Parcial 2doCorte Paradigmas
## Punto 1
## Punto 2
### Descripción del Funcionamiento de la Comunicación entre Agentes
En este sistema de calculadora, la comunicación entre agentes es manejada principalmente a través del agente "EntradaSalida" en el modelo. Este agente recibe la expresión matemática en una cadena de texto y luego la divide en tokens (números y operadores). A partir de estos tokens, "EntradaSalida" establece la secuencia de operaciones necesarias para resolver la expresión, respetando las reglas de precedencia.

Cada agente de operación (suma, resta, multiplicación, división, potencia) es responsable de una operación específica. Cuando "EntradaSalida" determina que un agente debe ejecutar una operación, pasa los procesos al agente correspondiente y recibe el resultado. Luego este resultado se agrega otra vez a la secuencia para completar la operacion.
### Informe
### Arquitectura del Sistema

    Modelo de Calculadora (CalculatorModel): Es el núcleo del sistema, diseñado para manejar y evaluar expresiones matemáticas. El CalculatorModel se compone del agente coordinador EntradaSalida, encargado de recibir la expresión del usuario y de gestionar la secuencia de operaciones. Utiliza un scheduler de Mesa para organizar y ejecutar los agentes de operación.
    Agentes de Operación: Cada operación matemática es gestionada por un agente especializado. Los agentes AgenteSuma, AgenteResta, AgenteMultiplicacion, AgenteDivision, y AgentePotencia se implementan como instancias autónomas de la clase AgenteOperacion. Su única responsabilidad es recibir dos valores y devolver el resultado de la operación correspondiente.

### 2. Interacción entre Agentes

    La interacción entre agentes sigue un flujo estructurado donde EntradaSalida funciona como el agente principal, coordinando el flujo de datos entre los agentes de operación. Este flujo se divide en tres fases:
        Análisis de la Expresión: EntradaSalida toma la expresión y la descompone en tokens que representan números y operadores.
        Gestión de Precedencia: Siguiendo las reglas de precedencia, EntradaSalida organiza los tokens en el orden adecuado para resolver las operaciones de mayor precedencia antes.
        Ejecución de Operaciones: EntradaSalida invoca a los agentes de operación en secuencia. Para cada operación, envía dos operandos al agente respectivo, espera el resultado y luego actualiza la lista de valores pendientes para la operación siguiente. Este proceso continúa hasta que solo queda un valor, que es el resultado final.

### 3. Mecanismos de Comunicación

    Comunicación Síncrona Directa: La comunicación entre el agente EntradaSalida y cada agente de operación es directa y sincrónica. EntradaSalida llama a un agente específico y espera el resultado antes de proceder. Este método asegura que cada operación se complete en el orden correcto y respete la precedencia de operadores.
    Almacenamiento Temporal de Resultados: Los resultados de las operaciones intermedias se almacenan temporalmente en una lista en el modelo CalculatorModel, lo cual permite gestionar el flujo de datos y reducir la complejidad de comunicación.
    Control de Precedencia: EntradaSalida se encarga de la precedencia usando un esquema de pila de operadores y valores. Este mecanismo organiza las operaciones de manera que la multiplicación, división y potencia se resuelvan antes de las sumas y restas.

### Ejemplo de Comunicación en la Evaluación de la Expresión

Tomemos como ejemplo la expresión 2 + 3 * 4 - 5 ^ 2. La interacción ocurre de la siguiente manera:

    EntradaSalida analiza la expresión y determina que ^ (potencia) y * (multiplicación) tienen precedencia sobre + y -.
    Primera interacción:
        EntradaSalida envía 5 y 2 al AgentePotencia, que devuelve 25.
        Este resultado reemplaza 5 ^ 2 en la expresión.
    Segunda interacción:
        EntradaSalida envía 3 y 4 al AgenteMultiplicacion, que devuelve 12.
        Este resultado reemplaza 3 * 4 en la expresión.
    Interacción final:
        EntradaSalida procesa el resto de la expresión (2 + 12 - 25) mediante el AgenteSuma y el AgenteResta, en orden.

Este enfoque asegura que cada agente de operación solo se activa cuando su turno y precedencia han sido asegurados, garantizando la correcta evaluación de la expresión.
### Punto 3
