package Ejercicios;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.Scanner;

interface Operacion {
    double calcular(double a, double b);
}

class AgenteSuma implements Operacion {
    public double calcular(double a, double b) {
        return a + b;
    }
}

class AgenteResta implements Operacion {
    public double calcular(double a, double b) {
        return a - b;
    }
}

class AgenteMultiplicacion implements Operacion {
    public double calcular(double a, double b) {
        return a * b;
    }
}

class AgenteDivision implements Operacion {
    public double calcular(double a, double b) {
        if (b == 0) throw new ArithmeticException("División por cero");
        return a / b;
    }
}

class AgentePotencia implements Operacion {
    public double calcular(double a, double b) {
        return Math.pow(a, b);
    }
}

class AgenteEntradaSalida {
    private final ExecutorService executor = Executors.newFixedThreadPool(5);
    
    public double procesarExpresion(String expresion) throws Exception {
        String[] tokens = expresion.split(" ");
        double resultado = 0;
        double num1 = Double.parseDouble(tokens[0]);
        
        for (int i = 1; i < tokens.length; i += 2) {
            String operador = tokens[i];
            double num2 = Double.parseDouble(tokens[i + 1]);
            resultado = resolverOperacion(num1, num2, operador);
            num1 = resultado; // Actualiza num1 para la siguiente operación
        }
        return resultado;
    }

    private double resolverOperacion(double a, double b, String operador) throws Exception {
        Future<Double> future = null;

        switch (operador) {
            case "+":
                future = executor.submit(() -> new AgenteSuma().calcular(a, b));
                break;
            case "-":
                future = executor.submit(() -> new AgenteResta().calcular(a, b));
                break;
            case "*":
                future = executor.submit(() -> new AgenteMultiplicacion().calcular(a, b));
                break;
            case "/":
                future = executor.submit(() -> new AgenteDivision().calcular(a, b));
                break;
            case "^":
                future = executor.submit(() -> new AgentePotencia().calcular(a, b));
                break;
            default:
                throw new Exception("Operador desconocido: " + operador);
        }
        return future.get();
    }

    public void cerrar() {
        executor.shutdown();
    }
}

public class CalculadoraAgentes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AgenteEntradaSalida agenteEntradaSalida = new AgenteEntradaSalida();

        System.out.println("Ingrese una expresión (ejemplo: 2 + 3 * 4 - 5): ");
        String expresion = scanner.nextLine();

        try {
            double resultado = agenteEntradaSalida.procesarExpresion(expresion);
            System.out.println("El resultado es: " + resultado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            agenteEntradaSalida.cerrar();
            scanner.close();
        }
    }
}
