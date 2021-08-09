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
    
    public void editarCuenta(int id, String cedula){
        try {
            cuenta.setIdCuenta(id);
            cuenta.setUsuario(pDAO.buscar(cedula).getEmail());
            cuenta.setClave(pDAO.buscar(cedula).getCedula());
            cuenta.setEstado("activo");
            cuenta.setPersona(pDAO.buscar(cedula));
            cjc.edit(cuenta);
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void darDeBaja(int idPersona){
        try {
            List <Cuenta> listaCuentas = cjc.findCuentaEntities();
            for (Cuenta cuenta1 : listaCuentas) {
                if (cuenta1.getPersona().getIdPersona() == idPersona) {
                    cuenta1.setEstado("inactivo");
                } else {
                    System.out.println("No se encontró la cuenta");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
