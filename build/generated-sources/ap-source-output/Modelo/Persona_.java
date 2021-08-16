package Modelo;

import Modelo.Cuenta;
import Modelo.Factura;
import Modelo.Rol;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-15T23:22:42")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> apellidos;
    public static volatile SingularAttribute<Persona, Rol> idRol;
    public static volatile SingularAttribute<Persona, String> cedula;
    public static volatile ListAttribute<Persona, Factura> facturaList;
    public static volatile ListAttribute<Persona, Cuenta> cuentaList;
    public static volatile SingularAttribute<Persona, String> telefono;
    public static volatile SingularAttribute<Persona, Integer> idPersona;
    public static volatile SingularAttribute<Persona, String> email;
    public static volatile SingularAttribute<Persona, String> nombres;

}