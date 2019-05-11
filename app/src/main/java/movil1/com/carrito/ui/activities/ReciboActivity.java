package movil1.com.carrito.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import movil1.com.carrito.R;
import movil1.com.carrito.dal.Modelos.Carrito;
import movil1.com.carrito.dal.Modelos.Producto;
import movil1.com.carrito.ui.adapters.CarritoAdapter;
import movil1.com.carrito.ui.adapters.TipoAdaptador;

public class ReciboActivity extends AppCompatActivity {

    private RecyclerView recyclerViewRecibo;
    private CarritoAdapter carritoAdapter;
    private List<Carrito> reciboList = new ArrayList<>();
    private Button btnAceptar;
    private TextView lbTotal;
    private double totalApagar = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo);
        setTitle("Detalle del pedido");

        recyclerViewRecibo = findViewById(R.id.recyclerRecibo);
        btnAceptar = findViewById(R.id.btnAceptar);
        lbTotal = findViewById(R.id.lbTotalRecibo);

        reciboList = (List<Carrito>) getIntent().getSerializableExtra("carrito");
        totalApagar = getIntent().getDoubleExtra("total", 0);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRecibo.setLayoutManager(linearLayoutManager);
        carritoAdapter = new CarritoAdapter(this, reciboList, TipoAdaptador.RECIBO);

        recyclerViewRecibo.setAdapter(carritoAdapter);
        lbTotal.setText("$ " + totalApagar);
        CarritoActivity.carritoList.clear();
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReciboActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
