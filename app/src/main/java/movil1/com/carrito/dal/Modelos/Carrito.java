package movil1.com.carrito.dal.Modelos;

import java.io.Serializable;

/**
 * Created by Javier on 8/5/2019.
 */

public class Carrito implements Serializable {
    private int cantidad;
    private Producto producto;

    public Carrito() {
    }

    public Carrito(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
