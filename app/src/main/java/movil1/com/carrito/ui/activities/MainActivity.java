package movil1.com.carrito.ui.activities    ;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import movil1.com.carrito.R;
import movil1.com.carrito.dal.Conexion;
import movil1.com.carrito.dal.Modelos.Producto;
import movil1.com.carrito.dal.bll.ProductoBLL;
import movil1.com.carrito.ui.adapters.ProductoAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProductos;
    private List<Producto> productoList = new ArrayList<>();
    private ProductoAdapter productoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lista de Productos");
        Conexion.iniciarConexion(this);
        recyclerViewProductos =  findViewById(R.id.recyclerProducto);
//        cargarDatos();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        productoAdapter =  new ProductoAdapter(this,productoList);
//
//        recyclerViewProductos.setAdapter(productoAdapter);
        recargarDatos();
        recyclerViewProductos.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        recargarDatos();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_carrito,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.menuCarrito:
                Intent intent = new Intent(this,CarritoActivity.class);
                startActivity(intent);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    public void cargarDatos(){
        productoList = ProductoBLL.selectAll();
        if(productoList.isEmpty()){
            ProductoBLL.insert("Adidas Predator","Zapatillas para jugar futbol",
                    R.drawable.img_adidas,80);
            ProductoBLL.insert("Rolex gold","Rolex version gold",
                    R.drawable.img_rolex,530);
            ProductoBLL.insert("Reebox asx","Zapatillas para correr",
                    R.drawable.img_reebok,65);
            ProductoBLL.insert("Fossil blak","Reloj manilla metalica",
                    R.drawable.img_reloj,280);
            ProductoBLL.insert("Polera Under Armour","Polera para hacer ejercicio",
                    R.drawable.img_polera,40);
            ProductoBLL.insert("Nike Mercurial","Zapatillas para jugar futbol",
                    R.drawable.img_nike,75);
            productoList = ProductoBLL.selectAll();
        }
    }

    private void recargarDatos() {
        cargarDatos();
        productoAdapter =  new ProductoAdapter(this,productoList);
        recyclerViewProductos.setAdapter(productoAdapter);
    }
}
