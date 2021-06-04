package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Profession extends AppCompatActivity {
    private EditText new1;
    private Button sub1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profession);
        new1 = (EditText)findViewById(R.id.editText11);
        sub1 = (Button)findViewById(R.id.button23);
        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    updatem(new1.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void updatem(String num) throws IOException {
        File dir = this.getFilesDir();
        File file = new File(dir,"profession");
        RandomAccessFile reader = new RandomAccessFile(file, "rw");
        reader.writeBytes(num);
        reader.close();
        Intent intent =new Intent(Profession.this,mainpage.class);
        startActivity(intent);
    }
}
