package com.example.androidhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView TX;
    private Handler mHandle,mHandler1;
    class MHandler extends Handler{
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    TX.setText("(重写内部类sendMessage)线程1执行");
                    break;
                case 2:
                    TX.setText("(重写内部类sendMessage)线程2执行");
                    break;

            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TX=findViewById(R.id.TX);

        mHandle=new Handler(){
            @Override
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 1:
                        TX.setText("(匿名内部类sendMessage)线程1执行");
                        break;
                    case 2: 
                        TX.setText("(匿名内部类sendMessage)线程2执行");
                        break; };}};
/*        new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler1.post(new Runnable() {
                    @Override
                    public void run() {
                        TX.setText("线程1执行");
                    }
                });
            }

        }.start();
        new Thread(){
            public void run(){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler1.post(new Runnable() {
                    @Override
                    public void run() {
                        TX.setText("线程2执行");
                    }
                });
            }
        }.start();*/
        new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg=Message.obtain();
                msg.what=1;
                msg.obj="A";
                mHandle.sendMessage(msg);
            }

        }.start();
        new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg=Message.obtain();
                msg.what=2;
                msg.obj="B";
                mHandle.sendMessage(msg);
            }
        }.start();
    }
}
