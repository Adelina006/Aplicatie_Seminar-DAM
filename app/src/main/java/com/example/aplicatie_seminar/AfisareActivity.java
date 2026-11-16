package com.example.aplicatie_seminar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class AfisareActivity extends AppCompatActivity {

    public List<Student> studentiAfisare = new ArrayList<>();
    public ListView listViewStudentiAfisare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afisare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listViewStudentiAfisare = findViewById(R.id.ListViewStudentiAfisare);
        Intent intent = getIntent();
        if(intent.hasExtra(AddActivity.ADD_Student))
        {
            Student student = (Student) intent.getSerializableExtra(AddActivity.ADD_Student);
            if(student != null)
            {
                studentiAfisare.add(student);
            }
        }
        CustomAdapter_Student adapter = new CustomAdapter_Student(getApplicationContext(), R.layout.item_student, studentiAfisare, getLayoutInflater()){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view = super.getView(position, convertView, parent);

                Student student = studentiAfisare.get(position);
                TextView tvMedie = view.findViewById(R.id.textViewMedie);
                if(student.getMedie() < 5)
                {
                    tvMedie.setTextColor(Color.RED);
                }
                else
                {
                    tvMedie.setTextColor(Color.GREEN);
                }
                return view;

            }
        };


        listViewStudentiAfisare.setAdapter(adapter);

    }
}