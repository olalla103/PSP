package Actividad2;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LocalFTP {

    public static void main(String[] args) {
        // Configuración del servidor FTP
        String server = "localhost"; // Dirección del servidor FTP (en tu caso, localhost)
        int port = 21; // Puerto por defecto para FTP
        String user = "uno"; // Usuario configurado en el servidor FTP
        String password = "uno"; // Contraseña configurada para el usuario "uno"
        String remoteDirectory = "/home/usuario"; // Ruta en el servidor FTP donde quieres subir el archivo

        // Crear JFileChooser para seleccionar el archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona un fichero para subir al servidor FTP");
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Conexión al servidor FTP
            FTPClient ftpClient = new FTPClient();
            try {
                ftpClient.connect(server, port);
                boolean login = ftpClient.login(user, password);

                if (login) {
                    ftpClient.enterLocalPassiveMode(); // Establecer modo pasivo
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE); // Configurar el tipo de archivo (binario)

                    // Cambiar al directorio remoto
                    if (!ftpClient.changeWorkingDirectory(remoteDirectory)) {
                        // Si no existe el directorio, crear uno
                        if (ftpClient.makeDirectory(remoteDirectory)) {
                            ftpClient.changeWorkingDirectory(remoteDirectory);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al acceder o crear el directorio.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                    // Subir el archivo seleccionado
                    String remoteFile = selectedFile.getName(); // El nombre del archivo remoto será el mismo que el archivo local
                    try (FileInputStream inputStream = new FileInputStream(selectedFile)) {
                        boolean done = ftpClient.storeFile(remoteFile, inputStream); // Subir el archivo
                        if (done) {
                            JOptionPane.showMessageDialog(null, remoteFile + " se subió correctamente.");

                            // Listar el contenido del directorio actual del servidor FTP
                            System.out.println("Contenido del directorio actual en el servidor FTP:");
                            for (String file : ftpClient.listNames()) {
                                System.out.println(file);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al subir el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo iniciar sesión en el servidor FTP.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                ftpClient.logout(); // Cerrar sesión después de la carga
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } finally {
                try {
                    if (ftpClient.isConnected()) {
                        ftpClient.disconnect(); // Desconectar al finalizar
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionó ningún archivo.");
        }
    }
}
