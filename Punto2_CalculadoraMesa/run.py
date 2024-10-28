from model.calculator_model import CalculatorModel

expresion = "2 + 3 * 4 - 5 ^ 2"
modelo = CalculatorModel(expresion)
resultado = modelo.evaluar_expresion()
print(f"Resultado de '{expresion}': {resultado}")

