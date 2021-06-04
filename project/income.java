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

public class income extends AppCompatActivity {

    private Button submitspend;
    private EditText amount;
    private RadioGroup typespend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);



        submitspend = (Button)findViewById(R.id.button10);
        amount = (EditText)findViewById(R.id.editText6);
        typespend = (RadioGroup)findViewById(R.id.radioGroup1);
        submitspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    record(amount.getText().toString(),typespend.getCheckedRadioButtonId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void record(String num,int type) throws IOException {
        Calendar c = Calendar.getInstance();
        File dir = this.getFilesDir();
        String date = String.valueOf(c.get(Calendar.YEAR))+","+String.valueOf(c.get(Calendar.MONTH))+","+String.valueOf(c.get(Calendar.DAY_OF_MONTH))+","+String.valueOf(c.get(Calendar.WEEK_OF_YEAR))+",";
        File file = new File(dir,"income");
        String buffer;
        RandomAccessFile reader= new RandomAccessFile(file,"rw");

        switch (type) {
            case R.id.radioButton4:
                reader.writeBytes(num + ",2,"+date); //weekly
                break;
            case R.id.radioButton6:
                reader.writeBytes(num + ",3,"+date);//monthly
                break;
        }
        reader.close();
        Intent intent =new Intent(income.this,walletcalcs.class);
        startActivity(intent);
    }
}











        /*
        income = (EditText)findViewById(R.id.editText8);
        period = (EditText)findViewById(R.id.editText7);
        name = (EditText)findViewById(R.id.editText9);
        sub= (Button)findViewById(R.id.button12);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    record();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void record() throws IOException {
        Calendar c = Calendar.getInstance();
        File dir = this.getFilesDir();
        String date = String.valueOf(c.get(Calendar.YEAR))+String.valueOf(c.get(Calendar.MONTH))+String.valueOf(c.get(Calendar.DAY_OF_YEAR));
        File file = new File(dir,"recurringincomes");
        String buffer;
        RandomAccessFile reader= new RandomAccessFile(file,"rw");
        reader.seek(file.length());
        reader.writeBytes(date+","+name.getText().toString()+","+period.getText().toString()+","+income.getText().toString()+","+date+",   \n");
        reader.close();
        File file2 = new File(dir,"incomes");
        RandomAccessFile reader2= new RandomAccessFile(file2,"rw");
        buffer=reader2.readLine();
        String[] temp=buffer.split(",");
        Double xcc=Double.parseDouble(temp[0]);
        xcc=xcc+Double.parseDouble(income.getText().toString());
        reader2.seek(0);
        reader2.writeBytes(String.valueOf(xcc)+",");
        reader2.close();
        Intent intent =new Intent(income.this,mainpage.class);
        startActivity(intent);
    }
}
*/