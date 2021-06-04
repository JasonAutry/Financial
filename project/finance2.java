package com.example.project;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class finance2 extends AppCompatActivity {

    String thing2[]= {"wants","savings","needs"};
    PieChart chartd;
    PieChart chartdex;
    PieChart chartt;
    PieChart charttex;
    private TextView allow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance2);
        chartd = (PieChart) findViewById(R.id.daily);
        chartdex = (PieChart) findViewById(R.id.dailyex);
        chartt = (PieChart) findViewById(R.id.monthly);
        charttex = (PieChart) findViewById(R.id.monthlyex);
        allow=(TextView)findViewById(R.id.textView21);
        try {
            setupPiechart1(chartd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            setupPiechart2(chartt);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            setupPiechart3(chartdex);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            setupPiechart4(charttex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private int[] getDaily() throws IOException {
        Calendar c = Calendar.getInstance();
        String buffer="q";
        File dir = this.getFilesDir();
        String date = String.valueOf(c.get(Calendar.YEAR))+String.valueOf(c.get(Calendar.MONTH))+String.valueOf(c.get(Calendar.DAY_OF_YEAR));
        File file = new File(dir,"transactions");
        int[] total={0,0,0};
        RandomAccessFile reader= new RandomAccessFile(file,"rw");
        reader.seek(0);
        //buffer=reader.readLine();
        while(((buffer = reader.readLine()) != null) && !((buffer).equals(date)))
        {}
        if(buffer==null)
        {
            return total;
        }
        int ppkk=0;
        while((buffer=reader.readLine())!=null&&!((buffer).equals("}")))
        {
            if(!buffer.equals("")) {
                String[] data = buffer.split(",");
                if (data.length > 2) {


                    int cash = (int) Double.parseDouble(data[0]);
                    int type = Integer.parseInt(data[1]);
                    if (type == 1) {
                        total[0] += cash;
                    }
                    if (type == 2) {
                        total[1] += cash;
                    }
                    if (type == 3) {
                        total[2] += cash;
                    }
                }
            }
        }
        ppkk=1;
        reader.close();
        return total;
    }
    private int[] getalltime() throws IOException {
        File dir = this.getFilesDir();
        File file = new File(dir,"transactions");
        String buffer;
        int[] total={0,0,0};
        RandomAccessFile reader= new RandomAccessFile(file,"rw");
        while((buffer=reader.readLine())!=null)
        {
            if(buffer!=null&&buffer.equals("{"))
            {
                while((buffer=reader.readLine())!=null&&!((buffer).equals("}")))
                {
                    if(!buffer.equals("")) {
                        String[] data = buffer.split(",");
                        if(data.length>=2) {
                            int cash = (int) Double.parseDouble(data[0]);
                            int type = Integer.parseInt(data[1]);
                            if (type == 1) {
                                total[0] += cash;
                            }
                            if (type == 2) {
                                total[1] += cash;
                            }
                            if (type == 3) {
                                total[2] += cash;
                            }
                        }
                    }
                }
            }
        }
        reader.close();
        return total;
    }
    private void setupPiechart1(PieChart chart) throws IOException {
        List<PieEntry> Pieinfo = new ArrayList<>();
        int[] thing = getDaily();
        for(int x = 0;x<thing.length;x++)
        {
            Pieinfo.add(new PieEntry(thing[x],thing2[x]));
        }
        PieDataSet data = new PieDataSet(Pieinfo,"piedata");
        data.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData datas = new PieData(data);

        chart.setData(datas);
        chart.invalidate();
    }
    private void setupPiechart2(PieChart chart) throws IOException {
        List<PieEntry> Pieinfo = new ArrayList<>();
        int[] thing = getalltime();
        for(int x = 0;x<thing.length;x++)
        {
            Pieinfo.add(new PieEntry(thing[x],thing2[x]));
        }
        PieDataSet data = new PieDataSet(Pieinfo,"piedata");
        data.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData datas = new PieData(data);

        chart.setData(datas);
        chart.invalidate();
    }
    private void setupPiechart3(PieChart chart) throws IOException {
        List<PieEntry> Pieinfo = new ArrayList<>();
        File dir = this.getFilesDir();
        File file4 = new File(dir,"wallet");
        String buffer4;
        RandomAccessFile reader4= new RandomAccessFile(file4,"rw");
        buffer4=reader4.readLine();
        String[] thing;
        thing=buffer4.split(",");
        double alltime=(int)(Double.valueOf(thing[2])*100);
        alltime/=100;
        allow.setText("Today's allowance is: $"+String.valueOf(alltime));
        int[] three=new int[3];
        three[0]=(int)(.3*alltime);
        three[1]=(int)(.5*alltime);
        three[2]=(int)(.2*alltime);

        for(int x = 0;x<thing2.length;x++)
        {
            Pieinfo.add(new PieEntry(three[x],thing2[x]));
        }
        PieDataSet data = new PieDataSet(Pieinfo,"piedata");
        data.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData datas = new PieData(data);
        reader4.close();
        chart.setData(datas);
        chart.invalidate();
    }
    private void setupPiechart4(PieChart chart) throws IOException {
        List<PieEntry> Pieinfo = new ArrayList<>();
        File dir = this.getFilesDir();
        File file4 = new File(dir,"wallet");
        String buffer4;
        RandomAccessFile reader4= new RandomAccessFile(file4,"rw");
        buffer4=reader4.readLine();
        String[] thing;
        thing=buffer4.split(",");
        double alltime=Double.valueOf(thing[5]);
        int[] three={(int)(.3*alltime),(int)(.5*alltime),(int)(.2*alltime)};

        for(int x = 0;x<thing2.length;x++)
        {
            Pieinfo.add(new PieEntry(three[x],thing2[x]));
        }
        PieDataSet data = new PieDataSet(Pieinfo,"piedata");
        data.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData datas = new PieData(data);
        reader4.close();
        chart.setData(datas);
        chart.invalidate();
    }
}
