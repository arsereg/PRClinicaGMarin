/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocios;

import java.sql.SQLException;
/**
 *
 * @author Adriana Bartels
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class Consulta {

    private String idDoctor;
    private LocalDate fechaRealiz;
    private String descrip;
    private ArrayList<String> listaMedicinas;
    private String idExp;
    private String idConsulta;
    private static int cantConsultas = 0;
    
    /**
     * Constructor con los datos de una consulta con el id enviado
     *
     * @param pidConsulta to set Id de la consulta
     * @param pidDoc to set Id del Doctor
     * @param pdesc to set Descripcion de la consulta
     * @param pidExp to set el Id del expediente
     * @param pfecha to set Fecha de la realizacion de la consulta
     */

    public Consulta(String pidConsulta, String pidDoc, String pdesc, String pidExp, LocalDate pfecha) {
        this.setIdConsulta(pidConsulta);
        this.setFechaRealiz(pfecha);
        this.setIdDoctor(pidDoc);
        this.setDescrip(pdesc);
        this.setIdExp(pidExp);
 
    }
    
    /**
     * Constructor con los datos de una consulta con el id enviado
     *
     * @param pidConsulta to set Id de la consulta
     * @param pidDoc to set Id del Doctor
     * @param pdesc to set Descripcion de la consulta
     * @param pidExp to set el Id del expediente
     */

    public Consulta(String pidDoc, String pdesc, String pidExp) {
        this(generarId(), pidDoc, pdesc, pidExp, LocalDate.now());
    }
    
    /**
     * @return descrip la fecha de realización de la consulta
     */

    public LocalDate getFechaRealiz() {
        return fechaRealiz;
    }
    
     /**
     * @param fechaRealiz the pid to set la fecha de realización de la consulta
     */

    public void setFechaRealiz(LocalDate fechaRealiz) {
        this.fechaRealiz = fechaRealiz;
    }
    
    /**
     * @return descrip la descripción de la consulta
     */

    public String getDescrip() {
        return descrip;
    }
    
    /**
     * @param pdescrip the pid to set la descripción de la consulta
     */

    public void setDescrip(String pdescrip) {
        this.descrip = pdescrip;
    }
    
    /**
     * @return idExp el Id del expediente
     */

    public String getIdExp() {
        return idExp;
    }
    
      /**
     * @param pidExp the pid to set el Id del expediente al que pertenece
     */

    public void setIdExp(String pidExp) {
        this.idExp = pidExp;
    }
    
     /**
     * @return idConsulta el Id de la consulta
     */

    public String getIdConsulta() {
        return idConsulta;
    }
    
      /**
     * @param pidConsulta the pid to set el Id de la consulta
     */

    public void setIdConsulta(String pidConsulta) {
        this.idConsulta = pidConsulta;
    }
    
    /**
     * @return cantConsultas la cantidad de consultas creadas
     */

    public static int getCantConsultas() {
        return cantConsultas;
    }
    
    /**
     * @param pcantConsultas the pid to set la cantidad de consultas creadas
     */

    public static void setCantConsultas(int pcantConsultas) {
        Consulta.cantConsultas = pcantConsultas;
    }
    
    /*Generación de ID*/
    /**
     * @return resul El ID generado para una consulta
     */

    private static String generarId() {
        String resul;
        resul = "" + ++cantConsultas;

        return resul;
    }
    
    /**
     * @return medicinas la lista de medicinas de una consulta
     * @throws Exception 
     * @throws SQLException 
     */

    public ArrayList<String> getListaMedicinas() throws SQLException, Exception{
    	setListaMedicinas(((new MultiConsulta()).obtenerListaMedicinas(getIdConsulta())));
		return listaMedicinas;
    }
    
    /**
     * @param pmedicinas the pid to set las medicinas recetadas de una consulta
     */

    public void setListaMedicinas(ArrayList<String> plistaMedicinas) {
        this.listaMedicinas = plistaMedicinas;
    }
    
    /**
     * @return idDoctor el Id del doctor
     */

    public String getIdDoctor() {
        return idDoctor;
    }
    
    /**
     * @param pidDoctor the pid to set el Id del doctor
     */

    public void setIdDoctor(String pidDoctor) {
        this.idDoctor = pidDoctor;
    }
    
    /**
     * Método que permite agregar las medicinas recetadas a la lista de medicinas
     * @param pmedicina the pid to set la medicina enviada a la lista
     * @throws Exception 
     * @throws SQLException 
     */

    public void agregarMedicina(String pmedicina) throws SQLException, Exception {
    	String medicina ="";
    	medicina =((new MultiConsulta()).agregarMedicina(pmedicina,getIdConsulta()));
    

    }
    public ArrayList<String> consultarMedicinas(String idConsulta) throws SQLException, Exception{
    	
    	return getListaMedicinas();
    }
    
    /**
     * Método que permite obtener la información de una sola consulta
     * @return informacionDeUnaConsulta la información de una consulta
     * @throws Exception 
     * @throws SQLException 
     */

    public String[] obtenerInformacionDeUnaConsulta() throws SQLException, Exception {
        String[] informacionDeUnaConsulta = new String[6];

        informacionDeUnaConsulta[0] = getIdConsulta();
        informacionDeUnaConsulta[1] = getFechaRealiz().toString();
        informacionDeUnaConsulta[2] = getDescrip();
        informacionDeUnaConsulta[3] = getListaMedicinas().toString();
        informacionDeUnaConsulta[4] = getIdExp();
        informacionDeUnaConsulta[5] = getIdDoctor();

        return informacionDeUnaConsulta;
    }
    
    /**
     * 
     * @return informacionConsulta Estado del objeto. 
     */

    @Override
    public String toString(){
        try {
            String informacionConsulta;
            informacionConsulta = "Consulta # " + idConsulta + "\n";
            informacionConsulta += "Doctor: " + getIdDoctor() + "\n";
            informacionConsulta += "Expediente: " + getIdExp() + "\n";
            informacionConsulta += "Descripción: " + getDescrip() + "\n";
            informacionConsulta += "Medicinas recetadas: " + getListaMedicinas() + "\n";
            
            return informacionConsulta;
        } catch (Exception ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
