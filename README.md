# Parcial 2doCorte Paradigmas
## Punto 1
## Punto 2
###Descripción del Funcionamiento de la Comunicación entre Agentes
En este sistema de calculadora, la comunicación entre agentes es manejada principalmente a través del agente "EntradaSalida" en el modelo. Este agente recibe la expresión matemática en una cadena de texto y luego la divide en tokens (números y operadores). A partir de estos tokens, "EntradaSalida" establece la secuencia de operaciones necesarias para resolver la expresión, respetando las reglas de precedencia.

Cada agente de operación (suma, resta, multiplicación, división, potencia) es responsable de una operación específica. Cuando EntradaSalida determina que un agente debe ejecutar una operación, pasa los operandos al agente correspondiente y recibe el resultado. Luego, este resultado se reintroduce en la secuencia de tokens para completar el cálculo global. Este tipo de comunicación es directa y sincrónica: el agente EntradaSalida envía los datos al agente de operación y espera la respuesta antes de continuar con la próxima operación.
### Punto 3
