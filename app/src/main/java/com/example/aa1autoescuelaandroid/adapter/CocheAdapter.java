package com.example.aa1autoescuelaandroid.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aa1autoescuelaandroid.R;
import com.example.aa1autoescuelaandroid.domain.Coche;
import com.example.aa1autoescuelaandroid.view.OnCocheClickListener;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CocheAdapter extends RecyclerView.Adapter<CocheAdapter.CocheHolder> {

    private static final String TAG = "CocheAdapter";
    private OnCocheClickListener listener;
    private List<Coche> cocheList;

    public CocheAdapter(List<Coche> cocheList, OnCocheClickListener listener) {
        this.cocheList = cocheList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CocheHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coche_item, parent, false);
        return new CocheHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CocheHolder holder, int position) {

        Coche coche = cocheList.get(position);

        String imageUriString = coche.getImage();
        if (imageUriString != null && !imageUriString.isEmpty()) {
            try {
                Uri imageUri = Uri.parse(imageUriString);
                holder.cocheImage.setImageURI(imageUri);
            } catch (Exception e) {
                Log.e(TAG, "Error al cargar imagen: " + e.getMessage());
                holder.cocheImage.setImageResource(R.drawable.ic_launcher_background);
            }
        } else {
            holder.cocheImage.setImageResource(R.drawable.ic_launcher_background);
        }

        holder.cocheMarca.setText(coche.getMarca());
        holder.cocheModelo.setText(coche.getModelo());
        holder.cocheTipoCambio.setText(coche.getTipoCambio());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCocheClick(coche);
            }
        });

        holder.deleteButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(coche);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cocheList.size();
    }

    public class CocheHolder extends RecyclerView.ViewHolder {

        private TextView cocheMarca;
        private TextView cocheModelo;
        private TextView cocheTipoCambio;
        private MaterialButton deleteButton;
        private ImageView cocheImage;

        public CocheHolder(@NonNull View itemView) {
            super(itemView);
            cocheImage = itemView.findViewById(R.id.item_coche_image);
            cocheMarca = itemView.findViewById(R.id.item_coche_marca);
            cocheModelo = itemView.findViewById(R.id.item_coche_modelo);
            cocheTipoCambio = itemView.findViewById(R.id.item_coche_tipo_cambio);
            deleteButton = itemView.findViewById(R.id.action_delete_coche);
        }
    }
}