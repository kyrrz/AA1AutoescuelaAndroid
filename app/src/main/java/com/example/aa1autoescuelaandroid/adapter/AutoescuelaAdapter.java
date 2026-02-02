package com.example.aa1autoescuelaandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aa1autoescuelaandroid.R;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.view.OnAutoescuelaClickListener;

import java.util.List;

public class AutoescuelaAdapter extends RecyclerView.Adapter<AutoescuelaAdapter.AutoescuelaHolder> {

    List<Autoescuela> autoescuelaList;
    OnAutoescuelaClickListener listener;

    public AutoescuelaAdapter(List<Autoescuela> autoescuelaList,
                              OnAutoescuelaClickListener listener) {
        this.autoescuelaList = autoescuelaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AutoescuelaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.autoescuela_item, parent , false);
        return new AutoescuelaHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AutoescuelaHolder holder, int position) {

        Autoescuela autoescuela = autoescuelaList.get(position);

        holder.itemAutoescuelaName.setText(autoescuela.getNombre());
        holder.itemAutoescuelaCity.setText(autoescuela.getCiudad());
        holder.itemAutoescuelaRating.setText(String.valueOf(autoescuela.getRating()));


        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAutoescuelaClick(autoescuela);
            }
        });
        holder.deleteButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(autoescuela);
            }
        });
    }

    @Override
    public int getItemCount() {
        return autoescuelaList.size();
    }

    public class AutoescuelaHolder extends RecyclerView.ViewHolder {
        private TextView itemAutoescuelaName;
        private TextView itemAutoescuelaCity;
        private TextView itemAutoescuelaRating;
        private ImageButton deleteButton;

        public AutoescuelaHolder(@NonNull View itemView) {
            super(itemView);

            itemAutoescuelaName = itemView.findViewById(R.id.itemAutoescuelaName);
            itemAutoescuelaCity = itemView.findViewById(R.id.itemAutoescuelaCity);
            itemAutoescuelaRating = itemView.findViewById(R.id.itemAutoescuelaRating);
            deleteButton = itemView.findViewById(R.id.action_delete_autoescuela);
        }
    }
}
