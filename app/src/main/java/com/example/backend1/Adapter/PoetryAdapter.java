package com.example.backend1.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backend1.API.APIInterface;
import com.example.backend1.API.ApiClient;
import com.example.backend1.MainActivity;
import com.example.backend1.Models.PoetryModel;
import com.example.backend1.R;
import com.example.backend1.Response.DeleteResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PoetryAdapter extends RecyclerView.Adapter<PoetryAdapter.ViewHolder> {

    Context context;
    List<PoetryModel> poetryModels;
    APIInterface apiInterface;

    public PoetryAdapter(Context context, List<PoetryModel> poetryModels) {
        this.context = context;
        this.poetryModels = poetryModels;
        Retrofit retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(APIInterface.class);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.poetry_list_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.poetName.setText(poetryModels.get(position).getPoet());
        holder.poetry.setText(poetryModels.get(position).getPoetry_data());
        holder.date_time.setText(poetryModels.get(position).getDate_time());

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePoetry(poetryModels.get(position).getId()+"",position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return poetryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView poetName, poetry, date_time;
        Button deleteBtn, updateBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            poetName = itemView.findViewById(R.id.poet_name);
            poetry = itemView.findViewById(R.id.poetry_data);
            date_time = itemView.findViewById(R.id.poetry_date);
            deleteBtn = itemView.findViewById(R.id.delete);
            updateBtn = itemView.findViewById(R.id.update);
        }
    }

    private void deletePoetry(String id, int position){
        apiInterface.deletePoetry(id).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                try{
                    if(response!=null)
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    if(response.body().getStatus().equals("1")){
                        poetryModels.remove(position);
                        notifyDataSetChanged();
                    }
                }
                catch (Exception e){
                    Log.e("Exception", e.getLocalizedMessage());
                    Toast.makeText(context, "Failed to get response", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                Toast.makeText(context, "Request failed: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}
