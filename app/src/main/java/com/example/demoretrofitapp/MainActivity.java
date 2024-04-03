package com.example.demoretrofitapp;

import android.os.Bundle;
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

        nameInput = findViewById(R.id.inputName);
        roleInput = findViewById(R.id.inputRole);
        addInputButton = findViewById(R.id.inputButton);
        resultDisplay = findViewById(R.id.resultDisplay);

        personRetrofitFunction = new PersonRetrofitFunction();

        addInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                String role = roleInput.getText().toString();

                personRetrofitFunction.insertPersonFunction(name, role, MainActivity.this, resultDisplay);
            }
        });
    }
}