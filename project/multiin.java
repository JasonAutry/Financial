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


public class multiin extends AppCompatActivity {



    private Button subm;
    private EditText amoun;
    private RadioGroup grou;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiin);

        subm = findViewById(R.id.qqqwww);
        amoun = (EditText)findViewById(R.id.editText8);
        grou=(RadioGroup)findViewById(R.id.radrad);
        subm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    recor(amoun.getText().toString(),grou.getCheckedRadioButtonId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void recor(String num,int type) throws IOException {
        Calendar c = Calendar.getInstance();
        File dir = this.getFilesDir();
        String date = String.valueOf(c.get(Calendar.YEAR))+String.valueOf(c.get(Calendar.MONTH))+String.valueOf(c.get(Calendar.DAY_OF_YEAR));
        File file = new File(dir,"income");
        RandomAccessFile reader= new RandomAccessFile(file,"rw");
switch(type)
{

    case R.id.weekly:
        reader.writeBytes(num+",2,"+String.valueOf(c.get(Calendar.YEAR))+","+String.valueOf(c.get(Calendar.MONTH))+","+String.valueOf(c.get(Calendar.DAY_OF_MONTH))+","+String.valueOf(c.get(Calendar.WEEK_OF_YEAR))+",");
        break;
    case R.id.monthly:
        reader.writeBytes(num+",3,"+String.valueOf(c.get(Calendar.YEAR))+","+String.valueOf(c.get(Calendar.MONTH))+","+String.valueOf(c.get(Calendar.DAY_OF_MONTH))+","+String.valueOf(c.get(Calendar.WEEK_OF_YEAR))+",");
        break;
}


        reader.close();
        Intent intent =new Intent(multiin.this,walletcalcs.class);
        startActivity(intent);
    }
}
