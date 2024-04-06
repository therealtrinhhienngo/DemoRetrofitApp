package com.example.demoretrofitapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoretrofitapp.Adapter.ListViewAdapter;
import com.example.demoretrofitapp.AppInterface.InsertPersonInterface;
import com.example.demoretrofitapp.Model.Person;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListPersonActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button addFormButton;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.63:3000/")
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
            .build();

    ArrayList<Person> personArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_person);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.listPersonView);
        addFormButton = findViewById(R.id.addFormButton);
        personArrayList = new ArrayList<>();

        personArrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(ListPersonActivity.this));

        ListViewAdapter listViewAdapter = new ListViewAdapter(ListPersonActivity.this, personArrayList);
        recyclerView.setAdapter(listViewAdapter);


        // Render data
        getPersonFunction(recyclerView, retrofit);

        addFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switch to add form view
                Intent switchActivity = new Intent(ListPersonActivity.this, MainActivity.class);
                switchActivity.putExtra("valueObj", new Person());
                switchActivity.putExtra("mode", "add");
                startActivity(switchActivity);
            }
        });
    }

    public void getPersonFunction(RecyclerView recyclerView, Retrofit retrofit){
        // Call insert function in AppInterface
        InsertPersonInterface insertPersonInterface = retrofit.create(InsertPersonInterface.class);

        Call<ArrayList<Person>> call = insertPersonInterface.getData();
        Toast.makeText(this, "On Load!!!", Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<ArrayList<Person>>() {
            @Override
            public void onResponse(Call<ArrayList<Person>> call, Response<ArrayList<Person>> response) {
                personArrayList = response.body();

                ListViewAdapter listViewAdapter = new ListViewAdapter(ListPersonActivity.this, personArrayList);

                recyclerView.setLayoutManager(new LinearLayoutManager(ListPersonActivity.this));
                recyclerView.setAdapter(listViewAdapter);
                Toast.makeText(ListPersonActivity.this, "Load Complete!!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Person>> call, @NonNull Throwable t) {
                Toast.makeText(ListPersonActivity.this, "Load Error!!!", Toast.LENGTH_SHORT).show();
                Log.e("Load Err", "Load Data Err: " + t);
            }
        });
    }
}