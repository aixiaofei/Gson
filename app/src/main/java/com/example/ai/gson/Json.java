package com.example.ai.gson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Json extends AppCompatActivity {
    private MyCallable gsonGet;
//    private Button button;
    private String res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        ExecutorService pool= Executors.newFixedThreadPool(1);
        gsonGet= new MyCallable("http://www.weather.com.cn/data/cityinfo/101010100.html");
        Future f1= pool.submit(gsonGet);
        try {
            res= f1.get().toString();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("ai",Integer.toString(res.length()));
        Gson gson= new Gson();
        weather w= gson.fromJson(res,weather.class);
        Log.d("ai",w.weatherinfo.city);
//        button= (Button) findViewById(R.id.json);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    StringBuilder stringBuilder = new StringBuilder();
//                    String buf;
//                    InputStream inputStream = getResources().openRawResource(R.raw.weather);
//                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                    while ((buf = bufferedReader.readLine()) != null) {
//                        stringBuilder.append(buf);
//                    }
//                    Gson gson= new Gson();
//                    weather res= gson.fromJson(stringBuilder.toString(),weather.class);
//                    Log.d("ai",res.weatherinfo.city);
//                }catch (IOException e){
//                    e.printStackTrace();
//                }
//            }
//        });
    }
}
