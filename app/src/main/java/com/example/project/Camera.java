package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.MediaController;
import android.widget.VideoView;

public class Camera extends AppCompatActivity {

    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        MediaController mediaController = new MediaController(this);

        videoView = (VideoView)findViewById(R.id.videoView);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        String url ="feeding.ddns.net:8081";
        String u1 = "https://www.youtube.com/watch?v=HBF7k3DXkls";
        videoView.setVideoURI(Uri.parse(u1));
        videoView.requestFocus();
        videoView.start();

    }
}