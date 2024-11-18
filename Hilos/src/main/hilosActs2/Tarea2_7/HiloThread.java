package main.hilosActs2.Tarea2_7;

public class HiloThread extends Thread {
    static Integer contador = 0; // Variable compartida entre los hilos.
    String nombre;

    public HiloThread(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            synchronized (HiloThread.class) { // Sincronizamos el acceso a "contador".
                contador++;
            }
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

    public Integer getContador() {
        return contador;
    }
}

