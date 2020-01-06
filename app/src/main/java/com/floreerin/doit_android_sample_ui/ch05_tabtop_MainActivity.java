package com.floreerin.doit_android_sample_ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;


public class ch05_tabtop_MainActivity extends AppCompatActivity {

    private long backkeyClickTime = 0;

    ch05_Fragment1 fragment1;
    ch05_Fragment2 fragment2;
    ch05_Fragment3 fragment3;
    ch05_Fragment4 fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch05_tabtop_main_activity);

        // 프레그먼트 화면 객체 생성
         fragment1 = new ch05_Fragment1();
         fragment2 = new ch05_Fragment2();
         fragment3 = new ch05_Fragment3();
         fragment4 = new ch05_Fragment4();

         // 처음에 나오는 프래그먼트 화면 설정
        getSupportFragmentManager().beginTransaction().replace(R.id.tabcontainer, fragment1).commit();

        // Tablayout 설정
        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // 탭 선택 리스너 함수 호출
            @Override
            public void onTabSelected(TabLayout.Tab tab) { // 각 탭을 선택시
                int position = tab.getPosition(); // 포지션 값 초기화

                Fragment selected = null;
                // 각 포지션에 맞는 탭을 선택하면 해당 프래그먼트로 이동한다.

                if (position == 0) {
                    selected = fragment1;
                } else if (position == 1) {
                    selected = fragment2;
                } else if (position == 2) {
                    selected = fragment3;
                } else if (position == 3) {
                    selected = fragment4;
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.tabcontainer, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // 뒤로가기 버튼 클릭시
    @Override
    public void onBackPressed() {
        if( System.currentTimeMillis() > backkeyClickTime + 2000){
            backkeyClickTime = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "뒤로 가기 버튼을 누르면 메인화면으로 이동합니다.",Toast.LENGTH_LONG).show();
        }
        else if(System.currentTimeMillis() <= backkeyClickTime + 2000){ // 2초안에 뒤로가기 버튼을 두번 눌렀으면
            finish();
        }
    }
}
