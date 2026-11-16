package com.example.aplicatie_seminar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter_Student extends ArrayAdapter<Student> {

    private Context context;
    private int resoure;
    private List<Student> list;

    private LayoutInflater layoutInflater;

    public CustomAdapter_Student(@NonNull Context context, int resource, List<Student> list, LayoutInflater layoutInflater) {
        super(context, resource, list);
        this.context = context;
        this.resoure = resource;
        this.list = list;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = layoutInflater.inflate(resoure, parent, false);
        Student student = list.get(position);

        if(student != null)
        {
            TextView textViewNume = view.findViewById(R.id.textViewNume);
            textViewNume.setText(student.getNume());

            TextView textViewDataNasterii = view.findViewById(R.id.textViewDataNasterii);
            textViewDataNasterii.setText(String.valueOf(student.getDataNasterii()));

            TextView textViewMedie = view.findViewById(R.id.textViewMedie);
            textViewMedie.setText(String.valueOf(student.getMedie()));

            TextView textViewFacultate = view.findViewById(R.id.textViewFacultate);
            textViewFacultate.setText(student.getFacultate());

            TextView textViewAreBursa = view.findViewById(R.id.textViewAreBursa);
            if(student.getAreBursa() == true)
            {
                textViewAreBursa.setText("DA");
            }
            else
            {
                textViewAreBursa.setText("NU");
            }
        }
        return view;
    }
}
