/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.CuentaJpaController;
import Controlador.JPA.DetallefacturaJpaController;
import Controlador.JPA.PersonaJpaController;
import Modelo.Cuenta;
import Modelo.Detallefactura;
import Modelo.Persona;
import Modelo.Rol;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Jonathan Javier
 */
public class PersonaDAO{

    private Persona persona = new Persona();
    private PersonaJpaController pjc = new PersonaJpaController();
    private DetallefacturaJpaController djc = new DetallefacturaJpaController();
    private CuentaJpaController c = new CuentaJpaController();

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
    
    public void crear(String nombres, String apellidos, String cedula, String email, String telefono, Rol rol) {
        try {
            persona.setIdPersona(Integer.BYTES);
            persona.setNombres(nombres);
            persona.setApellidos(apellidos);
            persona.setCedula(cedula);
            persona.setEmail(email);
            persona.setTelefono(telefono);
            persona.setCuentaList(new ArrayList<>());
            persona.setDetallefacturaList(new ArrayList<>());
            persona.setRolidRol(rol);
            persona.setIdRol(rol.getIdRol());
            pjc.create(persona);
            persona = null;
        } catch (Exception e) {
            System.out.println("error al crear");
            e.getMessage();
        }
    }

    public void editarPersona(int id, String nombres, String apellidos, String cedula, String email, String telefono, Rol rol) {
        try {
            persona.setIdPersona(id);
            persona.setNombres(nombres);
            persona.setApellidos(apellidos);
            persona.setCedula(cedula);
            persona.setEmail(email);
            persona.setTelefono(telefono);
            persona.setCuentaList(listaCuenta(id));
            persona.setRolidRol(rol);
            persona.setDetallefacturaList(listaFactura(id));
            persona.setIdRol(rol.getIdRol());
            pjc.edit(persona);
        } catch (Exception e) {
            System.out.println("Error al editar " + e.getMessage());
            e.getMessage();
        }
    }
    
    public List<Detallefactura> listaFactura(Integer id){
        List<Detallefactura> facturas = djc.findDetallefacturaEntities();
        List<Detallefactura> lista = new ArrayList<>();
        for (Detallefactura fac : facturas) {
            if(Objects.equals(id, fac.getPersonaList())){
                lista.add(fac);
            }
        }
        return lista;
    }
    
     public List<Cuenta> listaCuenta(Integer id){
        List<Cuenta> cuentas = c.findCuentaEntities();
        List<Cuenta> lista = new ArrayList<Cuenta>();
        for (Cuenta cue : cuentas) {
            if(Objects.equals(id, cue.getPersona().getIdPersona())){
                lista.add(cue);
            }
        }
        return lista;
    }
}
