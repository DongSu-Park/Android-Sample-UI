package com.floreerin.doit_android_sample_ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class ch06_MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback {

    private long backkeyClickTime = 0;

    Toolbar ch06_toolbar;
    ch06_Fragment1 ch06_fragment1;
    ch06_Fragment2 ch06_fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch06_activity_main);

        // 상단 바 설정
        ch06_toolbar = findViewById(R.id.ch06_toolbar);
        setSupportActionBar(ch06_toolbar);

        // 전체 화면 설정
        DrawerLayout drawer = findViewById(R.id.ch06_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                ch06_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // 바로가기 메뉴 화면 설정
        NavigationView navigationView = findViewById(R.id.ch06_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // 프레그먼트 설정
        ch06_fragment1 = new ch06_Fragment1();
        ch06_fragment2 = new ch06_Fragment2();

        getSupportFragmentManager().beginTransaction().add(R.id.ch06_showview, ch06_fragment1).commit(); // 처음에 나오는 화면은 fragment1으로 설정



    }

    // 바로가기 메뉴가 나타난 상태에서 back 버튼 클릭 시
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.ch06_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            if( System.currentTimeMillis() > backkeyClickTime + 2000){
                backkeyClickTime = System.currentTimeMillis();
                Toast.makeText(getApplicationContext(), "뒤로 가기 버튼을 누르면 메인화면으로 이동합니다.",Toast.LENGTH_LONG).show();
            }
            else if(System.currentTimeMillis() <= backkeyClickTime + 2000){
                finish();
            }
        }
    }

    // 네비게이션 메뉴 클릭 이벤트
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.ch06_nav_1){
            onFragmentSelected(1, null);
        } else if (id == R.id.ch06_nav_2){
            onFragmentSelected(2, null);
        }

        DrawerLayout drawer = findViewById(R.id.ch06_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentSelected(int position, Bundle bundle) {
        Fragment curFragment = null;

        if(position == 1){
            curFragment = ch06_fragment1;
            ch06_toolbar.setTitle("서비스(Service)");
        } else if (position == 2){
            curFragment = ch06_fragment2;
            ch06_toolbar.setTitle("브로드케스트, 위험권한");
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.ch06_showview, curFragment).commit();
    }
}
