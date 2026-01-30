package com.example.aa1autoescuelaandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aa1autoescuelaandroid.R;
import com.example.aa1autoescuelaandroid.domain.Coche;

import java.util.List;

public class CocheAdapter extends RecyclerView.Adapter<CocheAdapter.CocheHolder> {

    private Context context;
    private List<Coche> cocheList;

    public CocheAdapter(Context context, List<Coche> cocheList) {
        this.context = context;
        this.cocheList = cocheList;
    }

    @NonNull
    @Override
    public CocheHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coche_item, parent, false);
        return new CocheHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CocheHolder holder, int position) {
        holder.cocheMarca.setText(cocheList.get(position).getMarca());
        holder.cocheModelo.setText(cocheList.get(position).getModelo());
        holder.cocheTipoCambio.setText(cocheList.get(position).getTipoCambio());
    }

    @Override
    public int getItemCount() {
        return cocheList.size();
    }

    public class CocheHolder extends RecyclerView.ViewHolder {

        private TextView cocheMarca;
        private TextView cocheModelo;
        private TextView cocheTipoCambio;

        public CocheHolder(@NonNull View itemView) {
            super(itemView);

            cocheMarca = itemView.findViewById(R.id.item_coche_marca);
            cocheModelo = itemView.findViewById(R.id.item_coche_modelo);
            cocheTipoCambio = itemView.findViewById(R.id.item_coche_tipo_cambio);
        }
    }
}
