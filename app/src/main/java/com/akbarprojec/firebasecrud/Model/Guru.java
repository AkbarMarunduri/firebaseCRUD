package com.akbarprojec.firebasecrud.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Guru implements Parcelable {
    String Nip,Nama, Alamat, Sk;

    protected Guru(Parcel in) {
        Nip = in.readString();
        Nama = in.readString();
        Alamat = in.readString();
        Sk = in.readString();
    }

    public static final Creator<Guru> CREATOR = new Creator<Guru>() {
        @Override
        public Guru createFromParcel(Parcel in) {
            return new Guru(in);
        }

        @Override
        public Guru[] newArray(int size) {
            return new Guru[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Nip);
        parcel.writeString(Nama);
        parcel.writeString(Alamat);
        parcel.writeString(Sk);
    }

    public Guru() {
    }

    public String getNip() {
        return Nip;
    }

    public void setNip(String nip) {
        Nip = nip;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getSk() {
        return Sk;
    }

    public void setSk(String sk) {
        Sk = sk;
    }


}
