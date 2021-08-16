package Modelo;

import Modelo.Carrito;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-08-15T23:22:42")
@StaticMetamodel(Snack.class)
public class Snack_ { 

    public static volatile SingularAttribute<Snack, String> descripcion;
    public static volatile ListAttribute<Snack, Carrito> carritoList;
    public static volatile SingularAttribute<Snack, Float> precio;
    public static volatile SingularAttribute<Snack, String> img;
    public static volatile SingularAttribute<Snack, Integer> idSnack;
    public static volatile SingularAttribute<Snack, String> nombre;

}