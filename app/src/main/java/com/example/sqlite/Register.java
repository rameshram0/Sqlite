package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Register extends AppCompatActivity {

    DatabaseHelper db;
    DB_Tables data;

    EditText n,m,s,id;

    Button btn;

    TextView showdata;

    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db=new DatabaseHelper(this);
        data=new DB_Tables(this);
        data.openDB();


        n=findViewById(R.id.name);
        s=findViewById(R.id.pwd);
        m=findViewById(R.id.newpwd);
        btn=findViewById(R.id.register);
        showdata=findViewById(R.id.login);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s2=n.getText().toString();
              String s1=  s.getText().toString();
              String s3=  m.getText().toString();

                if (s2.isEmpty()){
                    getError("enter name");
                    return;
                }

                if(s1.length() == 0) {

                    getError("enter 4 digit password");
                    return;
                }
                    if (s3.length() == 0) {

                        getError("enter 4 digit password");
                        return;
                    }
                    if (!s1.equals(s3)) {

                        getError("password doesnot match");

                    }
                    else{
                        data.insertData(n.getText().toString(), s.getText().toString(), m.getText().toString());

                        Toast.makeText(Register.this, "sucessful", Toast.LENGTH_SHORT).show();

                        Intent i=new Intent(Register.this,Login.class);
                        startActivity(i);
                        finish();
                    }


              



            }
        });

        showdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
                overridePendingTransition(R.anim.fade_in_anim,R.anim.fade_out_anim);
            }
        });

      /*  update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.updateData(id.getText().toString(),n.getText().toString(),s.getText().toString(),m.getText().toString());
                Toast.makeText(Register.this, "updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteData(id.getText().toString());
                Toast.makeText(Register.this, "deleted", Toast.LENGTH_SHORT).show();

            }
        });*/

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

    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in_anim,R.anim.fade_out_anim);
    }
}
