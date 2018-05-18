package com.example.solar.newsproject;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;



public class NewsAdapter extends ArrayAdapter<NewData> {
    private Context context;
    private ArrayList<NewData> data;


    public NewsAdapter(Context context, ArrayList<NewData> data) {
        super(context, R.layout.item, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View item = LayoutInflater.from(context).inflate(R.layout.item, null);



        //Title
        TextView title = item.findViewById(R.id.title);
        title.setText(data.get(position).getTitle());

        //Content
        TextView artist = item.findViewById(R.id.textContent);
        artist.setText(data.get(position).getContent());

        //Link
        TextView album = item.findViewById(R.id.link);
        album.setText(data.get(position).getLink());

        LinearLayout body = item.findViewById(R.id.item_body);

        body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return item;
    }


}