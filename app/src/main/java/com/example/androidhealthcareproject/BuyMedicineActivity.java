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

public class BuyMedicineActivity extends AppCompatActivity {
    private  String[][] packages={
            {"Paracetamol", "", "","","100000"},
            {"Ibuprofen", "", "","","43000"},
            {"Aspirin", "", "","","180000"},
            {"Diphenhydramine", "", "","","88000"},
            {"Omeprazole", "", "","","30000"},
            {"Simvastatin", "", "","","30000"},
            {"Metformin", "", "","","24000"},
            {"Amoxicillin", "", "","","100000"},
            {"Cetirizine", "", "","","26000"},
            {"Salbutamol", "", "","","100000"},

    };
    private  String[] package_details={
            "Một loại thuốc giảm đau và hạ sốt thông thường. Nó được sử dụng để giảm các triệu chứng như đau đầu, đau cơ, đau răng và sốt nhẹ.",
            "Một loại thuốc chống viêm không steroid (NSAID) được sử dụng để giảm đau, viêm và hạ sốt. Nó thường được sử dụng trong trường hợp đau nhức, viêm khớp, đau cơ và sốt.",
            "Một loại thuốc chống viêm không steroid (NSAID) có tác dụng giảm đau, viêm và sốt. Nó cũng được sử dụng để ngăn ngừa cơn đau tim và đột quỵ ở những người có nguy cơ cao.",
            "Một loại thuốc kháng histamine và kháng cholinergic được sử dụng chủ yếu để giảm các triệu chứng dị ứng như ngứa, sổ mũi và phát ban. Nó cũng có thể được sử dụng như một thuốc an thần ngắn hạn.",
            "Một loại thuốc ức chế pompa proton (PPI) được sử dụng để giảm lượng axit trong dạ dày. Nó thường được sử dụng để điều trị bệnh trào ngược dạ dày-thực quản và loét dạ dày tá tràng.",
            "Một loại thuốc giảm cholesterol thuộc nhóm thuốc gọi là statin. Nó được sử dụng để điều trị cao cholesterol và giảm nguy cơ bệnh tim mạch.",
            "Một loại thuốc dùng trong điều trị tiểu đường kiểu 2. Nó là một loại thuốc chống đái tháo đường, giúp kiểm soát mức đường huyết.",
            "Một loại kháng sinh thuộc nhóm beta-lactam. Nó được sử dụng để điều trị nhiễm trùng nhiễm khuẩn như viêm họng, viêm phổi, viêm tai và nhiễm trùng đường tiết niệu.",
            "Một loại thuốc kháng histamine không gây buồn ngủ được sử dụng để giảm các triệu chứng dị ứng như ngứa, sổ mũi và nổi mẩn. ",
            "Một loại thuốc giãn phế quản thuộc nhóm các chất beta-2 agonist. Nó được sử dụng như một loại thuốc kháng cơn cảm mạo phổi và để điều trị hen suyễn.",


    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack, btnGotoCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst=findViewById(R.id.listViewBM);
        btnBack=findViewById(R.id.buttonBMBack);
        btnGotoCart=findViewById(R.id.buttonBMGotoCart);

        btnGotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, CartBuyMedicineActivity.class));
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Giá: "+packages[i][4]+ " VNĐ");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new  int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it= new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1", packages[i][0]);
                it.putExtra("text2", package_details[i]);
                it.putExtra("text3", packages[i][4]);
                startActivity(it);
            }
        });
    }
}