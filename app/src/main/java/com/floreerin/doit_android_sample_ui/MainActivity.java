package com.floreerin.doit_android_sample_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_go_5th;
    Button btn_go_6th;
    Button btn_go_7th;
    Button btn_go_11th;
    Button btn_go_12th;
    Button btn_go_13th;
    Button btn_go_14th;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_go_5th = findViewById(R.id.btn_go_ch05);
        btn_go_6th = findViewById(R.id.btn_go_ch06);
        btn_go_7th = findViewById(R.id.btn_go_ch07);

        btn_go_11th = findViewById(R.id.btn_go_ch11);
        btn_go_12th = findViewById(R.id.btn_go_ch12);
        btn_go_13th = findViewById(R.id.btn_go_ch13);
        btn_go_14th = findViewById(R.id.btn_go_ch14);

        btn_go_5th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ch05_Intro.class);
                startActivity(intent);

                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환시 페이드 인, 아웃 효과
            }
        });

        btn_go_6th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ch06_Intro.class);
                startActivity(intent);

                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환시 페이드 인, 아웃 효과
            }
        });

        btn_go_7th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ch07_Intro.class);
                startActivity(intent);

                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환시 페이드 인, 아웃 효과
            }
        });

        btn_go_11th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ch11_Intro.class);
                startActivity(intent);

                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환시 페이드 인, 아웃 효과
            }
        });

        btn_go_12th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ch12_Intro.class);
                startActivity(intent);

                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환시 페이드 인, 아웃 효과
            }
        });

        btn_go_13th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ch13_Intro.class);
                startActivity(intent);

                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환시 페이드 인, 아웃 효과
            }
        });

        btn_go_14th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ch14_Intro.class);
                startActivity(intent);

                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환시 페이드 인, 아웃 효과
            }
        });
    }
}
