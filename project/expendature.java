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

public class expendature extends AppCompatActivity {





    private Button submitspend;
    private EditText amount;
    private RadioGroup typespend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expendature);

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
        String date = String.valueOf(c.get(Calendar.YEAR))+String.valueOf(c.get(Calendar.MONTH))+String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        File file = new File(dir,"transactions");
        String buffer;
        int x=0;
        RandomAccessFile reader= new RandomAccessFile(file,"rw");
        while((buffer=reader.readLine())!=null&&!((buffer).equals(date)))
        {x++;}
            if(buffer!=null&&buffer.equals(date))
            {
                x++;
            }
            else if(buffer==null)
            {
                x++;
                reader.writeBytes("}\n"+date+"\n{\n");

            }
        while((buffer=reader.readLine())!=null)
        {}
            switch (type) {
                case R.id.radioButton2:
                    reader.writeBytes(num + ",1,"+String.valueOf(x)+",\n");//wants
                    break;
                case R.id.radioButton4:
                    reader.writeBytes(num + ",2,"+String.valueOf(x)+",\n"); //savings
                    break;
                case R.id.radioButton6:
                    reader.writeBytes(num + ",3,"+String.valueOf(x)+",\n");//needs
                    break;
            }
            reader.close();
        Intent intent =new Intent(expendature.this,walletcalcs.class);
        startActivity(intent);
    }
}
