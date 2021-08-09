/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.DAO;

import Controlador.JPA.SalaJpaController;
import Controlador.Utiles;
import Modelo.Sala;

/**
 *
 * @author timoa
 */
public class SalaDAO {
    private Sala sala = new Sala();
    private SalaJpaController sjc = new SalaJpaController();
    private Utiles u = new Utiles();
    
    
    public void agregar(int nroAsientos, int nroSala, int filas, int columnas){
        try {
            sala.setIdSala(Integer.BYTES);
            sala.setNroAsientos(nroAsientos);
            sala.setNroSala(nroSala);
            sala.setEstado("activo");
            sala.setIdCartelera(null);
            sala.setAsientos(u.toJson(nroAsientos));
            sala.setFilas(filas);
            sala.setColumnas(columnas);
            sjc.create(sala);
        } catch (Exception e) {
        }
    }
}
