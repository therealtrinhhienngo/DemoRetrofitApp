package com.example.demoretrofitapp.ModalFunction;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.demoretrofitapp.AppInterface.InsertPersonInterface;
import com.example.demoretrofitapp.Model.Person;
import com.example.demoretrofitapp.Model.ServerResponse;
import com.google.gson.GsonBuilder;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonRetrofitFunction {
    // Make new retrofit obj
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.63:3000/")
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
            .build();

    public void insertPersonFunction(String name, String role, Context context, TextView resultDisplay){
        // Make new obj
        Person newObj = new Person();

        // Put insert data into obj
        newObj.setId(UUID.randomUUID().toString());
        newObj.setName(name);
        newObj.setRole(role);

        // Call insert function in AppInterface
        InsertPersonInterface insertPersonInterface = retrofit.create(InsertPersonInterface.class);

        Call<ServerResponse> call = insertPersonInterface.insertPerson(newObj.getId(), newObj.getName(), newObj.getRole());
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                if(response.isSuccessful()){
//                    Toast.makeText(context, "Add Success!!!", Toast.LENGTH_SHORT).show();
                    resultDisplay.setText("Add Failed!!!");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {
//                Toast.makeText(context, "Add Failed!!!", Toast.LENGTH_SHORT).show();
                resultDisplay.setText("Add Success!!!");
                Log.d("Add Error", t.toString());
            }
        });
    }
}
