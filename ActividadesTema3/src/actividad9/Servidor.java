package actividad9;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(44444);
        System.out.println("Servidor iniciado...");

        while (true) {
            // Esperar a que un cliente se conecte
            Socket cliente = servidor.accept();
            InetAddress direccion = cliente.getInetAddress();
            int puerto = cliente.getPort();

            System.out.println("=>Conecta IP " + direccion + ", Puerto remoto: " + puerto);

            // Crear un hilo para atender al cliente
            HiloServidor hilo = new HiloServidor(cliente);
            hilo.start();
        }
    }
}

// Clase HiloServidor para manejar cada cliente en un hilo separado
class HiloServidor extends Thread {
    private Socket socket;

    public HiloServidor(Socket cliente) {
        this.socket = cliente;
    }

    @Override
    public void run() {
        try (
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String mensaje;
            System.out.println("COMUNICACIÓN CON: " + socket.toString());

            while ((mensaje = entrada.readLine()) != null) {
                if (mensaje.equals("*")) {
                    System.out.println("=>Desconecta IP " + socket.getInetAddress() + ", Puerto remoto: " + socket.getPort());
                    break;
                }

                // Convertir el mensaje a mayúsculas y enviarlo al cliente
                salida.println(mensaje.toUpperCase());
            }

            System.out.println("FIN CON: " + socket.toString());
        } catch (IOException e) {
            System.err.println("Error con el cliente: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
