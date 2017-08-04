package com.lizl.demo.myhandler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });
    }

    private void test() {
        Looper.prepare();
        final Handler myHandler = new Handler(){
            @Override
            public void handMessage(Message msg) {
                System.out.println("Thread "+Thread.currentThread().getName()+" receive "+msg.toString());
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        Message msg = new Message();
                        msg.what = 0;
                        synchronized (UUID.class) {
                            msg.obj = Thread.currentThread().getName() + " send " + UUID.randomUUID().toString();
                        }
                        System.out.println(msg);
                        myHandler.sendMessage(msg);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            },i+"").start();
        }
        Looper.loop();
    }
}
