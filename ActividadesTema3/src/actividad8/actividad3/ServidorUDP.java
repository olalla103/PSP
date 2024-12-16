package actividad8.actividad3;

import java.io.*;
import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) {
        try {
            // Inicializar el socket UDP
            DatagramSocket socket = new DatagramSocket(12345);
            System.out.println("Servidor UDP iniciado...");

            // Array de alumnos con datos predefinidos
            Alumno[] alumnos = {
                    new Alumno("1", "Olalla López", new Curso("1", "Matemáticas"), 85),
                    new Alumno("2", "Iñigo López", new Curso("2", "Historia"), 90),
                    new Alumno("3", "Paola Bernal", new Curso("3", "Física"), 78),
                    new Alumno("4", "Berta Bernal", new Curso("4", "Química"), 88),
                    new Alumno("5", "Jose Basques", new Curso("5", "Literatura"), 92)
            };

            byte[] buffer = new byte[1024];

            while (true) {
                // Recibir idalumno del cliente
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);
                String idSolicitado = new String(paqueteRecibido.getData()).trim();
                System.out.println("ID solicitado: " + idSolicitado);

                // Buscar el alumno correspondiente
                Alumno alumnoEncontrado = null;
                for (Alumno a : alumnos) {
                    if (a.getIdalumno().equals(idSolicitado)) {
                        alumnoEncontrado = a;
                        break;
                    }
                }

                // Si no existe, crear un alumno vacío
                if (alumnoEncontrado == null) {
                    alumnoEncontrado = new Alumno("0", "No existe", new Curso("0", "Sin curso"), 0);
                }

                // Enviar el objeto al cliente
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(alumnoEncontrado);

                byte[] datosEnviados = baos.toByteArray();
                DatagramPacket paqueteEnviado = new DatagramPacket(datosEnviados, datosEnviados.length,
                        paqueteRecibido.getAddress(), paqueteRecibido.getPort());
                socket.send(paqueteEnviado);
                System.out.println("Información enviada al cliente.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
