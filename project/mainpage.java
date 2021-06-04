package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainpage extends AppCompatActivity {
    private Button accbut;
    private Button finbut;
    private Button logbut;
    private Button expbut;
    private Button testing;
    private Button searching;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        accbut = (Button)findViewById( R.id.account);
        logbut = (Button)findViewById( R.id.logout);
        finbut = (Button)findViewById( R.id.finances);
        expbut = (Button)findViewById( R.id.button6);
        testing = (Button)findViewById( R.id.button12);
        searching = (Button)findViewById( R.id.button22);
        finbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gofinance();
            }
        });
        logbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goout();
            }
        });
        accbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goacc();
            }
        });
        expbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goexp();
            }
        });
        testing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotest();
            }
        });
        searching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gosearch();
            }
        });

    }
    private void goexp()
    {

        Intent intent =new Intent(mainpage.this,expendature.class);
        startActivity(intent);
    }
    private void goacc()
    {

        Intent intent =new Intent(mainpage.this,accountmanage.class);
        startActivity(intent);
    }
    private void goout()
    {

        Intent intent =new Intent(mainpage.this,MainActivity.class);
        startActivity(intent);
    }
    private void gofinance()
    {

        Intent intent =new Intent(mainpage.this,finance2.class);
        startActivity(intent);
    }
    private void gotest()
    {

        Intent intent =new Intent(mainpage.this,incomechoice.class);
        startActivity(intent);
    }
    private void gosearch()
    {

        Intent intent =new Intent(mainpage.this,search.class);
        startActivity(intent);
    }
}
