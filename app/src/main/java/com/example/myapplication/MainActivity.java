package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextInputEditText txtName, txtPhone;
    Button btnCreate;
    FloatingActionButton btnRetrieveView;

    String NAME, PHONE, URL = "http://orbitcompany.000webhostapp.com/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineComponents();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtName.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter required fields", Toast.LENGTH_SHORT).show();
                } else if (txtPhone.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter required fields", Toast.LENGTH_SHORT).show();
                } else if (txtName.getText().toString().isEmpty() && txtName.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter required fields", Toast.LENGTH_SHORT).show();
                } else {
                    getTextBoxData();
                    insertRequest();
                    Toast.makeText(MainActivity.this, "Inserting", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRetrieveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Retrieve View", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                        if (response.equals("Insert Success")) {
                            clearTextBoxes();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap<>();
                map.put("name", NAME);
                map.put("phone", PHONE);
                return map;
            }
        };
        requestQueue.add(request);
    }

    private void clearTextBoxes() {
        txtName.setText("");
        txtPhone.setText("");
    }

    private void getTextBoxData() {
        NAME = txtName.getText().toString();
        PHONE = txtPhone.getText().toString();
    }

    private void defineComponents() {
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        btnCreate = findViewById(R.id.btnCreate);
        btnRetrieveView = findViewById(R.id.floatingActionButton1);
    }
}