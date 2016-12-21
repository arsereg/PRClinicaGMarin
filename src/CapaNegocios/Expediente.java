/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocios;

import java.time.LocalDate;
import java.time.Month;
import static java.time.temporal.ChronoUnit.YEARS;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author arser
 */
public class Expediente {

    /**
     * @return the prefijoExp
     */
    public static String getPrefijoExp() {
        return prefijoExp;
    }

    /**
     * @param aCantExpedientes the cantExpedientes to set
     */
    public static void setCantExpedientes(int aCantExpedientes) {
        cantExpedientes = aCantExpedientes;
    }

    public Expediente(String pcedula, String pnombre, String pdir, String ptel, LocalDate pnacimiento, String pidExp){
        this.setId(pidExp);
        this.setCedulaPaci(pcedula);
        this.setFechaApertura(LocalDate.now());
        this.setNombre(pnombre);
        this.setDireccion(pdir);
        this.setTel(ptel);
        this.setNacimiento(pnacimiento);
        this.setConsultas(null);
    }
    
    public Expediente(String pcedula, String pnombre, String pdir, String ptel, LocalDate pnacimiento){
        this(pcedula, pnombre, pdir, ptel, pnacimiento, generarId());
    }
    
    private String id;
    private String cedulaPaci;
    private LocalDate fechaApertura;
    private String nombre;
    private String direccion;
    private String tel;
    private LocalDate nacimiento;
    private static String prefijoExp = "CSM-";
    private ArrayList<Consulta> consultas;
    private static int cantExpedientes = 0;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the cedulaPaci
     */
    public String getCedulaPaci() {
        return cedulaPaci;
    }

    /**
     * @param cedulaPaci the cedulaPaci to set
     */
    public void setCedulaPaci(String cedulaPaci) {
        this.cedulaPaci = cedulaPaci;
    }

    /**
     * @return the fechaApertura
     */
    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    /**
     * @param fechaApertura the fechaApertura to set
     */
    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the nacimiento
     */
    public LocalDate getNacimiento() {
        return nacimiento;
    }

    /**
     * @param nacimiento the nacimiento to set
     */
    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    /**
     * @return the consultas
     */
    public ArrayList<Consulta> getConsultas() throws Exception {
        if(consultas == null){
            this.setConsultas((new MultiConsulta()).obtenerConsultasDeUnExpediente(this.getId()));
        }
        return consultas;
    }

    /**
     * @param consultas the consultas to set
     */
    public void setConsultas(ArrayList<Consulta> consultas) {
        this.consultas = consultas;
    }
    
    //Comienza logica
    
    public int calcularEdad(){
        int years = (int) YEARS.between(this.getNacimiento(), LocalDate.now());
        return years;
    }
    
    public void registrarConsulta(String pidDoc, String pdesc, String pid) throws Exception{
        (new MultiConsulta()).crear(pidDoc, pdesc, pid);
    }
    
    public static String generarId(){
        return getPrefijoExp() + ++cantExpedientes;
    }
    
    public String[] obtenerInformacion(){
        String[] resul = new String[7];
        resul[0] = this.getId();
        resul[1] = this.getCedulaPaci();
        resul[2] = this.getFechaApertura().toString();
        resul[3] = this.getNombre();
        resul[4] = this.getDireccion();
        resul[5] = this.getTel();
        resul[6] = this.getNacimiento() + " ("+calcularEdad()+" a\u00f1os)";
        return resul;
    }
}
