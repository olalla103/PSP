package main.hilosActividades.Act2_1;

public class Hilos extends Thread {
    String palabra;

    public Hilos(String palabra) {
        this.palabra = palabra;
    }

    public void run() {
        try {
            while (palabra.length() > 0) {
                System.out.println(this.getPalabra());
                Thread.sleep(1000);

            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
}
