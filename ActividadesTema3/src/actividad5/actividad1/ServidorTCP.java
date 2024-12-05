package actividad5.actividad1;

import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) {
        int puerto = 12345;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado. Esperando conexión del cliente...");

            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado desde " + cliente.getInetAddress().getHostAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);

            String mensaje;
            while (true) {
                mensaje = in.readLine();
                if (mensaje.equals("*")) {
                    System.out.println("Cliente ha finalizado la conexión.");
                    break;
                }
                int caracteres = mensaje.length();
                System.out.println("Recibido: \"" + mensaje + "\" (caracteres: " + caracteres + ")");
                out.println("Número de caracteres: " + caracteres);
            }

            cliente.close();
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}

