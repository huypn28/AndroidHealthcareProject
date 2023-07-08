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
            {"Trần Thị Thu Loan", "TP Hồ Chí Minh", "7 năm", "0945673461","600000"},
            {"Hoàng Thị Mai Phương", "TP Hà Nội", "8 năm", "0345762651","700000"},
            {"Nguyễn Tá Dũng", "TP Hồ Chí Minh", "7 năm", "0987623563","600000"},
            {"Hoàng Thị Cúc", "TP Hà Nội", "10 năm", "0327673468","800000"},
            {"Trần Mạnh Hà", "TP Hà Nội", "5 năm", "0329875465","600000"},
    };
    private String[][] doctor_details2={
            {"Khương Đức Dục", "TP Hồ Chí Minh", "16 năm", "0947359401","800000"},
            {"Nguyễn Thị Đào", "TP Hồ Chí Minh", "15 năm", "0868907955","800000"},
            {"Trần Thị Thu Hằng", "TP Hà Nội", "10 năm", "0987767900","600000"},
            {"Nguyễn Công Bắc", "TP Hồ Chí Minh", "7 năm", "0942245657","500000"},
            {"Đỗ Thị Thu Hương", "TP Hà Nội", "12 năm", "0325552679","700000"},
    };
    private String[][] doctor_details3={
            {"Nguyễn Văn Hưng", "TP Hà Nội", "20 năm", "0985566425","900000"},
            {"Phạm Thị Thu Hằng", "TP Hà Nội", "7 năm", "0943678900","500000"},
            {"Nguyễn Thị Thanh Phượng", "TP Hồ Chí Minh", "7 năm", "0945952711","500000"},
            {"Bùi Thị Lan", "TP Hồ Chí Minh", "10 năm", "0986515279","600000"},
            {"Hà Hữu Hậu", "TP Hồ Chí Minh", "20 năm", "0935674112","900000"},
    };
    private String[][] doctor_details4={
            {"Hoàng Thị Năm", "TP Hà Nội", "10 năm", "0868673329","600000"},
            {"Phạm Thị Huệ", "TP Hà Nội", "6 năm", "0326689653","500000"},
            {"Nguyễn Thế Bắc", "TP Hồ Chí Minh", "15 năm", "0947790250","800000"},
            {"Bùi Thị Mỵ Lương", "TP Hà Nội", "13 năm", "098567908","700000"},
            {"Nguyễn Thị Tho", "TP Hà Nội", "11 năm", "0324478255","700000"},
    };
    private String[][] doctor_details5={
            {"Dư Thị Tuyết", "TP Hồ Chí Minh", "16 năm", "0322716868","800000"},
            {"Ngô Thị Lợi", "TP Hồ Chí Minh", "14 năm", "0944100078","700000"},
            {"Nguyễn Bá Vệ", "TP Hà Nội", "9 năm", "0935718252","600000"},
            {"Hoàng Mạnh Linh", "TP Hà Nội", "8 năm", "0982686444","600000"},
            {"Đặng Thị Loan", "TP Hồ Chí Minh", "12 năm", "0934888921","700000"},
    };
    private String[][] doctor_details6={
            {"Hoàng Ngọc Anh", "TP Hà Nội", "5 năm", "0930565724","500000"},
            {"Lưu Văn Phương", "TP Hồ Chí Minh", "7 năm", "0982435617","600000"},
            {"Trần Thanh Hiếu", "TP Hà Nội", "13 năm", "0941273506","700000"},
            {"Bùi Minh Thiện", "TP Hà Nội", "16 năm", "0868553265","800000"},
            {"Phùng Thị Bích Thu", "TP Hồ Chí Minh", "10 năm", "0863505122","700000"},
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
        button=findViewById(R.id.buttonDDBack);

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
        ListView listView= findViewById(R.id.listViewDD);
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