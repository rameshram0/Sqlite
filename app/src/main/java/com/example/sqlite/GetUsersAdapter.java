package com.example.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sqlite.controls.ColorGenerator;
import com.example.sqlite.controls.TextDrawable;

import java.util.ArrayList;
import java.util.List;

public class GetUsersAdapter extends RecyclerView.Adapter<GetUsersAdapter.ViewHolder> {

    private TextDrawable.IBuilder builder = null;
    private ColorGenerator generator = ColorGenerator.MATERIAL;

    private Context context;
    private List<GetUsers> list;
    private  OnNoteListener onNoteListener;
   // SwipeRefreshLayout swipeRefreshLayout;

    public GetUsersAdapter(Context context, List<GetUsers> list, OnNoteListener onNoteListener) {
        this.context = context;
        this.list = list;
        this.onNoteListener=onNoteListener;
       // this.swipeRefreshLayout=swipeRefreshLayout;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userlist, parent, false);
        builder = TextDrawable.builder().beginConfig().toUpperCase().textColor(Color.WHITE).endConfig().round();
        return new ViewHolder(v,onNoteListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GetUsers gu = list.get(position);

        int color = generator.getColor(gu.getCust_name());
        TextDrawable ic1 = builder.build(gu.getCust_name().substring(0, 1), color);
        holder.imageView.setImageDrawable(ic1);

      //  holder.id.setText(gu.getId());
        holder.name.setText(gu.getCust_name());
        holder.mobile.setText(gu.getCust_mbl());
        holder.userdate.setText(gu.getCurrent_date());

       /* swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showUpdate();

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView id,name,mobile,userdate;
        OnNoteListener onNoteListener;
        ImageView imageView;


        public ViewHolder(final View itemView, OnNoteListener onNoteListener) {
            super(itemView);


           // id=itemView.findViewById(R.id.userid);
            name = itemView.findViewById(R.id.username);

            mobile = itemView.findViewById(R.id.usermbl);
            userdate = itemView.findViewById(R.id.userdate);
            imageView=itemView.findViewById(R.id.img_cls);



            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {




            onNoteListener.onNoteclick(getAdapterPosition());
           /* switch (v.getId()) {
               *//* case R.id.like:
                    onNoteListener.onLike(this.getLayoutPosition());
                    break;*//*
                case R.id.comment:
                    onNoteListener.onComment(this.getLayoutPosition());
                    break;
                default:
                    break;
            }
*/
        }
    }
    public  interface OnNoteListener{
        void onNoteclick(int position);
       // void comment(View v, int position);
       // void onLike(int p);
        //void onComment(int p);
    }

    public void setFilter(ArrayList<GetUsers> newList){

        list=new ArrayList<>();
        list.addAll(newList);
        notifyDataSetChanged();
    }

   /* public  void showUpdate(){

        list.clear();
        DB_Tables data=new DB_Tables(context);
       // data.openDB();

        Cursor c=data.retriveCust();

        while(c.moveToNext()) {



            list.add(new GetUsers(c.getString(0),
                    c.getString(1).toUpperCase(),
                    c.getString(2)));


        }
       data.close();
        swipeRefreshLayout.setRefreshing(false);
        this.notifyDataSetChanged();*/





}
