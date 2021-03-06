package com.example.a1.appwelcome170523;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SplashActivity extends BaseActivity {

    String APP_PATH;
    String VIDEO_NAME="welcome_media.mp4";

    @Override
    protected void initVariables() {
        System.out.println("aaa---->>>>Splash--11111-over");
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        System.out.println("aaa---->>>>Splash---begin");
        setContentView(R.layout.activity_splash);
        APP_PATH = getApplicationContext().getFilesDir().getAbsolutePath();
    }

    @Override
    protected void loadData() {
        System.out.println("aaa---->>>>Splash---over");
        //初始化welcome_media.mp4文件。如果内存卡存在则直接播放，如果不存在则从资源文件中读取写入内存卡
        if(!new File(APP_PATH + VIDEO_NAME).exists()){
            try {
                //输入流
                InputStream in = getApplicationContext().getResources().openRawResource(R.raw.welcome_media);
                //输出流
                OutputStream out = new FileOutputStream(APP_PATH + VIDEO_NAME);
                //将资源文件welcome.mp3写入sd卡中
                byte[] buffer = new byte[1024];
                int length;

                while((length=in.read(buffer))>0){
                    out.write(buffer,0,length);
                }
                out.flush();
                out.close();
                in.close();
                Log.d("SplashActivity","aaa----->>>写入成功");

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                File file = new File(APP_PATH + VIDEO_NAME);
                if(file.exists()){
                    //如果视频写入成功，则打开引导界面
                    Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                    intent.putExtra("VideoPath",APP_PATH + VIDEO_NAME);
                    startActivity(intent);
                }else{
                    //如果写入不成功，跳过引导界面，直接打开程序主界面
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        },3000);
    }
}
