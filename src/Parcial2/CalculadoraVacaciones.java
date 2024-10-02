import javax.swing.*; // Para no hacer todas las importaciones
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

// Clase para la Ventana de Login
public class CalculadoraVacaciones extends JFrame {

    // Constructor para la ventana de Login
    public CalculadoraVacaciones() {
        // Configuración de la ventana
        setTitle("Bienvenido");
        setSize(450, 500); // Tamaño ajustado para que la imagen y el resto de elementos se vean bien
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK); // Color para Principal
        setLayout(null);
        
        // Cargar icono (favicon)
        Image icon = Toolkit.getDefaultToolkit().getImage("Parcial2\\imagen\\Paca2.png");
        // Colocar icono en el JFrame
        setIconImage(icon);
        
        // Cargar la imagen desde el paquete Parcial2.imagen
        ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource("Parcial2/imagen/Paca2.png"));
        JLabel lblImagen = new JLabel(icono);
        lblImagen.setBounds(75, 10, 300, 200); // Ajusta las coordenadas y el tamaño de la imagen
        add(lblImagen);
        
        // Campo de texto para el nombre
        JLabel lblNombre = new JLabel("Ingrese su nombre:");
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setBounds(75, 220, 200, 25);
        add(lblNombre);
        
        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(75, 250, 300, 30);
        add(txtNombre);
        
        // Botón de ingresar
        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(175, 300, 100, 30);
        add(btnIngresar);
        
        // Pie de página
        JLabel lblCopyright = new JLabel("Paca GUVI Emerson Guzman Programacion D");
        lblCopyright.setForeground(Color.WHITE);
        lblCopyright.setBounds(100, 350, 300, 25);
        add(lblCopyright);
        
        // Evento al hacer clic en el botón "Ingresar"
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el nombre ingresado
                String nombre = txtNombre.getText().trim();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese su nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Si el nombre es válido, abrir la ventana de términos y condiciones
                    VentanaTerminos terminos = new VentanaTerminos(nombre);
                    terminos.setVisible(true);
                    // Cerrar la ventana de login
                    dispose();
                }
            }
        });
        
        // Mostrar la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        new CalculadoraVacaciones();
    }
}


// Clase para la Ventana de Términos y Condiciones
class VentanaTerminos extends JFrame {
    private String nombre;

    // Constructor para la ventana de Términos
    public VentanaTerminos(String nombre) {
        this.nombre = nombre; // Guardamos el nombre
        // Configuración de la ventana
        setTitle("Licencia de uso");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Etiqueta de Términos y Condiciones
        JLabel lblTitulo = new JLabel("TÉRMINOS Y CONDICIONES");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(180, 20, 300, 30);
        add(lblTitulo);

        // Área de texto para mostrar los términos
        JTextArea txtTerminos = new JTextArea();
        txtTerminos.setText("TÉRMINOS Y CONDICIONES\n\n" +
                "A. PROHIBIDA SU VENTA O DISTRIBUCIÓN SIN AUTORIZACIÓN DEL GERENTE EMERSON.\n" +
                "B. PROHIBIDA LA ALTERACIÓN DEL CÓDIGO FUENTE Y TOMAR FOTOS.\n" +
                "PARA MÁS INFORMACIÓN SOBRE NUESTROS PRODUCTOS O SERVICIOS, POR FAVOR LLAME\n" +
                "502 42010505\n©2024 Emerson Guzman");
        txtTerminos.setEditable(false);
        txtTerminos.setBounds(50, 60, 500, 200);
        add(txtTerminos);

        // Checkbox para aceptar los términos
        JCheckBox chkAceptar = new JCheckBox("Yo " + nombre + " Acepto");
        chkAceptar.setBounds(50, 270, 250, 25);
        add(chkAceptar);

        // Botones para continuar o no aceptar
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setBounds(50, 310, 120, 30);
        add(btnContinuar);
        JButton btnNoAceptar = new JButton("No Acepto");
        btnNoAceptar.setBounds(200, 310, 120, 30);
        add(btnNoAceptar);
        

        // Deshabilitar el botón de continuar hasta que se acepte
        btnContinuar.setEnabled(false);
        chkAceptar.addActionListener(e -> btnContinuar.setEnabled(chkAceptar.isSelected()));

        // Evento al hacer clic en "Continuar"
        btnContinuar.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Gracias por aceptar los términos, " + nombre + ".");
            // Abrir la ventana principal con el nombre
            PantallaPrincipal pantallaPrincipal = new PantallaPrincipal(nombre);
            pantallaPrincipal.setBounds(0, 0, 500, 550);//Tamaño de la Pantalla Principal
            pantallaPrincipal.setVisible(true);
            pantallaPrincipal.setResizable(false);
            pantallaPrincipal.setLocationRelativeTo(null);
            // Cerrar la ventana de términos y condiciones
            dispose();
        });

        // Evento al hacer clic en "No Acepto"
    btnNoAceptar.addActionListener(e -> {
    JOptionPane.showMessageDialog(null, "No aceptaste los términos. Volviendo a la pantalla de inicio.");
    // Volver a la ventana de CalculadoraVacaciones
    new CalculadoraVacaciones();
    // Cerrar la ventana de términos y condiciones
    dispose();
    });


        // Mostrar la ventana
        setVisible(true);
    }
}




// Clase para la Pantalla Principal
class PantallaPrincipal extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu menuOpciones, menuCalcular, menuAcercaDe;
    private JMenuItem miColor, miNuevo, miSalir, miVacaciones, miDelProgramador;
    private JTextField txtNombre, txtApellidoPaterno, txtApellidoMaterno;
    private JComboBox<String> comboDepartamento, comboAntiguedad;
    private JTextArea textAreaResultado;
    private String nombreUsuario;

    public PantallaPrincipal(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario; // Guardamos el nombre del usuario
        setLayout(null);
        setTitle("Pantalla principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 0, 0));

          
        
        // Menú
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuOpciones = new JMenu("Opciones");
        menuBar.add(menuOpciones);
        menuCalcular = new JMenu("Calcular");
        menuBar.add(menuCalcular);
        menuAcercaDe = new JMenu("Acerca de");
        menuBar.add(menuAcercaDe);

        // Opciones del menú
        miColor = new JMenuItem("Cambiar Color");
        miColor.addActionListener(this);
        menuOpciones.add(miColor);
        miNuevo = new JMenuItem("Nuevo");
        miNuevo.addActionListener(this);
        menuOpciones.add(miNuevo);
        miSalir = new JMenuItem("Salir");
        miSalir.addActionListener(this);
        menuOpciones.add(miSalir);
        miVacaciones = new JMenuItem("Vacaciones");
        miVacaciones.addActionListener(this);
        menuCalcular.add(miVacaciones);
        miDelProgramador = new JMenuItem("Del Programador");
        miDelProgramador.addActionListener(this);
        menuAcercaDe.add(miDelProgramador);

        // Etiquetas y campos de texto
        JLabel lblTitulo = new JLabel("Bienvenido " + nombreUsuario);
        lblTitulo.setBounds(150, 30, 300, 30);
        lblTitulo.setFont(new Font("Andale Mono", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        add(lblTitulo);

        JLabel lblDatos = new JLabel("Datos del trabajador para el cálculo de vacaciones");
        lblDatos.setBounds(80, 70, 400, 30);
        lblDatos.setFont(new Font("Andale Mono", Font.BOLD, 14));
        lblDatos.setForeground(Color.WHITE);
        add(lblDatos);

        JLabel lblNombre = new JLabel("Nombre completo:");
        lblNombre.setBounds(20, 120, 150, 25);
        lblNombre.setFont(new Font("Andale Mono", Font.BOLD, 12));
        lblNombre.setForeground(Color.WHITE);
        add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(170, 120, 150, 25);
        add(txtNombre);

        JLabel lblApellidoPaterno = new JLabel("Apellido Paterno:");
        lblApellidoPaterno.setBounds(20, 160, 150, 25);
        lblApellidoPaterno.setFont(new Font("Andale Mono", Font.BOLD, 12));
        lblApellidoPaterno.setForeground(Color.WHITE);
        add(lblApellidoPaterno);
        txtApellidoPaterno = new JTextField();
        txtApellidoPaterno.setBounds(170, 160, 150, 25);
        add(txtApellidoPaterno);

        JLabel lblApellidoMaterno = new JLabel("Apellido Materno:");
        lblApellidoMaterno.setBounds(20, 200, 150, 25);
        lblApellidoMaterno.setFont(new Font("Andale Mono", Font.BOLD, 12));
        lblApellidoMaterno.setForeground(Color.WHITE);
        add(lblApellidoMaterno);
        txtApellidoMaterno = new JTextField();
        txtApellidoMaterno.setBounds(170, 200, 150, 25);
        add(txtApellidoMaterno);

        JLabel lblDepartamento = new JLabel("Departamento:");
        lblDepartamento.setBounds(20, 240, 150, 25);
        lblDepartamento.setFont(new Font("Andale Mono", Font.BOLD, 12));
        lblDepartamento.setForeground(Color.WHITE);
        add(lblDepartamento);
        String[] departamentos = {"Atencion al cliente", "Trabajadores de logistica", "Gerentes"};
        comboDepartamento = new JComboBox<>(departamentos);
        comboDepartamento.setBounds(170, 240, 150, 25);
        add(comboDepartamento);

        JLabel lblAntiguedad = new JLabel("Antigüedad (en años):");
        lblAntiguedad.setBounds(20, 280, 150, 25);
        lblAntiguedad.setFont(new Font("Andale Mono", Font.BOLD, 12));
        lblAntiguedad.setForeground(Color.WHITE);
        add(lblAntiguedad);
        String[] antiguedades = {"1", "2 a 6", "7 a 10+"};
        comboAntiguedad = new JComboBox<>(antiguedades);
        comboAntiguedad.setBounds(170, 280, 150, 25);
        add(comboAntiguedad);

        // Área de texto para mostrar el resultado
        textAreaResultado = new JTextArea();
        textAreaResultado.setBounds(20, 320, 350, 100);
        textAreaResultado.setEditable(false);
        add(textAreaResultado);

        // Pie de página
        JLabel lblCopyright = new JLabel("Paca GuVi Emerson Guzman Programacion D");
        lblCopyright.setForeground(Color.WHITE);
        lblCopyright.setBounds(50, 450, 300, 25);
        add(lblCopyright);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem fuente = (JMenuItem) e.getSource();
        if (fuente == miSalir) {
            System.exit(0);
        } else if (fuente == miVacaciones) {
            calcularVacaciones();
        } else if (fuente == miDelProgramador) {
            JOptionPane.showMessageDialog(this, "Hecho por Emerson Guzman");
        } else if (fuente == miColor) {
            // Opciones de color predefinidas
            Object[] colores = {"Negro", "Azul", "Verde", "Amarillo"};
            int seleccion = JOptionPane.showOptionDialog(this, "Selecciona un color", "Seleccionar color",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, colores, colores[0]);
            if (seleccion == JOptionPane.CLOSED_OPTION) return;
            Color nuevoColor = Color.BLACK; // Color de pantalla Vacacional
            switch (colores[seleccion].toString()) {
                case "Azul":
                    nuevoColor = Color.BLUE;
                    break;
                case "Verde":
                    nuevoColor = Color.GREEN;
                    break;
                case "Amarillo":
                    nuevoColor = Color.YELLOW;
            }
            getContentPane().setBackground(nuevoColor);
        } else if (fuente == miNuevo) {
            txtNombre.setText("");
            txtApellidoPaterno.setText("");
            txtApellidoMaterno.setText("");
            comboDepartamento.setSelectedIndex(0);
            comboAntiguedad.setSelectedIndex(0);
            textAreaResultado.setText("");
        }
    }

    private void calcularVacaciones() {
        String nombre = txtNombre.getText().trim();
        String apellidoPaterno = txtApellidoPaterno.getText().trim();
        String apellidoMaterno = txtApellidoMaterno.getText().trim();
        String departamento = (String) comboDepartamento.getSelectedItem();
        String antiguedad = (String) comboAntiguedad.getSelectedItem();

        if (nombre.isEmpty() || apellidoPaterno.isEmpty() || apellidoMaterno.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int antiguedadAnios = antiguedad.equals("7 a 10+") ? 7 : Integer.parseInt(antiguedad.replaceAll("[^0-9]", ""));
        int vacaciones = 0;

        switch (departamento) {
            case "Atencion al cliente":
                if (antiguedadAnios == 1) {
                    vacaciones = 6;
                } else if (antiguedadAnios >= 2 && antiguedadAnios <= 6) {
                    vacaciones = 14;
                } else if (antiguedadAnios >= 7) {
                    vacaciones = 20;
                }
                break;
            case "Trabajadores de logistica":
                if (antiguedadAnios == 1) {
                    vacaciones = 7;
                } else if (antiguedadAnios >= 2 && antiguedadAnios <= 6) {
                    vacaciones = 15;
                } else if (antiguedadAnios >= 7) {
                    vacaciones = 22;
                }
                break;
            case "Gerentes":
                if (antiguedadAnios == 1) {
                    vacaciones = 10;
                } else if (antiguedadAnios >= 2 && antiguedadAnios <= 6) {
                    vacaciones = 20;
                } else if (antiguedadAnios >= 7) {
                    vacaciones = 30;
                }
                break;
        }

        String resultado = String.format("Nombre Completo: %s %s %s\nDepartamento: %s\nAntigüedad: %s años\nDías de Vacaciones: %d días. Disfruta de tus vacaciones",
                nombre, apellidoPaterno, apellidoMaterno, departamento, antiguedad, vacaciones);
        textAreaResultado.setText(resultado);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PantallaPrincipal ventana = new PantallaPrincipal("Usuario");
            ventana.setBounds(520, 280, 550, 525); // Tamaño de pantalla
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}


