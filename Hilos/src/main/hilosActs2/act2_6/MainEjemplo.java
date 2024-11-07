package main.hilosActs2.act2_6;

public class MainEjemplo {
    public static void main(String[] args) {
        EjemploPrioridad2 h1 = new EjemploPrioridad2("Hilo1");
        EjemploPrioridad2 h2 = new EjemploPrioridad2("Hilo2");
        EjemploPrioridad2 h3 = new EjemploPrioridad2("Hilo3");

        h1.setPriority(Thread.NORM_PRIORITY);
        h2.setPriority(Thread.MAX_PRIORITY);
        h3.setPriority(Thread.MIN_PRIORITY);

        h1.start();
        h2.start();
        h3.start();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

        h1.pararHilo();
        h2.pararHilo();
        h3.pararHilo();

        System.out.println("h2 (Prioridad Maxima): " + h2.getContador());
        System.out.println("h1 (Prioridad Normal): " + h1.getContador());
        System.out.println("h3 (Prioridad Minima): " + h3.getContador());
    }

}




