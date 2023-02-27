package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {
    LinearLayout mainLinear;
    FloatingActionButton floatingActionButton;
    String URL = "https://orbitcompany.000webhostapp.com/retrieve_json.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mainLinear = findViewById(R.id.mainLinear);
        floatingActionButton = findViewById(R.id.floatingActionButton2);

        mainLinear.removeAllViews();

        getRequest();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("users");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject user = jsonArray.getJSONObject(i);
                        String id = user.getString("id");
                        String name = user.getString("name");
                        String contact = user.getString("phone");

                        setTextCards(id, name, contact);
                        //setCards(id, name, contact);
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity2.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void setCards(String id, String name, String contact) {
        TableLayout myLayout = new TableLayout(this);

        TableRow row1 = new TableRow(this);
        TableRow.LayoutParams layouparams1 = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
        layouparams1.span = 4;
        //row1.setLayoutParams = layouparams1 ;
        row1.setLayoutParams(layouparams1);

        TableRow.LayoutParams layouparams2 = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
        layouparams2.span = 2;
        TableRow row2 = new TableRow(this);
        row2.setLayoutParams(layouparams2) ;

        CardView cardView = new CardView(this);
        cardView.setRadius(18f);

        TextView txtId = new TextView(this);
        txtId.setText(id);

        TextView txtName = new TextView(this);
        txtName.setText(name);

        TextView txtContact = new TextView(this);
        txtContact.setText(contact);

        Button btnUpdate = new Button(this);
        btnUpdate.setText("Update");

        Button btnDelete = new Button(this);
        btnDelete.setText("Delete");
//then create and add your childs to each row :...
        row1.addView(txtId);
        row1.addView(txtName);
        row1.addView(txtContact);

        row1.addView(btnDelete);
        row1.addView(btnUpdate);

        myLayout.addView(row1);
        myLayout.addView(row2);

        cardView.addView(myLayout);

        mainLinear.addView(cardView);
    }

    private void setTextCards(String id, String name, String contact) {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30,10,30,10);

        CardView cardView = new CardView(this);
        cardView.setRadius(18f);

        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout2.setPadding(5,5,5,5);

        LinearLayout linearLayout3 = new LinearLayout(this);
        linearLayout3.setOrientation(LinearLayout.VERTICAL);
        linearLayout3.setPadding(5,5,5,5);

        Button btnDelete = new Button(this);
        btnDelete.setText("Delete");
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //deleteRequest(id);
            }
        });

        Button btnUpdate = new Button(this);
        btnUpdate.setText("Update");

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayout.setPadding(15,15,15,15);

        TextView txtId = new TextView(this);
        txtId.setText(id);

        TextView txtName = new TextView(this);
        txtName.setText(name);

        TextView txtContact = new TextView(this);
        txtContact.setText(contact);

        linearLayout.addView(txtId);
        linearLayout.addView(txtName);
        linearLayout.addView(txtContact);

        linearLayout3.addView(btnDelete);
        linearLayout3.addView(btnUpdate);

        linearLayout2.addView(linearLayout);    //vertical 1 add to sub-main horizontal [text boxes]
        linearLayout2.addView(linearLayout3);   //vertical 2 add to sub-main horizontal [buttons]
        linearLayout2.setGravity(Gravity.CENTER);

        cardView.addView(linearLayout2,layoutParams);        //horizontal 1 add to card

        mainLinear.addView(cardView,layoutParams);
    }
}