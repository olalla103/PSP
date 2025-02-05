package org.example;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EnviarCorreo {
    public static void main(String[] args) {
        try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            // Solicitar datos del usuario
            System.out.print("Introduce servidor SMTP: ");
            String servidorSMTP = scanner.nextLine();

            System.out.print("¿Necesita negociación TLS (S/N)? ");
            boolean usarTLS = scanner.nextLine().equalsIgnoreCase("S");

            System.out.print("Introduce usuario: ");
            String usuario = scanner.nextLine();

            System.out.print("Introduce contraseña: ");
            String password = scanner.nextLine();

            System.out.print("Introduce puerto: ");
            String puerto = scanner.nextLine();

            System.out.print("Introduce correo del remitente: ");
            String remitente = scanner.nextLine();

            System.out.print("Introduce correo destinatario: ");
            String destinatario = scanner.nextLine();

            System.out.print("Introduce asunto: ");
            String asunto = scanner.nextLine();

            System.out.println("Introduce el mensaje (finaliza con *):");
            StringBuilder mensajeBuilder = new StringBuilder();
            while (true) {
                String linea = scanner.nextLine();
                if (linea.equals("*")) break;
                mensajeBuilder.append(linea).append("\n");
            }
            String mensaje = mensajeBuilder.toString();

            // Validar campos requeridos
            if (mensaje.isEmpty() || remitente.isEmpty() || destinatario.isEmpty()) {
                System.out.println("Error: El remitente, destinatario o mensaje no pueden estar vacíos.");
                return;
            }

            // Configuración del servidor SMTP
            Properties props = new Properties();
            props.put("mail.smtp.host", servidorSMTP);
            props.put("mail.smtp.port", puerto);
            props.put("mail.smtp.auth", "true");

            if (usarTLS) {
                props.put("mail.smtp.starttls.enable", "true");
            }

            // Autenticación
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usuario, password);
                }
            });

            try {
                // Crear mensaje
                Message correo = new MimeMessage(session);
                correo.setFrom(new InternetAddress(remitente));
                correo.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
                correo.setSubject(asunto);
                correo.setText(mensaje);

                // Enviar mensaje
                Transport.send(correo);
                System.out.println("Correo enviado exitosamente.");
            } catch (MessagingException e) {
                System.out.println("Error al enviar el correo: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
