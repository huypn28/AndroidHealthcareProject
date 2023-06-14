package com.example.androidhealthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {
    private String[][] packages={
            {"Package 1: Full Body checkup", "","","","999"},
            {"Package 2: Blood Glucose Fasting", "","","","299"},
            {"Package 3: Covid-19 Antibody - IgG", "","","","899"},
            {"Package 4: Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)", "","","","499"},
            {"Package 5: Complete Hemogram", "","","","699"},
    };
    private  String[] package_details={
            "Blood Glucoso Fasting\n"+
                    " Complete Hemogram\n" +
                    "HbA1c\n" +
                    " Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "LDH Lactate Dehydrogenase, Serum\n" +
                    "Lipid Profile\n" +
                    "Liver Function Test",
           "Blood Glucose Fasting ",
           "Covid-19 Antibody - IgG",
           "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)",
           "Complete Hemogram\n" +
                   "CRP (C Reactive Protein) Quantitative, Surem\n"+
                   " Iron Studies\n" +
                   "Kidney Function Test\n" +
                   "Vitamin D Total-25 Hydroxy\n" +
                   "Liver Function Test"+
                   "Lipid Profile\n"

    };
    HashMap<String, String>item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnLTCart, btnLTBack;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        btnLTCart=findViewById(R.id.buttonLDAddCart);
        btnLTBack=findViewById(R.id.buttonLDBack);
        listView=findViewById(R.id.lVLT);

        btnLTBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this,HomeActivity.class));


            }
        });
        list=new ArrayList();
        for (int i=0;i<packages.length;i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Tổng tiền: " + packages[i][4] + " VNĐ");
            list.add(item);
        }
            sa=new SimpleAdapter(this,list,
                    R.layout.multi_lines,
                    new String[]{"line1","line2","line3","line4","line5"},
                    new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
            listView.setAdapter(sa);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent it= new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                    it.putExtra("text1", packages[i][0]);
                    it.putExtra("text2", package_details[i]);
                    it.putExtra("text3", packages[i][4]);
                    startActivity(it);
                }
            });

            btnLTCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
                }
            });


    }
}