package org.example.actvidad4_6;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ClienteFTP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Configuración del servidor FTP
        String servidor = "localhost";
        int puerto = 21;

        FTPClient ftpClient = new FTPClient();

        try {
            while (true) {
                System.out.println("Introduce el nombre de usuario:");
                String usuario = scanner.nextLine();

                System.out.println("Introduce la contraseña:");
                String contraseña = scanner.nextLine();

                // Intentar conexión al servidor FTP
                ftpClient.connect(servidor, puerto);
                if (ftpClient.login(usuario, contraseña)) {
                    System.out.println("Conexión exitosa como " + usuario);

                    // Establecer modo binario para transferencias
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                    ftpClient.enterLocalPassiveMode();

                    // Ruta de la carpeta del usuario
                    String rutaUsuario = usuario + "/LOG";
                    if (!ftpClient.changeWorkingDirectory(rutaUsuario)) {
                        System.out.println("La carpeta " + rutaUsuario + " no existe. Creándola...");
                        if (ftpClient.makeDirectory(rutaUsuario)) {
                            System.out.println("Carpeta creada correctamente.");
                        } else {
                            System.out.println("Error al crear la carpeta.");
                            break;
                        }
                    }

                    // Cambiar al directorio del usuario
                    ftpClient.changeWorkingDirectory(rutaUsuario);

                    // Leer o crear el archivo LOG.TXT
                    String nombreArchivo = "LOG.TXT";
                    InputStream inputStream = ftpClient.retrieveFileStream(nombreArchivo);
                    StringBuilder contenidoLog = new StringBuilder();
                    if (inputStream != null) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        String linea;
                        while ((linea = reader.readLine()) != null) {
                            contenidoLog.append(linea).append("\n");
                        }
                        reader.close();
                        ftpClient.completePendingCommand();
                        inputStream.close();
                    }

                    // Agregar nueva entrada al log
                    String horaConexion = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").format(new Date());
                    contenidoLog.append("Hora de conexión: ").append(horaConexion).append("\n");

                    // Subir el archivo actualizado al servidor
                    ByteArrayInputStream inputActualizado = new ByteArrayInputStream(contenidoLog.toString().getBytes());
                    if (ftpClient.storeFile(nombreArchivo, inputActualizado)) {
                        System.out.println("Log actualizado correctamente.");
                    } else {
                        System.out.println("Error al actualizar el log.");
                    }
                    inputActualizado.close();

                    // Desconectar al usuario
                    ftpClient.logout();
                    ftpClient.disconnect();
                    System.out.println("Desconexión exitosa.");

                    // Preguntar si desea continuar
                    System.out.println("¿Desea conectar otro usuario? (S/N)");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("N")) {
                        break;
                    }
                } else {
                    System.out.println("Error de autenticación. Intente nuevamente.");
                    ftpClient.disconnect();
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
