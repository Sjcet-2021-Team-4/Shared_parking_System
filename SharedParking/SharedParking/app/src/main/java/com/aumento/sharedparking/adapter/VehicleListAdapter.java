package com.aumento.sharedparking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aumento.sharedparking.R;
import com.aumento.sharedparking.modelclass.VehicleModelClass;

import java.util.List;


public class VehicleListAdapter extends RecyclerView.Adapter<VehicleListAdapter.MyViewHolder> {

    private List<VehicleModelClass> vehicleList;
    private Context mCtx;

    public VehicleListAdapter(List<VehicleModelClass> vehicleList, Context mCtx) {
        this.vehicleList = vehicleList;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_vehicle_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final VehicleModelClass lists = vehicleList.get(position);

        holder.vname.setText(lists.getVehicle_number());
        holder.vtype.setText(lists.getVehicle_type());


    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView vname, vtype;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            vname = (TextView) itemView.findViewById(R.id.vnameTV);
            vtype = (TextView) itemView.findViewById(R.id.vtypeTV);

        }
    }
}
