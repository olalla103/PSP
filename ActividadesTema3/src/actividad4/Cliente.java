package actividad4;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 12345;

        try (Socket socket = new Socket(host, puerto)) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Introduce un número: ");
            int numero = scanner.nextInt();

            // Enviar número al servidor
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(numero);

            // Recibir respuesta del servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String respuesta = in.readLine();
            System.out.println("El cuadrado del número es: " + respuesta);

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}

