package CapaNegocios;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author montero
 *
 */

public class Doctor {

	private String idDoctor;
	private String nombre;
	private String especialidad;
	private String telefono;

	/**
	 * @param pidDoctor to set identificacion de un Doctor
	 * @param pnombre to set Nombre de un Doctor
	 * @param pespecialidad to set Especialidad de un Doctor
	 * @param ptelefono to set Telefono de un Doctor
	 */
	Doctor(String pidDoctor,String pnombre, String pespecialidad, String ptelefono){
		this.setIdDoctor(pidDoctor);
		this.setNombre(pnombre);
		this.setEspecialidad(pespecialidad);
		this.setTelefono(ptelefono);
	}
	/**
	 * @param pidDoctor to set identificacion de un Doctor
	 * @param pnombre to set nombre de un Doctor
	 * @param pespecialidad to set especialidad de un Doctor
	 */
	Doctor(String pidDoctor,String pnombre, String pespecialidad){
		this(pidDoctor,pnombre,pespecialidad,"No tiene");
	}
	

	/**
	 * @return idDoctor identificacion unico de un Doctor
	 */
	public String getIdDoctor() {
		return idDoctor;
	}
	/**
	 * @param idDoctor the idDoctor to set identificador unico de un Doctor
	 */
	public void setIdDoctor(String idDoctor) {
		this.idDoctor = idDoctor;
	}
	/**
	 * @return nombre de un Doctor
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre de un Doctor
	 */
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return especialidad de un Doctor
	 */
	public String getEspecialidad() {
		return especialidad;
	}
	/**
	 * @param especialidad the especialidad to set de un Doctor
	 */
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	/**
	 * @return telefono de un doctor
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set the un Doctor
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
        /**
         * 
         * @param pced Nummero de cedula
         * @param pnombrePac Nombre del paciente
         * @param pdir Direccion del paciente
         * @param ptel Telefono del paciente
         * @param pnac Fecha de nacimiento
         * @return miExpediente
         * @throws SQLException Se lanza un error SQL
         * @throws Exception Se lanza un error general
         */
	public Expediente abrirExpediente(String pced,String pnombrePac, String pdir, String ptel, LocalDate pnac)throws SQLException, Exception{
		Expediente miExpediente = null;
		
		miExpediente =((new MultiExpediente()).crear(pced, pnombrePac, pdir, ptel, pnac));
		
	return miExpediente;
	}
	
        /**
         * 
         * @param pidDoctor ID del doctor
         * @param pidExp ID Del expediente
         * @param pdesc Descripcion del expediente
         * @return miConsulta
         */
	public Consulta atenderConsulta(String pidDoctor,String pidExp,String pdesc){
		Consulta miConsulta = null;
		miConsulta =((new MultiConsulta()).crear(pidDoctor,pidExp,pdesc));
		return miConsulta;
	}
	/**
	 * @param pidConsulta to set Identificador unico de una consulta
	 * @param pmed to set Medicina de una consulta
	 * @throws SQLException Se lanza un error de SQL
	 * @throws Exception se lanza cuando hay un error general.
	 */
	public void agregarMedicina(String pidConsulta,String pmed)throws SQLException, Exception{
		Consulta miConsulta = null;
		miConsulta =((new MultiConsulta()).buscarConsulta(pidConsulta));
		miConsulta.agregarMedicina(pmed);
	}
	/**
	 * @param pidConsulta	to set identificador unico de la Consulta
	 * @return listaMedicinas la lista de medicinas de una consulta
	 * @throws SQLException Se lanza un error de SQL
	 * @throws Exception se lanza cuando hay un error general.
	 */
	public ArrayList<String> registroMedicinas(String pidConsulta)throws SQLException, Exception{
		Consulta miConsulta = null;
		ArrayList<String> listaMedicinas =null;
		
		miConsulta =((new MultiConsulta()).buscarConsulta(pidConsulta));
		listaMedicinas = miConsulta.consultarMedicinas(pidConsulta);
		return listaMedicinas;
	}
	/**
	 * @return result los datos de un Doctor
	 */
	public String[] obtenerInformacion(){
		String[]result = new String[4];
		result[0]= this.getIdDoctor();
		result[1]= this.getNombre();
		result[2]=this.getEspecialidad();
		result[3]=this.getTelefono();
		return result;			
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String resul="";
		resul =  "Identificacion: " + getIdDoctor()
		+ "\nNombreDoctor: " + getNombre()
		+"\nEspecialidad: " + getEspecialidad()
		+"\nTelefono: "+ getTelefono();
		return resul;
	}
}
