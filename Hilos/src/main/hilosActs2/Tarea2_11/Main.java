package main.hilosActs2.Tarea2_11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private int x = 1;
    private int y = 100;
    private int direction = 1;
    private boolean running = true;
    private final JButton toggleButton = new JButton("Finalizar Hilo");

    public Main() {
        setTitle("Bouncing Letter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        // Botón para detener/reanudar el hilo
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                running = !running;
                toggleButton.setText(running ? "Finalizar Hilo" : "Reanudar Hilo");
            }
        });
        add(toggleButton, BorderLayout.SOUTH);

        Thread animationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (running) {
                        x += direction;

                        if (x >= drawingPanel.getWidth() - 20) {
                            direction = -1;
                        } else if (x <= 1) {
                            direction = 1;
                        }

                        drawingPanel.repaint();
                    }

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        animationThread.start();
    }

    // Panel de dibujo
    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("O", x, y); // Dibujar la letra
        }
    }

    public static void main(String[] args) {
        // Crear y mostrar el JFrame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main frame = new Main();
                frame.setVisible(true);
            }
        });
    }
}
