package com.example.breakingnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class theNews extends AppCompatActivity {

    private TextView textViewTitle, textViewDescription, textViewDate;
    private ImageView imageViewPhoto;
    private String idNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_news);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewDate = findViewById(R.id.textViewDate);
        imageViewPhoto = findViewById(R.id.imageViewPhoto);

        if(getIntent().hasExtra("name") &&
                getIntent().hasExtra("description") &&
                getIntent().hasExtra("photo") &&
                getIntent().hasExtra("date")
        )
        {
            textViewTitle.setText(getIntent().getStringExtra("name"));
            textViewDescription.setText(getIntent().getStringExtra("description"));
            textViewDate.setText(getIntent().getStringExtra("date"));
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transform(new FitCenter(), new RoundedCorners(10));
            Glide.with(this).load(getIntent().getStringExtra("photo")).apply(requestOptions).into(imageViewPhoto);

        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}