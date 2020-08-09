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
import com.usamaqadeer.freshveg.activities.Admin.Adapters.DeliveryBoysAdapter;
import com.usamaqadeer.freshveg.api.models.DeliveryBoysModel;
import com.usamaqadeer.freshveg.api.rest.RestAPI;
import com.usamaqadeer.freshveg.api.rest.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryBoysFragment extends Fragment {
    private RecyclerView recyclerView;
    private DeliveryBoysAdapter adapter;

    public DeliveryBoysFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery_boys, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        getDeliveryBoysData();

        return view;
    }

    /*GET DELIVERY BOYS API CALL*/
    private void getDeliveryBoysData(){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<List<DeliveryBoysModel>> call = service.getDeliveryBoys();
        call.enqueue(new Callback<List<DeliveryBoysModel>>() {
            @Override
            public void onResponse(Call<List<DeliveryBoysModel>> call, Response<List<DeliveryBoysModel>> response) {
                if (response.body().isEmpty())
                    Toast.makeText(getContext(), "Unable to retrieve delivery boys list.", Toast.LENGTH_SHORT).show();
                else
                    loadDeliveryBoysData(response.body());
            }

            @Override
            public void onFailure(Call<List<DeliveryBoysModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*LOAD PRODUCTS LIST IN RECYCLER VIEW USING ADAPTER*/
    private void loadDeliveryBoysData(List<DeliveryBoysModel> deliveryBoysList) {
        adapter = new DeliveryBoysAdapter(getContext(), deliveryBoysList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
