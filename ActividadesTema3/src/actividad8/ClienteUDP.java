package actividad8;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        try {
            // Crear el socket UDP del cliente
            DatagramSocket socket = new DatagramSocket();
            InetAddress direccionServidor = InetAddress.getByName("localhost");

            // Solicitar datos al usuario
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce el nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Introduce la edad: ");
            int edad = scanner.nextInt();

            // Crear y mostrar el objeto Persona
            Persona persona = new Persona(nombre, edad);
            System.out.println("Objeto a enviar: " + persona);

            // Serializar el objeto Persona
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(persona);

            // Enviar el objeto al servidor
            byte[] datosEnviados = baos.toByteArray();
            DatagramPacket paqueteEnviado = new DatagramPacket(datosEnviados, datosEnviados.length, direccionServidor, 12345);
            socket.send(paqueteEnviado);
            System.out.println("Objeto enviado al servidor.");

            // Recibir el objeto modificado del servidor
            byte[] bufferRecibido = new byte[1024];
            DatagramPacket paqueteRecibido = new DatagramPacket(bufferRecibido, bufferRecibido.length);
            socket.receive(paqueteRecibido);

            // Deserializar el objeto recibido
            ByteArrayInputStream bais = new ByteArrayInputStream(paqueteRecibido.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Persona personaModificada = (Persona) ois.readObject();

            // Mostrar el objeto modificado
            System.out.println("Objeto recibido del servidor: " + personaModificada);

            // Cerrar recursos
            socket.close();
            scanner.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
