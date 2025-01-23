package actividad9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteGUI().setVisible(true));
    }
}

// Clase para la interfaz gráfica del cliente
class ClienteGUI extends JFrame {
    private JTextField inputField; // Campo para escribir texto
    private JTextArea outputArea; // Área para mostrar resultados
    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;

    public ClienteGUI() {
        setTitle("VENTANA DEL CLIENTE - EJERCICIO 5");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Campo de entrada de texto
        inputField = new JTextField();
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        // Botones
        JButton enviarButton = new JButton("Enviar");
        JButton limpiarButton = new JButton("Limpiar");
        JButton salirButton = new JButton("Salir");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(enviarButton);
        buttonPanel.add(limpiarButton);
        buttonPanel.add(salirButton);

        // Añadir componentes
        add(new JLabel("Escribe texto:"), BorderLayout.NORTH);
        add(inputField, BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Conectar al servidor
        conectarServidor();

        // Acciones de los botones
        enviarButton.addActionListener((ActionEvent e) -> enviarTexto());
        limpiarButton.addActionListener((ActionEvent e) -> limpiarCampos());
        salirButton.addActionListener((ActionEvent e) -> salir());
    }

    private void conectarServidor() {
        try {
            socket = new Socket("localhost", 44444);
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar al servidor", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void enviarTexto() {
        String texto = inputField.getText().trim();
        if (!texto.isEmpty()) {
            salida.println(texto);
            if (texto.equals("*")) {
                salir();
                return;
            }

            try {
                String respuesta = entrada.readLine();
                outputArea.append("Enviado: " + texto + "\n");
                outputArea.append("Respuesta: " + respuesta + "\n");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al recibir respuesta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarCampos() {
        inputField.setText("");
        outputArea.setText("");
    }

    private void salir() {
        try {
            salida.println("*");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
