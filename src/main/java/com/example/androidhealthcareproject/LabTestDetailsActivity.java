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
                SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username= sharedPreferences.getString("username","").toString();
                String product= tvPackageName.getText().toString();
                float price= Float.parseFloat(it.getStringExtra("text3").toString());

                Database db= new Database(getApplicationContext(),"Chăm sóc sức khỏe", null, 1);

                if (db.checkCart(username, product)==1){
                    Toast.makeText(getApplicationContext(),"Đơn hàng đã được thêm", Toast.LENGTH_SHORT).show();
                }else {
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(),"Record Inserted to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
                }
            }
        });

    }
}