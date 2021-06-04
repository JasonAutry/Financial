package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;

public class hours extends AppCompatActivity {

    private Button subm;
    private EditText hourss;
    private RadioGroup grou;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours);

        subm = findViewById(R.id.button17);
        hourss = (EditText)findViewById(R.id.editText10);



        subm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                File dir = getFilesDir();
                File file = new File(dir,"wallet");


            }
        });
    }



    }
