package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    EditText n,m,s,id;

    Button btn,showdata,update,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new DatabaseHelper(this);

        id=findViewById(R.id.id);
        n=findViewById(R.id.name);
        s=findViewById(R.id.surname);
        m=findViewById(R.id.marks);
        btn=findViewById(R.id.button);
        showdata=findViewById(R.id.button2);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               db.insertData(n.getText().toString(),s.getText().toString(),m.getText().toString());

                Toast.makeText(MainActivity.this, "sucessful", Toast.LENGTH_SHORT).show();



            }
        });

        showdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Showdata.class));
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.updateData(id.getText().toString(),n.getText().toString(),s.getText().toString(),m.getText().toString());
                Toast.makeText(MainActivity.this, "updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteData(id.getText().toString());
                Toast.makeText(MainActivity.this, "deleted", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
