package com.floreerin.doit_android_sample_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ch06_Fragment1 extends Fragment {
    ImageView btn_play;
    ImageView btn_stop;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) getLayoutInflater().inflate(R.layout.ch06_fragment1, container,false);
        // 서비스 기능 수행
        btn_play = rootView.findViewById(R.id.play_btn);
        btn_stop = rootView.findViewById(R.id.stop_btn);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startService(new Intent(getActivity(),ch06_MusicService.class)); // 음악 재생 서비스 불러오기
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().stopService(new Intent(getActivity(),ch06_MusicService.class)); // 음악 정지 서비스 불러오기
            }
        });
        return rootView;
    }
}
