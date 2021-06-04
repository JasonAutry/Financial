package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;

public class printout extends AppCompatActivity {
private TextView ptint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printout);
        ptint=(TextView)findViewById(R.id.textView20);

        try {
            printstuff();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printstuff() throws IOException {
        File dir = this.getFilesDir();
        Intent intent = getIntent();
        String buffer=new String("");
        String date = intent.getExtras().getString("search1");
        String date2 = intent.getExtras().getString("search2");
        File file = new File(dir,"transactions");

        RandomAccessFile reader;
        reader = new RandomAccessFile(file,"rw");

        while((buffer=reader.readLine())!=null&&!(buffer.equals(date)))
        {}
        String theout=buffer;
        if(buffer==null)
        {
            ptint.setText("There are no transactions between these dates.");
        }
        else
        {
            while((buffer=reader.readLine())!=null&&!((buffer).equals(date2)))
            {
                if(!buffer.equals("")) {
                    String[] data = buffer.split(",");
                    if (data.length > 2) {
                        switch(data[1]) {
                            case "1":
                                data[1] = "Want";
                                break;
                            case "2":
                                data[1] = "Saving";
                                break;
                            case "3":
                                data[1] = "Need";
                                break;

                        }
                        String temp=data[1]+": $"+data[0];
                        theout=theout+"\n"+temp;


                    }
                }
            }
            if(!date.equals(date2))
                theout=theout+"\n"+buffer;
            if(!date.equals(date2))
                while((((buffer = reader.readLine())) != null) && !((buffer).equals("}")))
                {
                    if(!buffer.equals("")) {
                        String[] data = buffer.split(",");
                        if (data.length > 2) {
                            switch(data[1]) {
                                case "1":
                                    data[1] = "Want";
                                    break;
                                case "2":
                                    data[1] = "Saving";
                                    break;
                                case "3":
                                    data[1] = "Need";
                                    break;

                            }
                            String temp=data[1]+": $"+data[0];
                            theout=theout+"\n"+temp;


                        }
                        else
                        {
                            theout=theout+"\n"+buffer;
                        }
                    }
                }
            ptint.setText(theout);
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
