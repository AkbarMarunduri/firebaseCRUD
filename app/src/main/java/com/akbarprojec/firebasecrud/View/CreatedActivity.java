package com.akbarprojec.firebasecrud.View;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.akbarprojec.firebasecrud.Model.Mahasiswa;
import com.akbarprojec.firebasecrud.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class CreatedActivity extends AppCompatActivity implements View.OnClickListener {
private EditText edNim, edNama;
    private Button btSave;
    private Mahasiswa mahasiswa;

    //menghubungkan ke firebase dengan ini
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        edNim = findViewById(R.id.edt_nim);
        edNama = findViewById(R.id.edt_nama);
        btSave = findViewById(R.id.btn_save);


        btSave.setOnClickListener(this);

        mahasiswa = new Mahasiswa();
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_save) {
            SaveMahasiswa();
        }
    }

    void SaveMahasiswa() {
        String nama = edNama.getText().toString().trim();
        String nim = edNim.getText().toString().trim();

        boolean isEmptyField = false;

        if (TextUtils.isEmpty(nama)) {
            isEmptyField = true;
            edNama.setError("Field ini tidakboleh kosong");
        }
        if (TextUtils.isEmpty(nim)) {
            isEmptyField = true;
            edNim.setError("Field ini tidak boleh kosong");
        }

        if (!isEmptyField) {
            Toast.makeText(CreatedActivity.this, "Save Data....!!", Toast.LENGTH_SHORT).show();
            DatabaseReference dbMahasiswa = mDatabase.child("mahasiswa");

            String id = dbMahasiswa.push().getKey();
            mahasiswa.setId(id);
            mahasiswa.setNama(nama);
            mahasiswa.setNim(nim);
            mahasiswa.setFoto("");

            //insert data
            dbMahasiswa.child(id).setValue(mahasiswa);
            finish();
        }

    }
}
