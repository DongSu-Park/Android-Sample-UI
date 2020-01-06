package com.floreerin.doit_android_sample_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ch05_Intro extends AppCompatActivity {
    Button goto_toptab_menu;
    Button goto_view_menu;
    Button goto_point_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch05_intro);

        goto_toptab_menu = findViewById(R.id.okBtn_5th_toptap);
        goto_view_menu = findViewById(R.id.okBtn_5th_view);
        goto_point_menu = findViewById(R.id.okBtn_5th_point);

        goto_toptab_menu.setOnClickListener(new View.OnClickListener() { // 상단 탭 기능 보여주는 버튼
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ch05_tabtop_MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환시 페이드 인, 아웃 효과

                finish();
            }
        });

        goto_view_menu.setOnClickListener(new View.OnClickListener() { // 뷰페이저 기능 보여주는 버튼
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ch05_view_MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환시 페이드 인, 아웃 효과

                finish();
            }
        });

        goto_point_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //
                Intent intent = new Intent(getApplicationContext(), ch05_Navigation_MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환시 페이드 인, 아웃 효과

                finish();
            }
        });
    }

    public void BackTitle(View view) { // Back 버튼 클릭시
        finish(); // 엑티비티 종료 후 메인화면으로 이동

        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환시 페이드 인, 아웃 효과
    }
}
