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
import javax.swing.JOptionPane;

/**
 *
 * @author arser
 */
public class MultiExpediente {
    public Expediente buscar(String pid) throws Exception{
        Expediente resul=null;
        ResultSet rs;
        String sql;
        sql = "SELECT * from TExpediente where idexp = '"+pid+"';";
        rs = Conector.getConector().ejecutarSQL(sql,true);
        if (rs.next()) {
                resul = new Expediente(
                        rs.getString("cedPaciente"),
                        rs.getString("nombre"), 
                        rs.getString("Direccion"), 
                        rs.getString("telefono"),
                        rs.getDate("nacimiento").toLocalDate(),
                        rs.getString("idExp")
                );
        }else{
            JOptionPane.showMessageDialog(null, pid);
            sql = "SELECT * FROM TExpediente where cedPaciente = '"+pid+"';";
            rs = Conector.getConector().ejecutarSQL(sql, true);
            if(rs.next()){
                resul = new Expediente(
                        rs.getString("cedPaciente"),
                        rs.getString("nombre"), 
                        rs.getString("Direccion"), 
                        rs.getString("telefono"),
                        rs.getDate("nacimiento").toLocalDate(),
                        rs.getString("idExp")
                );
            }
        }
        rs.close();
        return resul;
    }
    
    public void borrar(String pid) throws Exception{
        String sql = "Delete from texpediente where cedPaciente = '"+pid+"'";
        Conector.getConector().ejecutarSQL(sql);
    }
    
    public Expediente crear(String pcedula, String pnombre, String pdir, String ptel, LocalDate pnacimiento) throws Exception{
        if(sePuedeCrear(pcedula)){
            Expediente.setCantExpedientes(contarExpedientes());
            Expediente resul = new Expediente(pcedula, pnombre, pdir, ptel, pnacimiento);
            String pidexp = resul.getId();
            String fechaNac = "#"+pnacimiento.getMonthValue()+"/"+pnacimiento.getDayOfMonth()+"/"+pnacimiento.getYear()+"#";
            String fechaNow = "#"+LocalDate.now().getMonthValue()+"/"+LocalDate.now().getDayOfMonth()+"/"+LocalDate.now().getYear()+"#";
            String sql = "Insert into texpediente (idexp, cedpaciente, nombre, direccion, telefono, nacimiento, fechaApertura) values('"+pidexp+"','"+pcedula+"','"+pnombre+"','"+pdir+"','"+ptel+"', "+fechaNac+", "+fechaNow+");";
            Conector.getConector().ejecutarSQL(sql);
            return resul;
        }else{
            return null;
        }
    }
    
    public int contarExpedientes() throws Exception{
        String sql = "Select count(1) from texpediente;";
        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        rs.next();
        return rs.getInt(1);
    }
    
    public boolean sePuedeCrear(String pid) throws Exception{
        boolean resul = false;
        String sql = "Select count(1) from texpediente where cedPaciente = '"+pid+"';";
        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        rs.next();
        int cant = rs.getInt(1);
        if(cant == 0){
            resul = true;
        }
        return resul;
    }
    
    public String[][] obtenerListaExpedientes() throws Exception{
        String sql = "select idexp, nombre from texpediente;";
        ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
        rs = Conector.getConector().ejecutarSQL(sql, true);
        String[][] resul = new String[contarExpedientes()][2];
        int i = 0;
        while(rs.next()){
            String id = rs.getString(1);
            String nombre = rs.getString(2);
            resul[i][0] = nombre;
            resul[i][1] = id;
            i++;
        }
        return resul;
    }
}
