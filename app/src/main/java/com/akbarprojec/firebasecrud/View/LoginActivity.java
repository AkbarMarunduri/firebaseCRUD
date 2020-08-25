package com.akbarprojec.firebasecrud.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.akbarprojec.firebasecrud.R;
import com.akbarprojec.firebasecrud.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //mendeclarasikan autentifikasi firebase
    private FirebaseAuth firebaseAuth;
    private boolean islogedIn;
    TextView user,pass;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        user = binding.textUsernaem;
        pass = binding.textPassword;

        binding.btLogin.setOnClickListener(this);
        binding.btSingup.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        islogedIn=updateUi();
        if (islogedIn) {
            goTomainActivitya();
        }
    }

    private  void goTomainActivitya() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void login(final String username, final String pasword) {
        if (TextUtils.isEmpty(username)) {
            Snackbar.make(findViewById(android.R.id.content), "Username tidak boleh kosong", Snackbar.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(pasword)) {
            Snackbar.make(findViewById(android.R.id.content), "passwoed tidak boleh kosong", Snackbar.LENGTH_LONG).show();
        } else {
            //login
            firebaseAuth.signInWithEmailAndPassword(username, pasword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                goTomainActivitya();
                            } else {
                                //gagal login
                                showMessageBox("Username and Paassword not macth");
                            }
                        }
                    });
        }
    }

    private void showMessageBox(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Login")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cretae", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        createdAcount();
                    }
                });
        builder.show();
    }
    private boolean updateUi() {
        if (firebaseAuth.getCurrentUser() != null) {
            return true;
        } else {
            return false;
        }
    }

    private void createdAcount() {
        Intent intent = new Intent(LoginActivity.this, SingUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btLogin:
                String username = user.getText().toString();
                String password = pass.getText().toString();
                login(username, password);
                break;
            case R.id.btSingup:
                createdAcount();
                break;
        }
    }
}
