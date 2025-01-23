package main.hilosActividades.Act2_1;

// Empieza siendo ordenado,
// más tarde se desordena dado a que los hilos no tienen prioridades añadidas.
public class Main {
    public static void main(String[] args) {
      /*  Hilos hilo1 = new Hilos("TIC");
        Hilos hilo2 = new Hilos("TAC");

        hilo1.start();
        hilo2.start();*/

        // ACTIVIDAD 1
        Actividad1 h1 = new Actividad1("Hilo 1");
        Actividad1 h2 = new Actividad1("Hilo 2");
        Actividad1 h3 = new Actividad1("Hilo 3");
        Actividad1 h4 = new Actividad1("Hilo 4");
        Actividad1 h5 = new Actividad1("Hilo 5");

        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();

    }
}
