package com.example.trucksharing;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AllOrderAdapter.OnItemClickListener{

    public Toolbar toolbar;
    public FloatingActionButton fab_add;
    public RecyclerView rv_all_order;
    public AllOrderAdapter allOrderAdapter;


    private DatabaseHelper dbHelper;
    public String username = "";
    public List<ItemBean> itemBeanList;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        toolbar = findViewById(R.id.toolbar);
        fab_add = findViewById(R.id.fab_add);
        rv_all_order = findViewById(R.id.rv_all_order_home);


        setSupportActionBar(toolbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_all_order.setLayoutManager(linearLayoutManager);
        username = getIntent().getStringExtra("username");
        itemBeanList = new ArrayList<>();
        initData();

        allOrderAdapter = new AllOrderAdapter(itemBeanList,this);
        allOrderAdapter.addItemClickListener(this);
        rv_all_order.setAdapter(allOrderAdapter);
        rv_all_order.setItemAnimator(new DefaultItemAnimator());

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,AddOrderActivity.class);
                i.putExtra("username",username);
                startActivity(i);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent i = new Intent(HomeActivity.this,HomeActivity.class);
                i.putExtra("username",username);
                startActivity(i);
                break;
            case R.id.account:
                Intent j = new Intent(HomeActivity.this,AccountActivity.class);
                j.putExtra("username",username);
                startActivity(j);
                break;
            case R.id.myOrder:
                for(int k =0;k<itemBeanList.size();k++){
                    if(!itemBeanList.get(k).sender.equals(username)){
                        itemBeanList.remove(k);
                        allOrderAdapter.notifyDataSetChanged();
                    }
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("Range")
    public void initData(){
        dbHelper = new DatabaseHelper(this,"LocalDatabase.db",null,2);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("OrderList", null, "sender=? or isPublic=?", new String[]{username,"true"}, null, null, null);
        if(cursor.moveToFirst()){
            do{
                String u_id = cursor.getString(cursor.getColumnIndex("u_id"));
                String pickUpDate = cursor.getString(cursor.getColumnIndex("pickUpDate"));
                String pickUpTime = cursor.getString(cursor.getColumnIndex("pickUpTime"));
                String pickUpLocation = cursor.getString(cursor.getColumnIndex("pickUpLocation"));
                String vehicleType = cursor.getString(cursor.getColumnIndex("vehicleType"));
                String weight = cursor.getString(cursor.getColumnIndex("Weight"));
                String width = cursor.getString(cursor.getColumnIndex("width"));
                String length = cursor.getString(cursor.getColumnIndex("length"));
                String sender = cursor.getString(cursor.getColumnIndex("sender"));
                String height = cursor.getString(cursor.getColumnIndex("height"));
                String receiver = cursor.getString(cursor.getColumnIndex("receiver"));
                String goodType = cursor.getString(cursor.getColumnIndex("goodType"));
                ItemBean bean = new ItemBean();
                bean.u_id = u_id;
                bean.pickUpDetailDate = pickUpDate;
                bean.sender = sender;
                bean.receiver = receiver;
                bean.Weight = weight;
                bean.width = width;
                bean.height = height;
                bean.length = length;
                bean.pickUpDetailTime = pickUpTime;
                bean.goodType = goodType;

                bean.goodDetail = "kg = "+weight+",length width height's cm = :"+length+","+width+","+height;
                bean.vehicleType = vehicleType;
                itemBeanList.add(bean);
            }while(cursor.moveToNext());
        }


    }

    @Override
    public void onItemClick(View view, int position) {
        Intent i = new Intent(HomeActivity.this,OrderDetailActivity.class);
        i.putExtra("sender",username);
        i.putExtra("receiver",itemBeanList.get(position).receiver);
        i.putExtra("pickUpTime",itemBeanList.get(position).pickUpDetailTime);
        i.putExtra("weight",itemBeanList.get(position).Weight);
        i.putExtra("width",itemBeanList.get(position).width);
        i.putExtra("height",itemBeanList.get(position).height);
        i.putExtra("Length",itemBeanList.get(position).length);
        i.putExtra("goodType",itemBeanList.get(position).goodType);
        startActivity(i);
    }

}