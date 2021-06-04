package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class onein extends AppCompatActivity {
private Button submit;
private EditText info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onein);
        submit = (Button) findViewById(R.id.button16);
        info = (EditText) findViewById((R.id.editText7));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    submi();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void submi() throws IOException {
        File dir = this.getFilesDir();
        File file1 = new File(dir,"wallet");
        String buffer;
        RandomAccessFile reader = new RandomAccessFile(file1, "rw");
        buffer = reader.readLine();
        reader.seek(0);
        String[] chop = buffer.split(",");
        chop[0]=String.valueOf((int)(Double.parseDouble(info.getText().toString())+Double.parseDouble(chop[0])));
        reader.writeBytes(chop[0]+","+chop[1]+","+chop[2]+","+chop[3]+","+chop[4]+",");
        Intent intent =new Intent(onein.this,mainpage.class);
        startActivity(intent);
    }
}

