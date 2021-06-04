package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;

import static java.lang.Integer.*;

public class walletcalcs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walletcalcs);
        int x= 3;
        try {
            x = check();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch(x){
            case 0:
                Intent intent =new Intent(walletcalcs.this,mainpage.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent2 =new Intent(walletcalcs.this,hourin.class);
                startActivity(intent2);
                break;
            case 2:
                try {
                    monthlycalc();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    weeklycalc();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    monthlycalc();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        Intent intent =new Intent(walletcalcs.this,mainpage.class);
        startActivity(intent);
    }

    private void weeklycalc() throws IOException {
        Calendar c = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        File dir = this.getFilesDir();
        String[] splitbuffer;
        File file1 = new File(dir,"income");
        String buffer1;
        RandomAccessFile reader1= new RandomAccessFile(file1,"rw");
        File file2 = new File(dir,"transactions");
        String buffer2;
        RandomAccessFile reader2= new RandomAccessFile(file2,"rw");
        File file3 = new File(dir,"userinfo");
        String buffer3;
        RandomAccessFile reader3= new RandomAccessFile(file3,"rw");
        File file4 = new File(dir,"wallet");
        String buffer4;
        RandomAccessFile reader4= new RandomAccessFile(file4,"rw");
        buffer4=reader4.readLine();
        splitbuffer=buffer4.split(",");

        double moneyamt=0;
        moneyamt=moneyamt+Double.parseDouble(splitbuffer[0]);
        while((buffer2=reader2.readLine())!=null)
        {
            String[] splitbuffer2=buffer2.split(",");
            if(splitbuffer2.length>2&&splitbuffer[1].equals(splitbuffer2[2]))
            {
                break;
            }
        }
        String[] data;
        int[] total={0,0,0};
        int trans=0;
        while((buffer2=reader2.readLine())!=null)
        {
            if(buffer2!=null&&buffer2.equals("{"))
            {
                while((buffer2=reader2.readLine())!=null&&!((buffer2).equals("}")))
                {
                    if(!buffer2.equals("")) {
                        data = buffer2.split(",");
                        trans=getInteger(data[2]);
                        int cash = Integer.parseInt(data[0]);
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
        moneyamt=moneyamt-total[0]-total[1]-total[2];
        reader4.seek(0);
        int today=c.get(Calendar.DAY_OF_WEEK);
        int end=c.getActualMaximum(Calendar.DAY_OF_WEEK);
        double dailyall = moneyamt/(end-today+1);
        reader4.writeBytes(String.valueOf(moneyamt)+","+trans+","+dailyall+","+String.valueOf(today)+","+String.valueOf(end)+","+splitbuffer[5]+",");
        reader1.close();
        reader2.close();
        reader3.close();
        reader4.close();
    }

    private void monthlycalc() throws IOException {
        Calendar c = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        File dir = this.getFilesDir();
        String[] splitbuffer;
        File file1 = new File(dir,"income");
        String buffer1;
        RandomAccessFile reader1= new RandomAccessFile(file1,"rw");
        File file2 = new File(dir,"transactions");
        String buffer2;
        RandomAccessFile reader2= new RandomAccessFile(file2,"rw");
        File file3 = new File(dir,"userinfo");
        String buffer3;
        RandomAccessFile reader3= new RandomAccessFile(file3,"rw");
        File file4 = new File(dir,"wallet");
        String buffer4;
        RandomAccessFile reader4= new RandomAccessFile(file4,"rw");
        buffer4=reader4.readLine();
        splitbuffer=buffer4.split(",");
        double alltime=Double.valueOf(splitbuffer[5]);
        double moneyamt=0;
        moneyamt=moneyamt+Double.parseDouble(splitbuffer[0]);
        while((buffer2=reader2.readLine())!=null)
        {

            if((buffer2.split(",").length>1)&&splitbuffer[1].equals(buffer2.split(",")[2]))
            {
                break;
            }
        }
        String[] data;
        double[] total={0,0,0};
        int trans=0;
        while((buffer2=reader2.readLine())!=null)
        {
            if(buffer2!=null&&buffer2.equals("{"))
            {
                while((buffer2=reader2.readLine())!=null&&!((buffer2).equals("}")))
                {
                    if(buffer2.length()>2) {
                        data = buffer2.split(",");
                        trans=getInteger(data[2]);
                        double cash = Double.parseDouble(data[0]);
                        int type = Integer.parseInt(data[1]);
                        if (type == 1) {
                            total[0] += (int)cash;
                        }
                        if (type == 2) {
                            total[1] += (int)cash;
                        }
                        if (type == 3) {
                            total[2] += (int)cash;
                        }
                    }
                }
            }
        }
        double dailyall=0;
        moneyamt=moneyamt-total[0]-total[1]-total[2];
        reader4.seek(0);
        int today=c.get(Calendar.DAY_OF_MONTH);
        int end=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        if(today!=Integer.parseInt(splitbuffer[3]))
            dailyall = moneyamt/(end-today+1);
        else
            dailyall=(int)Double.parseDouble(splitbuffer[2]);
        reader4.writeBytes(String.valueOf(moneyamt)+","+trans+","+String.valueOf(dailyall)+","+String.valueOf(today)+","+String.valueOf(end)+","+String.valueOf(alltime)+",");
        reader1.close();
        reader2.close();
        reader3.close();
        reader4.close();
    }

    private int check() throws IOException {
        Calendar c = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        File dir = this.getFilesDir();
        String[] splitbuffer;
        File file1 = new File(dir,"income");
        String buffer1;
        RandomAccessFile reader1= new RandomAccessFile(file1,"rw");
        File file2 = new File(dir,"transactions");
        String buffer2;
        RandomAccessFile reader2= new RandomAccessFile(file2,"rw");
        File file3 = new File(dir,"userinfo");
        String buffer3;
        RandomAccessFile reader3= new RandomAccessFile(file3,"rw");
        File file4 = new File(dir,"wallet");
        String buffer4;
        RandomAccessFile reader4= new RandomAccessFile(file4,"rw");
        buffer1=reader1.readLine();
        if(buffer1==null){
            return 0;
        }
        else {
            splitbuffer=buffer1.split(",");
            if(splitbuffer[1].equals("2")) {
                c2.set(Integer.parseInt(splitbuffer[2]), Integer.parseInt(splitbuffer[3]), Integer.parseInt(splitbuffer[4]));
                if(c2.get(Calendar.WEEK_OF_YEAR)!=c.get(Calendar.WEEK_OF_YEAR)){
                    c2.add(Calendar.WEEK_OF_YEAR,1);
                    reader1.seek(0);
                    reader1.writeBytes(splitbuffer[0]+","+splitbuffer[1]+","+String.valueOf(c2.get(Calendar.YEAR))+","+String.valueOf(c2.get(Calendar.MONTH))+","+String.valueOf(c2.get(Calendar.DAY_OF_MONTH))+","+String.valueOf(c2.get(Calendar.WEEK_OF_YEAR))+",");
                    reader1.close();
                    reader2.close();
                    reader3.close();
                    reader4.close();
                    return 1;
                //wallet:currentbalance,last transaction number,daily allowance,today,next paycheck,all time money

                }
                reader1.seek(0);
                reader1.writeBytes(splitbuffer[0]+","+splitbuffer[1]+","+String.valueOf(c.get(Calendar.YEAR))+","+String.valueOf(c2.get(Calendar.MONTH))+","+String.valueOf(c.get(Calendar.DAY_OF_MONTH))+","+String.valueOf(c2.get(Calendar.WEEK_OF_YEAR))+",");
                reader1.close();
                reader2.close();
                reader3.close();
                reader4.close();
                return 3;
            }
            if(splitbuffer[1].equals("3")) {
                c2.set(Integer.parseInt(splitbuffer[2]), Integer.parseInt(splitbuffer[3]), Integer.parseInt(splitbuffer[4]));
                if(c2.get(Calendar.MONTH)!=c.get(Calendar.MONTH)){
                    c2.add(Calendar.MONTH,1);
                    reader1.seek(0);
                    reader1.writeBytes(splitbuffer[0]+","+splitbuffer[1]+","+String.valueOf(c2.get(Calendar.YEAR))+","+String.valueOf(c2.get(Calendar.MONTH))+","+String.valueOf(c2.get(Calendar.DAY_OF_MONTH))+","+
                            String.valueOf(c2.get(Calendar.WEEK_OF_YEAR))+",");
                    reader1.close();
                    String[] splitbuffer2;
                    splitbuffer2=reader4.readLine().split(",");
                    reader4.writeBytes(String.valueOf((Integer.valueOf(splitbuffer[0] )+Integer.valueOf(splitbuffer2[0])))+","+splitbuffer2[1]+","+splitbuffer2[2]+","+splitbuffer2[3]+","+splitbuffer2[4]+","+String.valueOf((Integer.valueOf(splitbuffer[0] )+Integer.valueOf(splitbuffer2[5])))+",");
                    reader2.close();
                    reader3.close();
                    reader4.close();
                    return 2;
                }
                reader1.seek(0);
                reader1.writeBytes(splitbuffer[0]+","+splitbuffer[1]+","+String.valueOf(c.get(Calendar.YEAR))+","+String.valueOf(c2.get(Calendar.MONTH))+","+String.valueOf(c.get(Calendar.DAY_OF_MONTH))+","+
                        String.valueOf(c.get(Calendar.WEEK_OF_YEAR))+",");
                reader1.close();
                reader2.close();
                reader3.close();
                reader4.close();
                return 4;
            }
        }
        reader1.close();
        reader2.close();
        reader3.close();
        reader4.close();
        return 4;
    }
}