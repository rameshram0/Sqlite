package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Dashboard extends AppCompatActivity implements GetUsersAdapter.OnNoteListener, SearchView.OnQueryTextListener{



    FragmentTransaction ft;
    FragmentManager fm;
    Fragment t=null;

    FloatingActionButton fa;

    RecyclerView recyclerView;

    ArrayList<GetUsers> list;

    GetUsersAdapter getUsersAdapter;

    DB_Tables data;

    Toolbar toolbar;

    SwipeRefreshLayout swipeRefreshLayout;

    //dialog widgets

    EditText cname, cmbl;
    Button save, v;
    DatabaseHelper db;


    String cn, cm, date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);




        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        swipeRefreshLayout=findViewById(R.id.swipeup);


        recyclerView=findViewById(R.id.rvdata);
        list=new ArrayList<>();

        data=new DB_Tables(this);
        data.openDB();

        showData();

        Intent refresh = new Intent("com.trans.refresh");
        sendBroadcast(refresh);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });




        fa=findViewById(R.id.floatingActionButton);



        Intent i=getIntent();
        String g=i.getStringExtra("token");
       // tv.setText(g);



        fa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /* fm=getSupportFragmentManager();
                ft=fm.beginTransaction();
                Testing t=new Testing();

                ft.replace(R.id.container,t);
              //  ft.addToBackStack(null);

                ft.commit();*/

              showWindow();
            }


        });




    }
    private void showWindow() {

        Dialog d=new Dialog(this);
        d.setContentView(R.layout.fragment_testing);


        cname = d.findViewById(R.id.custname);
        cmbl = d.findViewById(R.id.custmbl);
        save = (Button) d.findViewById(R.id.add);


        db = new DatabaseHelper(this);
        data = new DB_Tables(this);
        data.openDB();

        getDate();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cn = cname.getText().toString();
                cm = cmbl.getText().toString();

                boolean res = data.insertCust(cn, cm, date);

                if (res) {
                    //Toast.makeText(getActivity(), "sucessful", Toast.LENGTH_SHORT).show();
                    getSuccess("inserted success");
                    return;

                } else {
                    Toast.makeText(Dashboard.this, "failed", Toast.LENGTH_SHORT).show();
                }


            }

        });
        d.show();

    }

    private void getSuccess(String text) {

        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Inserted!")
                .setContentText(text)
                .setConfirmText("Ok")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                        Intent i=new Intent(Dashboard.this,Dashboard.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.fade_in_anim, R.anim.fade_out_anim);
                    }
                })
                .show();

    }


    private void getDate() {

        long s = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date(s);
        date = sdf.format(d);
        Log.d("time", "time: " + date);
    }

    boolean backbtn=false;
    public void onBackPressed(){

           // super.onBackPressed();
        if (backbtn){
            super.onBackPressed();
            overridePendingTransition(R.anim.fade_in_anim, R.anim.fade_out_anim);

        }else {
            backbtn = true;
            Toast.makeText(this, "press again to exit", Toast.LENGTH_SHORT).show();
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backbtn = false;
                }
            }, 2000);
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_ENTER) {
             event.getKeyCode();

        }
        return super.onKeyDown(keyCode, event);
    }

    public  void showData(){

        list.clear();
        Cursor c=data.retriveCust();

        while(c.moveToNext()) {

            list.add(new GetUsers(c.getString(0),
                    c.getString(1).toUpperCase(),
                    c.getString(2),
                    c.getString(3)));



        }
        getUsersAdapter=new GetUsersAdapter(getApplicationContext(),list,this);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(getUsersAdapter);
        getUsersAdapter.notifyDataSetChanged();



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        newText=newText.toLowerCase();
        ArrayList<GetUsers> newList=new ArrayList<>();

        for(GetUsers getUsers: list){

            String name=getUsers.getCust_name().toLowerCase();

            if (name.contains(newText)){

                newList.add(getUsers);
            }

        }
        getUsersAdapter.setFilter(newList);
        return true;
    }


    @Override
    public void onNoteclick(int position) {

        Intent i=new Intent(Dashboard.this,Users.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in_anim,R.anim.fade_out_anim);

    }
}
