package com.example.uitest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "Userdata.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT primary key , contact TEXT, dob TEXT)"); // change here

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");

    }

    public Boolean insertuserdata(String name, String location, String price){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", location);
        contentValues.put("dob", price);
        long result = DB.insert("Userdetails", null, contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }

    }

    public Boolean updateuserdata(String name, String location, String price){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("contact", location);
        contentValues.put("dob", price);

        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[] {name});
        if(cursor.getCount()>0){
            long result = DB.update("Userdetails", contentValues, "name=?", new String[] {name});
            if(result == -1){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }


    }

    public Boolean deletedata(String name){
        SQLiteDatabase DB = this.getWritableDatabase();


        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[] {name});
        if(cursor.getCount()>0){
            long result = DB.delete("Userdetails", "name=?", new String[] {name});
            if(result == -1){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }


    }

    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();


        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;


    }

}
