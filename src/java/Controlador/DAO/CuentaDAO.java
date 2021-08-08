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
 * @author timoa
 */
public class CuentaDAO {

    private Cuenta cuenta = new Cuenta();
    private PersonaDAO pDAO = new PersonaDAO();
    private CuentaJpaController cjc = new CuentaJpaController();

    public void crear(String cedula) {
        try {
            cuenta.setIdCuenta(Integer.BYTES);
            cuenta.setUsuario(pDAO.buscarCedula(cedula).getEmail());
            cuenta.setClave(pDAO.buscarCedula(cedula).getCedula());
            cuenta.setEstado("activo");
            cuenta.setPersona(pDAO.buscarCedula(cedula));
            cjc.create(cuenta);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void editar(String usuario, String clave, int id) {
        try {
            cuenta.setIdCuenta(id);
            cuenta.setUsuario(usuario);
            cuenta.setClave(clave);
            cuenta.setEstado("activo");
            cuenta.setPersona(buscarPersona(id));
            cjc.edit(cuenta);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void editarA(int id, String usuario, String clave, String estado, Persona persona) {
        try {
            cuenta.setIdCuenta(id);
            cuenta.setUsuario(usuario);
            cuenta.setClave(clave);
            cuenta.setEstado(estado);
            cuenta.setPersona(persona);
            cjc.edit(cuenta);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public Boolean verificarLogin(String usuario, String clave) {
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

    public Persona buscarPersona(int id) {
        List<Cuenta> list = cjc.findCuentaEntities();
        Persona cue = null;
        for (Cuenta c : list) {
            if (id == c.getIdCuenta()) {
                cue = c.getPersona();
                break;
            }
        }

        return cue;
    }

    public int buscarIDCuenta(String usuario) {
        List<Cuenta> list = cjc.findCuentaEntities();
        int per = 0;
        for (Cuenta c : list) {
            if (usuario.equals(c.getUsuario())) {
                per = c.getIdCuenta();
                break;
            }
        }
        return per;
    }

    public Cuenta buscarCuenta(String usuario) {
        List<Cuenta> list = cjc.findCuentaEntities();
        Cuenta cue = null;
        for (Cuenta c : list) {
            if (usuario.equals(c.getUsuario())) {
                cue = c;
                break;
            }
        }
        return cue;
    }

    public int buscaridROl(String usuario) {
        List<Cuenta> list = cjc.findCuentaEntities();
        int per = 0;
        for (Cuenta c : list) {
            if (usuario.equals(c.getUsuario())) {
                per = c.getPersona().getIdRol().getIdRol();
                break;
            }
        }
        return per;
    }

    public Cuenta buscarCuentaId(int id) {
        List<Cuenta> list = cjc.findCuentaEntities();
        Cuenta per = null;
        for (Cuenta c : list) {
            if (id == c.getIdCuenta()) {
                per = c;
                break;
            }
        }
        return per;
    }
    
    public int buscarPorCedula(String cedula){
        int idpersona = pDAO.buscarCedula(cedula).getIdPersona();
        List<Cuenta> list = cjc.findCuentaEntities();
        int idCuenta = 0;
        for (Cuenta c : list) {
            if (idpersona == c.getPersona().getIdPersona()) {
                idCuenta = c.getIdCuenta();
                break;
            }
        }
        return idCuenta;
    }
}
