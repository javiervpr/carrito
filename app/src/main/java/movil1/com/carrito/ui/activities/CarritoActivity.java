package movil1.com.carrito.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import movil1.com.carrito.R;
import movil1.com.carrito.dal.Modelos.Carrito;
import movil1.com.carrito.dal.Modelos.Producto;
import movil1.com.carrito.ui.adapters.CarritoAdapter;
import movil1.com.carrito.ui.adapters.TipoAdaptador;

public class CarritoActivity extends AppCompatActivity {

    public static List<Carrito> carritoList = new ArrayList<>();
    private CarritoAdapter carritoAdapter;
    private RecyclerView recyclerViewCarrito;
    private Button btnPagar;
    private Button btnBorrarCarrito;
    private double totalAPagar=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        setTitle("Carrito de compras");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerViewCarrito = findViewById(R.id.recyclerCarrito);
        btnPagar =  findViewById(R.id.btnPagar);
        btnBorrarCarrito = findViewById(R.id.btnBorrarCarrito);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCarrito.setLayoutManager(linearLayoutManager);

        recargarDatos();
        actualizarTotal();
        acciones();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void acciones(){
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(carritoList.isEmpty()){
                    Toast.makeText(CarritoActivity.this, "No hay ningun producto en el carrito",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(CarritoActivity.this,ReciboActivity.class);
                intent.putExtra("carrito", (Serializable) carritoList);
                totalAPagar = calcularTotal();
                intent.putExtra("total",totalAPagar);
                startActivity(intent);
            }
        });

        btnBorrarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarCarrito();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        recargarDatos();
        actualizarTotal();
    }

    public static void adicionarItem(Producto producto) {
        for (Carrito actual: carritoList) {
            if(actual.getProducto().getId()==(producto.getId())){
                int index = carritoList.indexOf(actual);
                carritoList.get(index).setCantidad(actual.getCantidad()+1);
                return;

            }
        }
        carritoList.add(new Carrito(1,producto));
    }

    public void borrarCarrito() {
        carritoList.clear();
        totalAPagar=calcularTotal();
        btnPagar.setText("PAGAR (Total: "+(int)calcularTotal()+"$)");
        recargarDatos();
    }
    public void actualizarTotal(){
        totalAPagar=calcularTotal();
        btnPagar.setText("PAGAR (Total: "+(int)calcularTotal()+"$)");
    }
    public void recargarDatos(){

        carritoAdapter =  new CarritoAdapter(this,carritoList, TipoAdaptador.CARRITO);
        recyclerViewCarrito.setAdapter(carritoAdapter);
    }
    public double calcularTotal(){
        double total=0;
        for (Carrito actual : carritoList){
            total+= actual.getProducto().getPrecio() * actual.getCantidad();
        }
        return total;
    }

}
