/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch2Multifuncional;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import scratch2Multifuncional.json.JLeerScratch;
import scratch2Multifuncional.json.JMotor;

/**
 *
 * @author eduardo
 */
public class Scratch2LittleBits extends javax.swing.JFrame {


    private ScratchModule moModule;
    private ScratchHttp moHTTP;
    private Thread moThrHTTP;
    private Timer moTimer;
//    private boolean mbConectados = false;
    private JMotor moPruebas;
    private Thread moThread;


    /**
     * Creates new form Scratch2LittleBits
     */
    public Scratch2LittleBits() throws Exception {
        initComponents();
        txtProyectoSCratch.setEditable(false);
        this.setMaximizedBounds(this.getBounds());
        this.setLocation(10, Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight());
        txtA1.setDocument(new JPlainDocumentMax(3));
        txtA2.setDocument(new JPlainDocumentMax(3));
        txtRegulador.setDocument(new JPlainDocumentMax(3));
        txtA1.setText("100");
        txtA2.setText("100");
        txtRegulador.setText("100");
        btnProbar.setEnabled(true);
        btnProbarPARAR.setEnabled(false);
        cmbPosition.removeAllItems();
        cmbPosition.addItem("1");
        cmbPosition.addItem("2");
        cmbPosition.setSelectedIndex(0);
        moModule = new ScratchModule(txtA1, txtA2, txtRegulador, lblResult);
        moHTTP = new ScratchHttp(this);
        moThrHTTP = new Thread(moHTTP);
        moThrHTTP.start();
        lanzarTimer();

        try{
            txtProyectoSCratch.setText(JDatosGeneralesP.getDatosGenerales().getUltFichero());
            procesar();
        }catch(Throwable e){
            txtProyectoSCratch.setText("");
        }
    }
    /**arrancamos enlace scratch*/
    private void lanzarTimer() throws IOException{
        moTimer = new Timer();
        moTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {

                        if(moHTTP!=null && moHTTP.isConectado()){
                            lblScratch.setText("Conectado");
                            lblConexion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_connected.png")));
                        }else{
                            lblScratch.setText("DESCONECTADO");
                            lblConexion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon.png")));
                        }
                        try{
                            if(Integer.valueOf(txtA1.getText())>100){
                                txtA1.setText("100");
                            }
                            if(Integer.valueOf(txtA1.getText())<0){
                                txtA1.setText("0");
                            }                        
                        }catch(Throwable en){txtA1.setText("0");}
                        try{
                            if(Integer.valueOf(txtA2.getText())>100){
                                txtA2.setText("100");
                            }
                            if(Integer.valueOf(txtA2.getText())<0){
                                txtA2.setText("0");
                            }
                        }catch(Throwable en){txtA2.setText("0");}
                        try{
                            if(Integer.valueOf(txtRegulador.getText())>100){
                                txtRegulador.setText("100");
                            }
                            if(Integer.valueOf(txtRegulador.getText())<0){
                                txtRegulador.setText("0");
                            }
                        }catch(Throwable en){txtRegulador.setText("0");}
//                        if(moArduino!=null && moArduino.isConnected()){
//                            lblLittleBits.setText("Conectado");
//                        }else{
//                            lblLittleBits.setText("DESCONECTADO");
//                        }
//                        if(moArduino!=null){
//                            jComboBox1.setSelectedItem(moArduino.getPuerto());                            
//                        }
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }, 50, 2000);
        
    }
//    private void pararTimer(){
//        moHTTP.parar();
//        moTimer.cancel();
//        lblScratch.setText("DESCONECTADO");
//    }

    public ScratchModule getModule(){
        return moModule;
    } 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        lblConexion = new javax.swing.JLabel();
        lblLittleBitsLogo = new javax.swing.JLabel();
        lblScratch = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblResult = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtA2 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtRegulador = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtA1 = new javax.swing.JTextField();
        lblModulo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtProyectoSCratch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lblScraptScript = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnProbar = new javax.swing.JButton();
        btnProbarPARAR = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cmbPosition = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        cmbPort = new javax.swing.JComboBox();
        lblLittleBits = new javax.swing.JLabel();
        btnLoad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Scratch + Littlebits");
        setAlwaysOnTop(true);
        setResizable(false);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel2.setLayout(new java.awt.GridBagLayout());

        lblConexion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon.png"))); // NOI18N
        jPanel2.add(lblConexion, new java.awt.GridBagConstraints());

        lblLittleBitsLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LITTLEBITS.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(lblLittleBitsLogo, gridBagConstraints);

        lblScratch.setBackground(new java.awt.Color(255, 204, 51));
        lblScratch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScratch.setText(" ");
        lblScratch.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        jPanel2.add(lblScratch, gridBagConstraints);

        jPanel9.setOpaque(false);
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel7.setForeground(new java.awt.Color(153, 0, 153));
        jLabel7.setText("out");
        jLabel7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel9.add(jLabel7, gridBagConstraints);

        lblResult.setBackground(javax.swing.UIManager.getDefaults().getColor("OptionPane.warningDialog.titlePane.background"));
        lblResult.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblResult.setForeground(javax.swing.UIManager.getDefaults().getColor("OptionPane.warningDialog.titlePane.foreground"));
        lblResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResult.setText("0");
        lblResult.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblResult.setMaximumSize(new java.awt.Dimension(100, 40));
        lblResult.setMinimumSize(new java.awt.Dimension(100, 40));
        lblResult.setOpaque(true);
        lblResult.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        jPanel9.add(lblResult, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 50, 5, 5);
        jPanel2.add(jPanel9, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel4.setForeground(new java.awt.Color(153, 0, 153));
        jLabel4.setText("a2");
        jPanel6.add(jLabel4, new java.awt.GridBagConstraints());

        txtA2.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        txtA2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtA2.setText("100");
        txtA2.setMinimumSize(new java.awt.Dimension(100, 40));
        txtA2.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(txtA2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel2.add(jPanel6, gridBagConstraints);

        jPanel8.setOpaque(false);
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel6.setForeground(new java.awt.Color(153, 0, 153));
        jLabel6.setText("Regulator");
        jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel8.add(jLabel6, gridBagConstraints);

        txtRegulador.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        txtRegulador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRegulador.setText("100");
        txtRegulador.setMinimumSize(new java.awt.Dimension(100, 40));
        txtRegulador.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        jPanel8.add(txtRegulador, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 90, 5, 5);
        jPanel2.add(jPanel8, gridBagConstraints);

        jPanel7.setOpaque(false);
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel5.setForeground(new java.awt.Color(153, 0, 153));
        jLabel5.setText("a1");
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel7.add(jLabel5, gridBagConstraints);

        txtA1.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        txtA1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtA1.setText("100");
        txtA1.setMinimumSize(new java.awt.Dimension(100, 40));
        txtA1.setName(""); // NOI18N
        txtA1.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        jPanel7.add(txtA1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 5, 5);
        jPanel2.add(jPanel7, gridBagConstraints);

        lblModulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/modulo.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, -40, 5);
        jPanel2.add(lblModulo, gridBagConstraints);

        jTabbedPane1.addTab("Link", jPanel2);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Proyect Scratch"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Path");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        jPanel3.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        jPanel3.add(txtProyectoSCratch, gridBagConstraints);

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        jPanel3.add(jButton1, gridBagConstraints);

        lblScraptScript.setBackground(javax.swing.UIManager.getDefaults().getColor("OptionPane.questionDialog.titlePane.background"));
        lblScraptScript.setForeground(javax.swing.UIManager.getDefaults().getColor("OptionPane.questionDialog.titlePane.foreground"));
        lblScraptScript.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScraptScript.setText(" ");
        lblScraptScript.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        jPanel3.add(lblScraptScript, gridBagConstraints);

        btnProbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Play16.gif"))); // NOI18N
        btnProbar.setText("Probe");
        btnProbar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProbarActionPerformed(evt);
            }
        });
        jPanel4.add(btnProbar);

        btnProbarPARAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Stop16.gif"))); // NOI18N
        btnProbarPARAR.setText("Stop probe");
        btnProbarPARAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProbarPARARActionPerformed(evt);
            }
        });
        jPanel4.add(btnProbarPARAR);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Module"));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Position");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        jPanel5.add(jLabel3, gridBagConstraints);

        cmbPosition.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPosition.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPositionItemStateChanged(evt);
            }
        });
        cmbPosition.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbPositionFocusGained(evt);
            }
        });
        cmbPosition.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbPositionMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        jPanel5.add(cmbPosition, gridBagConstraints);

        jLabel1.setText("Port littlebits");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        jPanel5.add(jLabel1, gridBagConstraints);

        cmbPort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPort.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPortItemStateChanged(evt);
            }
        });
        cmbPort.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbPortFocusGained(evt);
            }
        });
        cmbPort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbPortMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        jPanel5.add(cmbPort, gridBagConstraints);

        lblLittleBits.setBackground(new java.awt.Color(255, 102, 102));
        lblLittleBits.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLittleBits.setText(" ");
        lblLittleBits.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        jPanel5.add(lblLittleBits, gridBagConstraints);

        btnLoad.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Forward24.gif"))); // NOI18N
        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        jPanel5.add(btnLoad, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jPanel5, gridBagConstraints);

        jTabbedPane1.addTab("Load", jPanel1);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbPortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPortMouseClicked
        
    }//GEN-LAST:event_cmbPortMouseClicked

    private void cmbPortFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbPortFocusGained
        recargarPuertos();
    }//GEN-LAST:event_cmbPortFocusGained

    private void cmbPortItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPortItemStateChanged

    }//GEN-LAST:event_cmbPortItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        File loFile = null;
        try{
            JFileChooser loFileM = new JFileChooser();
            loFileM.setCurrentDirectory(new File(txtProyectoSCratch.getText()).getParentFile());
            loFileM.setSelectedFile(new File(txtProyectoSCratch.getText()));
            if(loFileM.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                loFile = loFileM.getSelectedFile();
                txtProyectoSCratch.setText(loFile.getAbsolutePath());
                procesar();
                JDatosGeneralesP.getDatosGenerales().setUltFichero(loFile.getAbsolutePath());
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.toString(), "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void procesar() throws Exception{
        JLeerScratch loLeer = new JLeerScratch(new File(txtProyectoSCratch.getText()));
        loLeer.procesar();
        String lsScript;
        if(loLeer.getListaScripts().length>1){
            lsScript = (String) JOptionPane.showInputDialog(this, 
                "Select scratch script",
                "scratch script",
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                loLeer.getListaScripts(), 
                loLeer.getListaScripts()[0]);

        }else{
            lsScript = loLeer.getListaScripts()[0];
        }
        loLeer.getScriptReducido(lsScript);
        lblScraptScript.setText(lsScript);        
    }
    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed

        lblLittleBits.setText("Cargando");
        btnLoad.setEnabled(false);
        
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                ModuleLoad loLoad=null;
                try{
                    loLoad = new ModuleLoad(cmbPort.getSelectedItem().toString());
                    loLoad.openPort();


                    JLeerScratch loLeer = new JLeerScratch(new File(txtProyectoSCratch.getText()));
                    loLeer.procesar();
                    String lsS =loLeer.getScriptReducido(lblScraptScript.getText());

                    int[] lal = new int[lsS.length()+2+loLeer.mcsSerialInicio.length()];
                    for(int i = 0 ; i < loLeer.mcsSerialInicio.length();i++){
                        lal[i]=loLeer.mcsSerialInicio.charAt(i);
                    }
                    lal[loLeer.mcsSerialInicio.length()]=cmbPosition.getSelectedItem().toString().charAt(0);
                    
                    for(int i = 0 ; i < lsS.length();i++){
                        lal[i+loLeer.mcsSerialInicio.length()+1]=lsS.charAt(i);
                    }
                    lal[lal.length-1]='\n';

                    loLoad.write(lal);
                    Thread.sleep(500);
                    String lalResp = loLoad.read();
                    
                    lblLittleBits.setText("Load OK ");
                    lblLittleBits.setToolTipText("Load OK " + String.valueOf(lalResp));
                    
                }catch(Exception e){
                    lblLittleBits.setText("ERROR: "+e.toString());
                    e.printStackTrace();
                }finally {
                    btnLoad.setEnabled(true);
                    try {
                        loLoad.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnProbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProbarActionPerformed
        try{
            moHTTP.parar();
            //lector scratch
            JLeerScratch loLeer = new JLeerScratch(new File(txtProyectoSCratch.getText()));
            loLeer.procesar();
            //motor pruebas
            moPruebas = new JMotor(moModule);
            moPruebas.setScript(loLeer.getScriptReducido(lblScraptScript.getText()));
            moThread = new Thread(moPruebas);
            moThread.start();
            //pantalla
            btnProbar.setEnabled(false);
            btnProbarPARAR.setEnabled(true);
            
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.toString(), "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnProbarActionPerformed

    private void btnProbarPARARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProbarPARARActionPerformed
        try{
            //paramos pruebas
            moPruebas.parar();
            //arrancamos enlace scratch
            moHTTP.conectar();
            //pantalla
            btnProbar.setEnabled(true);
            btnProbarPARAR.setEnabled(false);
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.toString(), "", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnProbarPARARActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        try{
            recargarPuertos();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.toString(), "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void cmbPositionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPositionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPositionItemStateChanged

    private void cmbPositionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbPositionFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPositionFocusGained

    private void cmbPositionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPositionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPositionMouseClicked

    private void recargarPuertos(){
        String[] lasLista = ModuleLoad.getListaPuertos();
        cmbPort.removeAllItems();
        for(int i = 0 ; i < lasLista.length; i++)
            cmbPort.addItem(lasLista[i]);
        
    }
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
            java.util.logging.Logger.getLogger(Scratch2LittleBits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Scratch2LittleBits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Scratch2LittleBits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Scratch2LittleBits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Scratch2LittleBits lo = new Scratch2LittleBits();

                    lo.setVisible(true);
                } catch (Throwable ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(new JLabel(), ex.toString());
                }
            }
        });
    }


    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnProbar;
    private javax.swing.JButton btnProbarPARAR;
    private javax.swing.JComboBox cmbPort;
    private javax.swing.JComboBox cmbPosition;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblConexion;
    private javax.swing.JLabel lblLittleBits;
    private javax.swing.JLabel lblLittleBitsLogo;
    private javax.swing.JLabel lblModulo;
    private javax.swing.JLabel lblResult;
    private javax.swing.JLabel lblScraptScript;
    private javax.swing.JLabel lblScratch;
    private javax.swing.JTextField txtA1;
    private javax.swing.JTextField txtA2;
    private javax.swing.JTextField txtProyectoSCratch;
    private javax.swing.JTextField txtRegulador;
    // End of variables declaration//GEN-END:variables
}
