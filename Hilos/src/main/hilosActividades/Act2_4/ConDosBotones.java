package main.hilosActividades.Act2_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConDosBotones extends JFrame {
    private Thread hilo1, hilo2;
    private long contador1 = 0, contador2 = 0;
    private boolean suspenderHilo1 = false, suspenderHilo2 = false;
    private JLabel labelEstadoHilo1, labelEstadoHilo2;
    private JLabel labelContadorHilo1, labelContadorHilo2;
    private JButton iniciarBtn, finalizarBtn;
    private JButton reanudarHilo1Btn, suspenderHilo1Btn;
    private JButton reanudarHilo2Btn, suspenderHilo2Btn;

    public ConDosBotones() {
        setTitle("Control de Hilos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Botones de control
        iniciarBtn = new JButton("Comenzar Proceso");
        finalizarBtn = new JButton("Finalizar Proceso");
        reanudarHilo1Btn = new JButton("Reanudar Hilo 1");
        suspenderHilo1Btn = new JButton("Suspender Hilo 1");
        reanudarHilo2Btn = new JButton("Reanudar Hilo 2");
        suspenderHilo2Btn = new JButton("Suspender Hilo 2");

        // Etiquetas de estado y contador
        labelEstadoHilo1 = new JLabel("Hilo 1: ");
        labelContadorHilo1 = new JLabel("0");
        labelEstadoHilo2 = new JLabel("Hilo 2: ");
        labelContadorHilo2 = new JLabel("0");

        // Añadir componentes al JFrame
        add(iniciarBtn);
        add(finalizarBtn);
        add(reanudarHilo1Btn);
        add(suspenderHilo1Btn);
        add(labelEstadoHilo1);
        add(labelContadorHilo1);
        add(reanudarHilo2Btn);
        add(suspenderHilo2Btn);
        add(labelEstadoHilo2);
        add(labelContadorHilo2);

        // Configurar los listeners de botones
        iniciarBtn.addActionListener(e -> iniciarHilos());
        finalizarBtn.addActionListener(e -> finalizarHilos());
        reanudarHilo1Btn.addActionListener(e -> reanudarHilo(1));
        suspenderHilo1Btn.addActionListener(e -> suspenderHilo(1));
        reanudarHilo2Btn.addActionListener(e -> reanudarHilo(2));
        suspenderHilo2Btn.addActionListener(e -> suspenderHilo(2));
    }

    private void iniciarHilos() {
        // Crear e iniciar el hilo 1
        hilo1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (this) {
                    while (suspenderHilo1) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                contador1++;
                SwingUtilities.invokeLater(() -> labelContadorHilo1.setText(String.valueOf(contador1)));
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        hilo1.start();

        // Crear e iniciar el hilo 2
        hilo2 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (this) {
                    while (suspenderHilo2) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                contador2++;
                SwingUtilities.invokeLater(() -> labelContadorHilo2.setText(String.valueOf(contador2)));
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        hilo2.start();

        // Actualizar etiquetas de estado
        labelEstadoHilo1.setText("Hilo 1: Corriendo");
        labelEstadoHilo2.setText("Hilo 2: Corriendo");
    }

    private void finalizarHilos() {
        if (hilo1 != null) hilo1.interrupt();
        if (hilo2 != null) hilo2.interrupt();
        labelEstadoHilo1.setText("Hilo 1: Finalizado");
        labelEstadoHilo2.setText("Hilo 2: Finalizado");
    }

    private void suspenderHilo(int hilo) {
        if (hilo == 1) {
            suspenderHilo1 = true;
            labelEstadoHilo1.setText("Hilo 1: Suspendido");
        } else if (hilo == 2) {
            suspenderHilo2 = true;
            labelEstadoHilo2.setText("Hilo 2: Suspendido");
        }
    }

    private void reanudarHilo(int hilo) {
        synchronized (this) {
            if (hilo == 1) {
                suspenderHilo1 = false;
                labelEstadoHilo1.setText("Hilo 1: Corriendo");
            } else if (hilo == 2) {
                suspenderHilo2 = false;
                labelEstadoHilo2.setText("Hilo 2: Corriendo");
            }
            notifyAll(); // Notificar a los hilos para reanudar
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConDosBotones frame = new ConDosBotones();
            frame.setVisible(true);
        });
    }
}
