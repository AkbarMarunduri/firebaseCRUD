package com.akbarprojec.firebasecrud.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Mahasiswa implements Parcelable {
    private String id, nim, nama, foto;

    public Mahasiswa() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel desc, int flag) {
        desc.writeString(this.id);
        desc.writeString(this.nim);
        desc.writeString(this.nama);
        desc.writeString(this.foto);
    }

    protected Mahasiswa(Parcel in) {
        this.id = in.readString();
        this.nim = in.readString();
        this.nama = in.readString();
        this.foto = in.readString();
    }

    public static final Parcelable.Creator<Mahasiswa> CREATOR = new Creator<Mahasiswa>() {
        @Override
        public Mahasiswa createFromParcel(Parcel parcel) {
            return new Mahasiswa(parcel);
        }

        @Override
        public Mahasiswa[] newArray(int i) {
            return new Mahasiswa[i];
        }
    };
}
