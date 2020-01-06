package com.floreerin.doit_android_sample_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ch07_MainActivity extends AppCompatActivity {
    private long backkeyClickTime = 0;
    int set_img[] = {R.drawable.image01, R.drawable.image02, R.drawable.image03, R.drawable.image04, R.drawable.image05};
    String set_text[] = {"이미지 01 선택되었습니다.","이미지 02 선택되었습니다.",
            "이미지 03 선택되었습니다.","이미지 04 선택되었습니다.","이미지 05 선택되었습니다."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch07_activity_main);

        Spinner spinner = findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // 스피너 선택 리스너 호출
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { // 아이템 선택시 활동 내용
                ImageView setImage = findViewById(R.id.set_image);
                setImage.setImageResource(set_img[i]); // 이미지 변경

                TextView setText = findViewById(R.id.set_text);
                setText.setText(set_text[i]); // 택스트 내용 변경
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            } // 아무것도 선택하지 않았을 때 동작하지 않는다.
        });
    }

    @Override
    public void onBackPressed() {
        if( System.currentTimeMillis() > backkeyClickTime + 2000){
            backkeyClickTime = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "뒤로 가기 버튼을 누르면 메인화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        }
        else if(System.currentTimeMillis() <= backkeyClickTime + 2000){
            finish();
        }
    }
}
