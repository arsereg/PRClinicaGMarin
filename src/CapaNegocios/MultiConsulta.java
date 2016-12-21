/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocios;

import CapaBaseDatos.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 *
 * @author Adriana Bartels
 */
public class MultiConsulta {
    
    /**
     * Método que crea una consulta
     * @param pidConsulta the pidConsulta to set Id de la consulta
     * @param pidDoc the pidDoc to set Id del doctor
     * @param pdesc the pdesc to set Descripción de la consulta
     * @param pidExp the pidExp to set Expediente de la consulta
     * @return unaConsulta Consulta creada
     * @throws java.sql.SQLException Se lanza un error de SQL
     * @throws java.lang.Exception Se lanza un error general
     */

    public Consulta crear(String pidDoc, String pdesc, String pidExp) {
        Consulta.setCantConsultas(contarCantConsultas());
        Consulta unaConsulta = new Consulta(pidDoc, pdesc, pidExp);
        
        String sql;
        LocalDate fecha = unaConsulta.getFechaRealiz();
String fechaFormateada = "#"+fecha.getMonthValue()+"/"+fecha.getDayOfMonth()+"/"+fecha.getYear()+"#";
        sql = "INSERT INTO TConsulta "
                + "VALUES('"+unaConsulta.getIdConsulta()+"',"+ fechaFormateada+",'"+unaConsulta.getIdExp()+ "','"+unaConsulta.getDescrip()+"','"+unaConsulta.getIdDoctor()+"');";
        try {
            Conector.getConector().ejecutarSQL(sql);
        } catch (Exception ex) {
            Logger.getLogger(MultiConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }

        return unaConsulta;
    }
    
    /**
     *
     * @return size Cantidad de consultas creadas
     * @throws java.lang.Exception Se lanza un error general
     */

    public int contarCantConsultas() {
        int size = 0;
        try {
            String sql;
            sql = "SELECT idConsulta FROM TConsulta";
            java.sql.ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
            while (rs.next()) {
                size++;
            }
        } catch (Exception ex) {
            Logger.getLogger(MultiConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return size;
    }
    
    /**
     * @param pidExp the pidExp to set Id de un expediente
     *
     * @return resul Lista de consultas (String) que posee un expediente
     * @throws java.sql.SQLException Se lanza un error SQL
     * @throws java.lang.Exception Se lanza un error general
     */

    public String[][] obtenerListaDeConsultasDeUnExpediente(String pidExp) throws java.sql.SQLException, Exception {
        java.sql.ResultSet rs;
        String sql;
        sql = "SELECT  * FROM TConsulta WHERE idExp ='" + pidExp + "';";
        rs = Conector.getConector().ejecutarSQL(sql, true);
        int espaciosUtilizados = 0;
        while (rs.next()) {
            espaciosUtilizados++;
        }
        rs = Conector.getConector().ejecutarSQL(sql, true);
        String[][] resul = new String[espaciosUtilizados][6];
        String idConsulta;
        LocalDate fecha;
        String descripcion;
        String expediente;
        String doctorACargo;
        int j = 0;
        while (rs.next()) {

            String[] informacionConsulta = new String[5];
            idConsulta = rs.getString("idConsulta");
            fecha = rs.getDate("fechaRealizacion").toLocalDate();
            descripcion = rs.getString("descripcion");
            expediente = rs.getString("idExp");
            doctorACargo = rs.getString("idDoctor");
            informacionConsulta[0] = idConsulta;
            informacionConsulta[1] = fecha.toString();
            informacionConsulta[2] = descripcion;
            informacionConsulta[3] = expediente;
            informacionConsulta[4] = doctorACargo;
            resul[j] = informacionConsulta;
            j++;
        }
        rs.close();
        return resul;

    }
    
    public ArrayList<Consulta> obtenerConsultasDeUnExpediente(String pidExp) throws java.sql.SQLException, Exception {
        String sql = "select * from tconsulta where idexp = '"+pidExp+"';";
        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        ArrayList<Consulta> resul = new ArrayList<Consulta>();
        while(rs.next()){
            Consulta unaConsulta = new Consulta(
                            rs.getString("idConsulta"),
                            rs.getString("idDoctor"), 
                            rs.getString("descripcion"),
                            pidExp, 
                            rs.getDate("fechaRealizacion").toLocalDate());
            resul.add(unaConsulta);
        }
        return resul;
    }
    
    /**
     * @param pidConsultas the pidConsultas to set ID de la consulta buscada
     *
     * @return unaConsulta consulta buscada
     * @throws java.sql.SQLException Se lanza un error SQL
     * @throws java.lang.Exception Se lanza un error general
     */

    public Consulta buscarConsulta(String pidConsulta) throws java.sql.SQLException, Exception {
        Consulta unaConsulta = null;
        java.sql.ResultSet rs;
        String sql;
        sql = "SELECT  * FROM TConsulta WHERE idConsulta ='" + pidConsulta + "';";
        rs = Conector.getConector().ejecutarSQL(sql, true);
        if (rs.next()) {
            unaConsulta = new Consulta(
                    rs.getString("idConsulta"),
                    rs.getString("descripcion"),
                    rs.getString("idExp"),
                    rs.getString("idDoctor"),
                    rs.getDate("fechaRealizacion").toLocalDate());

        }
        rs.close();
        return unaConsulta;
    }
    
    /**
     *
     * @return resul Lista de todas las consultas (String)
     * @throws java.sql.SQLException Se lanza un error SQL
     * @throws java.lang.Exception Se lanza un error general
     */

    public String[][] obtenerListaDeConsultas() throws java.sql.SQLException, Exception {
        java.sql.ResultSet rs;
        String sql;
        sql = "SELECT * FROM TConsulta";
        rs = Conector.getConector().ejecutarSQL(sql, true);
        int espaciosUtilizados = 0;
        while (rs.next()) {
            espaciosUtilizados++;
        }
        rs.first();
        String[][] resul = new String[espaciosUtilizados][6];
        String idConsulta;
        LocalDate fecha;
        String descripcion;
        String medicinas;
        String expediente;
        String doctorACargo;
        int j = 0;
        while (rs.next()) {

            String[] informacionConsulta = new String[6];
            idConsulta = rs.getString("idConsulta");
            fecha = rs.getDate("fechaRealizacion").toLocalDate();
            descripcion = rs.getString("descripcion");
            medicinas = rs.getString("medicinas");
            expediente = rs.getString("idExp");
            doctorACargo = rs.getString("idDoctor");
            informacionConsulta[0] = idConsulta;
            informacionConsulta[1] = fecha.toString();
            informacionConsulta[2] = descripcion;
            informacionConsulta[3] = medicinas;
            informacionConsulta[4] = expediente;
            informacionConsulta[5] = doctorACargo;
            resul[j] = informacionConsulta;
            j++;
        }
        rs.close();
        return resul;

    }
    public ArrayList<String> obtenerListaMedicinas(String pidConsulta){
    	ArrayList<String> listaMedicinas = new ArrayList<String>();
    	try{
        java.sql.ResultSet rs;
        String sql;
        sql = "SELECT * FROM MedicinasXConsulta WHERE IDConsulta ='"+pidConsulta+"';";
        rs = Conector.getConector().ejecutarSQL(sql,true);
        while(rs.next()){
                listaMedicinas.add(rs.getString("medicina"));
        }
        rs.close();
        }catch(Exception e){
        
        }
        return listaMedicinas;
    }
    public String agregarMedicina(String pmedicina,String pidConsulta) throws SQLException, Exception{
	String sql = "Select count(1) from medicinasxconsulta";
        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        rs.next();
        int cant = rs.getInt(1);
        String msm="";
		String idMedicinas = "MED-" + cant;
                sql="insert into medicinasxconsulta (id, idconsulta, medicina) values('"+idMedicinas+"','"+pidConsulta+"','"+pmedicina+"');";				;
		
		Conector.getConector().ejecutarSQL(sql);
		return msm;
    }
    
}
