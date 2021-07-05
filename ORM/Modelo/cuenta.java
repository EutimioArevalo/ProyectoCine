/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author timoa
 */
@Entity
@Table(name = "cuenta")
public class cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_cuenta;
    private String usuario;
    private String clave;
    private String estado = "activo";
    @OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false, referencedColumnName = "id_persona")
    private persona persona;

    public persona getPersona() {
        return persona;
    }

    public void setPersona(persona persona) {
        this.persona = persona;
    }
    
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

    public Long getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(Long id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_cuenta != null ? id_cuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_cuenta fields are not set
        if (!(object instanceof cuenta)) {
            return false;
        }
        cuenta other = (cuenta) object;
        if ((this.id_cuenta == null && other.id_cuenta != null) || (this.id_cuenta != null && !this.id_cuenta.equals(other.id_cuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.cuenta[ id=" + id_cuenta + " ]";
    }
    
}
