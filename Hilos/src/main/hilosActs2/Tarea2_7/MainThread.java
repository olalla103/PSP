package main.hilosActs2.Tarea2_7;

public class MainThread {
    public static void main(String[] args) {
        HiloThread hilo1 = new HiloThread("Hilo1");
        HiloThread hilo2 = new HiloThread("Hilo2");
        HiloThread hilo3 = new HiloThread("Hilo3");
        HiloThread hilo4 = new HiloThread("Hilo4");
        HiloThread hilo5 = new HiloThread("Hilo5");


        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        System.out.println("El contador final puede no ser correcto sin join: " + HiloThread.contador);
    }
}
