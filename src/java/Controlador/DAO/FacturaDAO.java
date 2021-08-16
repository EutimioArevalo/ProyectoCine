/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.FacturaJpaController;
import Modelo.Detallefactura;
import Modelo.Factura;
import Modelo.Persona;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eutimio Arevalo
 */
public class FacturaDAO {
    Factura factura = new Factura();
    FacturaJpaController fjc = new FacturaJpaController();
    
    /**
     * Metodo para insertar una Factura en la Base de Datos.
     * @param total total de la compra.
     * @param cliente Nombre del cliente que hace la compra
     * @param persona Persona que hace la compra.
     * @param idDetalle Detalle de Factura asignado.
     */
    public void crear(float total, String cliente, Persona persona,Detallefactura idDetalle){
        try {
            factura.setIdFactura(Integer.BYTES);
            factura.setNroFactura("FAC: "+fjc.getFacturaCount()+1);
            factura.setTotal(total);
            factura.setCliente(cliente);
            factura.setPersona(persona);
            factura.setIdDetalle(idDetalle);
            fjc.create(factura);
        } catch (Exception e) {
        }
    }
    
    public List<Factura> obtenerLista(int idCuenta){
        CuentaDAO cuenta = new CuentaDAO();
        List<Factura> lista = fjc.findFacturaEntities();
        List<Factura> aux = new ArrayList<>();
        for (Factura factura1 : lista) {
            if(factura1.getPersona().getCedula().equals(cuenta.buscarCuentaId(idCuenta).getPersona().getCedula())){
                aux.add(factura1);
            }
        }
        return aux;
    }
}
