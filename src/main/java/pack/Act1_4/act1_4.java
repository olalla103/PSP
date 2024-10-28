package pack.Act1_4;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class act1_4 {

    public static void main(String[] args) throws IOException {
        // Directorio donde está la clase LeerNombre
        File directorio = new File("/home/usuario/PSP/out/production/PSP");

        // Usamos ProcessBuilder para ejecutar el comando java con la clase LeerNombre y sus argumentos
        ProcessBuilder pb = new ProcessBuilder("/home/usuario/.jdks/openjdk-23/bin/java", "EjemplosLibro.Act1_4.LeerNombre", "Me llamo Olalla");
        pb.directory(directorio);

        System.out.println("Directorio de trabajo: " + pb.directory());
        Process p = pb.start();

        // Leemos la salida del proceso
        try (InputStream is = p.getInputStream()) {
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



