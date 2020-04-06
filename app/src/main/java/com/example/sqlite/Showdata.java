package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class Showdata extends AppCompatActivity {

    TextView show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata);

        DatabaseHelper db=new DatabaseHelper(this);

        show=findViewById(R.id.textView);

        Cursor cursor=db.retriveData();

        StringBuilder sb =new StringBuilder();

        while (cursor.moveToNext()) {
            sb.append("\nNAME:" + cursor.getString(1)+"\nSURNAME:"+cursor.getString(2)+"\nMARKS"+cursor.getString(3));
        }
        show.setText(sb);

    }
}
