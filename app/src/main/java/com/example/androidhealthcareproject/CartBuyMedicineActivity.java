package com.example.androidhealthcareproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CartBuyMedicineActivity extends AppCompatActivity {
    HashMap<String, String> item;
    ArrayList list;
    ListView lst;
    SimpleAdapter sa;
    TextView tvTotal;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton, btnCheckout, btnBack;

    private String[][]packages={};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);

        dateButton=findViewById(R.id.buttonBMCartDatePicker);
        btnCheckout=findViewById(R.id.buttonBMCartCheckout);
        btnBack=findViewById(R.id.buttonBMCartBack);
        tvTotal=findViewById(R.id.textViewBMCartTotalPrice);
        lst=findViewById(R.id.listViewBMCart);

        SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username= sharedPreferences.getString("username","").toString();
        Log.d("TAG", "Username: " + username);
        RequestQueue queue = Volley.newRequestQueue(CartBuyMedicineActivity.this);
        String url = "http://192.168.101.57:9080/api/v1/user/cart/get";



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url+ "?username=" + username+ "&otype=" + "thuốc", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Xử lý dữ liệu nhận được từ backend
                        ArrayList<HashMap<String, String>> orderList = new ArrayList<>();
                        float totalAmount=0;
                        list = new ArrayList<>();
                        try {
                            packages = new String[response.length()][5];
                            for (int i = 0; i < response.length(); i++) {
                                JSONArray order = response.getJSONArray(i);
                                String line1 = order.getString(2);
                                String line2 = "";
                                String line3 = "";
                                String line4 = "";
                                String line5 = "Giá: " + order.getString(3) + " VNĐ";

                                // Calculate the total amount based on the "line5" value
                                totalAmount += Float.parseFloat(order.getString(3));

                                // Tạo HashMap để lưu trữ thông tin
                                item = new HashMap<>();
                                item.put("line1", line1);
                                item.put("line2", line2);
                                item.put("line3", line3);
                                item.put("line4", line4);
                                item.put("line5", line5);
                                list.add(item);
                            }

                            tvTotal.setText("Tổng giá: " + totalAmount);


                            // Tạo SimpleAdapter để hiển thị dữ liệu lên ListView
                            SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), list,
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



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartBuyMedicineActivity.this, BuyMedicineActivity.class));

            }
        });
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(CartBuyMedicineActivity.this,BuyMedicineBookActivity.class);
                it.putExtra("price", tvTotal.getText());
                it.putExtra("date", dateButton.getText());
                startActivity(it);

            }
        });
        //chon ngay
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePickerDialog.show();
            }
        });
    }
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                dateButton.setText(i2+"/"+i1+"/"+i);
            }
        };


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis() + 86400000);
    }


}