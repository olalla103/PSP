package actividad6;

import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) {
        int puerto = 12345; // Puerto en el que el servidor estará escuchando
        byte[] buffer = new byte[1024];
        boolean flag = false;

        try (DatagramSocket socket = new DatagramSocket(puerto)) { // Creación del socket UDP en el puerto especificado
            System.out.println("Servidor UDP iniciado. Esperando mensajes...");

            while (!flag) {
                // Recibir paquete enviado por el cliente
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);

                // Convertir el mensaje recibido en un string
                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("Mensaje recibido: " + mensaje);

                // Si el mensaje es "*", se termina la conexión
                if (mensaje.equals("*")) {
                    System.out.println("El cliente ha terminado la conexión.");
                    flag = true;
                }

                // Convertir el mensaje a mayúsculas y enviarlo de vuelta al cliente
                String respuesta = mensaje.toUpperCase();
                byte[] respuestaBytes = respuesta.getBytes();
                DatagramPacket paqueteEnviado = new DatagramPacket(respuestaBytes, respuestaBytes.length,
                        paqueteRecibido.getAddress(), paqueteRecibido.getPort());
                socket.send(paqueteEnviado);
            }
        } catch (Exception e) {
            // Mostrar cualquier error ocurrido durante la ejecución
            System.err.println("Error en el servidor UDP: " + e.getMessage());
        }
    }
}
