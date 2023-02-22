package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText txtName,txtPhone;
    Button btnCreate;
    FloatingActionButton btnRetrieveView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        defineComponents();
        
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Created", Toast.LENGTH_SHORT).show();
            }
        });
        
        btnRetrieveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Retrieve View", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void defineComponents() {
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        btnCreate = findViewById(R.id.btnCreate);
        btnRetrieveView = findViewById(R.id.floatingActionButton1);
    }
}