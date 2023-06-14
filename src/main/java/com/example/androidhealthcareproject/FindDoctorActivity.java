package com.example.androidhealthcareproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        CardView exit= findViewById(R.id.cardFDBack);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDoctorActivity.this, HomeActivity.class));
            }
        });

        CardView familyphysician= findViewById(R.id.cardFDFamilyPhysician);
        familyphysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Chuyên khoa y học gia đình");
                startActivity(it);
            }
        });
        CardView dietician= findViewById(R.id.cardFDDietician);
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Chuyên gia dinh dưỡng");
                startActivity(it);
            }
        });
        CardView dentist= findViewById(R.id.cardFDDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Nha khoa");
                startActivity(it);
            }
        });
        CardView surgeon= findViewById(R.id.cardFDSurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Phẫu thuật");
                startActivity(it);
            }
        });
        CardView cardiologists= findViewById(R.id.cardFDCardiologists);
        cardiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Tim mạch");
                startActivity(it);
            }
        });

    }
}