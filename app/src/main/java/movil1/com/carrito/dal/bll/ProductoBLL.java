package movil1.com.carrito.dal.bll;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import movil1.com.carrito.dal.Conexion;
import movil1.com.carrito.dal.Modelos.Producto;

/**
 * Created by Javier on 7/5/2019.
 */

public class ProductoBLL {

    private static String tablename = "producto";

    public static long insert(String nombre,String descripcion,int imagen,double precio){
        Conexion conexion = Conexion.getConexionActual();
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues parametros = new ContentValues();
        parametros.put("nombre",nombre);
        parametros.put("precio",precio);
        parametros.put("descripcion",descripcion);
        parametros.put("imagen",imagen);
        long primaryKey = db.insert(tablename,null,parametros);
        return primaryKey;
    }

    public static boolean update(String nombre,String descripcion,int imagen,double precio,int id){
        Conexion conexion = Conexion.getConexionActual();
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues parametros = new ContentValues();
        parametros.put("nombre",nombre);
        parametros.put("precio",precio);
        parametros.put("descripcion",descripcion);
        parametros.put("imagen",imagen);
        int filas = db.update(tablename,parametros,"id = ?",
                new String[]{String.valueOf(id)});
        return (filas>0);
    }
    public static boolean delete(int id) {
        Conexion objConexion = Conexion.getConexionActual();
        SQLiteDatabase db = objConexion.getWritableDatabase();

        int filas = db.delete(tablename, "id = ?",
                new String[]{String.valueOf(id)});
        return (filas > 0);
    }

    private static Producto rowToObject(Cursor objCursor) {
        Producto objProducto = new Producto();
        objProducto.setId(objCursor.getInt(0));
        objProducto.setNombre(objCursor.getString(1));
        objProducto.setPrecio(objCursor.getDouble(2));
        objProducto.setDescripcion(objCursor.getString(3));
        objProducto.setImagen(objCursor.getInt(4));
        return objProducto;
    }
    public static ArrayList<Producto> selectAll() {
        ArrayList<Producto> listaPersonas = new ArrayList<>();
        Conexion objConexion = Conexion.getConexionActual();
        SQLiteDatabase db = objConexion.getReadableDatabase();
        String query = "SELECT id,nombre,precio,descripcion,imagen FROM " + tablename;
        Cursor objCursor = db.rawQuery(query, new String[]{});
        while (objCursor.moveToNext()) {
            Producto objProducto = rowToObject(objCursor);
            listaPersonas.add(objProducto);
        }
        return listaPersonas;
    }
    public static Producto select(int id) {
        Conexion objConexion = Conexion.getConexionActual();
        SQLiteDatabase db = objConexion.getReadableDatabase();
        String query = "SELECT id,nombre,precio,descripcion,imagen FROM " +
                tablename +
                " WHERE id = ?";
        Cursor objCursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        if (objCursor.moveToFirst()) {
            Producto objProducto = rowToObject(objCursor);
            return objProducto;
        }
        return null;
    }

}
