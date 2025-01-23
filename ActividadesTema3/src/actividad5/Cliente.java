package actividad5;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 12345;

        try (Socket socket = new Socket(host, puerto)) {
            // Recibir mensaje del servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensaje = in.readLine();
            System.out.println("Mensaje del servidor: " + mensaje);

            // Enviar mensaje al servidor
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Gracias, servidor.");

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}

