import re 
from mesa import Model
from mesa.time import BaseScheduler
from agents.calculator_agents import AgenteSuma, AgenteResta, AgenteMultiplicacion, AgenteDivision, AgentePotencia

class CalculatorModel(Model):
    def __init__(self, expresion):
        super().__init__()
        self.expresion = expresion
        self.schedule = BaseScheduler(self)


        self.agentes = {
            '+': AgenteSuma("suma"),
            '-': AgenteResta("resta"),
            '*': AgenteMultiplicacion("multiplicacion"),
            '/': AgenteDivision("division"),
            '^': AgentePotencia("potencia")
        }

    def evaluar_expresion(self):
        tokens = self.analizar_expresion(self.expresion)
        return self.evaluar_tokens(tokens)

    def analizar_expresion(self, expresion):
        return re.findall(r'\d+\.?\d*|\+|\-|\*|\/|\^', expresion)

    def evaluar_tokens(self, tokens):
        valores = []
        operadores = []

        i = 0
        while i < len(tokens):
            token = tokens[i]
            if re.match(r'\d+\.?\d*', token):
                valores.append(float(token))
            elif token in self.agentes:
                while (operadores and operadores[-1] in {'*', '/', '^'} and 
                       self.precedencia(operadores[-1]) >= self.precedencia(token)):
                    self.procesar_operacion(valores, operadores.pop())
                operadores.append(token)
            i += 1

        while operadores:
            self.procesar_operacion(valores, operadores.pop())
        return valores[0]

    def precedencia(self, operador):
        if operador in {'+', '-'}:
            return 1
        elif operador in {'*', '/'}:
            return 2
        elif operador == '^':
            return 3
        return 0

    def procesar_operacion(self, valores, operador):
        b = valores.pop()
        a = valores.pop()
        resultado = self.agentes[operador].operar(a, b)
        valores.append(resultado)

