package main.hilosActividades.Act2_1;

public class Actividad1 extends Thread {
    String mensaje;

    public Actividad1(String mensaje) {
        this.mensaje = mensaje;
    }

    public void run() {

        System.out.println("Hola Mundo " + this.getMensaje() + " " + Thread.currentThread().getId());

    }

    public String getMensaje() {
        return mensaje;
    }

}
