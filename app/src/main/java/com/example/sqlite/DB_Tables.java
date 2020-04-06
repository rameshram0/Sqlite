package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DB_Tables {


    private Context context;

    private DatabaseHelper database = null;
    SQLiteDatabase db;

    public DB_Tables(Context context) {
        database = new DatabaseHelper(context);
    }

    public DB_Tables openDB() {
        try {
            db = database.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this;
    }

    public void close() {
        try {
            database.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertData(String name, String password, String newpassword) {

        SQLiteDatabase db = null;

        try {
            if (database != null) {

                db = database.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                contentValues.put("name", name);
                contentValues.put("password", password);
                contentValues.put("reenterpasword", newpassword);
                db.insert("users", null, contentValues);
                db.close();
                Log.d(TAG, "insertData: " + "inserted data" + contentValues);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public Cursor retrive(String name,String pwd) {


        if (database != null) {

            SQLiteDatabase db = database.getWritableDatabase();

            String q = "select * from users where name = ? and password =?";

            Cursor cursor = db.rawQuery(q, new String[]{name,pwd});

            Log.d(TAG, "retrive: " + "retrivve" + cursor);

            return cursor;
        }
        return null;
    }


    public boolean insertCust(String cname,String cmbl,String date) {

        SQLiteDatabase db = null;

        if(database!=null) {
           db= database.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("cust_name", cname);
            contentValues.put("cust_mbl", cmbl);
            contentValues.put("currentdate", date);

            db.insertWithOnConflict("customer", null, contentValues,SQLiteDatabase.CONFLICT_IGNORE);
            db.close();
            Log.d(TAG, "insertData: " + "inserted data" + contentValues);
            return true;
        }
        return false;
    }

     public Cursor retriveCust(){

        SQLiteDatabase sd=database.getReadableDatabase();

        String q="select * from customer";

        Cursor cursor=sd.rawQuery(q,null);

        return cursor;
    }

    public void updatePassword(String id,String npwd,String nepwd){
        SQLiteDatabase db=null;

        if(database!=null){
            String iwhereClause = "id='" + id + "'";
            db=database.getWritableDatabase();
            ContentValues cv=new ContentValues();
            cv.put("id",id);
            cv.put("password",npwd);
            cv.put("reenterpasword",nepwd);
            db.update("users",cv,iwhereClause,null);
            db.close();
            Log.d(TAG, "updated: " + "data" +cv);
        }


    }
}