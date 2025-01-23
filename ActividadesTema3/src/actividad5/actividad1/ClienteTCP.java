package actividad5.actividad1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteTCP {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 12345;

        try (Socket socket = new Socket(host, puerto)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            String mensaje;
            while (true) {
                System.out.print("Introduce una cadena (* para salir): ");
                mensaje = scanner.nextLine();
                out.println(mensaje);

                if (mensaje.equals("*")) break;

                String respuesta = in.readLine();
                System.out.println("Respuesta del servidor: " + respuesta);
            }

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
