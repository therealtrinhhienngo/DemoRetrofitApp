package com.example.demoretrofitapp.AppInterface;

import com.example.demoretrofitapp.Model.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InsertPersonInterface {
    @FormUrlEncoded
    @POST("journalRoute/add")
    Call<ServerResponse> insertPerson(
            @Field("id") String id,
            @Field("name") String name,
            @Field("role") String role
    );
}
