package main.hilosActs2.Tarea2_10;

public class Productor extends Thread {
    private Cola cola;
    private int n;

    public Productor(Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        String[] mensajes = {"PING", "PONG"};
        for (int i = 0; i < 10; i++) {
            String mensaje = mensajes[i % 2];
            cola.put(mensaje);
            System.out.println("Productor: " + n + " produce: " + mensaje);
        }
        System.out.println("Fin Productor: " + n);
    }
}
