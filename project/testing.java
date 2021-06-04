package com.example.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;

public class testing extends AppCompatActivity {
    private TextView only;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        only=(TextView)findViewById(R.id.textView14);
        File dir = this.getFilesDir();
        File file = new File(dir,"transactions");
        String out="";
        String buffer;
        int x=0;
        RandomAccessFile reader= null;
        try {
            reader = new RandomAccessFile(file,"rw");

        while((buffer=reader.readLine())!=null)
        {
            out+=buffer;
            out+="\n";
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        only.setText(out);
    }
}
