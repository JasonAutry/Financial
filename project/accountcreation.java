package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;

public class accountcreation extends AppCompatActivity {
    private EditText emailin;
    private EditText name1;
    private EditText name2;
    private EditText passwordin;
    private EditText idin;
    private Button createacc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountcreation);
        emailin = (EditText)findViewById(R.id.editText);
        name1 = (EditText)findViewById(R.id.editText3);
        name2 = (EditText)findViewById(R.id.editText4);
        passwordin = (EditText)findViewById(R.id.editText2);
        idin = (EditText)findViewById(R.id.editText5);
        createacc=(Button)findViewById(R.id.button2);
        createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createaccount(emailin.getText().toString(),name1.getText().toString(),name2.getText().toString(),passwordin.getText().toString(),idin.getText().toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(accountcreation.this, login.class);
                startActivity(intent);

            }
        });
    }
    private void createaccount(String emailinp,String name1in,String name2in,String passwordinp,String idinp) throws IOException {

        String fileContents=emailinp+","+passwordinp+","+name1in+" "+name2in+","+idinp+",\n";
        File file = new File(this.getFilesDir(), "userinfo");
        RandomAccessFile reader = new RandomAccessFile(file, "rw");
        File file2 = new File(this.getFilesDir(), "wallet");
        RandomAccessFile reader2 = new RandomAccessFile(file2, "rw");
        reader.writeBytes(fileContents);
        Calendar c = Calendar.getInstance();
        String date = String.valueOf(c.get(Calendar.YEAR))+","+String.valueOf(c.get(Calendar.MONTH))+","+String.valueOf(c.get(Calendar.DAY_OF_MONTH))+","+String.valueOf(c.get(Calendar.WEEK_OF_YEAR))+",";
        reader2.writeBytes("0,1,0,0,0,0,0,0,0");
        reader.close();

    }
}
