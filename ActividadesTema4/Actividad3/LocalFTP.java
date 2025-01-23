package Actividad3;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LocalFTP {

    private static FTPClient ftpClient = new FTPClient();


    public static void main(String[] args) throws IOException {

        // Crear ventana principal
        JFrame frame = new JFrame("Descargar ficheros");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Panel para ingresar usuario y contraseña
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));
        JLabel userLabel = new JLabel("Introduzca su usuario:");
        JTextField userField = new JTextField("Toalla"); // Usuario predefinido
        JLabel passLabel = new JLabel("Introduzca su contraseña:");
        JPasswordField passField = new JPasswordField("usuario"); // Contraseña predefinida
        JButton connectButton = new JButton("Conectar");
        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passLabel);
        loginPanel.add(passField);
        loginPanel.add(new JLabel());
        loginPanel.add(connectButton);

        frame.add(loginPanel, BorderLayout.NORTH);

        // Lista de archivos y botones
        DefaultListModel<String> fileListModel = new DefaultListModel<>();
        JList<String> fileList = new JList<>(fileListModel);
        JScrollPane scrollPane = new JScrollPane(fileList);

        JButton downloadButton = new JButton("Descargar");
        JButton exitButton = new JButton("Salir");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(downloadButton);
        buttonPanel.add(exitButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Acción para conectarse al servidor FTP
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userField.getText();
                String password = new String(passField.getPassword());
                try {
                    ftpClient.connect("localhost", 21); // Dirección y puerto del servidor FTP
                    boolean login = ftpClient.login(user, password);
                    ftpClient.changeWorkingDirectory("/home/usuario");

                    if (login) {
                        JOptionPane.showMessageDialog(frame, "Conexión realizada.");
                        ftpClient.enterLocalPassiveMode();

                        // Listar archivos disponibles
                        fileListModel.clear();
                        for (String file : ftpClient.listNames()) {
                            fileListModel.addElement(file);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "El usuario o la contraseña no es vádido.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error al conectar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para descargar un archivo
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFile = fileList.getSelectedValue();
                if (selectedFile == null) {
                    JOptionPane.showMessageDialog(frame, "Seleccione un archivo para su descarga.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Seleccionar directorio de destino
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showSaveDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File directory = fileChooser.getSelectedFile();
                    File localFile = new File(directory, selectedFile);

                    try (FileOutputStream fos = new FileOutputStream(localFile)) {
                        boolean success = ftpClient.retrieveFile(selectedFile, fos);
                        if (success) {
                            JOptionPane.showMessageDialog(frame, "El archivo se ha descargado correctamente en: " + localFile.getAbsolutePath());
                        } else {
                            JOptionPane.showMessageDialog(frame, "No se ha podido descargar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "No se ha podido guardar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Acción para salir
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (ftpClient.isConnected()) {
                        ftpClient.logout();
                        ftpClient.disconnect();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}

