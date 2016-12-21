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
 * Clase de expediente
 * @author arser
 */
public class Expediente {

    /**
     * Retorna el prefijo con el que se crean los id de cada expediente
     * @return the prefijoExp
     */
    public static String getPrefijoExp() {
        return prefijoExp;
    }

    /**
     * @param aCantExpedientes the cantExpedientes configura la cantidad de expedientes procesados
     */
    public static void setCantExpedientes(int aCantExpedientes) {
        cantExpedientes = aCantExpedientes;
    }

    /**
     * Constructor del expediente que se utiliza cuando se va a extraer de la base de datos.
     * @param pcedula Cedula del paciente
     * @param pnombre Nombre del paciente
     * @param pdir Direccion del paciente
     * @param ptel Teléfono del paciente
     * @param pnacimiento Fecha de nacimiento del paciente
     * @param pidExp ID del expediente
     */
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
    /**
     * Constructor del paciente que se utiliza cuando se va a generar desde cero
     * @param pcedula
     * @param pnombre
     * @param pdir
     * @param ptel
     * @param pnacimiento 
     */
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
     * @return Id del expediente
     */
    public String getId() {
        return id;
    }

    /**
     * @param id Configura el ID del expediente
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the cedulaPaci La cedula del paciente
     */
    public String getCedulaPaci() {
        return cedulaPaci;
    }

    /**
     * @param cedulaPaci the cedulaPaci configura la cedula del paciente
     */
    public void setCedulaPaci(String cedulaPaci) {
        this.cedulaPaci = cedulaPaci;
    }

    /**
     * @return the fechaApertura Fecha de apertura
     */
    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    /**
     * @param fechaApertura the fechaApertura configura la fecha de apertura
     */
    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    /**
     * @return the nombre El nombre del paciente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set Configura el nombre del paciente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the direccion La direccion del paciente
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set La direccion del paciente
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the tel Telefono del paciente
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set Configura el telefono del paciente
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the nacimiento Fecha de nacimiento del paciente
     */
    public LocalDate getNacimiento() {
        return nacimiento;
    }

    /**
     * @param nacimiento the nacimiento to set Fecha de nacimiento del paciente
     */
    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    /**
     * @return the consultas Levanta las consultas cuando se necesitan.
     */
    public ArrayList<Consulta> getConsultas() throws Exception {
        if(consultas == null){
            this.setConsultas((new MultiConsulta()).obtenerConsultasDeUnExpediente(this.getId()));
        }
        return consultas;
    }

    /**
     * @param consultas the consultas to set Configura las consultas
     */
    public void setConsultas(ArrayList<Consulta> consultas) {
        this.consultas = consultas;
    }
    
    //Comienza logica
    
    /**
     * Calcula la edad del paciente en base a su fecha de nacimiento.
     * @return years
     */
    public int calcularEdad(){
        int years = (int) YEARS.between(this.getNacimiento(), LocalDate.now());
        return years;
    }
    
    /**
     * Registra una consulta
     * @param pidDoc Identificacion del doctor
     * @param pdesc Descripcion de la consulta
     * @param pid Identificacion del expediente
     * @throws Exception 
     */
    public void registrarConsulta(String pidDoc, String pdesc, String pid) throws Exception{
        (new MultiConsulta()).crear(pidDoc, pdesc, pid);
    }
    
    /**
     * Genera un ID para el expediente
     * @return 
     */
    public static String generarId(){
        return getPrefijoExp() + ++cantExpedientes;
    }
    
    /**
     * Devuelve la informacion del expediente
     * @return 
     */
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
