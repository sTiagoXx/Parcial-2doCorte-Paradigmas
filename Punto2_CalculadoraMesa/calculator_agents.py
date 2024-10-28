from mesa import Agent

class AgenteOperacion(Agent):
    def __init__(self, nombre):
        self.nombre = nombre

    def operar(self, a, b):
        pass

class AgenteSuma(AgenteOperacion):
    def operar(self, a, b):
        return a + b

class AgenteResta(AgenteOperacion):
    def operar(self, a, b):
        return a - b

class AgenteMultiplicacion(AgenteOperacion):
    def operar(self, a, b):
        return a * b

class AgenteDivision(AgenteOperacion):
    def operar(self, a, b):
        if b == 0:
            raise ValueError("No se puede dividir por cero.")
        return a / b

class AgentePotencia(AgenteOperacion):
    def operar(self, a, b):
        return a ** b

