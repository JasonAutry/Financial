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

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File test = new File(this.getFilesDir(), "userinfo");
        if (test.exists()) {
            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);

        }
        else
        {
            Intent intent = new Intent(MainActivity.this, accountcreation.class);
            startActivity(intent);
        }
    }
}