package actividad8;

import java.io.*;
import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) {
        try {
            // Crea un socket UDP en el puerto 12345
            DatagramSocket socket = new DatagramSocket(12345);
            System.out.println("Servidor UDP iniciado...");

            byte[] buffer = new byte[1024]; // Buffer para recibir datos

            while (true) {
                // Esperar la recepción de un datagrama
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);
                System.out.println("Datagrama recibido del cliente.");

                // Deserializar el objeto Persona recibido
                ByteArrayInputStream bais = new ByteArrayInputStream(paqueteRecibido.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Persona persona = (Persona) ois.readObject(); // Leer el objeto Persona
                System.out.println("Persona recibida: " + persona);

                // Modificar el objeto Persona (sumar 5 años y convertir el nombre a mayúsculas)
                persona.setEdad(persona.getEdad() + 5);
                persona.setNombre(persona.getNombre().toUpperCase());
                System.out.println("Persona modificada: " + persona);

                // Serializar el objeto modificado
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(persona);

                // Enviar el objeto modificado al cliente
                byte[] datosEnviados = baos.toByteArray();
                InetAddress direccionCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();
                DatagramPacket paqueteEnviado = new DatagramPacket(datosEnviados, datosEnviados.length, direccionCliente, puertoCliente);

                socket.send(paqueteEnviado);
                System.out.println("Objeto modificado enviado al cliente.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
