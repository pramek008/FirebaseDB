package com.example.testbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BarangEdit extends AppCompatActivity {
    TextView tv_Key;
    EditText edBarang, edKode;
    Button btnEdit;
    DatabaseReference databaseReference;
    String id, barang, kode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang_edit);

        edBarang = findViewById(R.id.edBarang);
        edKode = findViewById(R.id.edKode);
        tv_Key = findViewById(R.id.tv_key);
        btnEdit = findViewById(R.id.btEdit);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = this.getIntent().getExtras();
        id = bundle.getString("kunci1");
        barang = bundle.getString("kunci2");
        kode = bundle.getString("kunci3");

        // menampilkan isi ke dalam kolom layout edittext textview
        tv_Key.setText(id);
        edBarang.setText(barang);
        edKode.setText(kode);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editBarang(new Barang(edBarang.getText().toString(),edKode.getText().toString()));
            }
        });
    }
    public void editBarang(Barang barang){
        databaseReference.child("Barang")
                .child(id)
                .setValue(barang)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(BarangEdit.this, "Data Berhasil Diupdate", Toast.LENGTH_LONG).show();
                        finish();

                    }
                });
    }
}