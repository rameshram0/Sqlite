package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Login extends AppCompatActivity {

    TextView show,forget;
    EditText name,pwd;
    Button fetch;

    DatabaseHelper db;

    DB_Tables data;

    FragmentManager fm;
    FragmentTransaction ft;
    String tokenname,tokenpwd;


    EditText npwd,enpwd;
    Button save;

   // DB_Tables data;

    String id;

    Activity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         db=new DatabaseHelper(this);
         data=new DB_Tables(this);

        // data.openDB();

        show=findViewById(R.id.register);
        forget=findViewById(R.id.forget);

        name=findViewById(R.id.uname);
        pwd=findViewById(R.id.pwd);
        fetch=findViewById(R.id.login);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               /*  fm=getSupportFragmentManager();
                 ft=fm.beginTransaction();
                ForgetScreen f=new ForgetScreen();
                ft.replace(R.id.container_forget,f);
                ft.commit();*/

               /*Intent i=new Intent(Login.this,ForgetScreen.class);
               startActivity(i);*/
               showDialog();

                overridePendingTransition(R.anim.fade_in_anim,R.anim.fade_out_anim);


            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this, Register.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in_anim,R.anim.fade_out_anim);
            }
        });


        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String check=name.getText().toString();
                String check1=pwd.getText().toString();

              Cursor c=data.retrive(check,check1);

               if(c.moveToNext()){
                   String tokenid= c.getString(0);
                   tokenname= c.getString(1);
                    tokenpwd=c.getString(2);

                   SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                   SharedPreferences.Editor editor=sp.edit();
                   editor.putString("id",tokenid);
                   editor.apply();

                   if (check.equals(tokenname) && check1.equals(tokenpwd)) {

                       Toast.makeText(Login.this, "tokenid" + tokenid, Toast.LENGTH_SHORT).show();
                       Toast.makeText(Login.this, "sucess", Toast.LENGTH_SHORT).show();
                       Intent i = new Intent(Login.this, Dashboard.class);
                       i.putExtra("token", tokenname);
                       startActivity(i);
                      // finish();
                   }
               }
                else {
                   getError("invalid user");
               }

            }

        });




    }

    private void getError(String text) {

        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Alert!")
                .setContentText(text)
                .setConfirmText("Ok")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                    }
                })
                .show();

    }
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in_anim,R.anim.fade_out_anim);
       /* Fragment t = fm.findFragmentById(R.id.container_forget);
        if (t != null) {

            ft = fm.beginTransaction();
            ft.remove(t);

            ft.commit();
        } else {

        }*/
    }
    public void showDialog(){

        Dialog d=new Dialog(this);
        d.setContentView(R.layout.fragment_forget_screen);

        data=new DB_Tables(this);
        data.openDB();

        context=this;

        npwd=d.findViewById(R.id.npwd);
        enpwd=d.findViewById(R.id.enpwd);
        save=d.findViewById(R.id.ok);

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

                    Intent i = new Intent(Login.this, Login.class);
                    startActivity(i);

                }

            }
        });
        d.show();
    }

    public void dataFetch(){


    }


    }

