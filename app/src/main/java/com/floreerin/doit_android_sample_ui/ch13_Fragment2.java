package com.floreerin.doit_android_sample_ui;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ch13_Fragment2 extends Fragment {

    String AUDIO_URL; // mp3 경로 객체
    TextView Mp3_Title; // mp3 제목 객체
    Button set_Mp3; // mp3 선택 엑티비티 버튼
    ImageView btn_Play; // 시작 버튼
    ImageView btn_Pause; // 일시정지 버튼
    ImageView btn_Restart; // 재시작 버튼
    ImageView btn_Stop; // 정지 버튼
    MediaPlayer mediaPlayer; // mediaPlayer 음악 실행 클래스

    SeekBar sb;
    int position; // 재생 멈춘 시점
    boolean isPlaying = false; // 재생중인지 확인하는 변수

    class MyThread extends Thread{ // seekbar 스레드 함수 실행
        @Override
        public void run() {
            while(isPlaying){
                try {
                    sb.setProgress(mediaPlayer.getCurrentPosition());
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) getLayoutInflater().inflate(R.layout.ch13_fragment2, container,false);

        // 음악 서비스 기능 실행 소스
        set_Mp3 = rootView.findViewById(R.id.set_mp3);
        Mp3_Title = rootView.findViewById(R.id.title_music);
        btn_Play = rootView.findViewById(R.id.btn_play);
        btn_Pause = rootView.findViewById(R.id.btn_pause);
        btn_Restart = rootView.findViewById(R.id.btn_restart);
        btn_Stop = rootView.findViewById(R.id.btn_stop);

        set_Mp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMp3SelectionActivty(); // mp3 파일을 선택하는 액티비티 실행 함수
            }
        });

        btn_Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AUDIO_URL != null){
                    playAudio(AUDIO_URL);
                    Toast.makeText(getContext(),"음악이 재생 되었습니다.",Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(getContext(),"음악을 먼저 선택하세요",Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null) {
                    position = mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                    Toast.makeText(getContext(),"음악 파일 재생 일시정지됨.",
                            Toast.LENGTH_LONG).show();

                    btn_Play.setVisibility(View.INVISIBLE);
                    btn_Restart.setVisibility(View.VISIBLE);
                    btn_Pause.setVisibility(View.INVISIBLE);

                    isPlaying = false;
                }
            }
        });

        btn_Restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    mediaPlayer.seekTo(position);
                    Toast.makeText(getContext(),"음악 파일 재생 재시작됨.",
                            Toast.LENGTH_LONG).show();

                    btn_Play.setVisibility(View.INVISIBLE);
                    btn_Restart.setVisibility(View.INVISIBLE);
                    btn_Pause.setVisibility(View.VISIBLE);

                    isPlaying = true;
                    new MyThread().start();
                }
            }
        });

        btn_Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer != null){
                    isPlaying = false;

                    mediaPlayer.stop();
                    mediaPlayer.release();

                    Toast.makeText(getContext(),"음악이 정지 되었습니다.",Toast.LENGTH_LONG).show();

                    btn_Play.setVisibility(View.VISIBLE);
                    btn_Restart.setVisibility(View.INVISIBLE);
                    btn_Pause.setVisibility(View.INVISIBLE);

                    sb.setProgress(0);
                }
            }
        });

        // 시크바 설정 소스
        sb = (SeekBar) rootView.findViewById(R.id.seekBar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                try {
                    if (seekBar.getMax() == progress) { // 진행바가 시크바의 최대점을 찍을 때
                        isPlaying = false;
                        mediaPlayer.stop(); // 음악을 정지함
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { // 시크바를 움직이고 있는 중에
                try {
                    isPlaying = false;
                    mediaPlayer.pause();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { // 시크바 움직이는 것을 완료하면 (터치에서 때면)
                try {
                    isPlaying = true; // 음악이 실행 중인것으로 바꿈
                    int playpostion = seekBar.getProgress();
                    mediaPlayer.seekTo(playpostion);
                    mediaPlayer.start();
                    new MyThread().start();

                    btn_Play.setVisibility(View.INVISIBLE);
                    btn_Restart.setVisibility(View.INVISIBLE);
                    btn_Pause.setVisibility(View.VISIBLE);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        return rootView;
    }

    private void playAudio(String audio_url) {
        killMediaPlayer();

        try {
            mediaPlayer = new MediaPlayer(); // 음악 서비스를 실행하는 MediaPlayer class 객체 생성
            mediaPlayer.setDataSource(audio_url); // 액티비티로 받은 url 경로를 지정
            mediaPlayer.prepare();
            mediaPlayer.start(); // 음악 재생

            int a = mediaPlayer.getDuration();
            sb.setMax(a);
            new MyThread().start();
            isPlaying = true;

            btn_Play.setVisibility(View.INVISIBLE);
            btn_Restart.setVisibility(View.INVISIBLE);
            btn_Pause.setVisibility(View.VISIBLE);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isPlaying = false;
        killMediaPlayer(); // 프레그먼트 종료시 음악 종료
    }

    private void killMediaPlayer() { // 음악 종료 메소드
        if (mediaPlayer != null){
            try {
                mediaPlayer.release();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void showMp3SelectionActivty() {
        if(AUDIO_URL != null){
            killMediaPlayer(); // 이미 실행 중인 음악이 있을 경우 음악을 정지하고 경로 객체에서 제거한다.
        }
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI); // audio의 uri을 건내받는 intent 명시
        startActivityForResult(intent, 101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null){
            if(requestCode == 101){
                Uri seletedMp3 = data.getData();
                String[] projection = {MediaStore.Audio.Media.TITLE , MediaStore.Audio.Media.DATA};
                Cursor cursor = getContext().getContentResolver().query(seletedMp3, projection, null, null, null);

                while(cursor.moveToNext()){
                    AUDIO_URL = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    Mp3_Title.setText("음악 : " + cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                }
                cursor.close();
            }
        }
    }
}

