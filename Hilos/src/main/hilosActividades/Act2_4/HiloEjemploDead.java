package main.hilosActividades.Act2_4;

public class HiloEjemploDead extends Thread {
    private boolean stopHilo = false;

    public void pararHilo() {
        stopHilo = true;
        System.out.println("Hilo parado");
    }

    public void run() {
        while (!stopHilo) {
            System.out.println("En el hilo");
        }
    }

    public static void main(String[] args) {
        HiloEjemploDead hilo = new HiloEjemploDead();
        hilo.start();
        for (int i = 0; i < 1000000; i++) {
            hilo.pararHilo();
        }
    }
}
