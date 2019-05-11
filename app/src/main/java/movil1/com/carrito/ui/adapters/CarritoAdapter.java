package movil1.com.carrito.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import movil1.com.carrito.R;
import movil1.com.carrito.dal.Modelos.Carrito;
import movil1.com.carrito.ui.activities.CarritoActivity;

/**
 * Created by Javier on 7/5/2019.
 */

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder> {

    private Context context;
    private List<Carrito> carritoList = new ArrayList<>();
    private TipoAdaptador tipo;

    public CarritoAdapter(Context context, List<Carrito> carritoList,TipoAdaptador tipo) {
        this.context = context;
        this.carritoList = carritoList;
        this.tipo = tipo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_carrito_item_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Carrito objCarrito = carritoList.get(position);

        holder.lbPrecio.setText("$"+String.valueOf(objCarrito.getProducto().getPrecio()));
        holder.lbNombre.setText(objCarrito.getProducto().getNombre());
        holder.lbCantidad.setText("x"+String.valueOf(objCarrito.getCantidad()));

        if(tipo == TipoAdaptador.RECIBO){
            holder.btnBorrar.setVisibility(View.INVISIBLE);
        }
        holder.btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carritoList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return carritoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView lbPrecio,lbNombre,lbCantidad;
        ImageButton btnBorrar;

        public ViewHolder(View itemView) {
            super(itemView);
            lbPrecio = itemView.findViewById(R.id.lbPrecioCarrito);
            lbNombre = itemView.findViewById(R.id.lbNombreProducto);
            lbCantidad = itemView.findViewById(R.id.lbCantidad);
            btnBorrar = itemView.findViewById(R.id.btnBorrarItem);


        }
    }
}
