package com.example.backend1.API;

import com.example.backend1.Response.DeleteResponse;
import com.example.backend1.Response.GetPoetryResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @GET("getPoetry.php")
    Call<GetPoetryResponse> getPoetry();

    @FormUrlEncoded
    @POST("deletePoetry.php")
    Call<DeleteResponse> deletePoetry(@Field("id") String id);
}
