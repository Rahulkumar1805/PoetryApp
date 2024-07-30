package com.example.backend1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backend1.API.APIInterface;
import com.example.backend1.API.ApiClient;
import com.example.backend1.Adapter.PoetryAdapter;
import com.example.backend1.Models.PoetryModel;
import com.example.backend1.Response.GetPoetryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PoetryAdapter poetryAdapter;
    APIInterface apiInterface;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initialization();
        getData();
    }

    private void initialization() {
        recyclerView = findViewById(R.id.poetryRecyclerView);
        progressBar = findViewById(R.id.progressBar);
        Retrofit retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(APIInterface.class);
    }

    private void setAdapter(List<PoetryModel> poetryModels) {
        poetryAdapter = new PoetryAdapter(this, poetryModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(poetryAdapter);
    }

    private void getData() {
        progressBar.setVisibility(View.VISIBLE);
        apiInterface.getPoetry().enqueue(new Callback<GetPoetryResponse>() {
            @Override
            public void onResponse(Call<GetPoetryResponse> call, Response<GetPoetryResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response != null && response.body() != null) {
                    if (response.body().getStatus().equals("1")) {
                        setAdapter(response.body().getData());
                    } else {
                        Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed to get response", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<GetPoetryResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e("failure", t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "Request failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
