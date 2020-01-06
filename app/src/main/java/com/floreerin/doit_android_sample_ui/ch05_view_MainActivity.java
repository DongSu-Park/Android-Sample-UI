package com.floreerin.doit_android_sample_ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ch05_view_MainActivity extends AppCompatActivity {

    private long backkeyClickTime = 0;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch05_view_main_activity);

        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(4);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        ch05_Fragment1 fragment1 = new ch05_Fragment1();
        adapter.addItem(fragment1);

        ch05_Fragment2 fragment2 = new ch05_Fragment2();
        adapter.addItem(fragment2);

        ch05_Fragment3 fragment3 = new ch05_Fragment3();
        adapter.addItem(fragment3);

        ch05_Fragment4 fragment4 = new ch05_Fragment4();
        adapter.addItem(fragment4);

        pager.setAdapter(adapter);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter { // 어댑터
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "페이지 " + position;
        }
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
