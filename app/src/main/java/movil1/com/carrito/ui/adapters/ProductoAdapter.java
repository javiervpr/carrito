package movil1.com.carrito.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import movil1.com.carrito.R;
import movil1.com.carrito.dal.Modelos.Producto;
import movil1.com.carrito.ui.activities.CarritoActivity;

/**
 * Created by Javier on 7/5/2019.
 */

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    private Context context;
    List<Producto> productoList;

    public ProductoAdapter(Context context, List<Producto> productoList) {
        this.context = context;
        this.productoList = productoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_producto_item_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Producto objProducto = productoList.get(position);
        holder.lbNombre.setText(objProducto.getNombre());
        holder.lbPrecio.setText(String.valueOf(objProducto.getPrecio())+" $");
        Glide.with(context).load(objProducto.getImagen())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.imagen);

        holder.btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarritoActivity.adicionarItem(objProducto);
                Toast.makeText(context, "Presionaste el elemento "+objProducto.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView lbNombre;
        TextView lbPrecio;
        ImageView imagen;
        Button btnAdicionar;

        public ViewHolder(View itemView) {
            super(itemView);
            lbNombre = itemView.findViewById(R.id.lbNombre);
            lbPrecio = itemView.findViewById(R.id.lbPrecio);
            imagen = itemView.findViewById(R.id.imagenProducto);
            btnAdicionar = itemView.findViewById(R.id.btnAdicionar);
        }
    }
}
