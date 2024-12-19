package actividad11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;

public class Cliente extends JFrame implements Runnable {
    Socket socket;
    JTextField mensaje = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton enviar = new JButton("Enviar"), salir = new JButton("Salir");
    DataInputStream dis;
    DataOutputStream dos;
    String nombre;

    // Constructor para inicializar el cliente y la interfaz gráfica
    public Cliente(Socket s, String nombre) {
        super("Cliente del Chat: " + nombre);
        this.socket = s;
        this.nombre = nombre;

        // Configuración de la interfaz gráfica
        setLayout(new BorderLayout());
        add("North", mensaje);
        add("Center", new JScrollPane(textArea));
        JPanel p = new JPanel();
        p.add(enviar);
        p.add(salir);
        add("South", p);
        textArea.setEditable(false);

        // Eventos para botones
        enviar.addActionListener((ActionEvent e) -> {
            try {
                dos.writeUTF(nombre + " dice: " + mensaje.getText());
                mensaje.setText("");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        salir.addActionListener((ActionEvent e) -> {
            try {
                dos.writeUTF("*");
                socket.close();
                System.exit(0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Inicializar los flujos de entrada/salida
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("Entra en el Chat: " + nombre);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Configuración de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    @Override
    public void run() {
        // Leer mensajes del servidor y mostrarlos en el área de texto
        try {
            String texto;
            while (true) {
                texto = dis.readUTF();
                textArea.append("\n" + texto);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String nombre = JOptionPane.showInputDialog("Introduce tu nombre:");
        try {
            Socket s = new Socket("localhost", 44444); // Cambiar "localhost" si es otra IP
            Cliente cliente = new Cliente(s, nombre);
            new Thread(cliente).start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
