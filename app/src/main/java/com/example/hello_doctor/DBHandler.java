package com.example.hello_doctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String q1 = "create table users(firstname text, lastname text, Idno text, email text, phone text,"
        + "password text)";
        db.execSQL(q1);

        String q2 = "create table UserAppoint(firstname text, Lastname text, Idno text,email text, Time text, date text, Appoint_nature text)";
        db.execSQL(q2);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int updateUser(String email, String phone, String ref)
    {

        SQLiteDatabase db = getWritableDatabase();
        String[] argsv = new String[1];
        ContentValues cv = new ContentValues();
        cv.put("email", email);
        cv.put("phone", phone);
        argsv[0] = ref;

        //db.execSQL("update users SET email = ?, phone = ?  where email=?", argsv );

        return db.update("users",cv, null, argsv);

    }

    public int updatePass(String password, String idno, String email)
    {

        SQLiteDatabase db = getWritableDatabase();
        String[] argsv = new String[2];
        ContentValues cv = new ContentValues();
        cv.put("password", password);

        argsv[0] = idno;
        argsv[1] = email;


        return db.update("users",cv, null, argsv);

    }

    public long UserAppointments(String firstname, String Lastname,String IdNo,String email,  String time,
                             String date, String natureOfAppoint){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("firstname", firstname);
        cv.put("Lastname", Lastname);
        cv.put("Idno",  IdNo);
        cv.put("email", email);
        cv.put("Time", time);
        cv.put("date", date);
        cv.put("Appoint_nature", natureOfAppoint);

        long i = db.insert("UserAppoint", null, cv);
        db.close();

        return  i;
    }

    public int DeleteApptn(String email, String date, String time)
    {

        String[] str = new String[3];
        str[0] = email;
        str[1] = date;
        str[2] = time;

        int res;
        SQLiteDatabase db = getWritableDatabase();
         res = db.delete("UserAppoint", null, str);
        db.close();

        return  res;
    }


    public int getAppointsments(String time, String date)
    {
        int res;
        String[] str = new String[2];
        str[0] = time;
        str[1] = date;
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor c = db.rawQuery("select * from UserAppoint where Time=? and date=?", str)) {

            if (c.moveToFirst()) {
                res = 1;
            } else {
                res = 0;
            }
        }
        return res;
    }

    public ArrayList DisplayAppointment(String firstname)
    {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> userc = new ArrayList<>();
        String[] one = new String[1];
        one[0] = firstname;

        Cursor c = db.rawQuery("select * from UserAppoint where email = ?", one);

        if(c.moveToFirst()){
            do{
                String firstname1 = c.getString(0);
                String lastname = c.getString(1);
                String Idno = c.getString(2);
                String email1 = c.getString(3);
                String time = c.getString(4);
                String date = c.getString(5);
                String appoint_nature = c.getString(6);

                userc.add(0, firstname1);
                userc.add(1, lastname);
                userc.add(2, Idno);
                userc.add(3, email1);
                userc.add(4, time);
                userc.add(5, date);
                userc.add(6, appoint_nature);

            }while(c.moveToNext());
        }


        db.close();
        c.close();
        return userc;
    }

    public void register(String firstname, String lastname, String Idno, String email, String phone,
                         String password){
        ContentValues cv = new ContentValues();
        cv.put("firstname", firstname);
        cv.put("lastname", lastname);
        cv.put("Idno",  Idno);
        cv.put("email", email);
        cv.put("phone", phone);
        cv.put("password", password);


        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    public int login(String username, String password) {
        int res;
        String[] str = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor c = db.rawQuery("select * from users where email=? and password=?", str)) {

            if (c.moveToFirst()) {
                res = 1;
            } else {
                res = 0;
            }
            //c.close();
        }
        return res;
    }

    public ArrayList getUser(String email)
    {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> userc = new ArrayList<>();
        String[] one = new String[1];
        one[0] = email;

        Cursor c = db.rawQuery("select * from users where email = ?", one);

        if(c.moveToFirst()){
            do{
                String firstname = c.getString(0);
                String lastname = c.getString(1);
                String Idno = c.getString(2);
                String email1 = c.getString(3);
                String phone = c.getString(4);

                userc.add(0, firstname);
                userc.add(1, lastname);
                userc.add(2, Idno);
                userc.add(3, email1);
                userc.add(4, phone);

            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return userc;


    }
}
