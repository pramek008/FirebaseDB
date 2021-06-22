package com.example.testbarang;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Barang implements Serializable {
    private String idfb;
    private String nama;
    private String kode;

    public Barang(){
    }

    public String getKode(){
        return kode;
    }
    public void setKode(String kd){
        this.kode = kd;
    }

    public String getNama(){
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId(){return idfb;}
    public void setId(String id){this.idfb = id;}

    @Override
    public String toString() {

        return "Barang{" +
                ", nama='" + nama + '\n'+
                ", kode='" + kode + '\n' +
                "id='" + idfb + '\n' +
                '}';
    }
    public Barang(String kd, String nm){
        kode = kd;
        nama = nm;
    }
}
