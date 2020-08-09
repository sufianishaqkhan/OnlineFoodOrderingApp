package com.usamaqadeer.freshveg.activities.Admin.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.usamaqadeer.freshveg.R;
import com.usamaqadeer.freshveg.activities.Admin.Adapters.DeliveryBoysAdapter;
import com.usamaqadeer.freshveg.activities.DashboardActivity;
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
    private FloatingActionButton fabAddDeliveryBoy;
    private EditText etName, etEmail, etPassword;
    private Spinner spShiftStart, spShiftEnd;
    private Button btnAdd, btnCancel;

    public DeliveryBoysFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery_boys, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        fabAddDeliveryBoy = view.findViewById(R.id.fab_add_db);

        initDialogView(view);
        getDeliveryBoysData();

        fabAddDeliveryBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDeliveryBoyDialog();
            }
        });

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

    /*SHOW ADD DELIVERY BOY DIALOG*/
    public void addDeliveryBoyDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.add_delivery_boy_layout);
        initAddDeliveryBoyDialog(dialog);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName.getText().toString().isEmpty() == true || etEmail.getText().toString().isEmpty() == true || etPassword.getText().toString().isEmpty() == true) {
                    Toast.makeText(getContext(), "Fields cannot be empty.", Toast.LENGTH_SHORT).show();
                }
                else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches())
                    Toast.makeText(getContext(), "Email address is not valid.", Toast.LENGTH_SHORT).show();
                else {
                    addDeliveryBoy(etName.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString(), spShiftStart.getSelectedItem().toString(), spShiftEnd.getSelectedItem().toString());
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

    private void initAddDeliveryBoyDialog(Dialog dialog) {
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
