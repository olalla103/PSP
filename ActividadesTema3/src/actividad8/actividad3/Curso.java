package actividad8.actividad3;

import java.io.Serializable;

public class Curso implements Serializable {
    private String id;
    private String descripcion;

    // Constructor sin parámetros
    public Curso() {
        this.id = "";
        this.descripcion = "";
    }

    // Constructor con parámetros
    public Curso(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Curso{id='" + id + "', descripcion='" + descripcion + "'}";
    }
}
