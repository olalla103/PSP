package main.hilosActs2.act2_6;

public class HiloPrioridad1 extends Thread {
    private int c = 0;
    private boolean stopHilo = false;

    public HiloPrioridad1(String nombre) {
        super(nombre);
    }

    public int getContador() {
        return c;
    }

    public void pararHilo(int c) {
        stopHilo = true;
    }

    public void run() {
        while (!stopHilo) {
            try {
                Thread.sleep(2);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
