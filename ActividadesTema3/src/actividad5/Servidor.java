package actividad5;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 12345;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado. Esperando clientes...");

            for (int i = 1; i <= 3; i++) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente " + i + " conectado desde " + cliente.getInetAddress().getHostAddress());

                // Enviar mensaje al cliente
                PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
                out.println("Eres el cliente número " + i);

                // Recibir mensaje del cliente
                BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                String mensaje = in.readLine();
                System.out.println("Mensaje del cliente " + i + ": " + mensaje);

                cliente.close();
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
