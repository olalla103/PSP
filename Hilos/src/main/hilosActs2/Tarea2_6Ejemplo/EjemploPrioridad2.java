package main.hilosActs2.act2_6;

public class EjemploPrioridad2 extends Thread {
    private int c = 0;
    private boolean stopHilo = false;

    public EjemploPrioridad2(String nombre) {
        super(nombre);
    }

    public int getContador() {
        return c;
    }

    public void pararHilo() {
        stopHilo = true;
    }

    public void run() {
        while (!stopHilo) {
            try {
                Thread.sleep(2);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            c++;
        }
        System.out.println("Fin hilo " + this.getName());
    }
}
