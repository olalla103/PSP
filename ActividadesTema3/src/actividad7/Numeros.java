package actividad7;

import java.io.Serializable;

/**
 * Clase Numeros: Representa un objeto con un número entero y sus cálculos asociados.
 * Implementa Serializable para permitir su transmisión a través de streams.
 */
public class Numeros implements Serializable {
    private int numero;       // Número introducido
    private long cuadrado;    // Cuadrado del número
    private long cubo;        // Cubo del número

    /**
     * Constructor sin parámetros. Inicializa los atributos a 0.
     */
    public Numeros() {
        this.numero = 0;
        this.cuadrado = 0;
        this.cubo = 0;
    }

    /**
     * Constructor con parámetro número.
     *
     * @param numero Número a asignar.
     */
    public Numeros(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public long getCubo() {
        return cubo;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }
}