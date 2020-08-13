package com.usamaqadeer.freshveg.activities.DeliveryBoy.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.usamaqadeer.freshveg.R;
import com.usamaqadeer.freshveg.activities.Admin.Fragments.AssignOrdersFragment;
import com.usamaqadeer.freshveg.activities.DeliveryBoy.Fragments.ViewOrdersFragment;
import com.usamaqadeer.freshveg.api.models.OrderAssignsModel;

import java.util.List;

public class ViewOrdersAdapter extends RecyclerView.Adapter<ViewOrdersAdapter.ViewHolder> {
    private Context context;
    private List<OrderAssignsModel> orderAssignsList;

    public ViewOrdersAdapter(Context context, List<OrderAssignsModel> orderAssignsList){
        this.context = context;
        this.orderAssignsList = orderAssignsList;
    }

    @Override
    @NonNull
    public ViewOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.order_assigns_list_item, parent, false);
        return new ViewOrdersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewOrdersAdapter.ViewHolder view, int position) {
        final OrderAssignsModel selectedOrderAssign = orderAssignsList.get(position);

        view.tvOId.setText(Integer.toString(selectedOrderAssign.getOa_id()));
        view.tvOName.setText(selectedOrderAssign.getU_name());
        view.tvOStatus.setText(selectedOrderAssign.getOa_status());
        view.tvOPName.setText(selectedOrderAssign.getP_name());
        view.tvOPQty.setText(Integer.toString(selectedOrderAssign.getOd_qty()));
        view.tvOPPrice.setText(selectedOrderAssign.getOd_price());

        view.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewOrdersFragment.u_id = selectedOrderAssign.getOa_u_id();
                ViewOrdersFragment.changeStatusToDeliveredPopUp.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() { return orderAssignsList.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout listItem;
        private TextView tvOId, tvOName, tvOStatus, tvOPName, tvOPQty, tvOPPrice;
        private ImageView ivOImage;

        ViewHolder(View itemView) {
            super(itemView);
            ivOImage = itemView.findViewById(R.id.iv_order_img);
            tvOId =  itemView.findViewById(R.id.tv_order_id);
            tvOName = itemView.findViewById(R.id.tv_order_name);
            tvOStatus = itemView.findViewById(R.id.tv_order_status);
            tvOPName = itemView.findViewById(R.id.tv_order_product_name);
            tvOPQty = itemView.findViewById(R.id.tv_order_product_qty);
            tvOPPrice = itemView.findViewById(R.id.tv_order_product_price);
            listItem = itemView.findViewById(R.id.order_constraint);
        }
    }

    public OrderAssignsModel getItem(int id) { return orderAssignsList.get(id); }
}

