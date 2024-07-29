package com.example.backend1;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backend1.Adapter.PoetryAdapter;
import com.example.backend1.Models.PoetryModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PoetryAdapter poetryAdapter;
    List<PoetryModel> poetryModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        poetryModelList.add(new PoetryModel(1, "Shayri","Mirza","30-July-2024"));
        poetryModelList.add(new PoetryModel(1, "Tujhe kitna pyaar karte hain,\\n Hum ye keh nahin sakte,\\n Kyunki ye dil ke armaan hain,\\n Lafzon mein nahin aate",
                "Mirza","30-July-2024"));
        poetryModelList.add(new PoetryModel(1, "shayri2 Bfadfhhahagagh vvvvbv",
                "Mirza","30-July-2024"));
        initialization();
        setAdapter(poetryModelList);

    }
    private void initialization(){
        recyclerView = findViewById(R.id.poetryRecyclerView);
    }
    private void setAdapter(List<PoetryModel> poetryModels){
        poetryAdapter = new PoetryAdapter(this , poetryModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(poetryAdapter);
    }
}