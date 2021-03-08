package com.example.homework3;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private List<Location> locations;

    public LocationAdapter(List<Location> locations){

        this.locations=locations;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        Context context=parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View locationView = inflater.inflate(R.layout.item_location, parent, false);
        ViewHolder viewHolder = new ViewHolder(locationView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Location location=locations.get(position);

        holder.locNameTV.setText(location.getName());
        holder.locDimensionTV.setText("Dimension: "+location.getDimension());
        holder.locTypeTV.setText("Type: "+location.getType());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView locNameTV;
        TextView locDimensionTV;
        TextView locTypeTV;

        public ViewHolder(View itemView){
            super(itemView);
            Log.d("help", "viewholder");
            locNameTV=itemView.findViewById(R.id.tv_locName);
            locDimensionTV=itemView.findViewById(R.id.tv_locDimension);
            locTypeTV=itemView.findViewById(R.id.tv_locType);
        }
    }
}
