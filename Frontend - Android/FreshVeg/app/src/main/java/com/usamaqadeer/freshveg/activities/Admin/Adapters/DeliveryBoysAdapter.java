package com.usamaqadeer.freshveg.activities.Admin.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.usamaqadeer.freshveg.R;
import com.usamaqadeer.freshveg.api.models.DeliveryBoysModel;

import java.util.List;

public class DeliveryBoysAdapter extends RecyclerView.Adapter<DeliveryBoysAdapter.ViewHolder> {
    private Context context;
    private List<DeliveryBoysModel> deliveryBoysList;

    public DeliveryBoysAdapter(Context context, List<DeliveryBoysModel> deliveryBoysList){
        this.context = context;
        this.deliveryBoysList = deliveryBoysList;
    }

    @Override
    @NonNull
    public DeliveryBoysAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.delivery_boys_list_item, parent, false);
        return new DeliveryBoysAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryBoysAdapter.ViewHolder view, int position) {
        final DeliveryBoysModel selectedDeliveryBoy = deliveryBoysList.get(position);

        view.tvDBId.setText(Integer.toString(selectedDeliveryBoy.getDb_id()));
        view.tvDBName.setText(selectedDeliveryBoy.getDb_name());
        view.tvDBEmail.setText(selectedDeliveryBoy.getDb_email());
        view.tvShiftStart.setText(selectedDeliveryBoy.getDb_shiftstart());
        view.tvShiftEnd.setText(selectedDeliveryBoy.getDb_shiftend());

        view.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, selectedDeliveryBoy.getDb_name(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() { return deliveryBoysList.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout listItem;
        private TextView tvDBId, tvDBName, tvDBEmail, tvShiftStart, tvShiftEnd;
        private ImageView ivDBImage;

        ViewHolder(View itemView) {
            super(itemView);
            ivDBImage = itemView.findViewById(R.id.iv_db_img);
            tvDBId =  itemView.findViewById(R.id.tv_db_id);
            tvDBName = itemView.findViewById(R.id.tv_db_name);
            tvDBEmail = itemView.findViewById(R.id.tv_db_email);
            tvShiftStart = itemView.findViewById(R.id.tv_db_shift_start);
            tvShiftEnd = itemView.findViewById(R.id.tv_db_shift_end);
            listItem = itemView.findViewById(R.id.db_constraint);
        }
    }

    public DeliveryBoysModel getItem(int id) { return deliveryBoysList.get(id); }
}

