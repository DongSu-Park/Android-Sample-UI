package com.floreerin.doit_android_sample_ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class ch13_Fragment4 extends Fragment {

    private ch13_RecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) getLayoutInflater().inflate(R.layout.ch13_fragment4, container,false);

        init(rootView);

        return rootView;
    }

    private void init(ViewGroup rootView) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new ch13_RecyclerAdapter();

        getdata();

        recyclerView.setAdapter(adapter);
    }

    private void getdata() {
        List<String> listTitle = Arrays.asList("IMac Pro", "Macbook Pro", "Ipad Pro", "IPhone 11 Pro", "Apple Watch", "Airpods Pro");
        List<String> listContent = Arrays.asList(
                getResources().getString(R.string.content_imac_pro),
                getResources().getString(R.string.content_macbook_pro),
                getResources().getString(R.string.content_ipad_pro),
                getResources().getString(R.string.content_iPhone_11_pro),
                getResources().getString(R.string.content_apple_watch),
                getResources().getString(R.string.content_airpods_pro)
        );
        List<Integer> listResId = Arrays.asList(
                R.drawable.imac_pro,
                R.drawable.macbook_pro,
                R.drawable.ipad_pro,
                R.drawable.iphone_11_pro,
                R.drawable.apple_watch,
                R.drawable.airpods_pro
        );
        List<String> listYoutube_Url = Arrays.asList( // youtube url
                getResources().getString(R.string.youtube_imac_pro),
                getResources().getString(R.string.youtube_macbook_pro),
                getResources().getString(R.string.youtube_ipad_pro),
                getResources().getString(R.string.youtube_iPhone_11_pro),
                getResources().getString(R.string.youtube_apple_watch),
                getResources().getString(R.string.youtube_airpods_pro)
        );

        List<String> listWeb_Url = Arrays.asList( // web url
                getResources().getString(R.string.web_imac_pro),
                getResources().getString(R.string.web_macbook_pro),
                getResources().getString(R.string.web_ipad_pro),
                getResources().getString(R.string.web_iPhone_11_pro),
                getResources().getString(R.string.web_apple_watch),
                getResources().getString(R.string.web_airpods_pro)
        );

        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 설정
            ch13_Data data = new ch13_Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setResId(listResId.get(i));
            data.setYoutube_url(listYoutube_Url.get(i));
            data.setWeb_url(listWeb_Url.get(i));

            // 각 값이 들어간 data를 adapter에 추가
            adapter.addItem(data);

            // adapter의 값이 변경 알림 함수
            adapter.notifyDataSetChanged();
        }
    }
}
