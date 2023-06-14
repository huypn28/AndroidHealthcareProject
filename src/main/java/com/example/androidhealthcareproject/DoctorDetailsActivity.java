package com.example.androidhealthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1={
            {"Trần Thị Thu Loan", "14 Lý Tự Trọng, Phường Bến Nghé, Quận 1, TP Hồ Chí Minh", "7 năm", "0945673461","600.000"}
    };
    private String[][] doctor_details2={
            {"cde", "", "7 năm", "","600"}
    };
    private String[][] doctor_details3={
            {"fgh", "", "7 năm", "","600"}
    };
    private String[][] doctor_details4={
            {"jkl", "", "7 năm", "","600"}
    };
    private String[][] doctor_details5={
            {"mno", "", "7 năm", "","600"}
    };
    private String[][] doctor_details6={
            {"pqr", "", "7 năm", "","600"}
    };
    TextView textView;
    Button button;
    String[][]doctor_details={};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        textView=findViewById(R.id.tVLDTitle);
        button=findViewById(R.id.buttonLDAddCart);

        Intent it=getIntent();
        String title= it.getStringExtra("title");
        textView.setText(title);

        if(title.compareTo("Chuyên khoa y học gia đình")==0)
            doctor_details=doctor_details1;
        else
        if(title.compareTo("Chuyên gia dinh dưỡng")==0)
            doctor_details=doctor_details2;
        else
        if(title.compareTo("Nha khoa")==0)
            doctor_details=doctor_details3;
        else
        if(title.compareTo("Phẫu thuật")==0)
            doctor_details=doctor_details4;
        else
            doctor_details=doctor_details5;

            button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });
            list= new ArrayList();
            for (int i=0;i< doctor_details.length;i++){
                item= new HashMap<String, String>();
                item.put("line1","Tên bác sĩ: "+ doctor_details[i][0]);
                item.put("line2","Địa chỉ: "+ doctor_details[i][1]);
                item.put("line3","Kinh nghiệm: "+ doctor_details[i][2]);
                item.put("line4","SĐT: "+ doctor_details[i][3]);
                item.put("line5","Phí: "+ doctor_details[i][4]+" VNĐ");
                list.add(item);
            }
            simpleAdapter=new SimpleAdapter(this,list,
                    R.layout.multi_lines,
                    new String[]{"line1","line2","line3","line4","line5"},
                    new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView listView= findViewById(R.id.lVLT);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it= new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                it.putExtra("text4", doctor_details[i][3]);
                it.putExtra("text5", doctor_details[i][4]);
                startActivity(it);

            }
        });
    }
}