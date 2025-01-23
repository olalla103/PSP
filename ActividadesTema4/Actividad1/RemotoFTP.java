package Actividad1;

import org.apache.commons.net.ftp.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class RemotoFTP {

    public static void main(String[] args) {
        String server = "ftp.rediris.es"; // Servidor FTP
        int port = 21; // Puerto FTP por defecto
        String user = "anonymous"; // Usuario (anónimo)
        String pass = ""; // Contraseña (vacía para usuarios anónimos)

        FTPClient ftpClient = new FTPClient();

        try {
            // Conexión al servidor FTP
            ftpClient.connect(server, port);
            int replyCode = ftpClient.getReplyCode();
            if (!ftpClient.login(user, pass)) {
                System.out.println("No se ha podido loguear.");
                return;
            }

            System.out.println("Conectado al servidor FTP.");

            // Cambiar a modo pasivo y binario
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // Listar los archivos y directorios del directorio raíz
            System.out.println("Listando directorios y archivos:");
            FTPFile[] files = ftpClient.listFiles("/");
            if (files != null && files.length > 0) {
                for (FTPFile file : files) {
                    System.out.println(file.getName());
                }
            } else {
                System.out.println("No se encontraron archivos o directorios.");
            }
            // Cerrar sesión y desconectar
            ftpClient.logout();
            ftpClient.disconnect();
            System.out.println("Desconectado del servidor FTP.");
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
