package com.example.breakingnews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{


    private List<News> newsList;
    Context context;

    Adapter(Context context, List<News> news) {
        this.newsList = news;
        this.context = context;

    }

    public void searchedList(ArrayList<News> searchedList){
        newsList = searchedList;
        notifyDataSetChanged();
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.textViewName.setText(news.getName());
        holder.textViewDate.setText(news.getNewsDate());
        holder.cardTheNews.setOnClickListener(v -> {
            Intent intent = new Intent(context, theNews.class);
            intent.putExtra("name", newsList.get(position).getName());
            intent.putExtra("description", newsList.get(position).getDescription());
            intent.putExtra("photo", newsList.get(position).getPhoto());
            intent.putExtra("date", newsList.get(position).getNewsDate());
            System.out.println(newsList.get(position).getUid());
            this.context.startActivity(intent);
        });
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transform(new CenterCrop(), new RoundedCorners(10));
        Glide.with(this.context).load(newsList.get(position).getPhoto()).apply(requestOptions).into(holder.photoView);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final CardView cardTheNews;
        final TextView textViewName, textViewDate;
        final ImageView photoView;
        ViewHolder(View view){
            super(view);
            textViewName = view.findViewById(R.id.textViewName);
            photoView = view.findViewById(R.id.photo);
            textViewDate = view.findViewById(R.id.textViewDate);
            cardTheNews = view.findViewById(R.id.cardTheNews);
        }
    }
}