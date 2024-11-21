package main.hilosActs2.Tarea2_10;


public class Cola {
    private String mensaje;
    private boolean disponible = false;


    public synchronized String get() {
        while (!disponible) { // Espera mientras no haya un mensaje disponible
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        disponible = false;
        notifyAll();
        return mensaje;
    }


    public synchronized void put(String valor) {
        while (disponible) { // Espera mientras la cola esté ocupada
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mensaje = valor;
        disponible = true;
        notifyAll();
    }
}
