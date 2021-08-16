package Modelo;

import Modelo.Detallefactura;
import Modelo.Snack;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-15T23:22:42")
@StaticMetamodel(Carrito.class)
public class Carrito_ { 

    public static volatile SingularAttribute<Carrito, Detallefactura> idDetalle;
    public static volatile SingularAttribute<Carrito, Snack> snack;
    public static volatile SingularAttribute<Carrito, Integer> idcarrito;
    public static volatile SingularAttribute<Carrito, Integer> cantidad;
    public static volatile SingularAttribute<Carrito, String> nombreSnack;
    public static volatile SingularAttribute<Carrito, Float> precioT;
    public static volatile SingularAttribute<Carrito, Float> precioU;

}