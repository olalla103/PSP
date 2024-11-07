package main.hilosActs2.act2_6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ejercicio6 extends JFrame {
    // Variables de posición y velocidad de los hilos
    int x, y, j = 2;
    int x2, y2, j2 = 2;
    int x3, y3, j3 = 2;
    private Font fuente;
    String gana = " ";

    // Definir los hilos
    Letras hilo = null;
    Letras hilo2 = null;
    Letras hilo3 = null;

    // Constructor de la clase Ejercicio6
    public Ejercicio6() {
        // Configurar el JFrame
        setTitle("Carrera de Letras");
        setSize(600, 400);  // Tamaño del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centrar la ventana
        setVisible(true);
        init();
        start();
    }

    // Inicialización de las posiciones y fuente
    public void init() {
        x = 1;
        y = 50;

        x2 = 1;
        y2 = 100;

        x3 = 1;
        y3 = 150;

        fuente = new Font("Verdana", Font.BOLD, 26);
    }

    // Iniciar los hilos
    public void start() {
        if (hilo == null) {
            hilo = new Letras(x, y, j);
            hilo.setPriority(Thread.MIN_PRIORITY);
            hilo.start();
        }

        if (hilo2 == null) {
            hilo2 = new Letras(x2, y2, j2);
            hilo2.setPriority(Thread.MAX_PRIORITY);
            hilo2.start();
        }

        if (hilo3 == null) {
            hilo3 = new Letras(x3, y3, j3);
            hilo3.setPriority(Thread.NORM_PRIORITY);
            hilo3.start();
        }
    }

    // Clase interna para el hilo
    class Letras extends Thread {
        private boolean stopHilo = false;
        int x, y, j;

        // Constructor
        Letras(int x, int y, int j) {
            this.x = x;
            this.y = y;
            this.j = j;
        }

        // Detener el hilo
        void PararHilo(boolean p) {
            this.stopHilo = p;
        }

        // Método que define el comportamiento del hilo
        public void run() {
            while (!stopHilo) {
                this.x = this.x + this.j;
                repaint();
                try {
                    sleep((long) (Math.random() * 1000 + 1));
                } catch (InterruptedException e) {
                }
            }
            System.out.println("Fin hilo " + getName());
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        void setX(int x) {
            this.x = x;
        }

        void setY(int y) {
            this.y = y;
        }

        void setJ(int j) {
            this.j = j;
        }
    }

    // Sobrescribir el método paint para dibujar en el JFrame
    public void paint(Graphics g) {
        super.paint(g);  // Llamar al método paint de JFrame
        g.setFont(fuente);
        g.drawString("Ganador: " + gana, 2, 200);
        g.drawRect(getWidth() - 15, 1, getWidth() - 15, getHeight() - 2);

        // Verificar si algún hilo llegó al final
        if (hilo.getX() >= getWidth() - 15) {
            gana = "1";
            PararHilos();
        }
        g.drawString("1", hilo.getX(), hilo.getY());

        if (hilo2.getX() >= getWidth() - 15) {
            gana = "2";
            PararHilos();
        }
        g.drawString("2", hilo2.getX(), hilo2.getY());

        if (hilo3.getX() >= getWidth() - 15) {
            gana = "3";
            PararHilos();
        }
        g.drawString("3", hilo3.getX(), hilo3.getY());

        g.drawString("Ganador: " + gana, 2, 200);
    }

    // Método para detener los hilos
    private void PararHilos() {
        hilo.PararHilo(true);
        hilo2.PararHilo(true);
        hilo3.PararHilo(true);
    }

    // Método principal
    public static void main(String[] args) {
        new Ejercicio6();
    }
}
