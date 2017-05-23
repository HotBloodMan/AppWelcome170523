package com.example.a1.appwelcome170523;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import java.io.File;

public class GuideActivity extends BaseActivity {

    //注入adapter中的数组
    private String[] data = {"欢迎来到玩艺网,一款设计师品牌分享品质生活和开心送礼的购物应用",
            "定格生活中最让你心动的瞬间,我们让他全新呈现", "你送的礼物,给了他一个终身难忘的欢乐",
            "加入LuckyBox,每月寻找一款属于你的小幸运", "邀请你志同道合的小伙伴下载玩艺网,你可获得现金赠送"};

    String videoPath;
    private VideoView videoView;
    private ImageView imgEntry;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private GuideAdapter adapter;

    @Override
    protected void initVariables() {
        videoPath=getIntent().getStringExtra("VideoPath");
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        setContentView(R.layout.activity_guide);
        videoView = (VideoView) this.findViewById(R.id.id_video);
        imgEntry = (ImageView) this.findViewById(R.id.id_imgEntry);
        imgEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        viewPager= (ViewPager) this.findViewById(R.id.iv_viewPager);
        adapter = new GuideAdapter(getApplicationContext(), data);
        viewPager.setAdapter(adapter);
        System.out.println("aaa----->>>>1111 adapter= "+adapter.getCount());

        circleIndicator= (CircleIndicator) this.findViewById(R.id.id_circleIndicator);
        circleIndicator.setViewPager(viewPager);

       //让进入主界面的按钮是否显示
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                System.out.println("ccc------>>>>>>"+position);
                if(position==4){
                    imgEntry.setVisibility(View.VISIBLE);
                }
                /*
                * 这里也可以不用自定义的页面指示器，通过图片的形式
                * bt_start.setVisibility(View.GONE);
                  iv1.setImageResource(R.mipmap.dot_normal);
                  iv2.setImageResource(R.mipmap.dot_normal);
                  iv3.setImageResource(R.mipmap.dot_normal);
                  if (position == 0) {
                  iv1.setImageResource(R.mipmap.dot_focus);
                  } else if (position == 1) {
                iv2.setImageResource(R.mipmap.dot_focus);
                 } else {
                iv3.setImageResource(R.mipmap.dot_focus);
                bt_start.setVisibility(View.VISIBLE);
            }
                *
                *
                * */

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void loadData() {
        File file = new File(videoPath);
        if(!file.exists()){
            Log.d("GuideActivity","aaa----->>>视频文件不存在");
        }else{
           videoView.setVideoPath(file.getPath());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    //设置为填充父窗体
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            //设置布局
            videoView.setLayoutParams(layoutParams);
            //循环播放
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            videoView.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoView!=null){
            //释放掉内存
            videoView.suspend();
        }
    }
}
