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
            {"Gói 1: Khám tổng quát", "","","","400000"},
            {"Gói 2: Xét nghiệm đường trong máu lúc đói", "","","","100000"},
            {"Gói 3: Xét nghiệm kháng thể IgG Covid-19", "","","","100000"},
            {"Gói 4: Chỉ số toàn diện của tuyến giáp (T3, T4 và TSH)", "","","","200000"},
            {"Gói 5: Phân tích máu toàn diện", "","","","150000"},
    };
    private  String[] package_details={
            "   Xét nghiệm máu: \n" +
                    "- Đếm huyết cầu và huyết tương: Đánh giá tổng số huyết cầu, bao gồm hồng cầu, bạch cầu và tiểu cầu. \n" +
                    "   Xét nghiệm chức năng gan: Bao gồm các chỉ số như AST, ALT, bilirubin và albumin để đánh giá chức năng gan. \n" +
                    "   Xét nghiệm chức năng thận: Bao gồm các chỉ số như creatinine và urea để đánh giá chức năng thận. \n" +
                    "   Chỉ số quá trình viêm: Bao gồm c-reactive protein (CRP) và tỷ lệ kết tủa của hồng cầu (ESR) để xác định sự có mặt của viêm nhiễm trong cơ thể. \n"+
                    "  Xét nghiệm nước tiểu: \n" +
                    "- Xét nghiệm cơ bản: Kiểm tra màu sắc, độ trong suốt, pH, glucose, protein, ketones và bilirubin trong nước tiểu. \n" +
                    "- Xét nghiệm tạo hình: Đếm và đánh giá tính chất hình thái của tế bào, bao gồm hồng cầu, bạch cầu và tế bào bám dính. " +
                    "   Xét nghiệm chức năng tuyến giáp: Đánh giá mức độ hoạt động của tuyến giáp thông qua xét nghiệm mức độ hormone tuyến giáp (TSH) và các hoocmon tuyến giáp khác như T3 và T4. \n" +
                    "   Xét nghiệm lipid máu: Đo mức độ cholesterol toàn phần, HDL (lipoprotein có mật độ cao), LDL (lipoprotein có mật độ thấp) và triglyceride để đánh giá rối loạn lipid trong cơ thể.\n" +
                    "   Xét nghiệm chức năng tim mạch: Bao gồm xét nghiệm troponin, enzyme tim và các chỉ số khác để đánh giá chức năng tim mạch và xác định sự tổn thương cơ tim. \n" +
                    "   Xét nghiệm chức năng tuyến yên: Đo mức độ hormone tuyến yên, bao gồm T3, T4 và hormone kích thích tuyến yên (TSH), để đánh giá chức năng của tuyến yên. \n" +
                    "   Xét nghiệm chức năng tụy: Bao gồm đo mức độ insulin và glucose trong máu để đánh giá chức năng của tụy. \n" +
                    "   Xét nghiệm chức năng gan: Bao gồm các chỉ số như AST, ALT, bilirubin và albumin để đánh giá chức năng gan. \n" +
                    "   Xét nghiệm nghiệm khuẩn: Đánh giá sự có mặt và loại khuẩn trong một mẫu, như xét nghiệm nước tiểu, nước da hay nước vùng xương, nhằm xác định sự nhiễm trùng.\n"
                    ,
           "   Trước khi thực hiện xét nghiệm, bạn sẽ được yêu cầu không ăn uống gì (ngoại trừ nước không đường) trong khoảng thời gian 8-12 giờ trước xét nghiệm. Điều này để đảm bảo mức đường trong máu không bị ảnh hưởng bởi thức ăn gần đây. \n" +
                   "   Quá trình xét nghiệm:  chuyên gia y tế sẽ lấy một mẫu máu từ tĩnh mạch trong tay của bạn bằng một kim tiêm. Mẫu máu sẽ được đưa vào ống chứa để đo lượng glucose có trong máu của bạn. \n" +
                   "   Ý nghĩa kết quả: Kết quả sẽ cho biết mức đường trong máu khi bạn đói. \n" +
                   "- Kết quả bình thường: Thông thường, mức đường máu đói bình thường nằm trong khoảng 70-99 mg/dL (3.9-5.5 mmol/L). \n" +
                   "- Kết quả cao: Một kết quả cao hơn mức bình thường có thể chỉ ra sự tăng đường trong máu, nguy cơ mắc các vấn đề liên quan đến đường như tiểu đường loại 2 hoặc tiểu đường gestational (trong thai kỳ), bệnh tiền tiểu đường hoặc khả năng giảm sức chịu đựng đường của cơ thể. \n" +
                   "- Kết quả thấp: Kết quả thấp hơn mức bình thường có thể cho thấy sự thiếu hụt đường trong máu, có thể gây ra các triệu chứng như chóng mặt, mệt mỏi, hoa mắt hoặc nhịp tim nhanh.",
           "   Được sử dụng để đánh giá xem người đã được tiêm chủng vaccine Covid-19 có phản ứng miễn dịch đối với virus hay không, hoặc để xác định liệu một người đã mắc phải Covid-19 trong quá khứ hay không. \n" +
                   "   Quá trình xét nghiệm: Xét nghiệm sẽ yêu cầu lấy một mẫu máu từ tĩnh mạch trong tay của bạn. Mẫu máu sau đó sẽ được xử lý để phân lập và phát hiện kháng thể IgG chống lại virus SARS-CoV-2. Các phương pháp xét nghiệm kháng thể IgG có thể khác nhau tùy thuộc vào phương pháp xét nghiệm được sử dụng trong phòng thí nghiệm. \n" +
                   "   Ý nghĩa kết quả: \n" +
                   "- Kết quả dương tính (positive): Nếu xét nghiệm cho thấy có sự hiện diện của kháng thể IgG chống lại virus, điều này có thể cho thấy bạn đã tiếp xúc với virus SARS-CoV-2 hoặc đã được tiêm chủng vaccine Covid-19 trong quá khứ. \n" +
                   "- Kết quả âm tính (negative): Nếu xét nghiệm không phát hiện kháng thể IgG chống lại virus, điều này có thể chỉ ra rằng bạn chưa mắc phải Covid-19 hoặc chưa phản ứng miễn dịch đủ sau tiêm chủng vaccine Covid-19. Tuy nhiên, đôi khi kháng thể IgG có thể không được phát hiện trong giai đoạn sớm sau nhiễm virus hoặc tiêm chủng vaccine.",
           "   Triiodothyronine (T3): Đây là một hormone được tạo ra bởi tuyến giáp và có vai trò quan trọng trong quá trình chuyển hóa, tăng cường sự phát triển và hoạt động của các cơ quan và mô trong cơ thể. \n" +
                   "   Thyroxine (T4): Đây là một hormone khác được tạo ra bởi tuyến giáp. Nó chủ yếu có tác dụng làm tăng năng lượng và duy trì hoạt động chức năng của cơ thể. \n" +
                   "   Thyroid Stimulating Hormone (TSH): Đây là một hormone được tiết ra bởi tuyến yên, có tác dụng kích thích tuyến giáp để sản xuất và giải phóng hormone T3 và T4. Mức độ TSH được điều chỉnh bởi hệ thống phản hồi âm giữa tuyến giáp và tuyến yên. \n" +
                   "   Quá trình xét nghiệm Thyroid Profile-Total thường bao gồm việc lấy một mẫu máu từ tĩnh mạch trong tay của bạn. Mẫu máu này sẽ được chuyển đến phòng thí nghiệm để đo lường nồng độ của T3, T4 và TSH trong máu của bạn. \n" +
                   "   Kết quả của xét nghiệm sẽ cho biết liệu mức độ các hormone thyroid trong cơ thể có nằm trong khoảng bình thường hay không. Thông qua xét nghiệm này, các bất thường trong chức năng của tuyến giáp có thể được phát hiện, bao gồm: \n" +
                   "- Tăng hoặc giảm hormone thyroid (T3 và T4): Các hiện tượng như tăng chức năng tuyến giáp (hyperthyroidism) hoặc suy giảm chức năng tuyến giáp (hypothyroidism) có thể được xác định. \n" +
                   "- Tăng hoặc giảm TSH: Khi tuyến giáp không hoạt động chính xác, hệ thống phản hồi âm giữa tuyến giáp và tuyến yên sẽ bị ảnh hưởng, dẫn đến sự tăng hoặc giảm sản xuất của TSH.\n"+"",
           "   Là một loại xét nghiệm huyết học cơ bản, cung cấp thông tin về các thành phần chính của máu. Nó bao gồm đánh giá các yếu tố như hồng cầu, bạch cầu và các thành phần khác của máu. Dưới đây là một số thông tin chi tiết về các thành phần được đánh giá trong một bộ xét nghiệm Hemogram:\n" +
                   "- Hồng cầu (Red Blood Cells - RBC): Đo lượng hồng cầu có trong một đơn vị khối lượng máu. Thông số liên quan bao gồm số lượng hồng cầu, hàm lượng hemoglobin và kích thước hồng cầu.\n" +
                   "- Bạch cầu (White Blood Cells - WBC): Đo lượng bạch cầu có trong một đơn vị khối lượng máu. Thông số liên quan bao gồm số lượng bạch cầu và phân tích loại bạch cầu, bao gồm cả bạch cầu trung tính, bạch cầu lympho và bạch cầu monocyt.\n" +
                   "- Tiểu cầu (Platelets): Đo lượng tiểu cầu có trong một đơn vị khối lượng máu. Tiểu cầu có vai trò quan trọng trong quá trình đông máu.\n" +
                   "- Hồng cầu trung tính (Neutrophils): Đo lượng hồng cầu trung tính trong máu. Chúng là một loại bạch cầu chính trong phản ứng miễn dịch và có vai trò quan trọng trong việc chống lại nhiễm trùng.\n" +
                   "- Hồng cầu lympho (Lymphocytes): Đo lượng hồng cầu lympho trong máu. Chúng là một loại bạch cầu có vai trò trong hệ thống miễn dịch và có khả năng tiêu diệt vi khuẩn và tế bào nhiễm trùng.\n" +
                   "- Hồng cầu monocyt (Monocytes): Đo lượng hồng cầu monocyt trong máu. Chúng là một loại bạch cầu có khả năng diệt khuẩn và loại bỏ tế bào chết trong cơ thể.\n" +
                   "- Hemoglobin (Hb): Đo lượng hemoglobin, chất chịu oxi trong hồng cầu. Hemoglobin chịu trách nhiệm vận chuyển oxi từ phổi đến các tế bào trong cơ thể.\n" +
                   "- Hematocrit (Hct): Đo tỷ lệ phần trăm khối lượng máu là hồng cầu. Thông số này có thể cho biết về nồng độ hồng cầu trong máu.\n" +
                   "   Bộ xét nghiệm Complete Hemogram cung cấp thông tin quan trọng về sự chẩn đoán và đánh giá tình trạng sức khỏe chung của bệnh nhân, bao gồm các vấn đề như thiếu máu, nhiễm trùng, bệnh lý huyết học và nhiều bệnh lý khác liên quan đến máu." ,
    };
    HashMap<String, String>item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnLTGotoCart, btnLTBack;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        btnLTGotoCart=findViewById(R.id.buttonLDGotoCart);
        btnLTBack=findViewById(R.id.buttonLDBack);
        listView=findViewById(R.id.listViewLT);

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

            btnLTGotoCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
                }
            });


    }
}