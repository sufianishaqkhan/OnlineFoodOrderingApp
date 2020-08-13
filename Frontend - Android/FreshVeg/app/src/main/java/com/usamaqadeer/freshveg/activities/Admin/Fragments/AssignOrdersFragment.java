package com.usamaqadeer.freshveg.activities.Admin.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.usamaqadeer.freshveg.R;
import com.usamaqadeer.freshveg.activities.Admin.Adapters.AssignOrdersAdapter;
import com.usamaqadeer.freshveg.activities.Admin.Adapters.OrdersAdapter;
import com.usamaqadeer.freshveg.activities.User.UserDashboardActivity;
import com.usamaqadeer.freshveg.api.models.CategoriesModel;
import com.usamaqadeer.freshveg.api.models.DeliveryBoysModel;
import com.usamaqadeer.freshveg.api.models.OrderAssignsModel;
import com.usamaqadeer.freshveg.api.models.OrderDetailsModel;
import com.usamaqadeer.freshveg.api.models.ProductsModel;
import com.usamaqadeer.freshveg.api.rest.RestAPI;
import com.usamaqadeer.freshveg.api.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignOrdersFragment extends Fragment {
    private RecyclerView recyclerView;
    private AssignOrdersAdapter adapter;
    private Spinner spDeliveryBoys;
    private List<DeliveryBoysModel> deliveryBoysModelList = new ArrayList<>();
    private List<String> spDeliveryBoysList = new ArrayList<>();
    public static RelativeLayout assignToDBPopUp;
    public static LinearLayout btnAssignToDB;
    public static int u_id;
    public static int db_id;

    public AssignOrdersFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assign_orders, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        assignToDBPopUp = view.findViewById(R.id.popupLayout);
        btnAssignToDB = view.findViewById(R.id.ll_assign_to_Db);
        spDeliveryBoys = view.findViewById(R.id.sp_delivery_boys);

        getDeliveryBoys();
        getOrderAssignsData();

        btnAssignToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db_id = deliveryBoysModelList.get(spDeliveryBoys.getSelectedItemPosition()).getDb_id();
                assignOrderToDeliveryBoy(u_id, db_id);
                assignToDBPopUp.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }

    /*GET DELIVERY BOYS API CALL*/
    private void getDeliveryBoys(){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<List<DeliveryBoysModel>> call = service.getDeliveryBoys();
        call.enqueue(new Callback<List<DeliveryBoysModel>>() {
            @Override
            public void onResponse(Call<List<DeliveryBoysModel>> call, Response<List<DeliveryBoysModel>> response) {
                if (response.body().isEmpty())
                    Toast.makeText(getContext(), "Unable to retrieve order delivery boys list.", Toast.LENGTH_SHORT).show();
                else{
                    deliveryBoysModelList = response.body();
                    populateDeliveryBoysSpinner(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<DeliveryBoysModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*GET ORDER ASSIGNS API CALL*/
    private void assignOrderToDeliveryBoy(int u_id, int db_id){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<String> call = service.assignOrderToDB(u_id, db_id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().equals("false"))
                    Toast.makeText(getContext(), "Order assignment failed. Please try again later.", Toast.LENGTH_SHORT).show();
                else if (response.body().equals("true")){
                    Toast.makeText(getContext(), "Order assigned to delivery boy.", Toast.LENGTH_SHORT).show();
                    getDeliveryBoys();
                    getOrderAssignsData();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
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

    /*POPULATE DELIVERY BOYS SPINNER*/
    private void populateDeliveryBoysSpinner(List<DeliveryBoysModel> deliveryBoysModelList) {
        spDeliveryBoysList.clear();
        for (int i = 0; i < deliveryBoysModelList.size(); i++)
            spDeliveryBoysList.add(deliveryBoysModelList.get(i).getDb_name());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spDeliveryBoysList);
        spDeliveryBoys.setAdapter(adapter);
        spDeliveryBoys.setSelection(0, true);
    }

    /*LOAD ORDER ASSIGNS LIST IN RECYCLER VIEW USING ADAPTER*/
    private void loadOrderAssignsData(List<OrderAssignsModel> orderAssignsList) {
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
            if (!noRepeat.get(i).getOa_status().equals("DELIVERED"))
                filteredList.add(noRepeat.get(i));

        adapter = new AssignOrdersAdapter(getContext(), filteredList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}