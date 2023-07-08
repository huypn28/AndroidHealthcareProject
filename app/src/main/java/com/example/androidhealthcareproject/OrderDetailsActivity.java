package com.example.androidhealthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {

    private String[][]order_details={};

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        btn=findViewById(R.id.buttonODBack);
        lst=findViewById(R.id.listViewOD);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, HomeActivity.class));
            }
        });


        //Database db= new Database(getApplicationContext(),"Chăm sóc sức khỏe", null,1);

        SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username= sharedPreferences.getString("username","").toString();
        Log.d("TAG", "Username: " + username);
        RequestQueue queue = Volley.newRequestQueue(OrderDetailsActivity.this);
        String url = "http://192.168.101.57:9080/api/v1/user/orderplace/get";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url+ "?username=" + username, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Xử lý dữ liệu nhận được từ backend
                        ArrayList<HashMap<String, String>> orderList = new ArrayList<>();

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONArray order = response.getJSONArray(i);

                                // Lấy các thông tin cần thiết từ đối tượng JSON
                                String line1 = order.getString(2);
                                String line2 = order.getString(3);

                                String line4 = "";

                                if (order.getString(9).equals("thuốc")) {
                                    line4 = "Ngày đặt: " + order.getString(6);
                                } else {
                                    line4 = "Ngày khám: " + order.getString(6) + " " + order.getString(7);
                                }

                                String line3 = "Giá tiền: " + order.getString(8) + " VNĐ";
                                String line5 = order.getString(9);
                                // Tạo HashMap để lưu trữ thông tin
                                HashMap<String, String> item = new HashMap<>();
                                item.put("line1", line1);
                                item.put("line2", line2);
                                item.put("line3", line3);
                                item.put("line4", line4);
                                item.put("line5", line5);

                                // Thêm HashMap vào danh sách
                                orderList.add(item);
                            }

                            // Tạo SimpleAdapter để hiển thị dữ liệu lên ListView
                            SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), orderList,
                                    R.layout.multi_lines,
                                    new String[]{"line1", "line2", "line3", "line4", "line5"},
                                    new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

                            // Gán adapter cho ListView
                            lst.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý khi có lỗi xảy ra
                    }
                });

        // Thêm yêu cầu vào hàng đợi RequestQueue
        queue.add(jsonArrayRequest);

    }


}
