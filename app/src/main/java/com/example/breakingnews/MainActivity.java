package com.example.breakingnews;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

/*import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;*/
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                db.collection("news")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        listNews.add(new News(document.getId().toString(),
                                                document.get("description").toString(),
                                                document.get("name").toString(),
                                                document.get("photo").toString()));
                                        System.out.println(document.getId());
                                        System.out.println(document.get("description").toString());
                                        System.out.println(document.get("name").toString());
                                        System.out.println(document.get("photo").toString());
                                        System.out.println(listNews.size()+" ");
                                    }
                                    adapterNews = new Adapter(MainActivity.this, listNews);
                                    recyclerViewNews.setAdapter(adapterNews);
                                } else {
                                    Log.w(TAG, "Error getting documents.", task.getException());
                                }
                            }
                        });
            }
        });

        // Add a new document with a generated ID




        db.collection("news")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                listNews.add(new News(document.getId().toString(),
                                        document.get("description").toString(),
                                        document.get("name").toString(),
                                        document.get("photo").toString()));
                                System.out.println(document.getId());
                                System.out.println(document.get("description").toString());
                                System.out.println(document.get("name").toString());
                                System.out.println(document.get("photo").toString());
                            }
                            adapterNews = new Adapter(MainActivity.this, listNews);
                            recyclerViewNews.setAdapter(adapterNews);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}