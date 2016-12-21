package CapaNegocios;

import java.sql.SQLException;
import java.util.ArrayList;

import CapaBaseDatos.Conector;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class MultiDoctor {
	
		
	/**
	 * @param pidDoctor the pidDoctor to set Identificador unico de un Doctor
	 * @param pnombre the pnombre to set Nombre de un Doctor
	 * @param pespecialidad the pespecialidad to set Especilidad de un Doctor
	 * @param ptelefono the ptelefono to set Numero telefonico de un Doctor
	 * @return miDoctor El Doctor creado (Objeto)
	 * @throws java.sql.SQLException Se lanza un error de SQL
	 * @throws Exception Exception Se lanza un error general
	 */
	public Doctor crear(String pidDoctor,String pnombre, String pespecialidad, String ptelefono) throws java.sql.SQLException,Exception{
		Doctor miDoctor = new Doctor(pidDoctor,pnombre,pespecialidad, ptelefono);
		String sql; 	
		sql="INSERT INTO TDoctor " +
				" VALUES('"+miDoctor.getIdDoctor()+"', '"+miDoctor.getNombre()+"','"+miDoctor.getEspecialidad()+"','"+miDoctor.getTelefono()+"');";
		Conector.getConector().ejecutarSQL(sql);
		return miDoctor;
	}
	/**
	 * @param pidDoctor the pid to set Identificador unico de un Doctor
	 * @return unDoctor
	 * @throws java.sql.SQLException Se lanza un error de SQL
	 * @throws Exception Exception Se lanza un error general
	 */
	public Doctor buscar(String pidDoctor) throws java.sql.SQLException,Exception{
		Doctor unDoctor = null;
		java.sql.ResultSet rs;
		String sql;
		sql = "SELECT * FROM TDoctor WHERE idDoctor='"+pidDoctor+"';";
		rs = Conector.getConector().ejecutarSQL(sql,true);
		if (rs.next()){
			unDoctor = new Doctor(
					rs.getString("idDoctor"),
					rs.getString("nombre"),
					rs.getString("especialidad"),
					rs.getString("telefono"));
			
		}
		rs.close();
		return unDoctor;		
	}
		
	/**
	 * @return misDatos Lista de identificaciones y nombres de todos Los Doctores
	 * @throws java.sql.SQLException Se lanza un error de SQL
	 * @throws Exception Exception Se lanza un error general
	 */
	public String[][] obtenerDatosDoctores() throws java.sql.SQLException,Exception{
		int cantDoctores = contarDoctores();
		java.sql.ResultSet rs;
		String sql;
		String[][] misDatos = new String[cantDoctores][2];
		sql="Select idDoctor, nombre from TDoctor;";
		rs = Conector.getConector().ejecutarSQL(sql,true);
		String id;
		String nombre;
		int i = 0;
		while(rs.next()){
			id = rs.getString("idDoctor");
			nombre = rs.getString("nombre");
			misDatos[i][0]=id;
			misDatos[i][1]=nombre;
			i++;
		}
		rs.close();	
		return misDatos;
	}
	public int contarDoctores() throws SQLException, Exception{
		java.sql.ResultSet rs;
		String sql;
		sql= "Select count(1) from TDoctor;";
		rs = Conector.getConector().ejecutarSQL(sql,true);
		rs.next();
		return rs.getInt(1);
	
	}
	/**
	 * @param pidDoctor the pidDoctor to set Identificador unico de un Doctor
	 * @return cantDoctor Indice de un Doctor registrado
	 * @throws SQLException Se lanza un error de SQL
	 * @throws Exception Exception Se lanza un error general
	 */
	public int verificarDoctorRegistrado(String pidDoctor) throws SQLException, Exception{
		int cantDoctor = 0;
		java.sql.ResultSet rs;
		String sql;
		sql = "SELECT count(idDoctor) FROM TDoctor WHERE idDoctor='"+pidDoctor+"';";
		rs = Conector.getConector().ejecutarSQL(sql,true);
		rs.next();
		cantDoctor = rs.getInt(1);
		rs.close();
		return cantDoctor;
		
	}
        
        public String obtenerNombreDoctor(String pidDoctor) throws Exception{
            String resul = "";
            String sql = "select nombre from tdoctor where idDoctor = '"+pidDoctor+"';";
            ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
            rs.next();
            resul = rs.getString("nombre");
            return resul;
        }
}

