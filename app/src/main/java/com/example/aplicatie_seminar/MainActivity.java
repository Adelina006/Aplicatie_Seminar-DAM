package com.example.aplicatie_seminar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD = 100;
    public static final int REQUEST_CODE_EDIT = 200;
    public static final String EDIT_Student = "Student editat";
    int poz;
    public ListView listViewStudentiMain;
    public List<Student> studenti = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButtonPlusStudent);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
               // startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        listViewStudentiMain = findViewById(R.id.ListViewStudentiMain);

        listViewStudentiMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Student student = studenti.get(position);
                ArrayAdapter adapter = (ArrayAdapter) listViewStudentiMain.getAdapter();

                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Stergere student")
                        .setMessage("Doriti sa stergeti?")
                        .setNegativeButton("NU", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("DA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                studenti.remove(student);
                                adapter.notifyDataSetChanged();
                                dialog.cancel();
                            }
                        })
                        .create();
                dialog.show();

                return true;
            }
        });

        listViewStudentiMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                poz = position;
              Student studentAux = studenti.get(position);
              Intent intent = new Intent(getApplicationContext(), AddActivity.class);
              intent.putExtra(EDIT_Student, studentAux);
              startActivityForResult(intent, REQUEST_CODE_EDIT);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK && data != null)
        {
            Student student = (Student) data.getSerializableExtra(AddActivity.ADD_Student);
            if(student != null)
            {
                studenti.add(student);
                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, studenti);
                listViewStudentiMain.setAdapter(adapter);
            }
        }
        if(requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK && data != null)
        {
            Student student = (Student) data.getSerializableExtra(AddActivity.ADD_Student);
            if(student != null)
            {
                studenti.get(poz).setNume(student.getNume());
                studenti.get(poz).setDataNasterii(student.getDataNasterii());
                studenti.get(poz).setMedie(student.getMedie());
                studenti.get(poz).setFacultate(student.getFacultate());
                studenti.get(poz).setAreBursa(student.getAreBursa());
            }

           ArrayAdapter<Student> adapter = (ArrayAdapter<Student>) listViewStudentiMain.getAdapter();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu_principal, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.optiunea1)
        {
            Intent intent = new Intent(getApplicationContext(), BNRActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.optiunea2)
        {
            return true;
        }
        else if (item.getItemId() == R.id.optiunea3)
        {
            return true;
        }
        else if (item.getItemId() == R.id.optiunea4)
        {
            return true;
        }

        return false;
    }
}