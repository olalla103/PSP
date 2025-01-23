package act2_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class act9 extends JFrame implements ActionListener {
    private HiloContador hilo1, hilo2;
    private Font fuente;
    private JButton btnIniciar, btnFinalizar, btnInterrumpir1, btnInterrumpir2;
    private JLabel estadoHilo1, estadoHilo2, contadorHilo1, contadorHilo2;

    public act9() {
        // Configuración del JFrame
        setTitle("Ejecutar e Interrumpir Hilos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Botón iniciar
        JPanel panelSuperior = new JPanel();
        btnIniciar = new JButton("Comenzar Proceso");
        btnIniciar.addActionListener(this);
        panelSuperior.add(btnIniciar);
        add(panelSuperior, BorderLayout.NORTH);

        // Botón interrumpir y etiquetas estado
        JPanel panelCentral = new JPanel(new GridLayout(2, 3, 10, 10));

        // Hilo 1
        btnInterrumpir1 = new JButton("Interrumpir");
        btnInterrumpir1.addActionListener(this);
        btnInterrumpir1.setEnabled(false); // Se habilitará después de iniciar
        contadorHilo1 = new JLabel("0", SwingConstants.CENTER);
        estadoHilo1 = new JLabel("Hilo1", SwingConstants.CENTER);

        // Hilo 2
        btnInterrumpir2 = new JButton("Interrumpir");
        btnInterrumpir2.addActionListener(this);
        btnInterrumpir2.setEnabled(false); // Se habilitará después de iniciar
        contadorHilo2 = new JLabel("0", SwingConstants.CENTER);
        estadoHilo2 = new JLabel("Hilo2", SwingConstants.CENTER);

        // Agrego componentes al panel
        panelCentral.add(btnInterrumpir1);
        panelCentral.add(new JLabel("")); // Espacio en blanco
        panelCentral.add(btnInterrumpir2);
        panelCentral.add(contadorHilo1);
        panelCentral.add(new JLabel("")); // Espacio en blanco
        panelCentral.add(contadorHilo2);
        panelCentral.add(estadoHilo1);
        panelCentral.add(new JLabel("")); // Espacio en blanco
        panelCentral.add(estadoHilo2);

        add(panelCentral, BorderLayout.CENTER);

        // Botón de finalizar
        JPanel panelInferior = new JPanel();
        btnFinalizar = new JButton("Finalizar Proceso");
        btnFinalizar.addActionListener(this);
        btnFinalizar.setEnabled(false); // Se habilitará después de iniciar
        panelInferior.add(btnFinalizar);
        add(panelInferior, BorderLayout.SOUTH);

        fuente = new Font("Verdana", Font.BOLD, 16);
    }

    // Control pulsación de botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciar) {
            // Inicializa los hilos solo una vez
            hilo1 = new HiloContador("HILO 1", 10, contadorHilo1, estadoHilo1);
            hilo2 = new HiloContador("HILO 2", 20, contadorHilo2, estadoHilo2);

            hilo1.start();
            hilo2.start();

            btnIniciar.setEnabled(false); // Desactiva el botón de iniciar
            btnInterrumpir1.setEnabled(true);
            btnInterrumpir2.setEnabled(true);
            btnFinalizar.setEnabled(true);

            estadoHilo1.setText("Hilo1 Corriendo");
            estadoHilo2.setText("Hilo2 Corriendo");
        } else if (e.getSource() == btnInterrumpir1) {
            hilo1.detener();
            btnInterrumpir1.setEnabled(false);
            estadoHilo1.setText("Hilo1 Interrumpido");
        } else if (e.getSource() == btnInterrumpir2) {
            hilo2.detener();
            btnInterrumpir2.setEnabled(false);
            estadoHilo2.setText("Hilo2 Interrumpido");
        } else if (e.getSource() == btnFinalizar) {
            hilo1.detener();
            hilo2.detener();
            btnFinalizar.setEnabled(false);
            estadoHilo1.setText("Hilo1 Interrumpido");
            estadoHilo2.setText("Hilo2 Interrumpido");
        }
    }

    class HiloContador extends Thread {
        private long contador;
        private boolean enEjecucion = true;
        private String nombre;
        private JLabel labelContador, labelEstado;

        public HiloContador(String nombre, long contadorInicial, JLabel labelContador, JLabel labelEstado) {
            this.nombre = nombre;
            this.contador = contadorInicial;
            this.labelContador = labelContador;
            this.labelEstado = labelEstado;
        }

        public long getContador() {
            return contador;
        }

        public void detener() {
            enEjecucion = false;
            this.interrupt(); // Interrumpo el hilo para salir del sleep
        }

        public boolean isRunning() {
            return enEjecucion;
        }

        @Override
        public void run() {
            while (enEjecucion) {
                try {
                    Thread.sleep(300);
                    contador++;
                    labelContador.setText(String.valueOf(contador)); // Actualiza contador en la interfaz
                } catch (InterruptedException e) {
                    enEjecucion = false; // Sale del bucle si el hilo es interrumpido
                }
            }
            System.out.println(nombre + " finalizado con contador: " + contador);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            act9 frame = new act9();
            frame.setVisible(true);
        });
    }
}
