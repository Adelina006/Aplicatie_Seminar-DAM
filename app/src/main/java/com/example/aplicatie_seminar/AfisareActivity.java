package com.example.aplicatie_seminar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
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
        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(getApplicationContext(), android.R.layout.simple_list_item_1, studentiAfisare);
        listViewStudentiAfisare.setAdapter(adapter);

    }
}