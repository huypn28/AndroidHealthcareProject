package com.example.androidhealthcareproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername, edEmail, edPassword, edConfirm;
    Button btnRegister;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername=findViewById(R.id.editTextBookFullName);
        edPassword=findViewById(R.id.editTextBookContentNumber);
        edEmail=findViewById(R.id.editTextBookAddress);
        edConfirm=findViewById(R.id.editTextBookFees);
        btnRegister=findViewById(R.id.buttonBook);
        textView=findViewById(R.id.textViewExistingUser);





        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processFormFields();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }
        });

    }
    public  void processFormFields(){
        if (!validUsername()||!validEmail()||!validPasswordandConfirm()){
            return;
        }
         //Instantiate the request queue
        RequestQueue queue= Volley.newRequestQueue(RegisterActivity.this);

        //The url posting to:
        String url ="http://192.168.101.57:9080/api/v1/user/register";
        //String Request object
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equalsIgnoreCase("Đăng kí thành công")){
                    edUsername.setText(null);
                    edEmail.setText(null);
                    edPassword.setText(null);
                    edConfirm.setText(null);
                Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, " Đăng kí không thành công", Toast.LENGTH_SHORT).show();

            }
        }){@Nullable
            @Override
            protected Map<String, String> getParams()throws AuthFailureError{
            Map<String, String> params= new HashMap<>();
            params.put("username", edUsername.getText().toString());
            params.put("email", edEmail.getText().toString());
            params.put("password", edPassword.getText().toString());
            return params;
        }
        };
        queue.add(stringRequest);
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

    public boolean validEmail(){
        String email=edEmail.getText().toString();
        if (email.isEmpty()){
            edEmail.setError("Tên email không thể để trống");
            return false;
        }else {
            if(checkIfGmail(email)){
            edEmail.setError(null);
            return true;
            }else {
                edEmail.setError("Không phải là email");
                return false;
            }
        }
    }



    public boolean validPasswordandConfirm() {
        String confirm = edConfirm.getText().toString();
        String password = edPassword.getText().toString();

        if (password.isEmpty() || confirm.isEmpty()) {
            if (password.isEmpty()) {
                edPassword.setError("Mật khẩu không thể để trống");
            }
            else if (confirm.isEmpty()) {
                edConfirm.setError("Nhập lại mật khẩu không thể để trống");
            }
            if (!isValid(password)) {
                edPassword.setError("Mật khẩu phải có ít nhất 8 ký tự, phải có 1 chữ cái, 1 chữ số và 1 ký tự đặc biệt");
                return false;
            }

            return false;
        } else if (!password.equals(confirm)) {
                edConfirm.setError("Mật khẩu và nhập lại mật khẩu không trùng nhau");
                return false;
        } else {
            if (isValid(password)){
            edPassword.setError(null);
                return true;
            }
            edPassword.setError("Mật khẩu phải có ít nhất 8 ký tự, phải có 1 chữ cái, 1 chữ số và 1 ký tự đặc biệt");
            return false;
        }
    }



    private static boolean checkIfGmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        return Pattern.matches(regex, email);
    }
    public static boolean isValid(String passwordhere){
        int f1=0,f2=0,f3=0;
        if (passwordhere.length()<0){
            return false;
        }else {
            for (int p= 0; p< passwordhere.length();p++){
                if (Character.isLetter(passwordhere.charAt(p))){
                    f1=1;
                }
            }
            for (int r= 0; r< passwordhere.length();r++){
                if (Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }
            for (int s= 0; s< passwordhere.length();s++){
                char c=passwordhere.charAt(s);
                if (c>=33&&c<=46||c==64){
                    f3=1;
                }
            }
            if (f1==1&&f2==1&&f3==1)
                return true;
            return false;
        }
    }
}