package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class incomechoice extends AppCompatActivity {
    private Button One;
    private Button repeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomechoice);
        One=(Button)findViewById(R.id.button14);
        repeat=(Button)findViewById(R.id.button15);
        One.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goone();
            }
        });
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gorepeat();
            }
        });

    }
    private void goone()
    {

        Intent intent =new Intent(incomechoice.this,onein.class);
        startActivity(intent);
    }
    private void gorepeat()
    {

        Intent intent =new Intent(incomechoice.this,multiin.class);
        startActivity(intent);
    }
}
