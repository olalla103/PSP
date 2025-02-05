package org.example.actividad4_5;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class ClientePOP3 extends JFrame implements ActionListener {

    private JTextField tfServidor, tfPuerto, tfUsuario, tfPassword;
    private JCheckBox cbTLS;
    private JButton btnConectar, btnRecuperar, btnDesconectar;
    private JList<String> listaMensajes;
    private JTextArea taContenido;
    private DefaultListModel<String> modeloLista;

    private Store store;
    private Folder folder;

    public ClientePOP3() {
        setTitle("Cliente POP3");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para los datos de conexión
        JPanel panelConexion = new JPanel(new GridLayout(5, 2, 5, 5));
        panelConexion.setBorder(BorderFactory.createTitledBorder("Datos de conexión"));

        panelConexion.add(new JLabel("Servidor POP:"));
        tfServidor = new JTextField("localhost");
        panelConexion.add(tfServidor);

        panelConexion.add(new JLabel("Puerto:"));
        tfPuerto = new JTextField("110");
        panelConexion.add(tfPuerto);

        panelConexion.add(new JLabel("Usuario:"));
        tfUsuario = new JTextField();
        panelConexion.add(tfUsuario);

        panelConexion.add(new JLabel("Contraseña:"));
        tfPassword = new JPasswordField();
        panelConexion.add(tfPassword);

        panelConexion.add(new JLabel("Modo seguro (TLS):"));
        cbTLS = new JCheckBox();
        panelConexion.add(cbTLS);

        add(panelConexion, BorderLayout.NORTH);

        // Panel central para la lista de mensajes
        modeloLista = new DefaultListModel<>();
        listaMensajes = new JList<>(modeloLista);
        listaMensajes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaMensajes.addListSelectionListener(e -> mostrarContenidoMensaje());
        JScrollPane scrollLista = new JScrollPane(listaMensajes);
        scrollLista.setBorder(BorderFactory.createTitledBorder("Mensajes"));

        taContenido = new JTextArea();
        taContenido.setEditable(false);
        JScrollPane scrollContenido = new JScrollPane(taContenido);
        scrollContenido.setBorder(BorderFactory.createTitledBorder("Contenido del mensaje"));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollLista, scrollContenido);
        add(splitPane, BorderLayout.CENTER);

        // Panel inferior para los botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnConectar = new JButton("Conectar");
        btnConectar.addActionListener(this);
        btnConectar.setEnabled(false);

        btnRecuperar = new JButton("Recuperar mensajes");
        btnRecuperar.addActionListener(this);
        btnRecuperar.setEnabled(false);

        btnDesconectar = new JButton("Desconectar");
        btnDesconectar.addActionListener(this);
        btnDesconectar.setEnabled(false);

        panelBotones.add(btnConectar);
        panelBotones.add(btnRecuperar);
        panelBotones.add(btnDesconectar);

        add(panelBotones, BorderLayout.SOUTH);

        // Activar botón conectar cuando todos los datos están completos
        tfServidor.getDocument().addDocumentListener((SimpleDocumentListener) e -> validarDatosConexion());
        tfPuerto.getDocument().addDocumentListener((SimpleDocumentListener) e -> validarDatosConexion());
        tfUsuario.getDocument().addDocumentListener((SimpleDocumentListener) e -> validarDatosConexion());
        tfPassword.getDocument().addDocumentListener((SimpleDocumentListener) e -> validarDatosConexion());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConectar) {
            conectarServidor();
        } else if (e.getSource() == btnRecuperar) {
            recuperarMensajes();
        } else if (e.getSource() == btnDesconectar) {
            desconectarServidor();
        }
    }

    private void validarDatosConexion() {
        boolean habilitar = !tfServidor.getText().isEmpty() && !tfPuerto.getText().isEmpty()
                && !tfUsuario.getText().isEmpty() && !tfPassword.getText().isEmpty();
        btnConectar.setEnabled(habilitar);
    }

    private void conectarServidor() {
        try {
            // Configurar propiedades del servidor POP3
            Properties props = new Properties();
            props.put("mail.pop3.host", tfServidor.getText());
            props.put("mail.pop3.port", tfPuerto.getText());
            if (cbTLS.isSelected()) {
                props.put("mail.pop3.starttls.enable", "true");
            }

            Session session = Session.getInstance(props, null);
            store = session.getStore("pop3");
            store.connect(tfUsuario.getText(), tfPassword.getText());

            btnConectar.setEnabled(false);
            btnRecuperar.setEnabled(true);
            btnDesconectar.setEnabled(true);

            JOptionPane.showMessageDialog(this, "Conectado al servidor POP3");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al conectar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void recuperarMensajes() {
        try {
            folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            Message[] mensajes = folder.getMessages();
            modeloLista.clear();
            for (int i = 0; i < mensajes.length; i++) {
                modeloLista.addElement("Mensaje " + (i + 1) + ": " + mensajes[i].getSubject());
            }

            JOptionPane.showMessageDialog(this, "Mensajes recuperados: " + mensajes.length);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar mensajes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarContenidoMensaje() {
        int index = listaMensajes.getSelectedIndex();
        if (index != -1 && folder != null) {
            try {
                Message mensaje = folder.getMessage(index + 1);
                taContenido.setText(mensaje.getContent().toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al mostrar contenido: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void desconectarServidor() {
        try {
            if (folder != null && folder.isOpen()) {
                folder.close(false);
            }
            if (store != null) {
                store.close();
            }

            modeloLista.clear();
            taContenido.setText("");

            btnConectar.setEnabled(true);
            btnRecuperar.setEnabled(false);
            btnDesconectar.setEnabled(false);

            JOptionPane.showMessageDialog(this, "Desconectado del servidor POP3");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al desconectar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClientePOP3 cliente = new ClientePOP3();
            cliente.setVisible(true);
        });
    }
}

// Interfaz para simplificar DocumentListener
@FunctionalInterface
interface SimpleDocumentListener extends javax.swing.event.DocumentListener {
    void update(javax.swing.event.DocumentEvent e);

    @Override
    default void insertUpdate(javax.swing.event.DocumentEvent e) {
        update(e);
    }

    @Override
    default void removeUpdate(javax.swing.event.DocumentEvent e) {
        update(e);
    }

    @Override
    default void changedUpdate(javax.swing.event.DocumentEvent e) {
        update(e);
    }
}
