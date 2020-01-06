package com.floreerin.doit_android_sample_ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;


public class ch05_Navigation_MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback {
    private long backkeyClickTime = 0;

    Toolbar toolbar;
    ch05_Fragment1 ch05_fragment1;
    ch05_Fragment2 ch05_fragment2;
    ch05_Fragment3 ch05_fragment3;
    ch05_Fragment4 ch05_fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch05_navigation_main);

        // 상단 바 설정
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 전체 화면 설정
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // 바로가기 메뉴 화면 설정
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // 프레그먼트 설정
        ch05_fragment1 = new ch05_Fragment1();
        ch05_fragment2 = new ch05_Fragment2();
        ch05_fragment3 = new ch05_Fragment3();
        ch05_fragment4 = new ch05_Fragment4();

        getSupportFragmentManager().beginTransaction().add(R.id.showview, ch05_fragment1).commit(); // 처음에 나오는 화면은 fragment1으로 설정
    }

    // 바로가기 메뉴가 나타난 상태에서 back 버튼 클릭 시
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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

    // 옵션 메뉴 나타내기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // 네비게이션 메뉴 클릭 이벤트
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.nav_1){
            onFragmentSelected(1, null);
        } else if (id == R.id.nav_2){
            onFragmentSelected(2, null);
        } else if (id == R.id.nav_3){
            onFragmentSelected(3, null);
        } else if (id == R.id.nav_4){
            onFragmentSelected(4, null);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // 프레그먼트 선택 이벤트
    @Override
    public void onFragmentSelected(int position, Bundle bundle) {
        Fragment curFragment = null;

        if(position == 1){
            curFragment = ch05_fragment1;
            toolbar.setTitle("첫 번째 화면");
        } else if (position == 2){
            curFragment = ch05_fragment2;
            toolbar.setTitle("두 번째 화면");
        } else if (position == 3){
            curFragment = ch05_fragment3;
            toolbar.setTitle("세 번째 화면");
        } else if (position == 4){
            curFragment = ch05_fragment4;
            toolbar.setTitle("네 번째 화면");
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.showview, curFragment).commit();
    }

}
