package com.example.androidhealthcareproject;

import static com.example.androidhealthcareproject.R.id.buttonLabTestBookBack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.regex.Pattern;

public class LabTestBookActivity extends AppCompatActivity {


    EditText edName, edAddress, edContact, edAge;
    Button btnBooking,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edName=findViewById(R.id.editTextLabTestBookFullName);
        edAddress=findViewById(R.id.editTextLabTestBookAddress);
        edContact=findViewById(R.id.editTextLabTestBookNumber);
        edAge=findViewById(R.id.editTextLabTestBookAge);
        btnBooking=findViewById(R.id.buttonLabTestBookBook);
        btnBack=findViewById(buttonLabTestBookBack);


        Intent it=getIntent();
        String[] price= it.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=it.getStringExtra("date");
        String time=it.getStringExtra("time");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db= new Database(getApplicationContext(),"Chăm sóc sức khỏe", null,1);
                SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username= sharedPreferences.getString("username","").toString();

                Log.d("TAG", "Username: " + username);
                String fullname = edName.getText().toString();
                String address = edAddress.getText().toString();
                String contact = edContact.getText().toString();
                int age = Integer.parseInt(edAge.getText().toString());

                // Create a JSON object with the required data
                RequestQueue queue = Volley.newRequestQueue(LabTestBookActivity.this);
                String url = "http://192.168.101.57/api/v1/user/orderplace/register";
                String urldel = "http://192.168.101.57:9080/api/v1/user/cart/delete"+"?username="+username+"&otype="+"xét nghiệm";



                // Make a POST request to the server
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Handle the response
                                startActivity(new Intent(LabTestBookActivity.this, LabTestActivity.class));
                                StringRequest stringRequest1 = new StringRequest(Request.Method.DELETE, urldel,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                // Handle the response
                                                startActivity(new Intent(LabTestBookActivity.this, LabTestActivity.class));
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                // Handle the error
                                                Toast.makeText(LabTestBookActivity.this, "Không đặt được hàng", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                // Add the request to the RequestQueue

                                queue.add(stringRequest1);

                            }
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
                        params.put("fullname", fullname);
                        params.put("address", address);
                        params.put("contact", contact);
                        params.put("age", String.valueOf(age));
                        params.put("date", date);
                        params.put("time", ""); // Set the time value accordingly
                        params.put("amount", String.valueOf(Float.parseFloat(price[1]))); // Assuming price[1] contains the amount
                        params.put("otype", "xét nghiệm");
                        return params;
                    }
                };

// Add the request to the RequestQueue
                queue.add(stringRequest);

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestBookActivity.this,CartLabActivity.class));


            }
        });



    }
}