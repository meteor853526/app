package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class activity_mainpage extends AppCompatActivity {
    private String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        Bundle bundle = getIntent().getExtras();
        account = bundle.getString("account");
        VideoView videoView=findViewById(R.id.Vd_View);
        String videoPath="android.resource://"+getPackageName()+"/"+R.raw.breakfast;
        Uri uri= Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        MediaController mediaController=new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

        //設置循環播放
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });

    }
    public void onClickBtn(View view) {
        System.out.println(account+"2");
        Intent intent = new Intent(this, Foodpage_1.class);
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClickBtn_2(View view) {
        System.out.println(account+"2_2");
        Intent intent = new Intent(this, Foodpage2.class);
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void onClickBtn_3(View view) {
        System.out.println(account+"2_3");
        Intent intent = new Intent(this, Foodpage3.class);
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void onClickToCategory_page(View view) {
        Intent intent = new Intent(activity_mainpage.this, Category_page.class);
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void onClickToCheckOutPage(View view) {
        Intent intent = new Intent(activity_mainpage.this, Checkout_page.class);
        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}