package com.example.androidhealthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HealthArticlesDetailsActivity extends AppCompatActivity {

    TextView textView;
    ImageView img;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles_details);

        btnBack = findViewById(R.id.buttonHADetailsBack);
        textView = findViewById(R.id.textViewHADetails);
        img = findViewById(R.id.imageViewHADetails);

        Intent intent=getIntent();
        textView.setText(intent.getStringExtra("text1"));

        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            int resId= bundle.getInt("text2");
            img.setImageResource(resId);
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticlesDetailsActivity.this, HealthArticlesActivity.class));
            }
        });

    }
}