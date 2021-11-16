package com.example.breakingnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<News> newsList;

    Adapter(Context context, List<News> news) {
        this.newsList = news;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.textViewName.setText(news.getName());
        holder.webViewPhoto.loadUrl(news.getPhoto());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textViewName;
        final WebView webViewPhoto;
        ViewHolder(View view){
            super(view);
            textViewName = view.findViewById(R.id.textViewName);
            webViewPhoto = view.findViewById(R.id.webViewPhoto);
        }
    }
}