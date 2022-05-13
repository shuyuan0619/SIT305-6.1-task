package com.example.trucksharing;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;

public class AccountActivity extends AppCompatActivity {

    public ImageView iv_headIcon;
    public TextView full_name_login_TextView;
    public TextView text_username_login_TextView;
    public TextView text_password_login_TextView;
    public TextView text_phone_login_TextView;
    public Button btn_back_Button;
    private DatabaseHelper dbHelper;
    public String username;
    @Override
    @SuppressLint("Range")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        iv_headIcon = findViewById(R.id.iv_headIcon);
        full_name_login_TextView = findViewById(R.id.full_name_login_TextView);
        text_username_login_TextView = findViewById(R.id.text_username_login_TextView);
        text_password_login_TextView = findViewById(R.id.text_password_login_TextView);
        text_phone_login_TextView = findViewById(R.id.text_phone_login_TextView);
        btn_back_Button = findViewById(R.id.btn_back_Button);
        username = getIntent().getStringExtra("username");
        dbHelper = new DatabaseHelper(this,"LocalDatabase.db",null,2);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Person", null, "username=?", new String[]{username}, null, null, null);
        if(cursor.moveToFirst()){
            do{
                String u_id = cursor.getString(cursor.getColumnIndex("u_id"));
                String fullName = cursor.getString(cursor.getColumnIndex("fullName"));
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                String head = cursor.getString(cursor.getColumnIndex("head"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                full_name_login_TextView.setText("fullName : "+fullName);
                text_username_login_TextView.setText("username : "+username);
                text_password_login_TextView.setText("password:"+password);
                text_phone_login_TextView.setText("phone : "+phone);
                Bitmap bitmap = ImageUtil.base64ToImage(head);
                iv_headIcon.setImageBitmap(bitmap);
            }while(cursor.moveToNext());
        }
        btn_back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}