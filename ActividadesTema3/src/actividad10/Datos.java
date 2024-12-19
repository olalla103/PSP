package actividad10;

import java.io.Serializable;

public class Datos implements Serializable {
    private int numero; // Número introducido por el cliente
    private int intentos; // Número de intentos realizados por el cliente
    private String mensaje; // Mensaje del servidor para el cliente
    private boolean ganador; // Indica si el cliente ganó

    // Constructor
    public Datos(int numero, int intentos) {
        this.numero = numero;
        this.intentos = intentos;
        this.mensaje = "";
        this.ganador = false;
    }

    // Getters y Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isGanador() {
        return ganador;
    }

    public void setGanador(boolean ganador) {
        this.ganador = ganador;
    }
}
