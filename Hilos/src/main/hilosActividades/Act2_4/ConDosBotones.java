package main.hilosActividades.Act2_4;

import javax.swing.*;
import java.awt.*;

public class ConDosBotones extends JFrame {
    // Declaración de los hilos y contadores
    private Thread hilo1, hilo2;
    private long contador1 = 0, contador2 = 0;

    // Variables para suspender y reanudar los hilos
    private boolean suspenderHilo1 = false, suspenderHilo2 = false;

    private JLabel labelEstadoHilo1, labelEstadoHilo2; // Etiquetas del estado de cada hilo
    private JLabel labelContadorHilo1, labelContadorHilo2; // Etiquetas para mostrar el contador de cada hilo
    private JButton iniciar, finalizar; // Botones para iniciar y finalizar
    private JButton reanudarHilo1Btn, suspenderHilo1Btn, reanudarHilo2Btn, suspenderHilo2Btn; // Botones para el control de los hilos

    public ConDosBotones() {
        // Configuración inicial del JFrame
        setTitle("Control de Hilos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Inicializo los botones
        iniciar = new JButton("Comenzar Proceso");
        finalizar = new JButton("Finalizar Proceso");
        reanudarHilo1Btn = new JButton("Reanudar Hilo 1");
        suspenderHilo1Btn = new JButton("Suspender Hilo 1");
        reanudarHilo2Btn = new JButton("Reanudar Hilo 2");
        suspenderHilo2Btn = new JButton("Suspender Hilo 2");

        // Inicializo las etiquetas
        labelEstadoHilo1 = new JLabel("Hilo 1: ");
        labelContadorHilo1 = new JLabel("0");
        labelEstadoHilo2 = new JLabel("Hilo 2: ");
        labelContadorHilo2 = new JLabel("0");

        // Añado componentes al JFrame
        add(iniciar);
        add(finalizar);
        add(reanudarHilo1Btn);
        add(suspenderHilo1Btn);
        add(labelEstadoHilo1);
        add(labelContadorHilo1);
        add(reanudarHilo2Btn);
        add(suspenderHilo2Btn);
        add(labelEstadoHilo2);
        add(labelContadorHilo2);

        // Listeners para los botones
        iniciar.addActionListener(e -> iniciarHilos());
        finalizar.addActionListener(e -> finalizarHilos());
        reanudarHilo1Btn.addActionListener(e -> reanudarHilo(1));
        suspenderHilo1Btn.addActionListener(e -> suspenderHilo(1));
        reanudarHilo2Btn.addActionListener(e -> reanudarHilo(2));
        suspenderHilo2Btn.addActionListener(e -> suspenderHilo(2));
    }

    private void iniciarHilos() {
        // Inicia y ejecuta el hilo 1
        hilo1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) { // Verifica si el hilo ha sido interrumpido
                synchronized (this) { // Bloque sincronizado para control de suspensión
                    while (suspenderHilo1) { // Si se suspende, espera a ser notificado
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                contador1++; // Incrementa el contador
                SwingUtilities.invokeLater(() -> labelContadorHilo1.setText(String.valueOf(contador1))); // Actualiza la etiqueta en el EDT
                try {
                    Thread.sleep(300); // Pausa de 300 ms
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        hilo1.start();

        // Inicia y ejecuta el hilo 2 de forma similar al hilo 1
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

        // Actualiza etiquetas de estado para mostrar que los hilos están corriendo
        labelEstadoHilo1.setText("Hilo 1: Corriendo");
        labelEstadoHilo2.setText("Hilo 2: Corriendo");
    }

    private void finalizarHilos() {
        // Interrumpe los hilos y actualiza las etiquetas de estado
        if (hilo1 != null) hilo1.interrupt();
        if (hilo2 != null) hilo2.interrupt();
        labelEstadoHilo1.setText("Hilo 1: Finalizado");
        labelEstadoHilo2.setText("Hilo 2: Finalizado");
    }

    private void suspenderHilo(int hilo) {
        // Suspende el hilo especificado y actualiza la etiqueta de estado
        if (hilo == 1) {
            suspenderHilo1 = true;
            labelEstadoHilo1.setText("Hilo 1: Suspendido");
        } else if (hilo == 2) {
            suspenderHilo2 = true;
            labelEstadoHilo2.setText("Hilo 2: Suspendido");
        }
    }

    private void reanudarHilo(int hilo) {
        // Reanuda el hilo especificado y actualiza la etiqueta de estado
        synchronized (this) {
            if (hilo == 1) {
                suspenderHilo1 = false;
                labelEstadoHilo1.setText("Hilo 1: Corriendo");
            } else if (hilo == 2) {
                suspenderHilo2 = false;
                labelEstadoHilo2.setText("Hilo 2: Corriendo");
            }
            notifyAll(); // Notifica a todos los hilos para reanudar ejecución
        }
    }

    public static void main(String[] args) {
        // Crea y muestra el JFrame en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            ConDosBotones frame = new ConDosBotones();
            frame.setVisible(true);
        });
    }
}
