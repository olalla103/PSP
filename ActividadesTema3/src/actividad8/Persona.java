package actividad8;

import java.io.Serializable;

// La clase Persona implementa Serializable para permitir la transmisión del objeto.
public class Persona implements Serializable {
    private String nombre; // Atributo nombre
    private int edad;      // Atributo edad

    // Constructor sin parámetros
    public Persona() {
        this.nombre = "";
        this.edad = 0;
    }

    // Constructor con parámetros
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    // Métodos getter y setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    // Representación en texto del objeto
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Edad: " + edad;
    }
}
