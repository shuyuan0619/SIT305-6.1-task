package com.example.trucksharing;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddOrderActivity extends AppCompatActivity {

    public String username;
    public Button next_add_Button;
    public EditText receiver_name_add_EditText;
    public EditText edit_pick_up_date_add;
    public EditText edit_pick_up_time_add;
    public EditText edit_pick_up_location_add;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        next_add_Button = findViewById(R.id.next_add_Button);
        receiver_name_add_EditText = findViewById(R.id.receiver_name_add_EditText);
        edit_pick_up_date_add = findViewById(R.id.edit_pick_up_date_add);
        edit_pick_up_time_add = findViewById(R.id.edit_pick_up_time_add);
        edit_pick_up_location_add = findViewById(R.id.edit_pick_up_location_add);
        username = getIntent().getStringExtra("username");

        next_add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddOrderActivity.this,AddOrderNextActivity.class);
                i.putExtra("username",username);
                i.putExtra("receiver",receiver_name_add_EditText.getText().toString());
                i.putExtra("date",edit_pick_up_date_add.getText().toString());
                i.putExtra("time",edit_pick_up_time_add.getText().toString());
                i.putExtra("location",edit_pick_up_location_add.getText().toString());
                startActivity(i);
            }
        });

    }

}