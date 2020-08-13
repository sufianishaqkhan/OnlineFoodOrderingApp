package com.usamaqadeer.freshveg.activities.User.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.usamaqadeer.freshveg.R;
import com.usamaqadeer.freshveg.activities.User.Adapters.UserOrdersAdapter;
import com.usamaqadeer.freshveg.activities.User.UserDashboardActivity;
import com.usamaqadeer.freshveg.api.models.OrderAssignsModel;
import com.usamaqadeer.freshveg.api.models.ProductsModel;
import com.usamaqadeer.freshveg.api.rest.RestAPI;
import com.usamaqadeer.freshveg.api.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserOrdersFragment extends Fragment {
    private RecyclerView recyclerView;
    private UserOrdersAdapter adapter;
    private FloatingActionButton fabAddOrder;
    private Spinner spProducts;
    private EditText etQty, etLocation;
    private TextView tvPrice;
    private Button btnOrder, btnCancel;
    private List<ProductsModel> productsList;
    private List<String> spProductsList = new ArrayList<>();
    private double currentProductUnitPrice;

    public UserOrdersFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_user_orders, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        fabAddOrder = view.findViewById(R.id.fab_add_order);

        initDialogView(view);
        getProductsData();
        getUserOrdersData(UserDashboardActivity.u_id);

        fabAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrderDialog();
            }
        });

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
                    productsList = response.body();
            }

            @Override
            public void onFailure(Call<List<ProductsModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*GET USER ORDERS API CALL*/
    private void getUserOrdersData(String uId){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<List<OrderAssignsModel>> call = service.getUserOrders(uId);
        call.enqueue(new Callback<List<OrderAssignsModel>>() {
            @Override
            public void onResponse(Call<List<OrderAssignsModel>> call, Response<List<OrderAssignsModel>> response) {
                if (response.body().isEmpty())
                    Toast.makeText(getContext(), "Unable to retrieve user orders list.", Toast.LENGTH_SHORT).show();
                else
                    loadUserOrdersData(response.body());
            }

            @Override
            public void onFailure(Call<List<OrderAssignsModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*CREATE NEW ORDER API CALL*/
    private void addOrder(String uId, String pId, String qty, String location, String price){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<String> call = service.postOrder(uId, pId, qty, location, price);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().equals("false"))
                    Toast.makeText(getContext(), "Order creation failed. Please try again later.", Toast.LENGTH_SHORT).show();
                else if (response.body().equals("true")){
                    Toast.makeText(getContext(), "Order successfully created.", Toast.LENGTH_SHORT).show();
                    getProductsData();
                    getUserOrdersData(UserDashboardActivity.u_id);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*LOAD USER ORDERS LIST IN RECYCLER VIEW USING ADAPTER*/
    private void loadUserOrdersData(List<OrderAssignsModel> userOrdersList) {
        List<OrderAssignsModel> noRepeat = new ArrayList<>();
        for (OrderAssignsModel item : userOrdersList) {
            boolean isFound = false;
            for (OrderAssignsModel e : noRepeat) {
                if (e.getOd_price().equals(item.getOd_price()) || (e.equals(item))) {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) noRepeat.add(item);
        }
        adapter = new UserOrdersAdapter(getContext(), noRepeat);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    /*SHOW NEW ORDER DIALOG*/
    public void addOrderDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.add_order_layout);
        initOrderDialog(dialog);

        etQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0)
                    tvPrice.setText("Rs " + (float) (currentProductUnitPrice * Double.parseDouble(s.toString())));
                if(s.length() == 0)
                    tvPrice.setText("Rs 0.00");

            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etQty.getText().toString().isEmpty() == true || etLocation.getText().toString().isEmpty() == true) {
                    Toast.makeText(getContext(), "Fields cannot be empty.", Toast.LENGTH_SHORT).show();
                }
                else {
                    addOrder(UserDashboardActivity.u_id, String.valueOf(productsList.get(spProducts.getSelectedItemPosition()).getP_id()), etQty.getText().toString(), etLocation.getText().toString(), tvPrice.getText().toString().replace("Rs ", ""));
                    dialog.dismiss();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

        dialog.getWindow().setLayout((6 * getResources().getDisplayMetrics().widthPixels)/7, (4 * getResources().getDisplayMetrics().heightPixels)/5);
    }

    private void initOrderDialog(Dialog dialog) {
        spProducts = dialog.findViewById(R.id.sp_product);
        etQty = dialog.findViewById(R.id.et_qty);
        etLocation = dialog.findViewById(R.id.et_location);
        tvPrice = dialog.findViewById(R.id.tv_total_price);
        btnOrder = dialog.findViewById(R.id.btn_order);
        btnCancel = dialog.findViewById(R.id.btn_cancel);
        populateProductsSpinner(productsList);
    }

    private void initDialogView(View view) {
        spProducts = view.findViewById(R.id.sp_product);
        etQty = view.findViewById(R.id.et_email);
        etLocation = view.findViewById(R.id.et_password);
        tvPrice = view.findViewById(R.id.tv_total_price);
        btnOrder = view.findViewById(R.id.btn_order);
        btnCancel = view.findViewById(R.id.btn_cancel);
    }

    private void populateProductsSpinner(final List<ProductsModel> productsList) {
        currentProductUnitPrice = Double.parseDouble(productsList.get(0).getP_unitprice());

        spProductsList.clear();
        for (int i = 0; i < productsList.size(); i++)
            spProductsList.add(productsList.get(i).getP_name());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spProductsList);
        spProducts.setAdapter(adapter);
        spProducts.setSelection(0, true);

        spProducts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentProductUnitPrice = Double.parseDouble(productsList.get(i).getP_unitprice());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }
}