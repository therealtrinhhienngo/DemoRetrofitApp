package com.example.demoretrofitapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demoretrofitapp.ModalFunction.PersonRetrofitFunction;
import com.example.demoretrofitapp.Model.Person;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText nameInput, roleInput;
    Button addInputButton;
    PersonRetrofitFunction personRetrofitFunction;
    TextView resultDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        Person receivedObject = (Person) intent.getSerializableExtra("valueObj");
        String mode = intent.getStringExtra("mode");

        Log.d("Person Obj Get", "Mode Get: " + mode);

        nameInput = findViewById(R.id.inputName);
        roleInput = findViewById(R.id.inputRole);
        addInputButton = findViewById(R.id.inputButton);
        resultDisplay = findViewById(R.id.resultDisplay);

        personRetrofitFunction = new PersonRetrofitFunction();

        nameInput.setText(receivedObject.getName());
        roleInput.setText(receivedObject.getRole());

        if (Objects.equals(mode, "update")){
            addInputButton.setText("Update");
        }
        else {
            addInputButton.setText("Add");
        }

        addInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                String role = roleInput.getText().toString();

                if(Objects.equals(mode, "update")){
                    personRetrofitFunction.updatePersonFunction(receivedObject.getId(), name, role, MainActivity.this, resultDisplay);
                    startActivity(new Intent(MainActivity.this, ListPersonActivity.class));
                }
                else{
                    personRetrofitFunction.insertPersonFunction(name, role, MainActivity.this, resultDisplay);
                    startActivity(new Intent(MainActivity.this, ListPersonActivity.class));
                }
            }
        });
    }
}