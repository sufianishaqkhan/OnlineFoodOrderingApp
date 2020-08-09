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

import com.squareup.picasso.Picasso;
import com.usamaqadeer.freshveg.R;
import com.usamaqadeer.freshveg.api.models.ProductsModel;
import com.usamaqadeer.freshveg.api.rest.RestClient;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private Context context;
    private List<ProductsModel> productsList;

    public ProductsAdapter(Context context, List<ProductsModel> productsList){
        this.context = context;
        this.productsList = productsList;
    }

    @Override
    @NonNull
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.products_list_item, parent, false);
        return new ProductsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder view, int position) {
        final ProductsModel selectedProduct = productsList.get(position);

        Picasso.get()
                .load(RestClient.IMAGE_URL +  selectedProduct.getP_pic())
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(view.ivProductImage);
        view.tvProductId.setText(Integer.toString(selectedProduct.getP_id()));
        view.tvProductName.setText(selectedProduct.getP_name());
        view.tvProductQty.setText(Integer.toString(selectedProduct.getP_qty()));
        view.tvProductPrice.setText("Rs " + selectedProduct.getP_unitprice());

        view.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, selectedProduct.getP_name(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() { return productsList.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout listItem;
        private TextView tvProductId, tvProductName, tvProductQty, tvProductPrice;
        private ImageView ivProductImage;

        ViewHolder(View itemView) {
            super(itemView);
            ivProductImage = itemView.findViewById(R.id.iv_product_img);
            tvProductId =  itemView.findViewById(R.id.tv_product_id);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvProductQty = itemView.findViewById(R.id.tv_product_qty);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price);
            listItem = itemView.findViewById(R.id.product_constraint);
        }
    }

    public ProductsModel getItem(int id) { return productsList.get(id); }
}
