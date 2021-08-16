/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.CuentaJpaController;
import Modelo.Cuenta;
import Modelo.Persona;
import java.util.List;

/**
 *
 * @author Jonathan Javier
 */
public class CuentaDAO {

    private Cuenta cuenta = new Cuenta();
    private PersonaDAO pDAO = new PersonaDAO();
    private CuentaJpaController cjc = new CuentaJpaController();
    
    /**
     * Este método se encarga de crear una cartelera nueva
     *
     * @param horario es una cadena de texto con la hora en que se proyectará la pelicula
     * @param duracionCartelera es una cadena de texto con la fecha limite en que se mostrará la cartelera
     * @param precio es una dato de tipo float que contiene el precio a pagar por ver la pelicula
     * @param pelicula objeto de tipo Pelicula que permite asignarla a la cartelera
     * @param sala objeto de tipo Sala que permite asignarla a la cartelera
     */
    public void crear(String cedula) {
        try {
            cuenta.setIdCuenta(Integer.SIZE);
            cuenta.setUsuario(pDAO.buscar(cedula).getEmail());
            cuenta.setClave(pDAO.buscar(cedula).getCedula());
            cuenta.setEstado("activo");
            cuenta.setPersona(pDAO.buscar(cedula));
            cjc.create(cuenta);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public Boolean buscar(String usuario, String clave) {
        List<Cuenta> list = cjc.findCuentaEntities();
        Boolean cue = false;
        for (Cuenta c : list) {
            if (usuario.equalsIgnoreCase(c.getUsuario()) && clave.equalsIgnoreCase(c.getClave())) {
                cue = true;
                break;
            }
        }
        return cue;
    }
    
    public void editarCuenta(int idCuenta, String cedula){
        try {
            cuenta.setIdCuenta(idCuenta);
            cuenta.setUsuario(pDAO.buscar(cedula).getEmail());
            cuenta.setClave(pDAO.buscar(cedula).getCedula());
            cuenta.setEstado("activo");
            cuenta.setPersona(pDAO.buscar(cedula));
            cjc.edit(cuenta);
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void darDeBaja(int idCuenta, String cedula){
        try {
            cuenta.setIdCuenta(idCuenta);
            cuenta.setUsuario(pDAO.buscar(cedula).getEmail());
            cuenta.setClave(pDAO.buscar(cedula).getCedula());
            cuenta.setEstado("inactivo");
            cuenta.setPersona(pDAO.buscar(cedula));
            cjc.edit(cuenta);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
