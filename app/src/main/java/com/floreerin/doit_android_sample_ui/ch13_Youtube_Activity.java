package com.floreerin.doit_android_sample_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class ch13_Youtube_Activity extends YouTubeBaseActivity {

    YouTubePlayerView playerView;
    WebView webView;

//  개인용 유튜브 API_KEY를 여기에 넣어주시기 바랍니다.
    private static String API_KEY = " ";
    String youtube_ID;
    String web_Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch13_youtube_activity);

        playerView = findViewById(R.id.playerView);
        webView = findViewById(R.id.apple_web);

        Intent url_get = getIntent();
        youtube_ID = url_get.getStringExtra("youtube_id"); // 리스트에 있는 유튜브 주소 id 값
        web_Url = url_get.getStringExtra("web_url"); // 리스트에 있는 웹페이지 주소 값

        initPlayer(); // 유튜브 실행

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl(web_Url);
    }

    private void initPlayer() {

        playerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(youtube_ID);
                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }

        });
    }
}
