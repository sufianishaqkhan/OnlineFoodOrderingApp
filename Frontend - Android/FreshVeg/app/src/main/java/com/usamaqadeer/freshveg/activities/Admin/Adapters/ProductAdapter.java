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

import com.usamaqadeer.freshveg.activities.Admin.Model.Product;
import com.usamaqadeer.freshveg.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private ArrayList<Product> productlist;
    private Context context;

    public ProductAdapter(ArrayList<Product> filmList, Context context) {
        this.productlist = filmList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_row_items, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder myViewHolder, int i) {
        final Product product = productlist.get(i);
        myViewHolder.tvProductId.setText(product.getId());
        myViewHolder.tvProductName.setText(product.getName());
        myViewHolder.tvProductQty.setText(product.getQty());
        myViewHolder.tvProductPrice.setText(product.getQty());
//        myViewHolder.l
//        myViewHolder.imgProduct.setImageURI(pr);
//                .setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((MainActivity) context).gotoNextFragment(FilmsDetailFragment.newInstance(film));
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return productlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgProduct;
        public TextView tvProductId, tvProductName, tvProductQty, tvProductPrice;
        ConstraintLayout constraintLayout;
        public MyViewHolder(View view) {
            super(view);
            imgProduct = (ImageView) view.findViewById(R.id.imgv_product_img);
            tvProductId = (TextView) view.findViewById(R.id.tv_Product_Id);
            tvProductName = (TextView) view.findViewById(R.id.tv_Product_name);
            tvProductQty = (TextView) view.findViewById(R.id.tv_Product_qty);
            tvProductPrice = (TextView) view.findViewById(R.id.tv_Product_price);
            constraintLayout = itemView.findViewById(R.id.product_constraint);
        }
    }
}
