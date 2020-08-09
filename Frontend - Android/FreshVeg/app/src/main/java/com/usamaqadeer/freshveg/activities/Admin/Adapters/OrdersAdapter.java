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
import com.usamaqadeer.freshveg.api.models.OrderDetailsModel;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    private Context context;
    private List<OrderDetailsModel> orderDetailsList;

    public OrdersAdapter(Context context, List<OrderDetailsModel> orderDetailsList){
        this.context = context;
        this.orderDetailsList = orderDetailsList;
    }

    @Override
    @NonNull
    public OrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.order_details_list_item, parent, false);
        return new OrdersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.ViewHolder view, int position) {
        final OrderDetailsModel selectedOrderDetails = orderDetailsList.get(position);

        view.tvOId.setText(Integer.toString(selectedOrderDetails.getOd_id()));
        view.tvOName.setText(selectedOrderDetails.getU_NAME());
        view.tvOLocation.setText(selectedOrderDetails.getOd_delivered_loc());
        view.tvOPName.setText(selectedOrderDetails.getP_NAME());
        view.tvOPQty.setText(Integer.toString(selectedOrderDetails.getOd_qty()));
        view.tvOPPrice.setText(selectedOrderDetails.getOd_price());

        view.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, selectedOrderDetails.getOd_price(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() { return orderDetailsList.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout listItem;
        private TextView tvOId, tvOName, tvOLocation, tvOPName, tvOPQty, tvOPPrice;
        private ImageView ivOImage;

        ViewHolder(View itemView) {
            super(itemView);
            ivOImage = itemView.findViewById(R.id.iv_order_img);
            tvOId =  itemView.findViewById(R.id.tv_order_id);
            tvOName = itemView.findViewById(R.id.tv_order_name);
            tvOLocation = itemView.findViewById(R.id.tv_order_location);
            tvOPName = itemView.findViewById(R.id.tv_order_product_name);
            tvOPQty = itemView.findViewById(R.id.tv_order_product_qty);
            tvOPPrice = itemView.findViewById(R.id.tv_order_product_price);
            listItem = itemView.findViewById(R.id.order_constraint);
        }
    }

    public OrderDetailsModel getItem(int id) { return orderDetailsList.get(id); }
}
