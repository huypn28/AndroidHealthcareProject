package com.example.androidhealthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LabTestDetailsActivity extends AppCompatActivity {
    TextView tvPackageName, tvTotalCost;
    EditText edDetails;
    Button btnAddtoCart, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        tvPackageName=findViewById(R.id.TVLDPackageName);
        tvTotalCost=findViewById(R.id.textViewLDTotalCost);
        edDetails=findViewById(R.id.edLDTextMultiline);
        btnAddtoCart=findViewById(R.id.buttonLDAddCart);
        btnBack=findViewById(R.id.buttonLDBack);

        edDetails.setKeyListener(null);

        Intent it= getIntent();
        tvPackageName.setText(it.getStringExtra("text1"));
        edDetails.setText(it.getStringExtra("text2"));
        tvTotalCost.setText("Tổng tiền: "+it.getStringExtra("text3")+" VNĐ");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));

            }
        });

        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");
                Log.d("TAG", "Username: " + username);
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(it.getStringExtra("text3").toString());
                Log.d("TAG", "Product: " + product);
                Log.d("TAG", "Price: " + price);
                RequestQueue queue = Volley.newRequestQueue(LabTestDetailsActivity.this);


                // Construct the API URL with query parameters
                String url = "http://192.168.101.57:9080/api/v1/user/cart/register";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Handle the response


                                    startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));}

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle the error
                            }
                        }) {
                    // Override getParams() if you need to include parameters in the request body
                    // Here, we are including the parameters in the request body
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("username", username);
                        params.put("product", product);
                        params.put("price", String.valueOf(price));
                        params.put("otype", "xét nghiệm");
                        return params;
                    }
                };

// Add the request to the RequestQueue
                queue.add(stringRequest);

            }
        });

    }
}