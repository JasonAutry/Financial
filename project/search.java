package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class search extends AppCompatActivity {
    private String pressed;
    private String pressed2;
    private CalendarView selector;
    private TextView firstdate;
    private TextView selecteddate;
    private TextView seconddate;
    private Button selectstart;
    private Button selectend;
    private Button startsearch;
    private String selected;
    private String shown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        selector=(CalendarView)findViewById(R.id.calendarView);
        selecteddate=(TextView)findViewById(R.id.textView19);
        selectstart=(Button)findViewById(R.id.button19);
        firstdate=(TextView)findViewById(R.id.textView17);
        selectend=(Button)findViewById(R.id.button20);
        seconddate=(TextView)findViewById(R.id.textView18);
        startsearch=(Button)findViewById(R.id.button21);
        selector.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selecteddate.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
                shown=String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year);
                selected=String.valueOf(year)+String.valueOf(month)+String.valueOf(dayOfMonth);
            }
        });
        selectstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed=selected;
                firstdate.setText(shown);
            }
        });
        selectend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed2=selected;
                seconddate.setText(shown);
            }
        });
        startsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pressed!=null&&pressed2!=null)
                {
                    Intent i = new Intent(search.this, printout.class);
                    i.putExtra("search1", pressed);
                    i.putExtra("search2", pressed2);
                    startActivity(i);
                }
            }
        });
    }

}
