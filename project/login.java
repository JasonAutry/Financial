package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class login extends AppCompatActivity {

    private EditText password;
    private EditText email;
    private Button loginp;
    private Button forgot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        password = (EditText)findViewById(R.id.passwordtext);
        email = (EditText)findViewById(R.id.emailtext);
        loginp = (Button)findViewById(R.id.button);
        forgot = (Button)findViewById(R.id.button3);
        loginp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    validate(password.getText().toString(),email.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    private void validate(String pass,String mail) throws IOException {

        String getinfo;
        String[] splitter;
        File file = new File(this.getFilesDir(), "userinfo");
        RandomAccessFile reader = new RandomAccessFile(file, "rw");
        getinfo = reader.readLine();
        splitter = getinfo.split(",");

        reader.close();
        if (mail.equals(splitter[0]) && pass.equals(splitter[1])) {
            Intent intent = new Intent(login.this, walletcalcs.class);
            startActivity(intent);
        }
    }
}