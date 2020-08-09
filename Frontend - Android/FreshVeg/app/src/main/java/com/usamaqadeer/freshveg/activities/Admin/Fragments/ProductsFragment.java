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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.usamaqadeer.freshveg.R;
import com.usamaqadeer.freshveg.activities.Admin.Adapters.ProductsAdapter;
import com.usamaqadeer.freshveg.api.models.CategoriesModel;
import com.usamaqadeer.freshveg.api.models.ProductsModel;
import com.usamaqadeer.freshveg.api.rest.RestAPI;
import com.usamaqadeer.freshveg.api.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProductsAdapter adapter;
    private FloatingActionButton fabAddProduct;
    private EditText etName, etPrice, etQty;
    private Spinner spCategories;
    private Button btnAdd, btnCancel;
    private List<CategoriesModel> categoriesList;
    private List<String> spCategoriesList = new ArrayList<>();;

    public ProductsFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        fabAddProduct = view.findViewById(R.id.fab_add_product);

        initDialogView(view);
        getCategoriesData();
        getProductsData();

        fabAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductDialog();
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

    /*GET CATEGORIES API CALL*/
    private void getCategoriesData(){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<List<CategoriesModel>> call = service.getCategories();
        call.enqueue(new Callback<List<CategoriesModel>>() {
            @Override
            public void onResponse(Call<List<CategoriesModel>> call, Response<List<CategoriesModel>> response) {
                if (response.body().isEmpty())
                    Toast.makeText(getContext(), "Unable to retrieve categories list.", Toast.LENGTH_SHORT).show();
                else
                    categoriesList = response.body();
            }

            @Override
            public void onFailure(Call<List<CategoriesModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*SHOW ADD PRODUCT BOY DIALOG*/
    public void addProductDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.add_product_layout);
        initAddProductDialog(dialog);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName.getText().toString().isEmpty() == true || etPrice.getText().toString().isEmpty() == true || etQty.getText().toString().isEmpty() == true) {
                    Toast.makeText(getContext(), "Fields cannot be empty.", Toast.LENGTH_SHORT).show();
                }
                else {
                    addProduct(etName.getText().toString(), categoriesList.get(spCategories.getSelectedItemPosition()).getC_id(), etPrice.getText().toString(), etQty.getText().toString());
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

    private void initAddProductDialog(Dialog dialog) {
        etName = dialog.findViewById(R.id.et_name);
        spCategories = dialog.findViewById(R.id.sp_category);
        etPrice = dialog.findViewById(R.id.et_price);
        etQty = dialog.findViewById(R.id.et_qty);
        btnAdd = dialog.findViewById(R.id.btn_add_product);
        btnCancel = dialog.findViewById(R.id.btn_cancel);
        populateCategoriesSpinner(categoriesList);
    }

    private void initDialogView(View view) {
        etName = view.findViewById(R.id.et_name);
        spCategories = view.findViewById(R.id.sp_category);
        etPrice = view.findViewById(R.id.et_price);
        etQty = view.findViewById(R.id.et_qty);
        btnAdd = view.findViewById(R.id.btn_add_product);
        btnCancel = view.findViewById(R.id.btn_cancel);
    }

    private void populateCategoriesSpinner(List<CategoriesModel> categoriesList) {
        for (int i = 0; i < categoriesList.size(); i++)
            spCategoriesList.add(categoriesList.get(i).getC_categorytype());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spCategoriesList);
        spCategories.setAdapter(adapter);
        spCategories.setSelection(0, true);
    }

    /*ADD PRODUCT API CALL*/
    private void addProduct(String name, int pId, String price, String qty){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<String> call = service.postProduct(name, pId, price, qty, "kg");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().equals("false"))
                    Toast.makeText(getContext(), "Failed to add product. Please try again later.", Toast.LENGTH_SHORT).show();
                else if (response.body().equals("true")){
                    Toast.makeText(getContext(), "Product added successfully.", Toast.LENGTH_SHORT).show();
                    getProductsData();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
