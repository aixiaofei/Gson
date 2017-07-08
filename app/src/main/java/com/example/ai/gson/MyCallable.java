package com.example.ai.gson;

import android.provider.ContactsContract;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Created by ai on 2017/7/8.
 */

public class MyCallable implements Callable {

    private String ourl;
    private StringBuilder builder= new StringBuilder();
    public MyCallable(String ourl){
        this.ourl=ourl;
    }

    @Override
    public Object call() throws Exception {
        Log.d("ai","login");
        try {
            URL url = new URL(ourl);
            //使用方法打开链接,并使用connection接受返回值
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
//            connection.setRequestProperty("apikey","5868f593cba259fad241bfae766bd3be");
            connection.setConnectTimeout(5 * 1000);
            connection.connect();
            Log.d("ai","strat!");
            //获取connection输入流,并用is接受返回值
            InputStream is = connection.getInputStream();
            //将字节流向字符流的转换
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder;
    }
}
