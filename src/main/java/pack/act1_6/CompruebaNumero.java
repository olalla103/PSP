package pack.act1_6;

import java.io.*;

public class CompruebaNumero {
    public static void main(String[] args) throws IOException {
        // Directorio donde está la clase LeerNombre
        File directorio = new File("/home/usuario/PSP/out/production/PSP");

        // Uso processBuilder para ejecutar el comando java con la clase LeerNombre y sus argumentos
        ProcessBuilder pb = new ProcessBuilder("/home/usuario/.jdks/openjdk-23/bin/java", "/home/usuario/PSP/src/EjemplosLibro/act1_6/Act6.java");
        pb.directory(directorio);

        System.out.println("Directorio de trabajo: " + pb.directory());
        Process p = pb.start();

        OutputStream os = p.getOutputStream();
        os.write("10\n".getBytes()); // le paso el número 10
        os.write("2\n".getBytes());
        os.flush();

        // Leemos la salida del proceso
        try (InputStream is = p.getInputStream()) {
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int exitVal;

        try {
            exitVal = p.waitFor();
            System.out.println("Valor de salida: " + exitVal);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


}

