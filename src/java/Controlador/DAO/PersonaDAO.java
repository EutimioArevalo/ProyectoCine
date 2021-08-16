/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.PersonaJpaController;
import Modelo.Persona;
import Modelo.Rol;
import java.util.List;

/**
 *
 * @author Jonathan Andrade, Eutimio Arévalo
 */
public class PersonaDAO {

    private Persona persona = new Persona();
    private PersonaJpaController pjc = new PersonaJpaController();

    /**
     * Metodo para insertar una Persona en la Base de Datos.
     *
     * @param nombres Nombres de la Persona
     * @param apellidos Apellidos de la Persona
     * @param cedula Cedula de la Persona
     * @param email Email de la Persona
     * @param telefono Telefono de la Persona
     * @param idrol Rol de la persona
     */
    public void crear(String nombres, String apellidos, String cedula, String email, String telefono, Rol idrol) {
        try {
            persona.setIdPersona(Integer.BYTES);
            persona.setNombres(nombres);
            persona.setApellidos(apellidos);
            persona.setCedula(cedula);
            persona.setEmail(email);
            persona.setTelefono(telefono);
            persona.setIdRol(idrol);
            pjc.create(persona);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Metodo para editar una persona
     *
     * @param id Identificador de la persona a Editar
     * @param nombres Nuevos nombres
     * @param apellidos Nuevos apellidos
     * @param cedula nueva cedula
     * @param email nuevo email
     * @param telefono nuevo telefono
     * @param idrol nuevo rol
     */
    public void editarPersona(int id, String nombres, String apellidos, String cedula, String email, String telefono, Rol idrol) {
        try {
            Persona aux  = pjc.findPersona(id);
            aux.setIdPersona(id);
            aux.setNombres(nombres);
            aux.setApellidos(apellidos);
            aux.setCedula(cedula);
            aux.setEmail(email);
            aux.setTelefono(telefono);
            aux.setIdRol(idrol);
            pjc.edit(aux);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Metodo para buscar una persona por su cedula
     *
     * @param cedula Cedula de la persona
     * @return Persona con la cedula ingresada
     */
    public Persona buscarCedula(String cedula) {
        List<Persona> list = pjc.findPersonaEntities();
        Persona per = null;
        for (Persona p : list) {
            if (cedula.equals(p.getCedula())) {
                per = p;
                break;
            }
        }
        return per;
    }

    /**
     * Metodo para buscar una persona por su Identificador
     *
     * @param id Identificador de la persona
     * @return Persona con el identificador ingresado
     */
    public Persona buscarPorId(int id) {
        List<Persona> list = pjc.findPersonaEntities();
        Persona per = null;
        for (Persona p : list) {
            if (id == p.getIdPersona()) {
                per = p;
                break;
            }
        }
        return per;
    }

    public Persona buscar(String cedula) {
        List<Persona> list = pjc.findPersonaEntities();
        Persona per = null;
        for (Persona p : list) {
            if (cedula.equals(p.getCedula())) {
                per = p;
                break;
            }
        }
        return per;
    }

}
