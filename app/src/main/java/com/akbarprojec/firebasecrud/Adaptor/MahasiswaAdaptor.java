package com.akbarprojec.firebasecrud.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.akbarprojec.firebasecrud.Model.Mahasiswa;
import com.akbarprojec.firebasecrud.R;

import java.util.ArrayList;

public class MahasiswaAdaptor extends BaseAdapter {
    private Context context;
    private ArrayList<Mahasiswa> mahasiswasList = new ArrayList<>();

    public MahasiswaAdaptor(Context context) {
        this.context = context;
    }

    public void setMahasiswasList(ArrayList<Mahasiswa> mahasiswasList) {
        this.mahasiswasList = mahasiswasList;
    }

    @Override
    public int getCount() {
        return mahasiswasList.size();
    }

    @Override
    public Object getItem(int i) {
        return mahasiswasList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        if (view == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_mahasiswa, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);
        Mahasiswa mahasiswa = (Mahasiswa) getItem(i);
        viewHolder.bind(mahasiswa);
        return itemView;
    }

    private class ViewHolder{
        private TextView txtNim, txtNama;

        ViewHolder(View view) {
            txtNama = view.findViewById(R.id.txt_nama);
            txtNim = view.findViewById(R.id.txt_nim);
        }
        void bind(Mahasiswa mahasiswa){
            txtNama.setText(mahasiswa.getNama());
            txtNim.setText(mahasiswa.getId());
        }
    }
}
