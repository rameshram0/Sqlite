package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {

   /* public static final String DATABASE_NAME="student.db";
    public static final String Table_name="student_details";
    public static final String Col_1="ID";
    public static final String Col_2="NAME";
    public static final String Col_3="SURNAME";
    public static final String Col_4="MARKS";*/


    public static final String DATABASE_NAME = "Students.db";
    public static final String TABLE_NAME= "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";



    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME , null, 1);

       // SQLiteDatabase d=this.getWritableDatabase();

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COL_1 + " INTEGER PRIMARY KEY," + COL_2 + " TEXT,"
                +COL_3 + " TEXT," + COL_4 + " TEXT" +  ")";

       // db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");
       db.execSQL(CREATE_CONTACTS_TABLE);
        Log.d(TAG, "onCreate: "+"dbcreated");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void insertData(String name,String surname,String marks) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.insert(TABLE_NAME,null ,contentValues);
        db.close();
        Log.d(TAG, "insertData: "+"inserted data"+contentValues);

    }

    public Cursor retriveData(){

        SQLiteDatabase sd=this.getReadableDatabase();

        Cursor cursor=sd.rawQuery("select * from " + TABLE_NAME,null);

        return cursor;


    }

    public void updateData(String id,String name,String surname,String marks) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
       db.update(TABLE_NAME,contentValues,"ID = ?",new String[] { id } );
        db.close();
        Log.d(TAG, "insertData: "+"inserted data"+contentValues);

    }

    public void deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME," ID =?",new String[] {id});

    }

    }

