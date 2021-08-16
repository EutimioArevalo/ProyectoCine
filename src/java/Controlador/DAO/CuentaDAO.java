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
 * @author Eutimio Arevalo
 */
public class CuentaDAO {

    private Cuenta cuenta = new Cuenta();
    private PersonaDAO pDAO = new PersonaDAO();
    private CuentaJpaController cjc = new CuentaJpaController();

    /**
     * Metodo para crear una cuenta mediante la cedula de una Persona
     * @param cedula Cedula de la persona insertada en la base con aterioridad.
     */
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

    /**
     * Metodo para editar una Cuenta.
     * @param id Identificador de la cuenta a editar
     * @param usuario Nuevo nombre de usuario
     * @param clave Nueva clave de usuario
     * @param estado Nuevo estado de la cuenta
     * @param persona Persona a la que le pertece la cuenta
     */
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

    /**
     * Metodo para verificar el Login
     * @param usuario Usuario a buscar en la bd
     * @param clave Clave a busacar en la bd
     * @return Retorna un Boolean segun el resultado de la busqueda.
     */
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

    /**
     * Metodo para buscar una persona mediante el Identificador de la Cuenta.
     * @param id Identificador de la cuenta.
     * @return Persona con la coinsidencia.
     */
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

    /**
     * Metodo para buscar el Identificador de una Cuenta mediante el Usuario.
     * @param usuario Usuario a buscar.
     * @return Identificador del usuario.
     */
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

    /**
     * Metodo para buscar una cuenta mediante el usuario
     * @param usuario usuario a buscar.
     * @return Cuenta con el usuario ingresado.
     */
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

    /**
     * Metodo para buscar rol mediante el usuario
     * @param usuario Usuario a buscar
     * @return Rol del usuario ingresado
     */
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

    /**
     * Metodo para buscar una cuenta mediante su Identificador
     * @param id Identificador de la Cuenta
     * @return Cuenta que coincide con el identificador.
     */
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
    
    /**
     * Metodo para buscar un Identificador de Cuenta mendiante la Cedula de una persona.
     * @param cedula Cedula de una persona.
     * @return Identificador con la coincidencia de la busqueda.
     */
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
