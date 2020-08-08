package com.usamaqadeer.freshveg;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.usamaqadeer.freshveg.Admin.AdminDashboard;
import com.usamaqadeer.freshveg.DeliveryBoy.DeliveryBoy;

public class DashboardActivity extends AppCompatActivity {

    EditText etEmail, etPassword, etConfirmPassword;
    Button btnLogin, btnCancel, btnReset;
    TextView tvForget, tvRegister;

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

    public void Admin(View view) {
        final Dialog dialog = new Dialog(DashboardActivity.this);
        dialog.setContentView(R.layout.login_layout);
        initDialog(dialog);
        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ForgetPassword(view);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPassword.getText().toString().isEmpty() == true || etEmail.getText().toString().isEmpty() == true) {
                    Toast.makeText(getApplicationContext(), "Please fill credientials carefully", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(DashboardActivity.this, AdminDashboard.class);
                    startActivity(intent);
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

    private void initDialog(Dialog dialog) {
        tvForget = dialog.findViewById(R.id.tvForget);
        etEmail = dialog.findViewById(R.id.etEmail);
        etPassword = dialog.findViewById(R.id.etPassword);
        btnLogin = dialog.findViewById(R.id.btnLogin);
        btnCancel = dialog.findViewById(R.id.btnCancel);
    }

    public void DeliveryBoy(View view) {
        final Dialog dialog = new Dialog(DashboardActivity.this);
        dialog.setContentView(R.layout.login_layout);
        initDialog(dialog);
        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ForgetPassword(view);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPassword.getText().toString().isEmpty() == true || etEmail.getText().toString().isEmpty() == true) {
                    Toast.makeText(getApplicationContext(), "Please fill credientials carefully", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(DashboardActivity.this, DeliveryBoy.class);
                    startActivity(intent);
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

    public void ForgetPassword(View view) {
        final Dialog dialog = new Dialog(DashboardActivity.this);
        dialog.setContentView(R.layout.forget_layout);
        initForget(dialog);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkCredientials()) {
                    Toast.makeText(getApplicationContext(), "Please fill credientials carefully", Toast.LENGTH_SHORT).show();
                } else {
                    dialog.dismiss();
                    //  Reset Password Code Goes Here

//                    Intent intent = new Intent(DashBoardActivity.this, DeliveryBoy.class);
//                    startActivity(intent);
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

    private void initForget(Dialog dialog) {
        etEmail = dialog.findViewById(R.id.etEmail);
        etPassword = dialog.findViewById(R.id.etPassword);
        etConfirmPassword = dialog.findViewById(R.id.etConfirmPassword);
        btnReset = dialog.findViewById(R.id.btnReset);
        btnCancel = dialog.findViewById(R.id.btnCancel);
    }

    private boolean checkCredientials() {
        if (etPassword.getText().toString().isEmpty() == true || etEmail.getText().toString().isEmpty() == true ||
                etConfirmPassword.getText().toString().isEmpty() == true)
            return false;
        if (!etConfirmPassword.getText().toString().equals(etPassword.getText().toString()))
            return false;
        return true;
    }

    public void ExistingUser(View view) {
        final Dialog dialog = new Dialog(DashboardActivity.this);
        dialog.setContentView(R.layout.login_layout);
        initDialog(dialog);
        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ForgetPassword(view);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPassword.getText().toString().isEmpty() == true || etEmail.getText().toString().isEmpty() == true) {
                    Toast.makeText(getApplicationContext(), "Please fill credientials carefully", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(DashboardActivity.this, DeliveryBoy.class);
                    startActivity(intent);
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
        final Dialog dialog = new Dialog(DashboardActivity.this);
        dialog.setContentView(R.layout.forget_layout);
        initForget(dialog);
        tvRegister = dialog.findViewById(R.id.textView2);
        tvRegister.setText("Registeration");
        etPassword.setHint("Enter your name");
        etConfirmPassword.setHint("Enter your password");
        btnReset.setText("Register");
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPassword.getText().toString().isEmpty() == true || etEmail.getText().toString().isEmpty() == true ||
                        etConfirmPassword.getText().toString().isEmpty() == true) {
                    Toast.makeText(getApplicationContext(), "Please fill credientials carefully", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(DashboardActivity.this, DeliveryBoy.class);
                    startActivity(intent);
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
}
