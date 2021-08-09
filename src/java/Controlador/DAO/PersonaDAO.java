/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.FacturaJpaController;
import Controlador.JPA.PersonaJpaController;
import Modelo.Cuenta;
import Modelo.Factura;
import Modelo.Persona;
import Modelo.Rol;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonathan Javier
 */
public class PersonaDAO {

    private Persona persona = new Persona();
    private PersonaJpaController pjc = new PersonaJpaController();

    public void crear(String nombres, String apellidos, String cedula, String email, String telefono, Rol rol) {
        try {
            persona.setIdPersona(Integer.BYTES);
            persona.setNombres(nombres);
            persona.setApellidos(apellidos);
            persona.setCedula(cedula);
            persona.setEmail(email);
            persona.setTelefono(telefono);
            persona.setIdRol(rol);
            pjc.create(persona);
        } catch (Exception e) {
            e.getMessage();
        }
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

    public void editarPersona(int id, String nombres, String apellidos, String cedula, String email, String telefono, Rol rol) {
        try {
            persona.setIdPersona(id);
            persona.setNombres(nombres);
            persona.setApellidos(apellidos);
            persona.setCedula(cedula);
            persona.setEmail(email);
            persona.setTelefono(telefono);
            persona.setIdRol(rol);
            System.out.println("Id Editado: "+persona.getIdPersona());
            System.out.println("Id Editado: "+persona.getNombres());
            pjc.edit(persona);
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void darDeBaja(){
        
    }
}
