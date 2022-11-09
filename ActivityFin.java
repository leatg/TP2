package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.VideoView;

public class ActivityFin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin);
        VideoView video = findViewById(R.id.videoView);
        video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video_intro);
        video.start();
        getSupportFragmentManager().beginTransaction().replace(R.id.fcvFin,FragmentMessage.newInstance("Bravo!")).commit();
    }
}