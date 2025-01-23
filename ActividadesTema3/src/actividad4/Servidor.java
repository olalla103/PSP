package actividad4;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 12345;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado. Esperando cliente...");

            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado desde " + cliente.getInetAddress().getHostAddress());

            // Recibir número
            BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            int numero = Integer.parseInt(in.readLine());
            System.out.println("Número recibido: " + numero);

            // Cuadrado del número
            PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
            out.println(numero * numero);

            cliente.close();
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}

