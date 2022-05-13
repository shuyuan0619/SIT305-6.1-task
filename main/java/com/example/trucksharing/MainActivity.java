package com.example.trucksharing;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button btn_login;
    public Button btn_singUp;
    public EditText username_EditText;
    public EditText pass_EditText;

    private DatabaseHelper dbHelper;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        btn_login = findViewById(R.id.btn_login);
        btn_singUp = findViewById(R.id.btn_signUp);
        username_EditText = findViewById(R.id.username_EditText);
        pass_EditText = findViewById(R.id.pass_EditText);
        dbHelper = new DatabaseHelper(this,"LocalDatabase.db",null,2);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            @SuppressLint("Range")
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();


                Cursor cursor = db.query("Person",null,"username=?", new String[]{username_EditText.getText().toString()},
                        null,null,null);

                if(cursor.moveToFirst()){
                    String password =   cursor.getString(cursor.getColumnIndex("password"));
                    if(password.equals(pass_EditText.getText().toString())){
                        Intent i = new Intent(MainActivity.this,HomeActivity.class);
                        i.putExtra("username",username_EditText.getText().toString());
                        startActivity(i);
                    }else{
                        Toast.makeText(MainActivity.this,"password not right",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else{
                    Toast.makeText(MainActivity.this,"please register first",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });

        btn_singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
    }
}