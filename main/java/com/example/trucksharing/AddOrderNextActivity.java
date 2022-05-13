package com.example.trucksharing;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.UUID;

public class AddOrderNextActivity extends AppCompatActivity {

    public String username = "";
    public String receiver = "";
    public String date = "";
    public String time = "";
    public String location = "";
    public String goodType = "";
    public String vehicleType = "";

    public Button btn_create_order;
    public RadioGroup radioGroup_foodType;
    public RadioGroup radioGroup_vehicleType;

    public RadioButton btn_fumiture;
    public RadioButton btn_dry_food;
    public RadioButton btn_food;
    public RadioButton btn_building_material;
    public RadioButton btn_other_good;

    public RadioButton btn_truck;
    public RadioButton btn_van;
    public RadioButton btn_refrigerated_truck;
    public RadioButton btn_mini_truck;
    public RadioButton btn_other_vehicle;

    public EditText edit_weight_add_next;
    public EditText edit_width_add_next;
    public EditText edit_length_add_next;
    public EditText edit_height_add_next;

    public EditText editText2;
    public EditText editText3;

    private DatabaseHelper dbHelper;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order_next);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        username = getIntent().getStringExtra("username");
        receiver = getIntent().getStringExtra("receiver");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        location = getIntent().getStringExtra("location");


        btn_create_order = findViewById(R.id.btn_create_order);
        radioGroup_foodType = findViewById(R.id.radioGroup);
        radioGroup_vehicleType = findViewById(R.id.radioGroup2);

        btn_fumiture = findViewById(R.id.btn_fumiture);
        btn_dry_food = findViewById(R.id.btn_dry_food);
        btn_food = findViewById(R.id.btn_food);
        btn_building_material = findViewById(R.id.btn_building_material);
        btn_other_good = findViewById(R.id.btn_other_good);

        btn_truck = findViewById(R.id.btn_truck);
        btn_van = findViewById(R.id.btn_van);
        btn_refrigerated_truck = findViewById(R.id.btn_refrigerated_truck);
        btn_mini_truck = findViewById(R.id.btn_mini_truck);
        btn_other_vehicle = findViewById(R.id.btn_other_vehicle);

        edit_weight_add_next = findViewById(R.id.edit_weight_add_next);
        edit_width_add_next = findViewById(R.id.edit_width_add_next);
        edit_length_add_next = findViewById(R.id.edit_length_add_next);
        edit_height_add_next = findViewById(R.id.edit_height_add_next);

        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);

        dbHelper = new DatabaseHelper(this,"LocalDatabase.db",null,2);


        radioGroup_foodType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == btn_fumiture.getId()){
                    goodType = btn_fumiture.getText().toString();
                }
                if(i == btn_dry_food.getId()){
                    goodType = btn_dry_food.getText().toString();
                }
                if(i == btn_food.getId()){
                    goodType = btn_food.getText().toString();
                }
                if(i == btn_building_material.getId()){
                    goodType = btn_building_material.getText().toString();
                }
                if(i == btn_other_good.getId()){
                    goodType = editText2.getText().toString();
                }

            }
        });

        radioGroup_vehicleType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == btn_truck.getId()){
                    vehicleType = btn_truck.getText().toString();
                }
                if(i == btn_van.getId()){
                    vehicleType = btn_van.getText().toString();
                }
                if(i == btn_refrigerated_truck.getId()){
                    vehicleType = btn_refrigerated_truck.getText().toString();
                }
                if(i == btn_mini_truck.getId()){
                    vehicleType = btn_mini_truck.getText().toString();
                }
                if(i == btn_other_vehicle.getId()){
                    vehicleType = editText3.getText().toString();
                }

            }
        });

        btn_create_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                String u_id = UUID.randomUUID().toString();
                contentValues.put("u_id",u_id);
                contentValues.put("sender",username);
                contentValues.put("receiver",receiver);
                contentValues.put("pickUpDate",date);
                contentValues.put("pickUpTime",time);
                contentValues.put("pickUpLocation",location);
                contentValues.put("goodType",goodType);
                contentValues.put("weight",edit_weight_add_next.getText().toString());
                contentValues.put("width",edit_width_add_next.getText().toString());
                contentValues.put("length",edit_length_add_next.getText().toString());
                contentValues.put("height",edit_height_add_next.getText().toString());
                contentValues.put("vehicleType",vehicleType);
                contentValues.put("isPublic","false");
                db.insert("OrderList",null,contentValues);
                Intent i = new Intent(AddOrderNextActivity.this,HomeActivity.class);
                i.putExtra("username",username);
                startActivity(i);

            }
        });
    }
}