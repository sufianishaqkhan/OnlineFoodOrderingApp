package com.usamaqadeer.freshveg.activities.Admin.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.usamaqadeer.freshveg.R;
import com.usamaqadeer.freshveg.api.models.CategoriesModel;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private Context context;
    private List<CategoriesModel> categoriesList;

    public CategoriesAdapter(Context context, List<CategoriesModel> categoriesList){
        this.context = context;
        this.categoriesList = categoriesList;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.categories_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder view, int position) {
        final CategoriesModel selectedCategory = categoriesList.get(position);

        view.tvCId.setText(Integer.toString(selectedCategory.getC_id()));
        view.tvCType.setText(selectedCategory.getC_categorytype());

        view.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, selectedCategory.getC_categorytype(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() { return categoriesList.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout listItem;
        private TextView tvCId, tvCType;

        ViewHolder(View itemView) {
            super(itemView);
            listItem = itemView.findViewById(R.id.list_item);
            tvCId = itemView.findViewById(R.id.tv_c_id);
            tvCType = itemView.findViewById(R.id.tv_c_type);
        }
    }

    public CategoriesModel getItem(int id) { return categoriesList.get(id); }
}
