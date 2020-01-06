package com.floreerin.doit_android_sample_ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ch13_Fragment3 extends Fragment {
    VideoView videoView;
    ImageView set_Video;
    Uri video_Uri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) getLayoutInflater().inflate(R.layout.ch13_fragment3, container,false);

        // 동영상 재생 기능 소스 구현
        videoView = rootView.findViewById(R.id.videoView);

        MediaController mc = new MediaController(getContext());
        videoView.setMediaController(mc); // MediaController 서비스 클래스 설정

        set_Video = rootView.findViewById(R.id.set_Video);
        set_Video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showVideoSelectionActivity(); // 비디오를 외부저장소에서 가져오는 엑티비티 실행
            }
        });

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        killVideoPlayer(); // 프레그먼트 종료 시 비디오 종료 함수 실행
    }

    private void killVideoPlayer() {
        if(videoView != null){
            try{
                videoView.pause(); // 현재 실행중인 비디오를 종료 한다.
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void showVideoSelectionActivity() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI); // audio의 uri을 건내받는 intent 명시
        startActivityForResult(intent, 101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null){
            if(requestCode == 101){
                Uri seletedVideo = data.getData(); // context:// 로 경로 설정됨
                video_Uri = seletedVideo;  // 설정된 경로를 video_Uri 객체에 저장

                videoView.setVideoURI(video_Uri); // 비디오 뷰 객체에에 경로에 해당한 동영상 설정
                videoView.requestFocus();
                videoView.start(); // 비디오뷰 실행
            }
        }
    }
}
