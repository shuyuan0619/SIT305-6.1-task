package com.example.trucksharing;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {

    public String sender="";
    public String receiver="";
    public String pickUpTime="";
    public String goodType="";

    public String weight = "";
    public String width = "";
    public String height = "";
    public String Length = "";

    public TextView text_sender_detail;
    public TextView pick_up_time_detail_TextView;
    public TextView receiver_detail_TextView;
    public TextView text_weight;
    public TextView text_good_type;
    public TextView text_width;
    public TextView text_height;
    public TextView text_length;

    public Button btn_call_driver;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        sender = getIntent().getStringExtra("sender");
        receiver = getIntent().getStringExtra("receiver");
        pickUpTime = getIntent().getStringExtra("pickUpTime");
        weight = getIntent().getStringExtra("weight");
        width = getIntent().getStringExtra("width");
        height = getIntent().getStringExtra("height");
        Length = getIntent().getStringExtra("Length");
        goodType = getIntent().getStringExtra("goodType");

        text_sender_detail = findViewById(R.id.text_sender_detail);
        pick_up_time_detail_TextView = findViewById(R.id.pick_up_time_detail_TextView);
        receiver_detail_TextView = findViewById(R.id.receiver_detail_TextView);
        text_weight = findViewById(R.id.text_weight);
        text_good_type = findViewById(R.id.text_good_type);
        text_width = findViewById(R.id.text_width);
        text_height = findViewById(R.id.text_height);
        text_length = findViewById(R.id.text_length);
        btn_call_driver = findViewById(R.id.btn_call_driver);

        text_sender_detail.setText("From Sender:"+sender);
        pick_up_time_detail_TextView.setText("Pick up time:"+pickUpTime);
        receiver_detail_TextView.setText("To Receiver:"+receiver);
        text_weight.setText("Weight:"+weight);
        text_good_type.setText("Good Type:"+goodType);
        text_width.setText("Width:"+width);
        text_height.setText("Height:"+height);
        text_length.setText("Length:"+Length);

        btn_call_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderDetailActivity.this,HomeActivity.class);
                i.putExtra("username",sender);
                startActivity(i);
            }
        });


    }
}