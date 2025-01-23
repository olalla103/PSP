package actividad3;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost"; // Dirección del servidor
        int puerto = 12345;

        try (Socket socket = new Socket(host, puerto)) {
            // Flujo de entrada para recibir mensaje
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensaje = in.readLine();
            System.out.println("Mensaje del servidor: " + mensaje);

            // Flujo de salida para enviar respuesta
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(mensaje.toLowerCase());

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
