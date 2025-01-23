package actividad1;

import java.net.*;  // Importa las clases de la biblioteca java.net necesarias para trabajar con direcciones de red.

public class Actividad3_1 {
    public static void main(String[] args) {

        // Vamos a probar con google
        System.out.println("\nProbando con: www.google.com");
        try {
            // Obtenemos la dirección InetAddress para google.
            InetAddress dir = InetAddress.getByName("www.google.com");

            pruebaMetodos(dir);

            // Obtenemos todas las direcciones IP asociadas al nombre del host.
            InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
            System.out.println("\nDIRECCIONES IP PARA: " + dir.getHostName());
            for (InetAddress direccion : direcciones) {
                // Imprimimos cada dirección IP asociada.
                System.out.println("\t" + direccion.toString());
            }

        } catch (UnknownHostException e) {
            // Se maneja el caso en que no se puede resolver el nombre o IP.
            System.out.println("Host desconocido: www.google.com");
            e.printStackTrace();
        }
    }


    // Prueba de varias funciones de la clase InetAddress.
    private static void pruebaMetodos(InetAddress dir) {
        System.out.println("\nMétodo getByName(): " + dir);

        try {
            // Obtenemos la dirección de nuestra máquina.
            InetAddress dir2 = InetAddress.getLocalHost();
            System.out.println("Método getLocalHost(): " + dir2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        System.out.println("Método getHostName(): " + dir.getHostName());  // Nombre de nuestra máquina
        System.out.println("Método getHostAddress(): " + dir.getHostAddress());  // Dirección IP
        System.out.println("Método toString(): " + dir.toString());  // Representación textual completa
    }
}
