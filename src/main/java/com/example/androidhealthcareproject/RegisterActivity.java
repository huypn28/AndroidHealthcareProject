package com.example.androidhealthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                String username=edUsername.getText().toString();
                String email=edEmail.getText().toString();
                String password=edPassword.getText().toString();
                String confirm=edConfirm.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if (username.length()==0||password.length()==0||email.length()==0||confirm.length()==0){
                    Toast.makeText(getApplicationContext(),"Nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }else {
                    if (password.compareTo(confirm)==0){
                        if (isValid(password)){
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(),"Mật khẩu đã được ghi", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                        }else {
                            Toast.makeText(getApplicationContext(),"Mật khẩu phải có ít nhất 8 ký tự, phải có 1 chữ cái, 1 chữ số và 1 ký tự đặc biệt, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"Mật khẩu và nhập lại mật khẩu không trùng nhau, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }
        });
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