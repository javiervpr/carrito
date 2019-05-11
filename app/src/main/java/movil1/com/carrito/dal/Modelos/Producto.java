package movil1.com.carrito.dal.Modelos;

import java.io.Serializable;

/**
 * Created by Javier on 7/5/2019.
 */

public class Producto implements Serializable{

    private int id;
    private String nombre;
    private String descripcion;
    private int imagen;
    private double precio;

    public Producto() {
    }

    public Producto(int id, String nombre, String descripcion, int imagen, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
