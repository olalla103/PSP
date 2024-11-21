package main.hilosActs2.Tarea2_10;

public class Consumidor extends Thread {
    private Cola cola;
    private int n;

    public Consumidor(Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            String mensaje = cola.get();
            System.out.println("Consumidor: " + n + " consume: " + mensaje);

            // Pausa para hacer más lento al consumidor
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Fin Consumidor: " + n);
    }
}

