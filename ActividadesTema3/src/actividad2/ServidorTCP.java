package actividad2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) {
        int puerto = 12345; // Puerto del servidor

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto " + puerto);
            System.out.println("Esperando conexiones...");

            // Aceptar conexión del primer cliente
            Socket cliente1 = servidor.accept();
            System.out.println("Cliente 1 conectado:");
            System.out.println("Puerto local: " + cliente1.getLocalPort());
            System.out.println("Puerto remoto: " + cliente1.getPort());

            // Aceptar conexión del segundo cliente
            Socket cliente2 = servidor.accept();
            System.out.println("Cliente 2 conectado:");
            System.out.println("Puerto local: " + cliente2.getLocalPort());
            System.out.println("Puerto remoto: " + cliente2.getPort());

            // Mantener el servidor abierto
            System.out.println("Conexiones establecidas. Manteniendo el servidor abierto...");
            Thread.sleep(10000); // Espera 10 segundos para mantener las conexiones abiertas

            cliente1.close();
            cliente2.close();
            System.out.println("Clientes desconectados. Cerrando servidor.");

        } catch (IOException | InterruptedException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
