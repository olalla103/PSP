package main.hilosActs2.Tarea2_9;

public class Productor extends Thread {
    private Cola cola;
    private int n;

    public Productor(Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            cola.put(i); // Pone el número en la cola
            System.out.println(i + " -> Productor: " + n + ", produce: " + i);

        }
    }
}