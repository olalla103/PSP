package actividad2;

import java.io.IOException;
import java.net.Socket;

public class ClienteTCP {
    public static void main(String[] args) {
        String direccionServidor = "localhost"; // Dirección del servidor
        int puerto = 12345; // Puerto del servidor

        try (Socket socket = new Socket(direccionServidor, puerto)) {
            System.out.println("Conectado al servidor.");
            System.out.println("Puerto local: " + socket.getLocalPort());
            System.out.println("Puerto remoto: " + socket.getPort());
            System.out.println("Dirección remota: " + socket.getInetAddress().getHostAddress());

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
