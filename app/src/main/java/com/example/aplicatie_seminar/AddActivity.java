package com.example.aplicatie_seminar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    public static final String ADD_Student = "Student adaugat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String[] facultati = {"CSIE", "FABIZ", "REI", "MRK"};
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, facultati);
        Spinner spinnerFacultati = findViewById(R.id.spinnerFacultate);
        spinnerFacultati.setAdapter(adapter);

        EditText etNume = findViewById(R.id.editTextNume);
        EditText etDataNasterii = findViewById(R.id.editTextDataNasterii);
        EditText etMedie = findViewById(R.id.editTextMedie);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button buttonAdaugareStudent = findViewById(R.id.buttonAdaugareStudent);

        Intent intent =  getIntent();

        if(intent.hasExtra(MainActivity.EDIT_Student))
        {
            Student student = (Student) intent.getSerializableExtra(MainActivity.EDIT_Student);
            etNume.setText(student.getNume());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            etDataNasterii.setText(sdf.format(student.getDataNasterii()));
            etMedie.setText(String.valueOf(student.getMedie()));
            ArrayAdapter adapterSpinner = (ArrayAdapter) spinnerFacultati.getAdapter();
            for(int i = 0; i < adapterSpinner.getCount(); i++)
            {
                if(adapterSpinner.getItem(i).equals(student.getFacultate()))
                {
                    spinnerFacultati.setSelection(i);
                    break;
                }
            }
            if(student.getAreBursa() == true)
            {
                radioGroup.check(R.id.radioButtonDA);
            }
            else {
                radioGroup.check(R.id.radioButtonNU);
            }

        }


        buttonAdaugareStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNume.getText().toString().isEmpty())
                {
                    etNume.setError("Introduceti numele studentului");
                }
                else
                    if(etDataNasterii.getText().toString().isEmpty()) {
                        etDataNasterii.setError("Introduceti data nasterii");
                    }
                    else
                        if(etMedie.getText().toString().isEmpty())
                        {
                            etMedie.setError("Introduceti media");
                        }
                        else
                        {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                            try {
                                Date dataNasterii = sdf.parse(etDataNasterii.getText().toString());
                                String nume = etNume.getText().toString();
                                float medie = Float.parseFloat(etMedie.getText().toString());
                                String facultate = spinnerFacultati.getSelectedItem().toString();
                                boolean areBursa = false;
                                int id = radioGroup.getCheckedRadioButtonId();
                                RadioButton buton = findViewById(id);
                                if(buton.getText().toString().equals("DA"))
                                {
                                    areBursa = true;
                                }
                                Student student = new Student(nume, dataNasterii, medie, facultate, areBursa);
                                //Toast.makeText(getApplicationContext(), student.toString(), Toast.LENGTH_LONG).show();
                                intent.putExtra(ADD_Student, student);
                                setResult(RESULT_OK, intent);
                                finish();


                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                            catch (Exception e){
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }

                        }
            }
        });
    }
}