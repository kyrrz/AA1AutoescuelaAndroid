package com.example.aa1autoescuelaandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aa1autoescuelaandroid.R;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;

import java.util.List;

public class AutoescuelaAdapter extends RecyclerView.Adapter<AutoescuelaAdapter.AutoescuelaHolder> {

    List<Autoescuela> autoescuelaList;

    public AutoescuelaAdapter(List<Autoescuela> autoescuelaList) {
        this.autoescuelaList = autoescuelaList;
    }

    @NonNull
    @Override
    public AutoescuelaAdapter.AutoescuelaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.autoescuela_item, parent , false   );
       return new AutoescuelaHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AutoescuelaAdapter.AutoescuelaHolder holder, int position) {
        holder.itemAutoescuelaName.setText(autoescuelaList.get(position).getNombre());
        holder.itemAutoescuelaCity.setText(autoescuelaList.get(position).getCiudad());
        holder.itemAutoescuelaRating.setText((int) autoescuelaList.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return autoescuelaList.size();
    }

    public class AutoescuelaHolder extends RecyclerView.ViewHolder {
        private TextView itemAutoescuelaName;
        private TextView itemAutoescuelaCity;
        private TextView itemAutoescuelaRating;

        public AutoescuelaHolder(@NonNull View itemView) {
            super(itemView);

            itemAutoescuelaName = itemView.findViewById(R.id.itemAutoescuelaName);
            itemAutoescuelaCity = itemView.findViewById(R.id.itemAutoescuelaCity);
            itemAutoescuelaRating = itemView.findViewById(R.id.itemAutoescuelaRating);


        }


    }
}
