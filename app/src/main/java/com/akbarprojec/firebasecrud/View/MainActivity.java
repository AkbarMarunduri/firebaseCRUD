package com.akbarprojec.firebasecrud.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.akbarprojec.firebasecrud.Adaptor.MahasiswaAdaptor;
import com.akbarprojec.firebasecrud.Model.Mahasiswa;
import com.akbarprojec.firebasecrud.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btAdd;
    ListView listView;

    private MahasiswaAdaptor adaptor;
    private ArrayList<Mahasiswa> mahasiswaList;
    DatabaseReference mReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btAdd = findViewById(R.id.btAdd);
        listView = findViewById(R.id.lvList);
        btAdd.setOnClickListener(this);

        mReference = FirebaseDatabase.getInstance().getReference("mahasiswa");
        mahasiswaList = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,UpdateData.class);
                intent.putExtra(UpdateData.EXTRA_MAHASISWA, mahasiswaList.get(i));
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mahasiswaList.clear();
                for (DataSnapshot maSnapshot : dataSnapshot.getChildren()) {
                    Mahasiswa mahasiswa = maSnapshot.getValue(Mahasiswa.class);
                    mahasiswaList.add(mahasiswa);
                }
                MahasiswaAdaptor adaptor = new MahasiswaAdaptor(MainActivity.this);
                adaptor.setMahasiswasList(mahasiswaList);
                listView.setAdapter(adaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,"Terjadi kesalahan",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btAdd) {
            Intent intent = new Intent(this, CreatedActivity.class);
            startActivity(intent);
        }
    }
}
