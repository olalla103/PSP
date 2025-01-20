package Actividad1;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FTPServerSetup {

    public static void main(String[] args) {
        // Ruta base para los directorios de los usuarios
        String baseDir = "C:\\FTPServer";

        // Lista de usuarios y contraseñas
        Map<String, String> users = new HashMap<>();
        users.put("user1", "password1");
        users.put("user2", "password2");
        users.put("user3", "password3");

        // Crear directorios base y asignar usuarios
        createDirectoriesAndUsers(baseDir, users);

        System.out.println("Configuración completada. Revisa el directorio: " + baseDir);
    }

    /**
     * Crear directorios para los usuarios y generar un archivo de configuración.
     */
    private static void createDirectoriesAndUsers(String baseDir, Map<String, String> users) {
        // Crear el directorio base
        try {
            Files.createDirectories(Paths.get(baseDir));
        } catch (IOException e) {
            System.err.println("Error al crear el directorio base: " + e.getMessage());
            return;
        }

        // Crear subdirectorios para cada usuario
        for (Map.Entry<String, String> entry : users.entrySet()) {
            String user = entry.getKey();
            String password = entry.getValue();

            // Crear directorio del usuario
            String userDir = baseDir + File.separator + user;
            try {
                Files.createDirectories(Paths.get(userDir));
                System.out.println("Directorio creado para " + user + ": " + userDir);
            } catch (IOException e) {
                System.err.println("Error al crear directorio para " + user + ": " + e.getMessage());
            }
        }

        // Crear archivo de configuración de usuarios
        String configFile = baseDir + File.separator + "usuarios.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write("Usuario: " + entry.getKey() + ", Contraseña: " + entry.getValue());
                writer.newLine();
            }
            System.out.println("Archivo de configuración creado: " + configFile);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo de configuración: " + e.getMessage());
        }
    }
}
