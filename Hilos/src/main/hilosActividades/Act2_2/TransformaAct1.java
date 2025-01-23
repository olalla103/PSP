package main.hilosActividades.Act2_2;

import java.applet.Applet;
import java.awt.*;

public class TransformaAct1 extends Thread {
    String palabra;

    public TransformaAct1(String palabra) throws HeadlessException {
        this.palabra = palabra;
    }

    @Override
    public void run() {
        try {
            int id = (int) Thread.currentThread().getId();
            // Espera un tiempo basado en el ID del hilo actual
            Thread.sleep(id * 100);
            // Imprime el ID del hilo actual
            System.out.println("Hola Mundo " + this.getPalabra() + " " + id);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPalabra() {
        return palabra;
    }
}
