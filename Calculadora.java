
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Calculadora {
    public double result;
    public double valor1;
    public double valor2;

    public Calculadora() {
    }

    public Calculadora(double result) {
        this.result = result;
    }

    public static double restar(double numero, double numero2) {
        double resultado = numero - numero2;

        return resultado;
    }

    public static double sumar(double numero, double numero2) {
        double resultado = numero + numero2;
        return resultado;
    }

    public static double multi(double numero, double numero2) {
        double resultado = numero * numero2;
        return resultado;
    }

    public static double dividir(double numero, double numero2) {
        double resultado;
        if (numero2 == 0) {
            throw new ArithmeticException("no se puede divivir entre 0");
        } else {
            resultado = numero / numero2;
        }
        return resultado;
    }

    public static double potencia(double uno, double dos) {
        return pow(uno, dos);
    }

    public static double raiz(double uno) {
        return sqrt(uno);
    }

    public static double porcentaje(double num) {
        return (num / 100);
    }
}
