package main.hilosActs2.Tarea2_7;

import static java.lang.Thread.sleep;

public class Hilo implements Runnable {
    String nombre;
    static Integer contador = 0;

    public Hilo(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            contador++;
        }

        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(getNombre() + " el contador vale: " + getContador());

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getContador() {
        return contador;
    }

    public static void setContador(Integer contador) {
        Hilo.contador = contador;
    }
}
