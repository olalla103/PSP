package actividad6;

import java.net.*;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        // Dirección del servidor y puerto al que nos conectaremos
        String host = "localhost";
        int puerto = 12345;
        boolean flag = false; // Boolean para parar la ejecución si el cliente introduce *

        // Buffer para recibir los datos del servidor
        byte[] buffer = new byte[1024];

        // Creación del socket UDP
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(5000);
            Scanner scanner = new Scanner(System.in);

            while (!flag) {
                // Solicitamos al usuario que introduzca un mensaje
                System.out.print("Introduce un mensaje (* para salir): ");
                String mensaje = scanner.nextLine();
                byte[] mensajeBytes = mensaje.getBytes();

                // Creamos el paquete UDP con el mensaje y los datos del servidor
                DatagramPacket paqueteEnviado = new DatagramPacket(mensajeBytes, mensajeBytes.length, InetAddress.getByName(host), puerto);
                socket.send(paqueteEnviado);

                // Si el usuario introduce '*', salimos del bucle y finalizamos el programa
                if (mensaje.equals("*")) {
                    flag = true;
                }

                try {
                    DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                    socket.receive(paqueteRecibido);

                    // Convertimos la respuesta del servidor a un string y la mostramos
                    String respuesta = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                    System.out.println("Respuesta del servidor: " + respuesta);
                } catch (SocketTimeoutException e) {
                    System.out.println("Tiempo de espera agotado. El paquete se perdió.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error en el cliente UDP: " + e.getMessage());
        }
    }
}
