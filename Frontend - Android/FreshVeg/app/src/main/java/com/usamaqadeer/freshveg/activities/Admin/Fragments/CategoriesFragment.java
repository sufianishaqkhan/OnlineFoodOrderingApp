package com.usamaqadeer.freshveg.activities.Admin.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.usamaqadeer.freshveg.R;
import com.usamaqadeer.freshveg.activities.Admin.Adapters.CategoriesAdapter;
import com.usamaqadeer.freshveg.activities.Admin.AdminDashboardActivity;
import com.usamaqadeer.freshveg.activities.DashboardActivity;
import com.usamaqadeer.freshveg.activities.DeliveryBoy.DeliveryBoyActivity;
import com.usamaqadeer.freshveg.api.models.CategoriesModel;
import com.usamaqadeer.freshveg.api.rest.RestAPI;
import com.usamaqadeer.freshveg.api.rest.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesFragment extends Fragment {
    private RecyclerView recyclerView;
    private CategoriesAdapter adapter;

    public CategoriesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        getCategoriesData();

        return view;
    }

    private void getCategoriesData(){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<List<CategoriesModel>> call = service.getCategories();
        call.enqueue(new Callback<List<CategoriesModel>>() {
            @Override
            public void onResponse(Call<List<CategoriesModel>> call, Response<List<CategoriesModel>> response) {
                if (response.body().isEmpty())
                    Toast.makeText(getContext(), "Unable to retrieve categories list.", Toast.LENGTH_SHORT).show();
                else
                    loadCategoriesData(response.body());
            }

            @Override
            public void onFailure(Call<List<CategoriesModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCategoriesData(List<CategoriesModel> categoriesList) {
        adapter = new CategoriesAdapter(getActivity().getApplicationContext(), categoriesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}