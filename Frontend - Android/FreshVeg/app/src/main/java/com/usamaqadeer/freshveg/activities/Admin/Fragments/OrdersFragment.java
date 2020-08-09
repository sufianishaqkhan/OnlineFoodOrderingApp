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
import com.usamaqadeer.freshveg.activities.Admin.Adapters.OrdersAdapter;
import com.usamaqadeer.freshveg.api.models.DeliveryBoysModel;
import com.usamaqadeer.freshveg.api.models.OrderDetailsModel;
import com.usamaqadeer.freshveg.api.rest.RestAPI;
import com.usamaqadeer.freshveg.api.rest.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersFragment extends Fragment {
    private RecyclerView recyclerView;
    private OrdersAdapter adapter;

    public OrdersFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        getOrderDetailsData();

        return view;
    }

    /*GET ORDERS API CALL*/
    private void getOrderDetailsData(){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<List<OrderDetailsModel>> call = service.getOrderDetails();
        call.enqueue(new Callback<List<OrderDetailsModel>>() {
            @Override
            public void onResponse(Call<List<OrderDetailsModel>> call, Response<List<OrderDetailsModel>> response) {
                if (response.body().isEmpty())
                    Toast.makeText(getContext(), "Unable to retrieve orders list.", Toast.LENGTH_SHORT).show();
                else
                    loadOrderDetailsData(response.body());
            }

            @Override
            public void onFailure(Call<List<OrderDetailsModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*LOAD ORDERS LIST IN RECYCLER VIEW USING ADAPTER*/
    private void loadOrderDetailsData(List<OrderDetailsModel> ordersList) {
        adapter = new OrdersAdapter(getContext(), ordersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}