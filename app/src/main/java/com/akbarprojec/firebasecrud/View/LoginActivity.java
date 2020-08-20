package com.akbarprojec.firebasecrud.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.akbarprojec.firebasecrud.R;
import com.akbarprojec.firebasecrud.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //mendeclarasikan autentifikasi firebase
    private FirebaseAuth firebaseAuth;

    private static final String TAG = "CustomAuthActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();

        binding.txtUser.setText("username");
        binding.txtPassword.setText("password");
        binding.txtTanggal.setText("08-12-2020");

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Ini dia", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        updateUi(firebaseUser);
    }

    private void updateUi(FirebaseUser user) {
        if (user != null) {

        }else{

        }
    }
    @Override
    public void onClick(View view) {

    }
}
