package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class hourin extends AppCompatActivity {

    private Button subm;
    private EditText hourss;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourin);

        subm = (Button) findViewById(R.id.button18);
        hourss = (EditText) findViewById(R.id.editText9);
        info = (TextView) findViewById(R.id.textView16);


        subm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                File dir = getFilesDir();
                File file = new File(dir, "wallet");
                RandomAccessFile reader = null;
                try {
                    reader = new RandomAccessFile(file,"rw");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String buffer= null;
                try {
                    buffer = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String[] splitbuff=buffer.split(",");
                File file2 = new File(dir, "income");
                RandomAccessFile reader2 = null;
                try {
                    reader2 = new RandomAccessFile(file2,"rw");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String buffer2= null;
                try {
                    buffer2 = reader2.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String[] splitbuff2=buffer2.split(",");
                int hoursinput=Integer.valueOf(hourss.getText().toString());
                int incomtot=hoursinput*Integer.valueOf(splitbuff2[0]);
                splitbuff[0]=String.valueOf(Integer.valueOf(splitbuff[0])+incomtot);
                splitbuff[5]=String.valueOf(Integer.valueOf(splitbuff[5])+incomtot);
                Intent intent =new Intent(hourin.this,walletcalcs.class);
                startActivity(intent);


            }
        });
    }
}
