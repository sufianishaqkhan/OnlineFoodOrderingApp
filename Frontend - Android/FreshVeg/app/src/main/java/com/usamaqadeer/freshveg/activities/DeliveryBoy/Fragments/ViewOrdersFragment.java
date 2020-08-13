package com.usamaqadeer.freshveg.activities.DeliveryBoy.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.usamaqadeer.freshveg.R;
import com.usamaqadeer.freshveg.activities.DeliveryBoy.Adapters.ViewOrdersAdapter;
import com.usamaqadeer.freshveg.activities.DeliveryBoy.DeliveryBoyDashboardActivity;
import com.usamaqadeer.freshveg.api.models.OrderAssignsModel;
import com.usamaqadeer.freshveg.api.rest.RestAPI;
import com.usamaqadeer.freshveg.api.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewOrdersFragment extends Fragment {
    private RecyclerView recyclerView;
    private ViewOrdersAdapter adapter;
    public static RelativeLayout changeStatusToDeliveredPopUp;
    public static LinearLayout btnChangeStatusToDelivered;
    public static int u_id;

    public ViewOrdersFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_orders, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        changeStatusToDeliveredPopUp = view.findViewById(R.id.popupLayout);
        btnChangeStatusToDelivered = view.findViewById(R.id.ll_change_status_to_Delivered);

        getOrdersData();

        btnChangeStatusToDelivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStatusToDelivered(u_id);
                changeStatusToDeliveredPopUp.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }

    /*GET ORDER ASSIGNS API CALL*/
    private void getOrdersData(){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<List<OrderAssignsModel>> call = service.getOrderAssignDetails();
        call.enqueue(new Callback<List<OrderAssignsModel>>() {
            @Override
            public void onResponse(Call<List<OrderAssignsModel>> call, Response<List<OrderAssignsModel>> response) {
                if (response.body().isEmpty())
                    Toast.makeText(getContext(), "Unable to retrieve orders list.", Toast.LENGTH_SHORT).show();
                else
                    loadOrdersData(response.body());
            }

            @Override
            public void onFailure(Call<List<OrderAssignsModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*GET ORDER ASSIGNS API CALL*/
    private void changeStatusToDelivered(int u_id){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<String> call = service.changeStatusToDelivered(u_id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().equals("false"))
                    Toast.makeText(getContext(), "Order status change failed. Please try again later.", Toast.LENGTH_SHORT).show();
                else if (response.body().equals("true")){
                    Toast.makeText(getContext(), "Order status changed to delivered.", Toast.LENGTH_SHORT).show();
                    getOrdersData();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*LOAD ORDER ASSIGNS LIST IN RECYCLER VIEW USING ADAPTER*/
    private void loadOrdersData(List<OrderAssignsModel> orderAssignsList) {
        List<OrderAssignsModel> noRepeat = new ArrayList<>();
        for (OrderAssignsModel item : orderAssignsList) {
            boolean isFound = false;
            for (OrderAssignsModel e : noRepeat) {
                if (e.getOd_price().equals(item.getOd_price()) || (e.equals(item))) {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) noRepeat.add(item);
        }
        List<OrderAssignsModel> filteredList = new ArrayList<>();
        for (int i=0; i<noRepeat.size(); i++)
            if (noRepeat.get(i).getOa_db_id() == Integer.parseInt(DeliveryBoyDashboardActivity.db_id) && noRepeat.get(i).getOa_status().equals("ACCEPTED"))
                filteredList.add(noRepeat.get(i));

        adapter = new ViewOrdersAdapter(getContext(), filteredList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}