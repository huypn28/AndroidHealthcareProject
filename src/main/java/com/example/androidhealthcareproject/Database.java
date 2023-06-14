package com.example.androidhealthcareproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1="create table users(username text, email text, password text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2="create table cart(username text, product text, price float, otype text)";
        sqLiteDatabase.execSQL(qry2);
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


}
