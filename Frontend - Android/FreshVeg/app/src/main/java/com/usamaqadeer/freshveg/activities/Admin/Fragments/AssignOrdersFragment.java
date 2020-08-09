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
import com.usamaqadeer.freshveg.activities.Admin.Adapters.AssignOrdersAdapter;
import com.usamaqadeer.freshveg.activities.Admin.Adapters.OrdersAdapter;
import com.usamaqadeer.freshveg.api.models.OrderAssignsModel;
import com.usamaqadeer.freshveg.api.models.OrderDetailsModel;
import com.usamaqadeer.freshveg.api.rest.RestAPI;
import com.usamaqadeer.freshveg.api.rest.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignOrdersFragment extends Fragment {
    private RecyclerView recyclerView;
    private AssignOrdersAdapter adapter;

    public AssignOrdersFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assign_orders, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        getOrderAssignsData();

        return view;
    }

    /*GET ORDER ASSIGNS API CALL*/
    private void getOrderAssignsData(){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<List<OrderAssignsModel>> call = service.getOrderAssignDetails();
        call.enqueue(new Callback<List<OrderAssignsModel>>() {
            @Override
            public void onResponse(Call<List<OrderAssignsModel>> call, Response<List<OrderAssignsModel>> response) {
                if (response.body().isEmpty())
                    Toast.makeText(getContext(), "Unable to retrieve order assigns list.", Toast.LENGTH_SHORT).show();
                else
                    loadOrderAssignsData(response.body());
            }

            @Override
            public void onFailure(Call<List<OrderAssignsModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*LOAD ORDER ASSIGNS LIST IN RECYCLER VIEW USING ADAPTER*/
    private void loadOrderAssignsData(List<OrderAssignsModel> orderAssignsList) {
        adapter = new AssignOrdersAdapter(getContext(), orderAssignsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}