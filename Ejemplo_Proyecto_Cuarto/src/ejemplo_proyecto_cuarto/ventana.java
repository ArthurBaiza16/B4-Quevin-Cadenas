package ejemplo_proyecto_cuarto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ventana extends JFrame {

    JPanel panelInicioSesion, panelNuevoUsuario,panelClientes;
    JTextField txtUsuario;
    JPasswordField txtContra;
    Usuario misUsuarios[] = new Usuario[10];

    public ventana() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        misUsuarios[0] = new Usuario("admin", "123");
        misUsuarios[1] = new Usuario("otro", "321");

    }

    public void iniciarComponentes() {
        panelInicioSesion = new JPanel();
        panelInicioSesion.setLayout(null);
        this.getContentPane().add(panelInicioSesion);
        componentesInicioSecion();

        panelNuevoUsuario = new JPanel();
        panelNuevoUsuario.setLayout(null);
        this.getContentPane().add(panelNuevoUsuario);
        panelNuevoUsuario.setVisible(false);
        
        panelClientes = new JPanel();
        panelClientes.setLayout(null);
        this.getContentPane().add(panelClientes);
        panelClientes.setVisible(false);
    }

    public void componentesInicioSecion() {
        this.setTitle("Inicio de Sesion");
        JLabel lblUsuario = new JLabel("Ingrese su Usuario");
        lblUsuario.setBounds(50, 15, 150, 15);
        panelInicioSesion.add(lblUsuario);

        JLabel lblContra = new JLabel("Ingrese su Password");
        lblContra.setBounds(50, 50, 150, 15);
        panelInicioSesion.add(lblContra);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(210, 15, 150, 20);
        panelInicioSesion.add(txtUsuario);

        txtContra = new JPasswordField();
        txtContra.setBounds(210, 50, 150, 20);
        panelInicioSesion.add(txtContra);

        JButton btnIniciar = new JButton("Ingresar");
        btnIniciar.setBounds(100, 150, 100, 30);
        panelInicioSesion.add(btnIniciar);

        ActionListener iniciarSesion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Usuario = txtUsuario.getText();
                String Contra = txtContra.getText();
                buscarUsuario(Usuario, Contra);
            }
        };
        btnIniciar.addActionListener(iniciarSesion);

        JButton btnNuevoUsuario = new JButton("Registrar");
        btnNuevoUsuario.setBounds(250, 150, 100, 30);
        panelInicioSesion.add(btnNuevoUsuario);
        ActionListener nuevoUsuario = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesNuevoUsuario();
                panelInicioSesion.setVisible(false);
                panelNuevoUsuario.setVisible(true);
            }
        };
        btnNuevoUsuario.addActionListener(nuevoUsuario);
        panelInicioSesion.repaint();
    }

    public void buscarUsuario(String usuario, String contra) {
        boolean encontrado = false;
        String nombre = " ";
        for (int i = 0; i <= 9; i++) {
            if (misUsuarios[i] != null) {
                if (misUsuarios[i].getNombre().equals(usuario) && misUsuarios[i].getContra().equals(contra)) {
                    encontrado = true;
                    nombre = misUsuarios[i].getNombre();
                    break;
                }
            }
        }

        if (encontrado) {
            JOptionPane.showMessageDialog(null, "Bienvenido " + nombre);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario Incorrecto");
        }
    }

    public void componentesNuevoUsuario() {
        this.setTitle("Registrar");
        JLabel nuevoNombre = new JLabel("Ingrese nombre de Usuario: ");
        nuevoNombre.setBounds(80, 25, 170, 20);
        panelNuevoUsuario.add(nuevoNombre);

        JLabel nuevaContra = new JLabel("Ingrese Password: ");
        nuevaContra.setBounds(80, 75, 170, 20);
        panelNuevoUsuario.add(nuevaContra);

        JTextField txtNombreNuevo = new JTextField();
        txtNombreNuevo.setBounds(250, 25, 150, 20);
        panelNuevoUsuario.add(txtNombreNuevo);

        JPasswordField txtContraNueva = new JPasswordField();
        txtContraNueva.setBounds(250, 75, 150, 20);
        panelNuevoUsuario.add(txtContraNueva);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(150, 180, 100, 30);
        panelNuevoUsuario.add(btnGuardar);
        ActionListener almacenar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombreNuevo.getText();
                String contra = txtContraNueva.getText();
                guardarUsuario(nombre, contra);

                if (guardarUsuario(nombre, contra)) {
                    txtNombreNuevo.setText("");
                    txtContraNueva.setText("");
                }

            }
        };

        btnGuardar.addActionListener(almacenar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(300, 180, 100, 30);
        panelNuevoUsuario.add(btnVolver);
        ActionListener volver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesInicioSecion();
                panelInicioSesion.setVisible(true);
                panelNuevoUsuario.setVisible(false);
            }
        };

        btnVolver.addActionListener(volver);

    }

    public boolean guardarUsuario(String nombre, String contra) {
        boolean guardado = false;
        if (nombre.equals(" ") || contra.equals(" ")) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        } else {
            if (comprobarDuplicadosUsuario(nombre)) {
                JOptionPane.showMessageDialog(null, "El nombre de Usuario ya esta en Uso");
            } else {
                boolean vacio = false;
                int posicion = -1;
                for (int i = 0; i <= 9; i++) {
                    if (misUsuarios[i] == null) {
                        vacio = true;
                        posicion = i;
                        break;

                    }
                }
                if (vacio) {
                    misUsuarios[posicion] = new Usuario(nombre, contra);
                    JOptionPane.showMessageDialog(null, "Usuario Guardado Exitosamente");
                    guardado = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Ya no se pueden Guardar mas Usuarios");
                }
            }
            

        }
        return guardado;
    }

    public boolean comprobarDuplicadosUsuario(String nombre) {
        boolean duplicado = false;
        for (int i = 0; i <= 9; i++) {
            if (misUsuarios[i] != null) {
                if (misUsuarios[i].getNombre().equals(nombre)) {
                    duplicado = true;
                    break;
                }

            }
        }
        return duplicado;
    }
    
    //------------------------------------> Componentes para mostrar los Clientes
    
    
}
