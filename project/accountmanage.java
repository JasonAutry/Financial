package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class accountmanage extends AppCompatActivity {


    private Button editmail;
    private Button editpassword;
    private Button editname;
    private Button editprof;
    private Button editid;
    private TextView showmail;
    private TextView showname;
    private TextView showprof;
    private TextView showpass;
    private TextView showid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountmanage);

        editmail = (Button)findViewById(R.id.button5);
        editprof = (Button)findViewById(R.id.button13);
        editid = (Button)findViewById(R.id.button9);
        editname = (Button)findViewById(R.id.button8);
        editpassword = (Button)findViewById(R.id.button7);
        showmail = (TextView)findViewById(R.id.textView2);
        showpass = (TextView)findViewById(R.id.textView4);
        showname = (TextView)findViewById(R.id.textView3);
        showprof = (TextView)findViewById(R.id.textView12);
        showid = (TextView)findViewById(R.id.textView5);
        File dir = this.getFilesDir();
        String[] splitbuffer;
        File file3 = new File(dir,"userinfo");
        String buffer3="";
        RandomAccessFile reader3= null;
        File file4 = new File(dir,"profession");
        String buffer4="";
        RandomAccessFile reader4= null;
        try {//1
            reader3 = new RandomAccessFile(file3,"rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            buffer3=reader3.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {//2
            reader4 = new RandomAccessFile(file4,"rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            buffer4=reader4.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        splitbuffer=buffer3.split(",");
        showmail.setText(splitbuffer[0]);
        showpass.setText(splitbuffer[1]);
        showname.setText(splitbuffer[2]);
        showid.setText(splitbuffer[3]);
        showprof.setText(buffer4);
        try {
            reader3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        editmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editm();
            }
        });
        editpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editp();
            }
        });
        editname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editn();
            }
        });
        editid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editi();
            }
        });
        editprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editprofess();
            }
        });
    }

    private void editm()
    {
        Intent intent =new Intent(accountmanage.this,medit.class);
        startActivity(intent);
    }
    private void editprofess()
    {
        Intent intent =new Intent(accountmanage.this,Profession.class);
        startActivity(intent);
    }
    private void editn()
    {
        Intent intent =new Intent(accountmanage.this,nedit.class);
        startActivity(intent);
    }
    private void editi()
    {
        Intent intent =new Intent(accountmanage.this,iedit.class);
        startActivity(intent);
    }
    private void editp()
    {
        Intent intent =new Intent(accountmanage.this,pedit.class);
        startActivity(intent);
    }
}
