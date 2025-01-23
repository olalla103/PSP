package pack.ejLibro;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class ejLectura {
    public static void main(String[] args) throws IOException, IOException {
        // Cambiar la ruta para que funcione en Linux
        File directorio = new File("./bin");
        ProcessBuilder pb = new ProcessBuilder("java", "EjemploLectura");
        pb.directory(directorio);

        // Se ejecuta el proceso
        Process p = pb.start();

        // Escritura - se envía la entrada
        OutputStream os = p.getOutputStream();
        os.write("Hola Manuel\n".getBytes());
        os.flush(); // Vacía el buffer de salida
        os.close(); // Cerramos el OutputStream

        // Lectura de la salida del proceso si es necesario
        try (var reader = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        // Comprobar la finalización del proceso
        try {
            int exitCode = p.waitFor();
            System.out.println("Proceso finalizado con código: " + exitCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
