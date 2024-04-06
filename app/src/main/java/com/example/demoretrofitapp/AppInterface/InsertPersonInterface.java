package com.example.demoretrofitapp.AppInterface;

import com.example.demoretrofitapp.Model.Person;
import com.example.demoretrofitapp.Model.ServerResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InsertPersonInterface {
    @FormUrlEncoded
    @POST("journalRoute/add")
    Call<ServerResponse> insertPerson(
            @Field("id") String id,
            @Field("name") String name,
            @Field("role") String role
    );

    @FormUrlEncoded
    @POST("journalRoute/delete")
    Call<ServerResponse> deletePerson(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("journalRoute/update")
    Call<ServerResponse> updatePerson(
            @Field("id") String id,
            @Field("name") String name,
            @Field("role") String role
    );


    @GET("journalRoute/get")
    Call<ArrayList<Person>> getData();
}
