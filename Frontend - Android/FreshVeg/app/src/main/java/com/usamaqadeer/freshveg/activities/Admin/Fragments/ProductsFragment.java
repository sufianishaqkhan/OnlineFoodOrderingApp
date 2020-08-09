package com.usamaqadeer.freshveg.activities.Admin.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.usamaqadeer.freshveg.R;
import com.usamaqadeer.freshveg.activities.Admin.Adapters.ProductsAdapter;
import com.usamaqadeer.freshveg.api.models.ProductsModel;
import com.usamaqadeer.freshveg.api.rest.RestAPI;
import com.usamaqadeer.freshveg.api.rest.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProductsAdapter adapter;

    public ProductsFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        getProductsData();

        return view;
    }

    /*GET PRODUCTS API CALL*/
    private void getProductsData(){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<List<ProductsModel>> call = service.getProducts();
        call.enqueue(new Callback<List<ProductsModel>>() {
            @Override
            public void onResponse(Call<List<ProductsModel>> call, Response<List<ProductsModel>> response) {
                if (response.body().isEmpty())
                    Toast.makeText(getContext(), "Unable to retrieve products list.", Toast.LENGTH_SHORT).show();
                else
                    loadProductsData(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductsModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*LOAD PRODUCTS LIST IN RECYCLER VIEW USING ADAPTER*/
    private void loadProductsData(List<ProductsModel> productsList) {
        adapter = new ProductsAdapter(getContext(), productsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
