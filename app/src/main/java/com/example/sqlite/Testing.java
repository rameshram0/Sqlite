package com.example.sqlite;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class Testing extends Fragment {

    EditText cname, cmbl;
    Button save, v;
    DatabaseHelper db;
    DB_Tables data;

    String cn, cm, date;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_testing, container, false);

        cname = view.findViewById(R.id.custname);
        cmbl = view.findViewById(R.id.custmbl);
        save = (Button) view.findViewById(R.id.add);


        db = new DatabaseHelper(getActivity());
        data = new DB_Tables(getActivity());
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

                } else {
                    Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
                }


            }

        });


        return view;

    }


    private void getSuccess(String text) {

        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Inserted!")
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


    private void getDate() {

        long s = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date(s);
        date = sdf.format(d);
        Log.d("time", "time: " + date);
    }



}
