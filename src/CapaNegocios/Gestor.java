/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author arser
 */
public class Gestor {
    public Expediente buscarExpediente(String pid) throws Exception{
        return (new MultiExpediente()).buscar(pid);
    }
    
    public String[][] obtenerListaConsultas(String pid){
        String[][] datos = null;
        try{
            datos = (new MultiConsulta()).obtenerListaDeConsultasDeUnExpediente(pid);
        }catch(Exception e){
            
        }
        return datos;
    }
    
    public void borrarExpediente(String pid) throws Exception{
        (new MultiExpediente()).borrar(pid);
    }
    
    public String crearExpediente(String pcedula, String pnombre, String pdir, String ptel, LocalDate pnacimiento) throws Exception{
        String result = "";
        Expediente unExp = (new MultiExpediente()).crear(pcedula, pnombre, pdir, ptel, pnacimiento);
        if(unExp == null){
            result = "El Expediente ya existe";
        }else{
            result = "El expediente se ha creado con éxito";
        }
        return result;
    }
    
    /**
	 * @param pidDoctor to set pidDoctor Identificador unico de un Doctor
	 * @return idDoctorValido Valido que un Doctor se pueda registrar
	 */
	public boolean sePuedeRegistrar(String pidDoctor){
		int cantDoctores = 0;
		boolean idDoctorValido = false;
		
		try {
			cantDoctores = ((new MultiDoctor()).verificarDoctorRegistrado(pidDoctor));
		} catch (Exception e) {
			e.printStackTrace();
		}	
		if(cantDoctores == 0){
			idDoctorValido = true;
		}else{
			idDoctorValido =false;
		}
		return idDoctorValido;
	}
	
	/**
	 * @param pidDoctor to set Identificador unico de un Doctor 
	 * @param pnombre to set Nombre de un Doctor
	 * @param pespecialidad to set Especilidad de un Doctor
	 * @param ptelefono to set Numero Tlefonico de un Doctor
	 * @return msm Mensaje  de un Doctor registrado
	 */
	public String registrarDoctor(String pidDoctor,String pnombre, String pespecialidad, String ptelefono){
		String msm= "";
		Doctor miDoctor = null;
		boolean idDoctorValido = true;
		idDoctorValido = sePuedeRegistrar(pidDoctor);
		
		if(idDoctorValido =true){
		try {
			miDoctor = ((new MultiDoctor()).crear(pidDoctor, pnombre, pespecialidad, ptelefono));
		} catch (Exception e) {
				e.printStackTrace();
			}
		msm = "Doctor Registrado";
		}else{
			msm = "Error"
					+"\nRegistro no se completo"
					+"\nDoctor se ha encontrado registrado";	
		}
		return msm;
	}
	/**
	 * @param pced to set Cedula de un Paciente
	 * @param pnombrePac to set Nombre de un Paciente
	 * @param pdir to set Direccion de un Paciente
	 * @param pnac to set Fecha de Naciemiento de un Paciente
	 * @param pidDoctor to set Identificador unico de un Doctor
	 * @return msm Mensaje de un Expediente Registrado
	 */
	public String registrarExpediente(String pced,String pnombrePac, String pdir, LocalDate pnac,String pidDoctor, String ptel){
		Doctor miDoctor=null;
		Expediente miExpediente = null;
		String msm="";
		
		miDoctor = obtenerDoctor(pidDoctor);
		
		try {
			miExpediente = miDoctor.abrirExpediente(pced, pnombrePac, pdir, ptel, pnac);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		msm = "Expediente Registrado";
		return msm;
	}
        
        public String[] obtenerDatosExpediente(String pid)throws Exception{
            MultiExpediente unMulti = new MultiExpediente();
            Expediente unExpediente = unMulti.buscar(pid);
            if(unExpediente == null){
                return null;
            }else{
                return unMulti.buscar(pid).obtenerInformacion();
            }
        }
        
	/**
	 * @param pidExp to set Identificador unico de un Expediente
	 * @param pdesc to set descripcion de una COnsulta
	 * @param pidDoctor to set Identificador unidco de un Doctor
	 * @return msm Mensaje de una COsulta Registrada
	 */
	public String registrarConsultas(String pidDoctor,String pidExp, String pdesc, ArrayList<String> medicinas){
            Doctor miDoctor=null;
		Consulta miConsulta = null;
		String msm="";
		miDoctor = this.obtenerDoctor(pidDoctor);
		miConsulta= miDoctor.atenderConsulta(pidDoctor,pidExp,pdesc);
                for(int i = 0; i < medicinas.size(); i++){
                    this.agregarMedicinas(miConsulta.getIdConsulta(), medicinas.get(i), pidDoctor);
                }
		msm ="Consulta Registrada";
		return msm;
	}
	/**
	 * @param pidConsulta to set Identificador unico de un Doctor
	 * @param pmed to set Medicina Recetada en una Consulta
	 * @param pidDoctor to set Identificador unico de una Doctor
	 */
	public void agregarMedicinas(String pidConsulta,String pmed,String pidDoctor){
		Doctor miDoctor=null;
		String msm="";
		
		miDoctor = obtenerDoctor(pidDoctor);
		try {
			miDoctor.agregarMedicina(pidConsulta,pmed);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * @param pidDoctor to set Identificador unico de un Doctor
	 * @param pidConsulta to set Identificador unico de una Consulta
	 * @return misMedicinas Lista de todas las medicinas de una consulta
	 */
	public ArrayList<String> registroMedicinas(String pidDoctor,String pidConsulta){
		ArrayList<String>misMedicinas = null;
		Doctor miDoctor=null;
		String msm="";
		
		miDoctor = obtenerDoctor(pidDoctor);
		try {
			misMedicinas = miDoctor.registroMedicinas(pidConsulta);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return misMedicinas;
		
	}
	/**
	 * @param pidDoctor to set identificador unico de un Doctor
	 * @return miDoctor(Objeto)
	 */
	public Doctor obtenerDoctor(String pidDoctor){
		Doctor miDoctor=null;
        try {
            miDoctor = ((new MultiDoctor()).buscar(pidDoctor));
        } catch (Exception ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
		return miDoctor;
	}
	/**
	 * @param pidDoctor to set identificador unico de un Doctor
	 * @return datosDoctor lista de datos de un Doctor
	 */
	public String[] buscarDoctor(String pidDoctor){
	   Doctor miDoctor = null;
	   String[]datosDoctor = new String[4];
	   
		try {
			miDoctor = ((new MultiDoctor()).buscar(pidDoctor));
		} catch (Exception e) {

			e.printStackTrace();
		}
		datosDoctor = miDoctor.obtenerInformacion();
		return datosDoctor;
	}
	/**
	 * @return listaDatosDoctores Lista de identificaciones y nombres de todos los doctores
	 */
	public String[][] obtenerListaDoctores(){
		String[][] listaDatosDoctores = null;
		 try {
			listaDatosDoctores= ((new MultiDoctor()).obtenerDatosDoctores());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		 
		return listaDatosDoctores;
	}
        
        public String[][] obtenerListaExpedientes() throws Exception{
            return new MultiExpediente().obtenerListaExpedientes();
        }
        
        public String obtenerNombreDoctor(String pid) throws Exception{
            return new MultiDoctor().obtenerNombreDoctor(pid);
        }
        
        public String[] obtenerDatosConsulta(String pid) throws Exception{
            return new MultiConsulta().buscarConsulta(pid).obtenerInformacionDeUnaConsulta();
            
        }
        
        public ArrayList<String> obtenerMedicinasDeConsulta(String pid){
            ArrayList<String> resul = new MultiConsulta().obtenerListaMedicinas(pid);
            return resul;
        }
}
