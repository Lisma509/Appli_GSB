package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<DataClass> {

    Context context;
    List<DataClass> mDataClassList;

    public MyAdapter(@NonNull Context context, List<DataClass>dataClasses) {

        super(context, R.layout.activity_data_item, dataClasses);
        this.context = context;
        this.mDataClassList = dataClasses;

    }
    @SuppressLint("MissingInflatedId")

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_data_item,null,true);
        TextView datev = view.findViewById(R.id.date);
        TextView praticienv = view.findViewById(R.id.praticien);
       TextView id_User = view.findViewById(R.id.id);
        datev.setText(mDataClassList.get(position).getDate());
        praticienv.setText(mDataClassList.get(position).getPraticien());
        id_User.setText(mDataClassList.get(position).getId_user());

        return view;
    }
}
