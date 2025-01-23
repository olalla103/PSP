package main.hilosActs2.Tarea2_7;

public class HiloRunnable implements Runnable {
    String nombre;
    static Integer contador = 0;

    public HiloRunnable(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            contador++;
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(getNombre() + " el contador vale: " + getContador());
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getContador() {
        return contador;
    }
}
