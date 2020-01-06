package com.floreerin.doit_android_sample_ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ch12_MainActivity extends AppCompatActivity {

    private long backkeyClickTime = 0;
    int initialColor = 0;

    ch12_PaintView PaintView;
    ImageView set_color;
    ImageView set_stroke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch12_activity_main);

        PaintView = new ch12_PaintView(this);

        LinearLayout container = findViewById(R.id.container);
        Resources res = getResources();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        container.addView(PaintView, params);

        set_color = findViewById(R.id.set_rgb);
        set_stroke = findViewById(R.id.set_stroke);

        set_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeRGB(); // 색을 변경하는 다이얼로그 호출
            }
        });

        set_stroke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeStroke(); // 선의 굵기를 변경하는 다이얼로그 호출
            }
        });
    }

    private void ChangeRGB() { // github의 ambilwarna color picker 외부 라이브러리 사용
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, initialColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                PaintView.setColor(color); // 컬러바에서 선택 후 선의 색을 설정.
            }
        });

        dialog.show();
    }

    private void ChangeStroke() { // 굵기 설정을 하는 다이얼로그 표시
        final EditText input = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("원하는 굵기를 입력하세요");
        builder.setView(input);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PaintView.setStrokeWidth(Float.parseFloat(input.getText().toString())); // float 형 변환으로 굵기를 설정
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backkeyClickTime + 2000) {
            backkeyClickTime = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "뒤로 가기 버튼을 누르면 메인화면으로 이동합니다.", Toast.LENGTH_LONG).show();
        } else if (System.currentTimeMillis() <= backkeyClickTime + 2000) {
            finish();
        }
    }
}
