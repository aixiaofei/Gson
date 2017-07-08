package com.example.ai.gson;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ai on 2017/7/8.
 */

class GsonGet extends Thread{

    private String ourl;
    private StringBuilder builder= new StringBuilder();
    public GsonGet(String ourl){
        this.ourl=ourl;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(ourl);
            //使用方法打开链接,并使用connection接受返回值
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
//            connection.setRequestProperty("apikey","5868f593cba259fad241bfae766bd3be");
            connection.setConnectTimeout(5 * 1000);
            connection.connect();
            //获取connection输入流,并用is接受返回值
            InputStream is = connection.getInputStream();
            //将字节流向字符流的转换
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line;
            /**
             * StringBuilder和StringBuffer不同的地方在于 StringBuffer是线程安全的
             * 单线程、不需要线程安全的情况下，处于性能的考虑，优先选择StringBuilder
             */
            while ((line = br.readLine())!= null) {
                builder.append(line);
            }

            br.close();
            isr.close();
            is.close();

            System.out.println(builder.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("ai","completed!");
    }

    public StringBuilder getBuilder(){
        return builder;
    }
}
