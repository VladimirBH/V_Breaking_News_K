package com.example.breakingnews;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    //private DatabaseReference db;
//    private TextView name;
//    private TextView description;
//    private WebView photo;
    private RecyclerView recyclerViewNews;
    private Adapter adapterNews;
    private RecyclerView.LayoutManager layoutManager;
    private List<News> listNews;

    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //db = FirebaseDatabase.getInstance().getReference("/news");

//        name = findViewById(R.id.name);
//        description = findViewById(R.id.description);
//        photo = findViewById(R.id.photo);
        swipeRefreshLayout = findViewById(R.id.upd);
        recyclerViewNews = findViewById(R.id.recyclerNews);
        recyclerViewNews.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewNews.setLayoutManager(layoutManager);
        listNews = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        getNews(db);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            listNews.clear();
            getNews(db);
            recyclerViewNews.setAdapter(adapterNews);
            swipeRefreshLayout.setRefreshing(false);
        });

    }

    void getNews(FirebaseFirestore db){
        db.collection("news")
                .orderBy("name")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            listNews.add(new News(document.getId().toString(),
                                    document.get("description").toString(),
                                    document.get("name").toString(),
                                    document.get("photo").toString(),
                                    dateFormat.format(document.getDate("date").getTime())));
//                            System.out.println(document.getId());
//                            System.out.println(document.get("description").toString());
//                            System.out.println(document.get("name").toString());
//                            System.out.println(dateFormat.format(document.getDate("date").getTime()));
//                            System.out.println(document.get("photo").toString());
                        }
                        System.out.println(listNews.size());

                        adapterNews = new Adapter(MainActivity.this, listNews);
                        recyclerViewNews.setAdapter(adapterNews);
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }
}
