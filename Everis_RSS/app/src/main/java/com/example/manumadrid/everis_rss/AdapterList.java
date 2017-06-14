package com.example.manumadrid.everis_rss;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by ManuMadrid on 14/06/2017.
 */

/**
 * Adaptador del RecyclerView que se encarga de formatear la lista
 */

public class AdapterList extends RecyclerView.Adapter<AdapterList.CustomViewHolder> {


    private ArrayList<String> titles;
    private ArrayList<String> descriptions;
    private ArrayList<String> images;
    private ArrayList<String> urls;
    private Context context;

    public AdapterList(News news, Context context) {


        this.titles = news.getTitles();
        this.descriptions = news.getDescriptions();
        this.images = news.getImages();
        this.urls = news.getUrls();
        this.context = context;

    }

    @Override
    public AdapterList.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_element, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterList.CustomViewHolder holder, int position) {
        String title = titles.get(position);
        String description = descriptions.get(position);
        String image = images.get(position);


        //Render image using Picasso library
        if (image != null) {
            Picasso.with(context).load(image)
                    .error(R.drawable.error)
                    .placeholder(R.drawable.error)
                    .into(holder.image);
        }

        holder.title.setText(title);
        holder.title.setTypeface(null, Typeface.BOLD);
        holder.description.setText(description);
        holder.setUrl(urls.get(position));
        holder.setImageurl(images.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }



    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image;
        protected TextView title;
        protected TextView description;
        protected String url;
        protected String imageurl;

        public CustomViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.imageNew);
            this.title = (TextView) view.findViewById(R.id.title);
            this.description = (TextView) view.findViewById(R.id.description);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(context,DetailActivity.class);
                    i.putExtra("title", title.getText());
                    i.putExtra("description", description.getText().toString());
                    i.putExtra("url", url);
                    i.putExtra("image", imageurl);
                    context.startActivity(i);
                }
            });
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

    }




}
