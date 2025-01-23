package actividad10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;

public class ClienteAdivinaGUI extends JFrame {
    private JTextField idJugadorField, intentosField, numeroField;
    private JTextArea outputArea;
    private Socket socket;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private int intentos = 0;

    public ClienteAdivinaGUI() {
        // Configuración básica de la ventana
        setTitle("JUGADOR - ADIVINA UN NÚMERO");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de información del jugador
        JPanel infoPanel = new JPanel(new GridLayout(2, 2));
        infoPanel.add(new JLabel("ID Jugador:"));
        idJugadorField = new JTextField("0");
        idJugadorField.setEditable(false); // Solo lectura
        infoPanel.add(idJugadorField);
        infoPanel.add(new JLabel("Intentos:"));
        intentosField = new JTextField("0");
        intentosField.setEditable(false); // Solo lectura
        infoPanel.add(intentosField);

        // Campo de entrada para el número
        numeroField = new JTextField();
        JLabel numeroLabel = new JLabel("Número a adivinar (1-25):");

        // Área de texto para mostrar mensajes del servidor
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        // Botones
        JButton enviarButton = new JButton("Enviar");
        JButton salirButton = new JButton("Salir");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(enviarButton);
        buttonPanel.add(salirButton);

        // Añadir componentes a la ventana
        add(infoPanel, BorderLayout.NORTH);
        add(numeroLabel, BorderLayout.WEST);
        add(numeroField, BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Conectar al servidor
        conectarServidor();

        // Eventos de los botones
        enviarButton.addActionListener((ActionEvent e) -> enviarNumero());
        salirButton.addActionListener((ActionEvent e) -> salir());
    }

    private void conectarServidor() {
        try {
            // Conectar al servidor
            socket = new Socket("localhost", 44444);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar al servidor", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void enviarNumero() {
        try {
            // Leer el número ingresado por el usuario
            String texto = numeroField.getText().trim();
            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un número.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int numero = Integer.parseInt(texto); // Convertir a entero
            intentos++; // Incrementar el contador de intentos
            Datos datos = new Datos(numero, intentos); // Crear objeto Datos

            salida.writeObject(datos); // Enviar objeto al servidor

            // Recibir la respuesta del servidor
            Datos respuesta = (Datos) entrada.readObject();
            outputArea.append("Servidor: " + respuesta.getMensaje() + "\n");

            // Actualizar intentos
            intentosField.setText(String.valueOf(intentos));

            // Comprobar si el juego terminó
            if (respuesta.isGanador()) {
                JOptionPane.showMessageDialog(this, "¡Juego terminado! " + respuesta.getMensaje(), "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
                salir();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error al comunicarse con el servidor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salir() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteAdivinaGUI().setVisible(true));
    }
}
