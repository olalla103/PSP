package pack.Act1_5;

import java.io.*;

public class act1_5 {
    public static void main(String[] args) throws IOException {
        File directorio = new File("/home/usuario/PSP/out/production/PSP");
        ProcessBuilder pb = new ProcessBuilder("/home/usuario/.jdks/openjdk-23/bin/java", "EjemplosLibro.Act6", "DIRR");
        pb.directory(directorio);
        System.out.println("Directorio de trabajo:  \n" + pb.directory());
        Process p = pb.start();

        try {
            InputStream er = p.getErrorStream();
            BufferedReader brer = new BufferedReader(new InputStreamReader(er));
            String liner = null;
            while ((liner = brer.readLine()) != null) {
                System.out.println("Error >" + liner);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
