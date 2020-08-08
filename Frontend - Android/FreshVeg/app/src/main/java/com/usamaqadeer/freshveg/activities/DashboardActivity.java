package com.usamaqadeer.freshveg.activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.usamaqadeer.freshveg.R;
import com.usamaqadeer.freshveg.activities.Admin.AdminDashboardActivity;
import com.usamaqadeer.freshveg.activities.DeliveryBoy.DeliveryBoyActivity;
import com.usamaqadeer.freshveg.activities.User.UserActivity;
import com.usamaqadeer.freshveg.api.rest.RestAPI;
import com.usamaqadeer.freshveg.api.rest.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    EditText etEmail, etPassword, etConfirmPassword;
    Button btnLogin, btnCancel, btnReset;
    TextView tvForgot, tvRegister;
    int forgetType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initView();
    }

    private void initView() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        btnReset = findViewById(R.id.btnReset);
    }

    private void initDialog(Dialog dialog) {
        tvForgot = dialog.findViewById(R.id.tvForgot);
        etEmail = dialog.findViewById(R.id.etEmail);
        etPassword = dialog.findViewById(R.id.etPassword);
        btnLogin = dialog.findViewById(R.id.btnLogin);
        btnCancel = dialog.findViewById(R.id.btnCancel);
    }

    private void initForget(Dialog dialog) {
        etEmail = dialog.findViewById(R.id.etEmail);
        etPassword = dialog.findViewById(R.id.etPassword);
        etConfirmPassword = dialog.findViewById(R.id.etConfirmPassword);
        btnReset = dialog.findViewById(R.id.btnReset);
        btnCancel = dialog.findViewById(R.id.btnCancel);
    }

    public void Admin(View view) {
        forgetType = 1;
        final Dialog dialog = new Dialog(DashboardActivity.this);
        dialog.setContentView(R.layout.login_layout);
        initDialog(dialog);
        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ForgotPassword(view);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etEmail.getText().toString().isEmpty() == true || etPassword.getText().toString().isEmpty() == true) {
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty.", Toast.LENGTH_SHORT).show();
                } else {
                    loginAdmin(etEmail.getText().toString(), etPassword.getText().toString());
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

    }

    public void DeliveryBoy(View view) {
        forgetType = 2;
        final Dialog dialog = new Dialog(DashboardActivity.this);
        dialog.setContentView(R.layout.login_layout);
        initDialog(dialog);
        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ForgotPassword(view);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPassword.getText().toString().isEmpty() == true || etEmail.getText().toString().isEmpty() == true) {
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty.", Toast.LENGTH_SHORT).show();
                } else {
                    loginDeliveryBoy(etEmail.getText().toString(), etPassword.getText().toString());
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
    }


    public void ExistingUser(View view) {
        forgetType = 3;
        final Dialog dialog = new Dialog(DashboardActivity.this);
        dialog.setContentView(R.layout.login_layout);
        initDialog(dialog);
        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ForgotPassword(view);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPassword.getText().toString().isEmpty() == true || etEmail.getText().toString().isEmpty() == true) {
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty.", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(etEmail.getText().toString(), etPassword.getText().toString());
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
    }

    public void NewUser(View view) {
        forgetType = 0;
        final Dialog dialog = new Dialog(DashboardActivity.this);
        dialog.setContentView(R.layout.forget_layout);
        initForget(dialog);
        tvRegister = dialog.findViewById(R.id.textView2);
        tvRegister.setText("Registration");
        etPassword.setHint("Enter your name");
        etPassword.setInputType(InputType.TYPE_CLASS_TEXT);
        etConfirmPassword.setHint("Enter your password");
        btnReset.setText("Register");
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPassword.getText().toString().isEmpty() == true || etEmail.getText().toString().isEmpty() == true || etConfirmPassword.getText().toString().isEmpty() == true)
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty.", Toast.LENGTH_SHORT).show();
                else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches())
                    Toast.makeText(getApplicationContext(), "Email address is not valid.", Toast.LENGTH_SHORT).show();
                else {
                    createUser(etPassword.getText().toString(), etEmail.getText().toString(), etConfirmPassword.getText().toString());
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
    }

    public void ForgotPassword(View view) {
        final Dialog dialog = new Dialog(DashboardActivity.this);
        dialog.setContentView(R.layout.forget_layout);
        initForget(dialog);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkCredentials() == 1)
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty.", Toast.LENGTH_SHORT).show();
                else if (checkCredentials() == 2)
                    Toast.makeText(getApplicationContext(), "Email address is not valid.", Toast.LENGTH_SHORT).show();
                else if (checkCredentials() == 3)
                    Toast.makeText(getApplicationContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();
                else {
                    forgotPassword(etEmail.getText().toString(), etPassword.getText().toString(), forgetType);
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
    }

    /*ADMIN LOGIN API CALL*/
    private void loginAdmin(String email, String password){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<String> call = service.loginAdmin(email, password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().equals("0"))
                    Toast.makeText(DashboardActivity.this, "Login failed. Please try again later.", Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(DashboardActivity.this, AdminDashboardActivity.class));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*DELIVERY BOY LOGIN API CALL*/
    private void loginDeliveryBoy(String email, String password){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<String> call = service.loginDeliveryBoy(email, password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().equals("0"))
                    Toast.makeText(DashboardActivity.this, "Login failed. Please try again later.", Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(DashboardActivity.this, DeliveryBoyActivity.class));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*USER LOGIN API CALL*/
    private void loginUser(String email, String password){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<String> call = service.loginUser(email, password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().equals("0"))
                    Toast.makeText(DashboardActivity.this, "Login failed. Please try again later.", Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(DashboardActivity.this, UserActivity.class));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*USER REGISTRATION API CALL*/
    private void createUser(String name, String email, String password){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<String> call = service.postUser(name, email, password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().equals("false"))
                    Toast.makeText(DashboardActivity.this, "User registration failed. Please try again later.", Toast.LENGTH_SHORT).show();
                else if (response.body().equals("true"))
                    Toast.makeText(DashboardActivity.this, "User successfully registered. Please login to continue.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*FOR PASSWORD API CALL FOR ADMIN, DELIVERY BOY AND USER*/
    private void forgotPassword(String email, String password, int forgetType){
        RestAPI service = RestClient.getRetrofitInstance().create(RestAPI.class);
        Call<String> call = null;

        switch(forgetType) {
            case 1:
                call = service.forgotPasswordAdmin(email, password);
                break;
            case 2:
                call = service.forgotPasswordDeliveryBoy(email, password);
                break;
            case 3: 
                call = service.forgotPasswordUser(email, password);
                break;
            default:
                return;
        }

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().equals("false"))
                    Toast.makeText(DashboardActivity.this, "Password reset failed because user not found.", Toast.LENGTH_SHORT).show();
                else if (response.body().equals("true"))
                    Toast.makeText(DashboardActivity.this, "Password has been reset successfully.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Failed to contact server. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int checkCredentials() {
        if (etPassword.getText().toString().isEmpty() == true || etEmail.getText().toString().isEmpty() == true ||
                etConfirmPassword.getText().toString().isEmpty() == true)
            return 1;
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches())
            return 2;
        if (!etConfirmPassword.getText().toString().equals(etPassword.getText().toString()))
            return 3;
        return 0;
    }

    public void closeApp(View view) {
        startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
