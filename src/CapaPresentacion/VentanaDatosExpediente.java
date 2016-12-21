/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPresentacion;

import CapaNegocios.Gestor;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author arser
 */
public class VentanaDatosExpediente extends javax.swing.JFrame  {

    Gestor gestor;
    private String idExpediente;
    private String idDoctor;
    /**
     * Creates new form VentanaDatosExpediente
     */
    public VentanaDatosExpediente(String pid, String pidDoc) throws Exception {
        gestor = new Gestor();
        this.setIdExpediente(pid);
        initComponents();
        setLocationRelativeTo(null);
        String[] datos = gestor.obtenerDatosExpediente(pid);
        numExp.setText("Num-Expediente: " + datos[0]);
        FechaApert.setText("Fecha de Apertura: " + datos[2]);
        Nombre.setText("Nombre: " + datos[3]);
        Direccion.setText("Direccion: " + datos[4]);
        FechaNacimiento.setText("Fecha nacimiento: " + datos[6]);
        Cedula.setText("Cedula: " + datos[1]);
        Telefono.setText("Telefono: " + datos[5]);
        this.setIdDoctor(pidDoc);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        numExp = new javax.swing.JLabel();
        FechaApert = new javax.swing.JLabel();
        Nombre = new javax.swing.JLabel();
        Direccion = new javax.swing.JLabel();
        FechaNacimiento = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Cedula = new javax.swing.JLabel();
        Telefono = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaConsultas = new javax.swing.JList<>();
        Gestor gestor = new Gestor();
        String[][] matriz = gestor.obtenerListaConsultas(idExpediente);
        ArrayList<String> lista = new ArrayList();
        for(int i = 0; i < matriz.length; i++){
            lista.add(matriz[i][1]);
        }
        DefaultListModel model = new DefaultListModel();
        for(int i = 0; i < lista.size(); i++){
            model.add(i, lista.get(i));
        }
        ListaConsultas= new JList(model);
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        numExp.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        numExp.setText("Num-Expediente:");

        FechaApert.setText("Fecha de Apertura:");

        Nombre.setText("Nombre:");

        Direccion.setText("Direccion:");

        FechaNacimiento.setText("Fecha de nacimiento:");

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Cedula.setText("jLabel1");

        Telefono.setText("jLabel1");

        ListaConsultas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(ListaConsultas);

        jLabel1.setText("Consultas");

        jButton2.setText("Crear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Seleccionar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(Telefono)
                    .addComponent(Cedula)
                    .addComponent(FechaNacimiento)
                    .addComponent(Direccion)
                    .addComponent(Nombre)
                    .addComponent(FechaApert)
                    .addComponent(numExp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)))
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(numExp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FechaApert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Nombre)
                        .addGap(18, 18, 18)
                        .addComponent(Direccion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FechaNacimiento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Cedula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Telefono)
                        .addGap(31, 31, 31)
                        .addComponent(jButton1)
                        .addGap(0, 116, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        VentanaCrearConsulta vent = new VentanaCrearConsulta(idDoctor, idExpediente);
        vent.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String idConsulta = gestor.obtenerListaConsultas(idExpediente)[ListaConsultas.getSelectedIndex()][0];
            VentanaDatosConsulta vent = new VentanaDatosConsulta(idConsulta);
            vent.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(VentanaDatosExpediente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cedula;
    private javax.swing.JLabel Direccion;
    private javax.swing.JLabel FechaApert;
    private javax.swing.JLabel FechaNacimiento;
    private javax.swing.JList<String> ListaConsultas;
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel Telefono;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel numExp;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the idExpediente
     */
    public String getIdExpediente() {
        return idExpediente;
    }

    /**
     * @param idExpediente the idExpediente to set
     */
    public void setIdExpediente(String idExpediente) {
        this.idExpediente = idExpediente;
    }

    /**
     * @return the idDoctor
     */
    public String getIdDoctor() {
        return idDoctor;
    }

    /**
     * @param idDoctor the idDoctor to set
     */
    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }
}
