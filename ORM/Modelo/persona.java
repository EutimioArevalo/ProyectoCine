/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author timoa
 */
@Entity
@Table(name = "persona")
public class persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_persona;
    private String nombres;
    private String apellidos;
    @Column(length = 10, unique = true)
    private String cedula;
    private String telefono;
    private String email;
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false, referencedColumnName = "id_rol")
    private rol rol;
    @OneToOne(mappedBy = "cuenta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private cuenta cuenta;
    @OneToOne(mappedBy = "factura", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private factura factura;
    public factura getFactura() {
        return factura;
    }

    public void setFactura(factura factura) {
        this.factura = factura;
    }
    
    public cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
    
    
    public rol getRol() {
        return rol;
    }

    public void setRol(rol rol) {
        this.rol = rol;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    public Long getId_persona() {
        return id_persona;
    }

    public void setId_persona(Long id_persona) {
        this.id_persona = id_persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_persona != null ? id_persona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_persona fields are not set
        if (!(object instanceof persona)) {
            return false;
        }
        persona other = (persona) object;
        if ((this.id_persona == null && other.id_persona != null) || (this.id_persona != null && !this.id_persona.equals(other.id_persona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.persona[ id=" + id_persona + " ]";
    }
    
}
