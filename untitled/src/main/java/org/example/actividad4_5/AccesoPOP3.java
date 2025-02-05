package org.example.actividad4_5;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class AccesoPOP3 {
    public static void main(String[] args) {
        // Configuración del servidor POP3
        String host = "pop.gmail.com"; // Servidor POP de Gmail
        String puerto = "995";        // Puerto seguro para POP3
        String usuario = "tu_correo@gmail.com"; // Cambia por tu correo
        String contraseña = "tu_contraseña";   // Cambia por tu contraseña o contraseña de aplicación

        // Configurar las propiedades para POP3
        Properties props = new Properties();
        props.put("mail.pop3.host", host);
        props.put("mail.pop3.port", puerto);
        props.put("mail.pop3.starttls.enable", "true"); // Habilitar TLS
        props.put("mail.pop3.ssl.enable", "true");      // Habilitar SSL

        // Crear una sesión con autenticación
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, contraseña);
            }
        });

        try {
            // Conectar al servidor POP3
            Store store = session.getStore("pop3s"); // Protocolo POP3 con SSL
            store.connect();

            // Acceder a la bandeja de entrada
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY); // Abrir en modo solo lectura

            // Obtener mensajes
            Message[] mensajes = folder.getMessages();
            System.out.println("Número de mensajes: " + mensajes.length);

            // Recorrer y mostrar cada mensaje
            for (int i = 0; i < mensajes.length; i++) {
                Message mensaje = mensajes[i];
                System.out.println("Mensaje " + (i + 1) + ":");
                System.out.println("De: " + mensaje.getFrom()[0]);
                System.out.println("Asunto: " + mensaje.getSubject());
                System.out.println("Fecha: " + mensaje.getSentDate());
                System.out.println("Contenido: " + mensaje.getContent());
                System.out.println("------------------------");
            }

            // Cerrar carpeta y desconectar del servidor
            folder.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
