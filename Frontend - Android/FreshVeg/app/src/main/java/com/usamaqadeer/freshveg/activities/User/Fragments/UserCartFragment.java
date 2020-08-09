package com.usamaqadeer.freshveg.activities.User.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.usamaqadeer.freshveg.R;
import com.usamaqadeer.freshveg.activities.User.UserDashboardActivity;
import com.usamaqadeer.freshveg.api.models.CategoriesModel;
import com.usamaqadeer.freshveg.api.models.ProductsModel;
import com.usamaqadeer.freshveg.api.rest.RestAPI;
import com.usamaqadeer.freshveg.api.rest.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserCartFragment extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton fabAddOrder;
    private EditText etName, etQty, etLocation;
    private TextView tvPrice;
    private Button btnAdd, btnCancel;
    private List<ProductsModel> productsList;


    public UserCartFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_cart, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        fabAddOrder = view.findViewById(R.id.fab_add_order);

        initDialogView(view);
        getProductsData();

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

    /*SHOW ADD DELIVERY BOY DIALOG*/
    public void addOrderDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.add_order_layout);
        initOrderDialog(dialog);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName.getText().toString().isEmpty() == true || etQty.getText().toString().isEmpty() == true || etLocation.getText().toString().isEmpty() == true) {
                    Toast.makeText(getContext(), "Fields cannot be empty.", Toast.LENGTH_SHORT).show();
                }
                else {
                    addOrder(UserDashboardActivity.u_id, etName.getText().toString(), etQty.getText().toString(), etLocation.getText().toString(), tvPrice.getText().toString());
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

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout((6 * width)/7, (4 * height)/5);
    }

    private void initOrderDialog(Dialog dialog) {
        etName = dialog.findViewById(R.id.et_name);
        etEmail = dialog.findViewById(R.id.et_email);
        etPassword = dialog.findViewById(R.id.et_password);
        spShiftStart = dialog.findViewById(R.id.sp_shift_start);
        spShiftEnd = dialog.findViewById(R.id.sp_shift_end);
        btnAdd = dialog.findViewById(R.id.btn_add_db);
        btnCancel = dialog.findViewById(R.id.btn_cancel);
    }

    private void initDialogView(View view) {
        etName = view.findViewById(R.id.et_name);
        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        spShiftStart = view.findViewById(R.id.sp_shift_start);
        spShiftEnd = view.findViewById(R.id.sp_shift_end);
        btnAdd = view.findViewById(R.id.btn_add_db);
        btnCancel = view.findViewById(R.id.btn_cancel);
    }

    /*ADD DELIVERY BOY API CALL*/
    private void addDeliveryBoy(String name, String email, String password, String shiftStart, String shiftEnd){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<String> call = service.postDeliveryBoy(name, email, password, shiftStart, shiftEnd);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().equals("false"))
                    Toast.makeText(getContext(), "Delivery boy registration failed. Please try again later.", Toast.LENGTH_SHORT).show();
                else if (response.body().equals("true")){
                    Toast.makeText(getContext(), "Delivery boy successfully registered.", Toast.LENGTH_SHORT).show();
                    getDeliveryBoysData();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}