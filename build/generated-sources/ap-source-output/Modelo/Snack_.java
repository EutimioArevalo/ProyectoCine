package Modelo;

import Modelo.Detallefactura;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-08T15:15:02")
@StaticMetamodel(Snack.class)
public class Snack_ { 

    public static volatile SingularAttribute<Snack, String> descripcion;
    public static volatile SingularAttribute<Snack, Float> precio;
    public static volatile SingularAttribute<Snack, String> img;
    public static volatile SingularAttribute<Snack, Integer> idSnack;
    public static volatile SingularAttribute<Snack, Detallefactura> idDetalleFactura;
    public static volatile SingularAttribute<Snack, String> nombre;

}