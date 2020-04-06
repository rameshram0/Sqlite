package com.example.sqlite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class ForgetScreen extends AppCompatActivity {

    EditText npwd, enpwd;
    Button save;

    DB_Tables data;

    String id;

    Activity context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_forget_screen);
  /*  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_forget_screen, container, false);
*/
      /*  data=new DB_Tables(this);
        data.openDB();

        context=this;

        npwd=findViewById(R.id.npwd);
        enpwd=findViewById(R.id.enpwd);
        save=findViewById(R.id.ok);

        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=sp.edit();
        id= sp.getString("id","");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String npass= npwd.getText().toString();
               String nepass=enpwd.getText().toString();

               data.updatePassword(id,npass,nepass);

               if ( !nepass.equals("") && !npass.equals("")) {

                   Intent i = new Intent(ForgetScreen.this, Login.class);
                   startActivity(i);

               }

            }
        });

       // return view;

    }

    public  void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in_anim,R.anim.fade_out_anim);
    }*/
    }
}