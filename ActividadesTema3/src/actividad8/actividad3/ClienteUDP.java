package actividad8.actividad3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress direccionServidor = InetAddress.getByName("localhost");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Introduce el ID del alumno (* para salir): ");
                String idAlumno = scanner.nextLine();
                if (idAlumno.equals("*")) break;

                // Enviar el idalumno al servidor
                byte[] datosEnviados = idAlumno.getBytes();
                DatagramPacket paqueteEnviado = new DatagramPacket(datosEnviados, datosEnviados.length,
                        direccionServidor, 12345);
                socket.send(paqueteEnviado);

                // Recibir el objeto Alumno del servidor
                byte[] bufferRecibido = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(bufferRecibido, bufferRecibido.length);
                socket.receive(paqueteRecibido);

                // Deserializar el objeto Alumno
                ByteArrayInputStream bais = new ByteArrayInputStream(paqueteRecibido.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Alumno alumno = (Alumno) ois.readObject();

                // Mostrar los datos del alumno
                System.out.println("Información recibida: " + alumno);
            }

            socket.close();
            scanner.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

