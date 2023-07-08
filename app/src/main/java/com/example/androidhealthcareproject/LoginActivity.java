package com.example.androidhealthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    EditText edUsername, edPassword;
    Button btnLogin;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername=findViewById(R.id.editTextLoginUsername);
        edPassword=findViewById(R.id.editTextLoginPassword);
        btnLogin=findViewById(R.id.buttonLogin);
        textView=findViewById(R.id.textViewNewUser);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();


                //startActivity(new Intent(LoginActivity.this, HomeActivity.class));

            }
        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });



    }
    public  void authenticateUser(){
        if (!validUsername()||!validPassword()){
            return;
        }
        //Instantiate the request queue
        RequestQueue queue= Volley.newRequestQueue(LoginActivity.this);

        //The url posting to:
        String url ="http://192.168.101.57:9080/api/v1/user/login";

        HashMap<String, String > params= new HashMap<String, String>();
        params.put("username", edUsername.getText().toString());
        params.put("password", edPassword.getText().toString());

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //get values from response
               /* try {
                    String username=(String) response.get("username");
                }catch (JSONException e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }*/
                try {
                    String username = response.getString("username"); // Extract the username from the response JSON
                    SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username); // Store the username in the SharedPreferences
                    editor.apply();

                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this,"Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);

    }
    public boolean validUsername(){
        String username=edUsername.getText().toString();
        if (username.isEmpty()){
            edUsername.setError("Tên đăng nhập không thể để trống");
            return false;
        }else {
            edUsername.setError(null);
            return true;
        }
    }
    public boolean validPassword(){
        String password=edPassword.getText().toString();
        if (password.isEmpty()){
            edPassword.setError("Mật khẩu không thể để trống");
            return false;
        }else {
            edPassword.setError(null);
            return true;
        }
    }
}