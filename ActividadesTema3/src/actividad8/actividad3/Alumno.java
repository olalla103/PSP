package actividad8.actividad3;

import java.io.Serializable;

public class Alumno implements Serializable {
    private String idalumno;
    private String nombre;
    private Curso curso;
    private int nota;

    // Constructor sin parámetros
    public Alumno() {
        this.idalumno = "";
        this.nombre = "";
        this.curso = new Curso();
        this.nota = 0;
    }

    // Constructor con parámetros
    public Alumno(String idalumno, String nombre, Curso curso, int nota) {
        this.idalumno = idalumno;
        this.nombre = nombre;
        this.curso = curso;
        this.nota = nota;
    }

    // Getters y Setters
    public String getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(String idalumno) {
        this.idalumno = idalumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Alumno{id='" + idalumno + "', nombre='" + nombre + "', curso=" + curso + ", nota=" + nota + "}";
    }
}

