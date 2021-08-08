/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.FacturaJpaController;
import Controlador.JPA.PersonaJpaController;
import Modelo.Factura;
import Modelo.Persona;
import Modelo.Rol;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author timoa
 */
public class PersonaDAO {

    private Persona persona = new Persona();
    private FacturaJpaController fjc = new FacturaJpaController();
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
    
    

    public List<Factura> listFactura(int id) {
        List<Factura> list;
        if (id == 0) {
            list = new ArrayList<>();
            return list;
        }
        return null;
    }

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
    

}
