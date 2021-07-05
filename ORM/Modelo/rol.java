/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author timoa
 */
@Entity
@Table(name = "rol")
public class rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_rol;
    private String nombre;
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<persona> listaPersona = new ArrayList<persona>();

    public List<persona> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(List<persona> listaPersona) {
        this.listaPersona = listaPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId_rol() {
        return id_rol;
    }

    public void setId_rol(Long id_rol) {
        this.id_rol = id_rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_rol != null ? id_rol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_rol fields are not set
        if (!(object instanceof rol)) {
            return false;
        }
        rol other = (rol) object;
        if ((this.id_rol == null && other.id_rol != null) || (this.id_rol != null && !this.id_rol.equals(other.id_rol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.rol[ id=" + id_rol + " ]";
    }
    
}
