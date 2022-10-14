package VISTA;

import CONTROLADOR.Conexion;
import CONTROLADOR.HistorialController;
import MODELO.Historial;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class VentanaCalculadoras extends javax.swing.JFrame {

    Conexion estado;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    HistorialController historialC = new HistorialController();
    double tiempo;
    double MET=0;
    public static String datos[] = new String[7];

    public VentanaCalculadoras() {
        initComponents();
//        SetImageLabel(jLabel33, "src/IMG/IMC_2.png");
        this.setLocationRelativeTo(this);
        setTitle("CALCULADORA PESO IDEAL");
        setResizable(false);
        cboxActividades.removeAllItems();
        llenarComboxActividadF();
        mostrarUsuario();
        txtFecha.setText(fechaActual());
        SetImageLabel(jLabel6, "src/IMG/nutritionist.png");
        SetImageLabel(labelFoto, "src/IMG/nutritionist.png");
        SetImageLabel(labelFoto1, "src/IMG/nutritionist.png");
//        jTable.getColumnModel().getColumn(0).setPreferredWidth(10);
////        jTable.getColumnModel().getColumn(1).setPreferredWidth(10);
////        jTable.getColumnModel().getColumn(2).setPreferredWidth(10);
////        jTable.getColumnModel().getColumn(3).setPreferredWidth(7);
////        jTable.getColumnModel().getColumn(4).setPreferredWidth(3);
////        jTable.getColumnModel().getColumn(5).setPreferredWidth(3);
////        jTable.getColumnModel().getColumn(6).setPreferredWidth(3);
////        jTable.getColumnModel().getColumn(7).setPreferredWidth(3);
        
//        llenarComboxQuemaC();
//        cboxQuemaC.removeAllItems();
    }

    public void calcularIMC() {
        String IMC;
        double estatura;
        double peso;
        double result = 0;
        String Observacion;
        int edad;
        if ((checkMasculino.getState() == false && checkFemenino.getState() == false) || txtEstatura.getText().equals("")
                || txtPeso.getText().equals("") || txtEdad.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "ERROR INGRESE O SELECCIONE CORRECTAMENTE EL DATO", "MENSAJE", 2);
        } else {
            estatura = Double.parseDouble(labelestaturaIMC.getText());
            peso = Double.parseDouble(txtPeso.getText());
            edad = Integer.parseInt(labeledadIMC.getText());
//            result = (peso / (estatura*estatura));
            result = Math.round((peso / (estatura * estatura) * 10.0)) / 10.0;
            IMC = String.valueOf(result);
            resultIMC.setText(IMC);
        }
        if (result <= 18.5) {
            Observacion = "ESTAS EN PESO BAJO";
        } else if (result <= 24.9) {
            Observacion = "ESTAS EN PESO NORMAL";
        } else if (result <= 29.9) {
            Observacion = "ESTAS EN SOBREPESO";
        } else if (result <= 39.9) {
            Observacion = "ESTAS EN OBESIDAD";
        } else {
            Observacion = "ESTAS EN OBESIDAD MORBIDA";
        }
        txtObservacionIMC.setText(Observacion);
    }

    public void calcularConsumoCaloriasDiarias() {
        int edad = Integer.parseInt(labeledadQC.getText());
        double resultado;
        double CAF;
        double altura = Double.parseDouble(labelalturaQC.getText());
        double peso = Double.parseDouble(labelpesoQC.getText());
        String actividad = cboxActividades.getSelectedItem().toString();
        if (labelgeneroQC.getText().equalsIgnoreCase("MASCULINO")) {
            if (edad >= 3 && edad <= 18) {
                CAF = switch (actividad.toUpperCase()) {
                    case "SEDENTARIO" ->
                        1;
                    case "BAJA" ->
                        1.13;
                    case "ACTIVO" ->
                        1.26;
                    default ->
                        1.42;
                };
                resultado = 88.5 - (61.9 * edad) + CAF * ((26.7 * peso) + (903 * altura));
            } else {
                CAF = switch (actividad.toUpperCase()) {
                    case "SEDENTARIO" ->
                        1;
                    case "BAJA" ->
                        1.11;
                    case "ACTIVO" ->
                        1.25;
                    default ->
                        1.48;
                };
                resultado = 662 - (9.53 * edad) + CAF * ((15.9 * peso) + (540 * altura));
            }
        } else {
            if (edad >= 3 && edad <= 18) {
                CAF = switch (actividad.toUpperCase()) {
                    case "SEDENTARIO" ->
                        1;
                    case "BAJA" ->
                        1.16;
                    case "ACTIVO" ->
                        1.31;
                    default ->
                        1.56;
                };
                resultado = 135.3 - (30.8 * edad) + CAF * ((10 * peso) + (934 * altura));
            } else {
                CAF = switch (actividad.toUpperCase()) {
                    case "SEDENTARIO" ->
                        1;
                    case "BAJA" ->
                        1.12;
                    case "ACTIVO" ->
                        1.27;
                    default ->
                        1.45;
                };
                resultado = 354 - (6.91 * edad) + CAF * ((9.36 * peso) + (726 * altura));
            }
        }
        resultado = Math.round(resultado * 100.0) / 100.0;
        resultIMC1.setText(String.valueOf(resultado));
    }

    public double calcularQuemaCalorias() {
        String deporte = cboxQuemaC.getSelectedItem().toString().toUpperCase();
        double resultadoQC;
        double peso = Double.parseDouble(labelpesoCQ.getText());
        double altura = Double.parseDouble(labelalturaCQ.getText());
        int edad = Integer.parseInt(labeledadCQ.getText());
        String genero = labelgeneroCQ.getText();
        switch (deporte) {
            case "CORRER" -> MET=7;
            case "CAMINATA" -> MET=2.5;
            case "NATACION" -> MET=9;
            case "CICLISMO" -> MET=8;
            default -> {
                    if (genero.equals("Masculino")) {
                            resultadoQC = 1821;
                            } else {
                                    resultadoQC = 1413;
                                    }
            }  
        }
        resultadoQC=(0.0175*peso*MET)*60;
        resultadoQC=Math.round(resultadoQC * 100.0) / 100.0;
        resultQuemaC.setText(String.valueOf(resultadoQC));
        return resultadoQC;
    }

    public void llenarDatos() {
        String genero;
        if (checkMasculino.getState() == true) {
            genero = "Masculino";
        } else {
            genero = "Femenino";
        }
        datos[2] = fechaActual();
        datos[3] = txtPeso.getText();
        datos[4] = genero;
        datos[5] = txtEdad.getText();
        datos[6] = txtEstatura.getText();
        lblnombreIMC.setText(datos[0]);
        lblapellidosIMC.setText(datos[1]);
        labelpesoIMC.setText(datos[3]);
        labelgeneroIMC.setText(datos[4]);
        labeledadIMC.setText(datos[5]);
        labelestaturaIMC.setText(datos[6]);
        labelnombreQC.setText(datos[0]);
        labelapellidoQC.setText(datos[1]);
        labelgeneroQC.setText(datos[4]);
        labeledadQC.setText(datos[5]);
        labelpesoQC.setText(datos[3]);
        labelnombreCQ.setText(datos[0]);
        labelapellidosCQ.setText(datos[1]);
        labelgeneroCQ.setText(datos[4]);
        labeledadCQ.setText(datos[5]);
        labelpesoCQ.setText(datos[3]);
        labelalturaQC.setText(txtEstatura.getText());
        labelalturaCQ.setText(txtEstatura.getText());
    }

    public void Listar() {
        String columnas[] = {"N°", "NOMBRE", "APELLIDOS", "FECHA",
            "PESO", "IMC", "CONSUMO CALORIAS", "QUEMA CALORIAS"};
        DefaultTableModel modelo = new DefaultTableModel();
        for (String columna : columnas) {
            modelo.addColumn(columna);
        }

        List lista = historialC.listar(datos[1]);
        Historial historial;
        Object obj[] = new Object[8];
        for (int i = 0; i < lista.size(); i++) {
            historial = (Historial) lista.get(i);
            obj[0] = historial.getHistorialId();
            obj[1] = historial.getNombre();
            obj[2] = historial.getApellidos();
            obj[3] = historial.getFecha();
            obj[4] = historial.getPeso();
            obj[5] = historial.getIMC();
            obj[6] = historial.getConsumo_calorias();
            obj[7] = historial.getQuema_calorias();
            modelo.addRow(obj);
        }
        jTable.setModel(modelo);
    }

    public void registrar() {
        Object obj[] = new Object[7];
        if (resultIMC.getText().length() == 0
                || resultIMC1.getText().length() == 0
                || resultQuemaC.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "CALCULE TODAS LAS CALCULADORAS", "MENSAJE", 2);
        } else {
            obj[0] = datos[0];
            obj[1] = datos[1];
            obj[2] = fechaActual();
            obj[3] = txtPeso.getText();
            obj[4] = resultIMC.getText();
            obj[5] = resultIMC1.getText();
            obj[6] = resultQuemaC.getText();
            historialC.registrar(obj);
            JOptionPane.showMessageDialog(null, "SE REGISTRO CORRECTAMENTE", "MENSAJE", 2);
            Listar();
        }
    }

    public void mostrarUsuario() {
        TEXTUSUARIO.setText(datos[0]);
    }
    
    public void mostrarPDF(){
        try {
            File file = new File("C:\\Users\\WINDOWS\\Documents\\NetBeansProjects\\CALCULADORA\\src\\IMG\\SageSalud.pdf");
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                   Desktop.getDesktop().open(file);
                }
            }
        } catch (Exception e) {
        }
    }
    public void llenarComboxActividadF() {
        String Actividad[] = {"Eliga opcion.....","SEDENTARIO", "SEDENTARIO", "BAJA",
            "ACTIVO", "MUY ACTIVO"};
        for (String actividades : Actividad) {
            cboxActividades.addItem(actividades);
        }
    }

    public void llenarComboxQuemaC() {
        String Actividad[] = {"Eliga opcion.....", "CORRER", "CAMINATA",
            "NATACION", "CICLISMO","SEDENTARIO"};
        for (String actividades : Actividad) {
            cboxQuemaC.addItem(actividades);
        }
    }

    public static String fechaActual() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatoFecha.format(fecha);
    }

    private void SetImageLabel(JLabel labelName, String root) {
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
        labelName.setIcon(icon);
        this.repaint();
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        materialTabbed1 = new VISTA.MaterialTabbed();
        USUARIODATOS = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        checkMasculino = new java.awt.Checkbox();
        checkFemenino = new java.awt.Checkbox();
        jLabel14 = new javax.swing.JLabel();
        btnGrabarDatosInicio = new javax.swing.JButton();
        txtPeso = new VISTA.TextField();
        txtEstatura = new VISTA.TextField();
        txtEdad = new VISTA.TextField();
        lblnombre = new javax.swing.JLabel();
        TEXTUSUARIO = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        labelFoto = new javax.swing.JLabel();
        IMC = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        resultIMC = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtObservacionIMC = new javax.swing.JTextField();
        lblnombreIMC = new javax.swing.JTextField();
        labeledadIMC = new javax.swing.JTextField();
        labelgeneroIMC = new javax.swing.JTextField();
        labelestaturaIMC = new javax.swing.JTextField();
        btnGrabarDatosInicio1 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblapellidosIMC = new javax.swing.JTextField();
        labelFoto1 = new javax.swing.JLabel();
        labelpesoIMC = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        CALORIAS = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        labelnombreQC = new javax.swing.JTextField();
        labeledadQC = new javax.swing.JTextField();
        labelgeneroQC = new javax.swing.JTextField();
        labelpesoQC = new javax.swing.JTextField();
        cboxActividades = new javax.swing.JComboBox<>();
        btnGrabarDatosInicio2 = new javax.swing.JButton();
        resultIMC1 = new javax.swing.JTextField();
        labelapellidoQC = new javax.swing.JTextField();
        labelalturaQC = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        PERDIDAC = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        labelnombreCQ = new javax.swing.JTextField();
        labeledadCQ = new javax.swing.JTextField();
        labelpesoCQ = new javax.swing.JTextField();
        labelgeneroCQ = new javax.swing.JTextField();
        cboxQuemaC = new javax.swing.JComboBox<>();
        btnGrabarDatosInicio3 = new javax.swing.JButton();
        resultQuemaC = new javax.swing.JTextField();
        labelapellidosCQ = new javax.swing.JTextField();
        labelalturaCQ = new javax.swing.JTextField();
        RMETs = new javax.swing.JTextField();
        RGastoE = new javax.swing.JTextField();
        HISTORIAL = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        btnGrabarDatosInicio4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        materialTabbed1.setBackground(new java.awt.Color(0, 153, 204));
        materialTabbed1.setForeground(new java.awt.Color(0, 0, 0));
        materialTabbed1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel6.setBackground(new java.awt.Color(97, 211, 211));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ELIGE TU GENERO");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel5.setOpaque(true);

        checkMasculino.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        checkMasculino.setForeground(new java.awt.Color(255, 255, 255));
        checkMasculino.setLabel("Hombre");

        checkFemenino.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        checkFemenino.setForeground(new java.awt.Color(255, 255, 255));
        checkFemenino.setLabel("Mujer");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("m");

        btnGrabarDatosInicio.setBackground(new java.awt.Color(0, 0, 0));
        btnGrabarDatosInicio.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnGrabarDatosInicio.setForeground(new java.awt.Color(102, 255, 51));
        btnGrabarDatosInicio.setText("INSERTAR DATOS");
        btnGrabarDatosInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGrabarDatosInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarDatosInicioActionPerformed(evt);
            }
        });

        txtPeso.setBackground(new java.awt.Color(255, 255, 255));
        txtPeso.setForeground(new java.awt.Color(0, 0, 0));
        txtPeso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPeso.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtPeso.setLabelText("INGRESE PESO");

        txtEstatura.setBackground(new java.awt.Color(255, 255, 255));
        txtEstatura.setForeground(new java.awt.Color(0, 0, 0));
        txtEstatura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEstatura.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtEstatura.setLabelText("INGRESE ESTATURA");

        txtEdad.setBackground(new java.awt.Color(255, 255, 255));
        txtEdad.setForeground(new java.awt.Color(0, 0, 0));
        txtEdad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEdad.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtEdad.setLabelText("INGRESE EDAD");

        lblnombre.setForeground(new java.awt.Color(255, 255, 255));

        TEXTUSUARIO.setEditable(false);
        TEXTUSUARIO.setBackground(new java.awt.Color(97, 211, 211));
        TEXTUSUARIO.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        TEXTUSUARIO.setForeground(new java.awt.Color(255, 255, 255));
        TEXTUSUARIO.setBorder(null);

        txtFecha.setEditable(false);
        txtFecha.setBackground(new java.awt.Color(97, 211, 211));
        txtFecha.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(255, 255, 255));
        txtFecha.setBorder(null);

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("BIENVENIDO");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("FECHA");

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("años.");

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Kg.");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(checkMasculino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(checkFemenino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtEdad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtPeso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEstatura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(76, 76, 76)
                .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TEXTUSUARIO, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(lblnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(198, 198, 198))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(btnGrabarDatosInicio)
                        .addGap(189, 189, 189))))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(76, 76, 76)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(363, Short.MAX_VALUE)))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(194, 194, 194)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(397, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblnombre)
                .addGap(24, 24, 24)
                .addComponent(TEXTUSUARIO, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel5)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkMasculino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkFemenino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEstatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addComponent(btnGrabarDatosInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150))))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addGap(494, 494, 494)))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(114, 114, 114)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(451, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout USUARIODATOSLayout = new javax.swing.GroupLayout(USUARIODATOS);
        USUARIODATOS.setLayout(USUARIODATOSLayout);
        USUARIODATOSLayout.setHorizontalGroup(
            USUARIODATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        USUARIODATOSLayout.setVerticalGroup(
            USUARIODATOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(USUARIODATOSLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        materialTabbed1.addTab("", new javax.swing.ImageIcon(getClass().getResource("/IMG/USER-removebg-preview.png")), USUARIODATOS); // NOI18N

        jPanel1.setBackground(new java.awt.Color(97, 211, 211));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CALCULAR EL IMC");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("IMC:");

        resultIMC.setBackground(new java.awt.Color(97, 211, 211));
        resultIMC.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        resultIMC.setForeground(new java.awt.Color(0, 0, 0));
        resultIMC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        resultIMC.setOpaque(false);

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/IMC_2.png"))); // NOI18N
        jLabel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel34.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("NIVEL DE PESO:");

        txtObservacionIMC.setBackground(new java.awt.Color(97, 211, 211));
        txtObservacionIMC.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtObservacionIMC.setForeground(new java.awt.Color(0, 0, 0));
        txtObservacionIMC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtObservacionIMC.setOpaque(false);

        lblnombreIMC.setEditable(false);
        lblnombreIMC.setBackground(new java.awt.Color(255, 255, 255));
        lblnombreIMC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblnombreIMC.setForeground(new java.awt.Color(0, 0, 0));
        lblnombreIMC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblnombreIMC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "NOMBRE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        labeledadIMC.setEditable(false);
        labeledadIMC.setBackground(new java.awt.Color(255, 255, 255));
        labeledadIMC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labeledadIMC.setForeground(new java.awt.Color(0, 0, 0));
        labeledadIMC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        labeledadIMC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "EDAD", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        labelgeneroIMC.setEditable(false);
        labelgeneroIMC.setBackground(new java.awt.Color(255, 255, 255));
        labelgeneroIMC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelgeneroIMC.setForeground(new java.awt.Color(0, 0, 0));
        labelgeneroIMC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        labelgeneroIMC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "GENERO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        labelestaturaIMC.setEditable(false);
        labelestaturaIMC.setBackground(new java.awt.Color(255, 255, 255));
        labelestaturaIMC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelestaturaIMC.setForeground(new java.awt.Color(0, 0, 0));
        labelestaturaIMC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        labelestaturaIMC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "ESTATURA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        btnGrabarDatosInicio1.setBackground(new java.awt.Color(0, 0, 0));
        btnGrabarDatosInicio1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnGrabarDatosInicio1.setForeground(new java.awt.Color(153, 255, 51));
        btnGrabarDatosInicio1.setText("CALCULAR");
        btnGrabarDatosInicio1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGrabarDatosInicio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarDatosInicio1ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("m");

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("años.");

        lblapellidosIMC.setEditable(false);
        lblapellidosIMC.setBackground(new java.awt.Color(255, 255, 255));
        lblapellidosIMC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblapellidosIMC.setForeground(new java.awt.Color(0, 0, 0));
        lblapellidosIMC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblapellidosIMC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "APELLIDOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        labelpesoIMC.setEditable(false);
        labelpesoIMC.setBackground(new java.awt.Color(255, 255, 255));
        labelpesoIMC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelpesoIMC.setForeground(new java.awt.Color(0, 0, 0));
        labelpesoIMC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        labelpesoIMC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "PESO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Kg");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelgeneroIMC, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(labelestaturaIMC, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel22)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(labelpesoIMC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23)
                                .addGap(30, 30, 30)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(resultIMC, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtObservacionIMC, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                        .addComponent(labelFoto1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblapellidosIMC, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(lblnombreIMC)
                            .addComponent(labeledadIMC))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(btnGrabarDatosInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelFoto1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel2)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblnombreIMC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblapellidosIMC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labeledadIMC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelpesoIMC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelgeneroIMC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(labelestaturaIMC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtObservacionIMC, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resultIMC, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)))
                        .addComponent(btnGrabarDatosInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(131, 131, 131))
        );

        javax.swing.GroupLayout IMCLayout = new javax.swing.GroupLayout(IMC);
        IMC.setLayout(IMCLayout);
        IMCLayout.setHorizontalGroup(
            IMCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IMCLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        IMCLayout.setVerticalGroup(
            IMCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IMCLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("", new javax.swing.ImageIcon(getClass().getResource("/IMG/calcular_el_imc-removebg-preview.png")), IMC); // NOI18N

        CALORIAS.setName(""); // NOI18N

        jPanel2.setBackground(new java.awt.Color(97, 211, 211));
        jPanel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CALCULADORA DE CONSUMO DE CALORIAS");

        jLabel15.setBackground(new java.awt.Color(97, 211, 211));
        jLabel15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("¿CUAL ES TU ACTIVIDAD FISICA?");
        jLabel15.setOpaque(true);

        jLabel37.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("CALORIAS POR SONSUMIR AL DIA:");

        labelnombreQC.setEditable(false);
        labelnombreQC.setBackground(new java.awt.Color(255, 255, 255));
        labelnombreQC.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        labelnombreQC.setForeground(new java.awt.Color(0, 0, 0));
        labelnombreQC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        labelnombreQC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "NOMBRE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        labeledadQC.setEditable(false);
        labeledadQC.setBackground(new java.awt.Color(255, 255, 255));
        labeledadQC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labeledadQC.setForeground(new java.awt.Color(0, 0, 0));
        labeledadQC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        labeledadQC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "EDAD", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        labelgeneroQC.setEditable(false);
        labelgeneroQC.setBackground(new java.awt.Color(255, 255, 255));
        labelgeneroQC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelgeneroQC.setForeground(new java.awt.Color(0, 0, 0));
        labelgeneroQC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        labelgeneroQC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "GENERO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        labelpesoQC.setEditable(false);
        labelpesoQC.setBackground(new java.awt.Color(255, 255, 255));
        labelpesoQC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelpesoQC.setForeground(new java.awt.Color(0, 0, 0));
        labelpesoQC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        labelpesoQC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "PESO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        cboxActividades.setBackground(new java.awt.Color(255, 255, 255));
        cboxActividades.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cboxActividades.setForeground(new java.awt.Color(0, 0, 0));
        cboxActividades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnGrabarDatosInicio2.setBackground(new java.awt.Color(0, 0, 0));
        btnGrabarDatosInicio2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnGrabarDatosInicio2.setForeground(new java.awt.Color(153, 255, 51));
        btnGrabarDatosInicio2.setText("CALCULAR");
        btnGrabarDatosInicio2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGrabarDatosInicio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarDatosInicio2ActionPerformed(evt);
            }
        });

        resultIMC1.setBackground(new java.awt.Color(97, 211, 211));
        resultIMC1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        resultIMC1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        resultIMC1.setOpaque(false);

        labelapellidoQC.setEditable(false);
        labelapellidoQC.setBackground(new java.awt.Color(255, 255, 255));
        labelapellidoQC.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        labelapellidoQC.setForeground(new java.awt.Color(0, 0, 0));
        labelapellidoQC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        labelapellidoQC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "APELLIDOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        labelalturaQC.setEditable(false);
        labelalturaQC.setBackground(new java.awt.Color(255, 255, 255));
        labelalturaQC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelalturaQC.setForeground(new java.awt.Color(0, 0, 0));
        labelalturaQC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        labelalturaQC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "ESTATURA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxActividades, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(labelapellidoQC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                .addComponent(labelnombreQC, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelpesoQC)
                                .addComponent(labelgeneroQC, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(155, 155, 155)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(labeledadQC))
                            .addComponent(labelalturaQC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(resultIMC1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btnGrabarDatosInicio2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(labelnombreQC, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(labelapellidoQC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(cboxActividades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labelgeneroQC, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(labelpesoQC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100))
                            .addComponent(labeledadQC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(labelalturaQC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resultIMC1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(btnGrabarDatosInicio2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(10, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout CALORIASLayout = new javax.swing.GroupLayout(CALORIAS);
        CALORIAS.setLayout(CALORIASLayout);
        CALORIASLayout.setHorizontalGroup(
            CALORIASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CALORIASLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        CALORIASLayout.setVerticalGroup(
            CALORIASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CALORIASLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("", new javax.swing.ImageIcon(getClass().getResource("/IMG/consumo_de_calorias-removebg-preview.png")), CALORIAS); // NOI18N

        jPanel3.setBackground(new java.awt.Color(97, 211, 211));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CALCULAR CALORIAS QUEMADAS");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("RESULTADO:");

        jLabel30.setBackground(new java.awt.Color(97, 211, 211));
        jLabel30.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("¿CUAL ES TU ACTIVIDAD FISICA?");
        jLabel30.setOpaque(true);

        labelnombreCQ.setEditable(false);
        labelnombreCQ.setBackground(new java.awt.Color(255, 255, 255));
        labelnombreCQ.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        labelnombreCQ.setForeground(new java.awt.Color(0, 0, 0));
        labelnombreCQ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        labelnombreCQ.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "NOMBRE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        labeledadCQ.setEditable(false);
        labeledadCQ.setBackground(new java.awt.Color(255, 255, 255));
        labeledadCQ.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labeledadCQ.setForeground(new java.awt.Color(0, 0, 0));
        labeledadCQ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        labeledadCQ.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "EDAD", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        labelpesoCQ.setEditable(false);
        labelpesoCQ.setBackground(new java.awt.Color(255, 255, 255));
        labelpesoCQ.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelpesoCQ.setForeground(new java.awt.Color(0, 0, 0));
        labelpesoCQ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        labelpesoCQ.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "PESO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        labelgeneroCQ.setEditable(false);
        labelgeneroCQ.setBackground(new java.awt.Color(255, 255, 255));
        labelgeneroCQ.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelgeneroCQ.setForeground(new java.awt.Color(0, 0, 0));
        labelgeneroCQ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        labelgeneroCQ.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "GENERO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        cboxQuemaC.setBackground(new java.awt.Color(255, 255, 255));
        cboxQuemaC.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cboxQuemaC.setForeground(new java.awt.Color(0, 0, 0));
        cboxQuemaC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige una opcion.........", "CORRER", "CAMINATA", "NATACION", "CICLISMO", "SEDENTARIO" }));
        cboxQuemaC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxQuemaCActionPerformed(evt);
            }
        });

        btnGrabarDatosInicio3.setBackground(new java.awt.Color(0, 0, 0));
        btnGrabarDatosInicio3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnGrabarDatosInicio3.setForeground(new java.awt.Color(102, 255, 102));
        btnGrabarDatosInicio3.setText("CALCULAR");
        btnGrabarDatosInicio3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGrabarDatosInicio3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarDatosInicio3ActionPerformed(evt);
            }
        });

        resultQuemaC.setBackground(new java.awt.Color(97, 211, 211));
        resultQuemaC.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        resultQuemaC.setForeground(new java.awt.Color(0, 0, 0));
        resultQuemaC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        resultQuemaC.setOpaque(false);

        labelapellidosCQ.setEditable(false);
        labelapellidosCQ.setBackground(new java.awt.Color(255, 255, 255));
        labelapellidosCQ.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        labelapellidosCQ.setForeground(new java.awt.Color(0, 0, 0));
        labelapellidosCQ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        labelapellidosCQ.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "APELLIDOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        labelalturaCQ.setEditable(false);
        labelalturaCQ.setBackground(new java.awt.Color(255, 255, 255));
        labelalturaCQ.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelalturaCQ.setForeground(new java.awt.Color(0, 0, 0));
        labelalturaCQ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        labelalturaCQ.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "ALTURA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        RMETs.setEditable(false);
        RMETs.setBackground(new java.awt.Color(255, 255, 255));
        RMETs.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        RMETs.setForeground(new java.awt.Color(0, 0, 0));
        RMETs.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RMETs.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "METs", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        RGastoE.setEditable(false);
        RGastoE.setBackground(new java.awt.Color(255, 255, 255));
        RGastoE.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        RGastoE.setForeground(new java.awt.Color(0, 0, 0));
        RGastoE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RGastoE.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Gasto Energetico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(resultQuemaC, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(btnGrabarDatosInicio3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(labelapellidosCQ, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelnombreCQ, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxQuemaC, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelalturaCQ, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labeledadCQ, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                            .addComponent(labelpesoCQ)
                            .addComponent(labelgeneroCQ)
                            .addComponent(RMETs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                            .addComponent(RGastoE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                        .addContainerGap(75, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel4)
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelnombreCQ, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelgeneroCQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelapellidosCQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelpesoCQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(labelalturaCQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(labeledadCQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(RGastoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addComponent(cboxQuemaC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(resultQuemaC, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(RMETs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnGrabarDatosInicio3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout PERDIDACLayout = new javax.swing.GroupLayout(PERDIDAC);
        PERDIDAC.setLayout(PERDIDACLayout);
        PERDIDACLayout.setHorizontalGroup(
            PERDIDACLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PERDIDACLayout.setVerticalGroup(
            PERDIDACLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PERDIDACLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("", new javax.swing.ImageIcon(getClass().getResource("/IMG/calorias_quemadas-removebg-preview.png")), PERDIDAC); // NOI18N

        jPanel4.setBackground(new java.awt.Color(97, 211, 211));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DE LA CALCULADORA");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 530, -1));

        jLabel36.setBackground(new java.awt.Color(255, 255, 255));
        jLabel36.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Quema Cal.");
        jLabel36.setOpaque(true);
        jPanel4.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(994, 166, -1, 36));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/cronometro.png"))); // NOI18N
        jPanel4.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 48, -1));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/cronometro.png"))); // NOI18N
        jPanel4.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 48, -1));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/cronometro.png"))); // NOI18N
        jPanel4.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 48, -1));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/cronometro.png"))); // NOI18N
        jPanel4.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 48, -1));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/cronometro.png"))); // NOI18N
        jPanel4.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 48, -1));

        jButton3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton3.setText("+");
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(828, 94, 57, -1));

        jTable.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jTable.setForeground(new java.awt.Color(255, 255, 255));
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N°", "NOMBRE", "APELLIDOS", "FECHA", "PESO", "IMC", "CC", "QC"
            }
        ));
        jScrollPane1.setViewportView(jTable);
        if (jTable.getColumnModel().getColumnCount() > 0) {
            jTable.getColumnModel().getColumn(0).setMinWidth(2);
            jTable.getColumnModel().getColumn(1).setMinWidth(30);
            jTable.getColumnModel().getColumn(2).setMinWidth(30);
            jTable.getColumnModel().getColumn(3).setMinWidth(15);
            jTable.getColumnModel().getColumn(4).setMinWidth(15);
            jTable.getColumnModel().getColumn(5).setMinWidth(15);
            jTable.getColumnModel().getColumn(6).setMinWidth(20);
            jTable.getColumnModel().getColumn(7).setMinWidth(20);
        }

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 600, 340));

        btnGrabarDatosInicio4.setBackground(new java.awt.Color(0, 0, 0));
        btnGrabarDatosInicio4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGrabarDatosInicio4.setForeground(new java.awt.Color(153, 255, 102));
        btnGrabarDatosInicio4.setText("GRABAR Y ACTUALIZAR");
        btnGrabarDatosInicio4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGrabarDatosInicio4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarDatosInicio4ActionPerformed(evt);
            }
        });
        jPanel4.add(btnGrabarDatosInicio4, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 547, 200, 40));

        jLabel7.setFont(new java.awt.Font("Berlin Sans FB", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("HISTORIAL DE USO");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 460, -1));

        jButton1.setText("INFORME");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 550, 100, 40));

        javax.swing.GroupLayout HISTORIALLayout = new javax.swing.GroupLayout(HISTORIAL);
        HISTORIAL.setLayout(HISTORIALLayout);
        HISTORIALLayout.setHorizontalGroup(
            HISTORIALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HISTORIALLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 689, Short.MAX_VALUE))
        );
        HISTORIALLayout.setVerticalGroup(
            HISTORIALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HISTORIALLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("", new javax.swing.ImageIcon(getClass().getResource("/IMG/historial-removebg-preview.png")), HISTORIAL); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(materialTabbed1, javax.swing.GroupLayout.PREFERRED_SIZE, 828, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        materialTabbed1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGrabarDatosInicio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarDatosInicio1ActionPerformed
        calcularIMC();
    }//GEN-LAST:event_btnGrabarDatosInicio1ActionPerformed

    private void btnGrabarDatosInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarDatosInicioActionPerformed
        llenarDatos();
        JOptionPane.showMessageDialog(null, "SE REGISTRO CORRECTAMENTE LOS DATOS", "MENSAJE", 1);
    }//GEN-LAST:event_btnGrabarDatosInicioActionPerformed

    private void btnGrabarDatosInicio4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarDatosInicio4ActionPerformed
        registrar();
    }//GEN-LAST:event_btnGrabarDatosInicio4ActionPerformed

    private void btnGrabarDatosInicio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarDatosInicio2ActionPerformed
        calcularConsumoCaloriasDiarias();
    }//GEN-LAST:event_btnGrabarDatosInicio2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mostrarPDF();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnGrabarDatosInicio3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarDatosInicio3ActionPerformed
        calcularQuemaCalorias();
    }//GEN-LAST:event_btnGrabarDatosInicio3ActionPerformed

    private void cboxQuemaCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxQuemaCActionPerformed
        double resultado;
        if ((cboxQuemaC.getSelectedItem().toString().equals("NATACION")) ||
            (cboxQuemaC.getSelectedItem().toString().equals("CICLISMO"))||
            (cboxQuemaC.getSelectedItem().toString().equals("CAMINATA"))||
            (cboxQuemaC.getSelectedItem().toString().equals("CORRER"))){
            tiempo = Double.parseDouble(JOptionPane.showInputDialog(null, "INGRESE HORAS", "MENSAJE", 1));
        }
        
        resultado=calcularQuemaCalorias()/60;
        RGastoE.setText(String.valueOf(resultado));
        RMETs.setText(String.valueOf(MET*tiempo));
    }//GEN-LAST:event_cboxQuemaCActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaCalculadoras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCalculadoras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCalculadoras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCalculadoras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaCalculadoras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CALORIAS;
    private javax.swing.JPanel HISTORIAL;
    private javax.swing.JPanel IMC;
    private javax.swing.JPanel PERDIDAC;
    private javax.swing.JTextField RGastoE;
    private javax.swing.JTextField RMETs;
    private javax.swing.JTextField TEXTUSUARIO;
    private javax.swing.JPanel USUARIODATOS;
    private javax.swing.JButton btnGrabarDatosInicio;
    private javax.swing.JButton btnGrabarDatosInicio1;
    private javax.swing.JButton btnGrabarDatosInicio2;
    private javax.swing.JButton btnGrabarDatosInicio3;
    private javax.swing.JButton btnGrabarDatosInicio4;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cboxActividades;
    private javax.swing.JComboBox<String> cboxQuemaC;
    private java.awt.Checkbox checkFemenino;
    private java.awt.Checkbox checkMasculino;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JLabel labelFoto1;
    private javax.swing.JTextField labelalturaCQ;
    private javax.swing.JTextField labelalturaQC;
    private javax.swing.JTextField labelapellidoQC;
    private javax.swing.JTextField labelapellidosCQ;
    private javax.swing.JTextField labeledadCQ;
    private javax.swing.JTextField labeledadIMC;
    private javax.swing.JTextField labeledadQC;
    private javax.swing.JTextField labelestaturaIMC;
    private javax.swing.JTextField labelgeneroCQ;
    private javax.swing.JTextField labelgeneroIMC;
    private javax.swing.JTextField labelgeneroQC;
    private javax.swing.JTextField labelnombreCQ;
    private javax.swing.JTextField labelnombreQC;
    private javax.swing.JTextField labelpesoCQ;
    private javax.swing.JTextField labelpesoIMC;
    private javax.swing.JTextField labelpesoQC;
    private javax.swing.JTextField lblapellidosIMC;
    private javax.swing.JLabel lblnombre;
    private javax.swing.JTextField lblnombreIMC;
    private VISTA.MaterialTabbed materialTabbed1;
    private javax.swing.JTextField resultIMC;
    private javax.swing.JTextField resultIMC1;
    private javax.swing.JTextField resultQuemaC;
    private VISTA.TextField txtEdad;
    private VISTA.TextField txtEstatura;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtObservacionIMC;
    private VISTA.TextField txtPeso;
    // End of variables declaration//GEN-END:variables

}
