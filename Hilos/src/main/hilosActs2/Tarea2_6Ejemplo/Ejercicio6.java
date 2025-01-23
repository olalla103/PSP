package main.hilosActs2.act2_6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ejercicio6 extends JFrame {

    Letras hilo = null;
    Letras hilo2 = null;
    Letras hilo3 = null;

    int x, y, j = 2;
    int x2, y2, j2 = 2;
    int x3, y3, j3 = 2;

    private Font fuente;
    String gana = " ";

    public Ejercicio6() {
        setTitle("Carrera de Hilos con Prioridades");
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fuente = new Font("Verdana", Font.BOLD, 26);
        x = 1;
        y = 50;
        x2 = 1;
        y2 = 100;
        x3 = 1;
        y3 = 150;

        // Botón para iniciar la carrera
        JButton btnIniciar = new JButton("Comenzar Carrera");
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarCarrera();
            }
        });

        add(btnIniciar, BorderLayout.SOUTH);
    }

    class Letras extends Thread {
        private boolean stopHilo = false;
        int x, y, j;

        void PararHilo(boolean p) {
            this.stopHilo = p;
        }

        Letras(int x, int y, int j) {
            this.x = x;
            this.y = y;
            this.j = j;
        }

        public void run() {
            while (!stopHilo) {
                this.x += this.j;
                repaint();
                try {
                    sleep((long) (Math.random() * 1000 + 1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
    }

    public void iniciarCarrera() {
        gana = "";
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

    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(fuente);
        g.drawString("Ganador: " + gana, 2, 250);
        g.drawRect(getWidth() - 15, 1, getWidth() - 15, getHeight() - 2);

        if (hilo != null && hilo.getX() >= getWidth() - 15) {
            gana = "1";
            PararHilos();
        }
        g.drawString("1", hilo != null ? hilo.getX() : x, y);

        if (hilo2 != null && hilo2.getX() >= getWidth() - 15) {
            gana = "2";
            PararHilos();
        }
        g.drawString("2", hilo2 != null ? hilo2.getX() : x2, y2);

        if (hilo3 != null && hilo3.getX() >= getWidth() - 15) {
            gana = "3";
            PararHilos();
        }
        g.drawString("3", hilo3 != null ? hilo3.getX() : x3, y3);
    }

    private void PararHilos() {
        if (hilo != null) hilo.PararHilo(true);
        if (hilo2 != null) hilo2.PararHilo(true);
        if (hilo3 != null) hilo3.PararHilo(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ejercicio6 frame = new Ejercicio6();
            frame.setVisible(true);
        });
    }
}
