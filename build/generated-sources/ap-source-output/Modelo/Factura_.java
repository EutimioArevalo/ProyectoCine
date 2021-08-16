package Modelo;

import Modelo.Detallefactura;
import Modelo.Persona;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-15T23:22:42")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, String> cliente;
    public static volatile SingularAttribute<Factura, Float> total;
    public static volatile SingularAttribute<Factura, String> nroFactura;
    public static volatile SingularAttribute<Factura, Persona> persona;
    public static volatile SingularAttribute<Factura, Detallefactura> idDetalle;
    public static volatile SingularAttribute<Factura, Integer> idFactura;

}