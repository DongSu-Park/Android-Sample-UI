package com.floreerin.doit_android_sample_ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ch13_RecyclerAdapter extends RecyclerView.Adapter<ch13_RecyclerAdapter.ItemViewHolder> {

    // adapter에 들어갈 list
    private ArrayList<ch13_Data> listData = new ArrayList<>();
    Context context;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ch13_itemview를 inflater
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ch13_itemview, parent, false);
        this.context = parent.getContext();
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ch13_RecyclerAdapter.ItemViewHolder holder, int position) {
        // item을 하나씩 보여주는 함수
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() { // recyclerview의 총 개수
        return listData.size();
    }

    void addItem(ch13_Data data){ // 외부에서 item을 추가할 함수
        listData.add(data);
    }


    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // 각 리스트의 문자열 및 이미지를 받은 ViewHolder
        private TextView textView1;
        private TextView textView2;
        private ImageView imageView;
        private ch13_Data data;

        ItemViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.item_image);

        }

        void onBind(ch13_Data data) {
            this.data = data;

            textView1.setText(data.getTitle());
            textView2.setText(data.getContent());
            imageView.setImageResource(data.getResId());

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ch13_Fragment4 test = new ch13_Fragment4();
            if(view.getId() == R.id.linearItem){
                Intent youtubeIntent = new Intent(context, ch13_Youtube_Activity.class);
                youtubeIntent.putExtra("youtube_id", data.getYoutube_url());
                youtubeIntent.putExtra("web_url",data.getWeb_url());

                context.startActivity(youtubeIntent);
            }
        }
    }
}