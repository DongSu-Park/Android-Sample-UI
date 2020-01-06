package com.floreerin.doit_android_sample_ui;

import android.app.Service;
import android.content.Intent;
import android.icu.util.Measure;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ch06_MusicService extends Service {

    private static final String TAG = "MusicService"; // 로그창에서 확인하기 위한 TAG 표시
    MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() { // 음악 재생 서비스 실행 시 재생할 음악을 설정
        Log.d(TAG, ": onCreate");
        player = MediaPlayer.create(this,R.raw.music1); // 재생할 음악 파일 대상 지정
        player.setLooping(false); // 음악이 끝나도 계속 실행 될 것인지 설정
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "음악을 재생합니다.", Toast.LENGTH_LONG).show();
        player.start(); // 재생 버튼 클릭시 음악 재생
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "음악 서비스가 종료되었습니다.", Toast.LENGTH_LONG).show();
        player.stop(); // 정지 버튼 클릭 시 음악 종료
    }
}
