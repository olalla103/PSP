package actividad3;

import java.io.*;
import java.net.*;

public class Servidor{
    public static void main(String[] args) {
        int puerto = 12345; // Puerto del servidor

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado. Esperando cliente...");

            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado desde " + cliente.getInetAddress().getHostAddress());

            // Flujo de salida para enviar mensaje
            PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
            out.println("¡Hola Cliente, conviértelo a minúsculas!");

            // Flujo de entrada para recibir mensaje
            BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            String respuesta = in.readLine();

            System.out.println("Respuesta del cliente en minúsculas: " + respuesta);

            cliente.close();
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
