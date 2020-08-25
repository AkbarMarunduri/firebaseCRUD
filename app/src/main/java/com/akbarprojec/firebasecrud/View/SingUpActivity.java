package com.akbarprojec.firebasecrud.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.akbarprojec.firebasecrud.R;
import com.akbarprojec.firebasecrud.databinding.ActivitySingUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SingUpActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    TextView username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySingUpBinding binding = DataBindingUtil.setContentView(SingUpActivity.this, R.layout.activity_sing_up);

        firebaseAuth = FirebaseAuth.getInstance();

        username = binding.textUsernaemSingup;
        password = binding.textPasswordSingup;
        binding.btNextSingup.setOnClickListener(listener);


    }

    private Button.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            singUp(user,pass);
        }
    };

    private void singUp(String user, String pass) {
        if (TextUtils.isEmpty(user)) {
            Snackbar.make(findViewById(android.R.id.content),"Username tidak boleh kosong",Snackbar.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(pass)) {
            Snackbar.make(findViewById(android.R.id.content), "password tidak boleh kosong", Snackbar.LENGTH_LONG).show();
        } else {
            firebaseAuth.createUserWithEmailAndPassword(user, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //berhasil mendaftar
                                AlertDialog builder = new AlertDialog.Builder(SingUpActivity.this)
                                        .setTitle("Singup")
                                        .setMessage("Acount anda sudah berhasil didaftarkan, Silahkan logindengan user dan password anda.")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                                finish();
                                            }
                                        })
                                        .show();
                            } else {
                                task.getException().printStackTrace();
                                //gagal melakukan pendaftaran
                                final Snackbar snackbar=Snackbar.make(findViewById(android.R.id.content),"Pendaftaran acount gagal, Silahkan coba lagi.",Snackbar.LENGTH_INDEFINITE);
                                snackbar.show();
                            }
                        }
                    });
        }
    }
}
