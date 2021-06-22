package com.example.testbarang;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterLihatBarang extends RecyclerView.Adapter<AdapterLihatBarang.ViewHolder> {
    private ArrayList<Barang> daftarBarang;
    private Context context;
    private DatabaseReference databaseReference;

    public AdapterLihatBarang(ArrayList<Barang> barangs, Context ctx){
        daftarBarang = barangs;
        context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterLihatBarang.ViewHolder holder, int position) {
        String id, barang, kode;

        id = daftarBarang.get(position).getId();
        barang = daftarBarang.get(position).getNama();
        kode = daftarBarang.get(position).getKode();

//        final String name = daftarBarang.get(position).getNama();

        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * untuk latihan Selanjutnya , jika ingin membaca detail data
                 * */
            }
        });
        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.menubarang);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.edit:
                                Bundle bundle = new Bundle();
                                bundle.putString("kunci1", id);
                                bundle.putString("kunci2", kode);
                                bundle.putString("kunci3", barang);

                                Intent intent = new Intent(view.getContext(), BarangEdit.class);
                                intent.putExtras(bundle);
                                view.getContext().startActivity(intent);
                                break;
                            case R.id.hapus:
                                AlertDialog.Builder alertDlg = new AlertDialog.Builder(view.getContext());
                                alertDlg.setTitle("Yakin data " + barang + " akan dihapus ?");
                                alertDlg.setMessage("Tekan 'Ya' untuk menghapus")
                                        .setCancelable(false)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                DeleteData(id);
                                                Toast.makeText(view.getContext(),"Data "+barang+" berhasil di hapus", Toast.LENGTH_LONG).show();
                                                Intent i = new Intent(view.getContext(), MainActivity.class);
                                                view.getContext().startActivity(i);
                                            }
                                        })
                                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();

                                            }
                                        });
                                AlertDialog alertDialog = alertDlg.create();
                                alertDialog.show();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
                return true;
            }
        });
        holder.tvTitle.setText(barang);
    }

    @Override
    public int getItemCount() {
        //mengembalikan jumlah item pada barang
        return daftarBarang.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;

        ViewHolder(View v){
            super(v);
            tvTitle =v.findViewById(R.id.tv_namabarang);

            databaseReference = FirebaseDatabase.getInstance().getReference();
        }
    }

    public void DeleteData(String id){
        if(databaseReference != null){
            databaseReference.child("Barang").child(id).removeValue();
        }
    }

}
