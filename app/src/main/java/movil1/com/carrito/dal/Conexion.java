package movil1.com.carrito.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Javier on 7/5/2019.
 */

public class Conexion extends SQLiteOpenHelper {

    private static final String NOMBREDB = "movil1.com.carrito.carrito";
    private static final int VERSION = 1;
    private static Conexion conexion;

    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static void iniciarConexion(Context context){
        if(conexion == null){
            conexion = new Conexion(context,NOMBREDB,null,VERSION);
        }
    }

    public static Conexion getConexionActual(){return conexion;}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE producto(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nombre TEXT NOT NULL," +
                "precio INTEGER NOT NULL," +
                "descripcion TEXT NOT NULL," +
                "imagen INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
