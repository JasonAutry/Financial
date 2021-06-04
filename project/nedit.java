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

public class nedit extends AppCompatActivity {
    private EditText new1;
    private Button sub1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nedit);
        new1 = (EditText)findViewById(R.id.passedit);
        sub1 = (Button)findViewById(R.id.button11);
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
        File file = new File(dir,"userinfo");
        String buffer;
        RandomAccessFile reader = new RandomAccessFile(file, "rw");
        buffer = reader.readLine();
        String[] chop = buffer.split(",");
        chop[2] = num;
        reader.seek(0);
        reader.writeBytes(chop[0]+","+chop[1]+","+chop[2]+","+chop[3]+",");
        reader.close();
        Intent intent =new Intent(nedit.this,mainpage.class);
        startActivity(intent);
    }
}
