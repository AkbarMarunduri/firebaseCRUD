package com.akbarprojec.firebasecrud.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.akbarprojec.firebasecrud.Model.Mahasiswa;
import com.akbarprojec.firebasecrud.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateData extends AppCompatActivity implements View.OnClickListener {
    private EditText edNim, edNama;
    private Button btUpdate;

    public static final String EXTRA_MAHASISWA = "extra_mahasiswa";
    public final int ALERT_DIALOG_CLOSE = 10;
    public final int ALERT_DIALOG_DELETE = 20;

    private Mahasiswa mahasiswa;
    private String mahasiswaID;

    DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        mReference = FirebaseDatabase.getInstance().getReference();
        mahasiswa = getIntent().getParcelableExtra(EXTRA_MAHASISWA);

        edNim = findViewById(R.id.edt_edit_nim);
        edNama = findViewById(R.id.edt_edit_nama);

        btUpdate = findViewById(R.id.btn_update);
        btUpdate.setOnClickListener(this);

        if (mahasiswa != null) {
            mahasiswaID = mahasiswa.getId();
        } else {
            mahasiswa = new Mahasiswa();
        }

        if (mahasiswa != null) {
            edNim.setText(mahasiswa.getNim());
            edNama.setText(mahasiswa.getNama());
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Upadate Data");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    private void updateMahasiswa() {
        String nama = edNama.getText().toString().trim();
        String nim = edNim.getText().toString().trim();

        boolean isEmptyField = false;
        if (TextUtils.isEmpty(nama)) {
            isEmptyField = true;
            edNama.setError("Field ini tidak boleh kosong..!");
        }

        if (TextUtils.isEmpty(nim)) {
            isEmptyField = true;
            edNim.setError("Field ini tidak boleh kosong..!");
        }

        if (!isEmptyField) {
            Toast.makeText(UpdateData.this, "Mengupdate data...!!", Toast.LENGTH_LONG).show();

            mahasiswa.setNim(nim);
            mahasiswa.setNama(nama);
            mahasiswa.setFoto("");

            DatabaseReference dbMahasiswa = mReference.child("mahasiswa");

            //update data
            dbMahasiswa.child(mahasiswaID).setValue(mahasiswa);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //pilih menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
                case R.id.action_delete:
                    showAlertDialog(ALERT_DIALOG_DELETE);
                    break;
                case android.R.id.home:
                    showAlertDialog(ALERT_DIALOG_CLOSE);
                    break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void showAlertDialog(int type) {
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle, dialogMsg;

        if (isDialogClose) {
            dialogTitle = "Batal";
            dialogMsg = "Apakah anda ingin membatalkan perubahan pada form..?";
        } else {
            dialogTitle = "Hapus Data";
            dialogMsg = "Apakah anda yakin ingin menghapus item ini..?";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(dialogTitle);
        builder.setMessage(dialogMsg)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (isDialogClose) {
                            finish();
                        } else {
                            //hapus daata
                            DatabaseReference dbMahasiswa = mReference.child("mahasiswa").child(mahasiswaID);
                            dbMahasiswa.removeValue();
                            Toast.makeText(UpdateData.this, "Data sudah dihapus", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_update) {
            updateMahasiswa();
        }
    }
}
