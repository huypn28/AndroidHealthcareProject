package com.example.androidhealthcareproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Database extends SQLiteOpenHelper {
    private Context context;
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context.getApplicationContext();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1="create table users(username text, email text, password text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2="create table cart(username text, product text, price float, otype text)";
        sqLiteDatabase.execSQL(qry2);

        String qry3="create table orderplace(username text, fullname text, address text, contact text, age int, date text, time text, amount float, otype text)";
        sqLiteDatabase.execSQL(qry3);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int ii) {


    }
    public void register(String username, String email, String password){
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null,cv);
        db.close();
    }
    public  int login(String username, String password){
        int result=0;
        String str[]= new String[2];
        str[0]=username;
        str[1]=password;
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor = db.rawQuery("select*from users where username=? and password=?",str);
        if (cursor.moveToFirst()){
            result=1;
        }
        return  result;
    }
    public void addCart(String username, String product, float price, String otype){
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null,cv);

        db.close();
    }


    public  int checkCart(String username, String product){
        int result=0;
        String str[]= new String[2];
        str[0]=username;
        str[1]=product;
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor = db.rawQuery("select*from cart where username=? and product=?",str);
        if (cursor.moveToFirst()){
            result=1;
        }
        db.close();
        return  result;
    }

    public  void removeCart(String username, String otype){
        String str[]= new String[2];
        str[0]=username;
        str[1]=otype;
        SQLiteDatabase db=getReadableDatabase();
        db.delete("cart","username=? and otype=?",str);
        db.close();
    }

    public ArrayList getCartData(String username, String otype){
        ArrayList<String> arrayList =  new ArrayList<>();
        SQLiteDatabase db= getReadableDatabase();
        String str[]= new String[2];
        str[0]=username;
        str[1]=otype;
        Cursor cursor = db.rawQuery("select*from cart where username=? and otype=?",str);
        if (cursor.moveToFirst()){
           do {
               String product=cursor.getString(1);
               String price=cursor.getString(2);
               arrayList.add(product+ "$"+price);
           }while (cursor.moveToNext());
        }
        db.close();
        return  arrayList;
    }

    public void addOrder(String username, String fullname, String address, String contact, int age, String date, String time,float price, String otype){
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contact",contact);
        cv.put("age",age);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("amount",price);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace", null,cv);
        db.close();
    }
    public  ArrayList getOrderData(String username){
        ArrayList<String> arrayList = new ArrayList<>();
        String str[]= new String[1];
        str[0]=username;
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor = db.rawQuery("select*from orderplace where username=?",str);
        if (cursor.moveToFirst()){
            do {
                arrayList.add(
                        cursor.getString(1)+"$"+
                        cursor.getString(2)+"$"+
                        cursor.getString(3)+"$"+
                        cursor.getString(4)+"$"+
                        cursor.getString(5)+"$"+
                        cursor.getString(6)+"$"+
                        cursor.getString(7)+"$"+
                        cursor.getString(8)
                        );
            }while (cursor.moveToNext());
        }
        db.close();
        return  arrayList;
    }
    public  int checkAppointmentExists(String username, String fullname, String address, String contact, String date, String time ){
        int result=0;
        String str[]= new String[6];
        str[0]=username;
        str[1]=fullname;
        str[2]=address;
        str[3]=contact;
        str[4]=date;
        str[5]=time;
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor = db.rawQuery("select*from orderplace where username=? and fullname=? and address=? and contact=? and date=? and time=?",str);
        if (cursor.moveToFirst()){
            result=1;
        }
        db.close();
        return  result;
    }



}
