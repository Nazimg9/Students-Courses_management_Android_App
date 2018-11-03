package com.techforallnow.tech_explica;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register_activity extends AppCompatActivity {
EditText n_etx,e_etx,mb_etx,pw_etx;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);
        actionBar= getSupportActionBar() ;
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#af0000")));
        n_etx=(EditText)findViewById(R.id.name_etx);
        e_etx=(EditText)findViewById(R.id.email_etx);
        mb_etx=(EditText)findViewById(R.id.mob_etx);
        pw_etx=(EditText)findViewById(R.id.pwd_etx);
    }

    public void submit_clicked(View view) {
       try {
           String n = n_etx.getText().toString().trim();
           String e = e_etx.getText().toString().trim();
           String mb=mb_etx.getText().toString().trim();
           String pw = pw_etx.getText().toString().trim();

           @SuppressLint("WrongConstant")
           SQLiteDatabase db = openOrCreateDatabase("tech_explica.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
           String sql = "CREATE TABLE IF NOT EXISTS non_enrolled_students" +
                   "(name TEXT NOT NULL,email TEXT PRIMARY KEY NOT NULL,mobile_no INTEGER,password TEXT NOT NULL,enrollcourse TEXT)";

           db.execSQL(sql);
           ContentValues values = new ContentValues();
           values.put("name", n);
           values.put("email", e);
           values.put("mobile_no", mb);
           values.put("password", pw);
           long re = db.insert("non_enrolled_students", null, values);
           if (re>0)

           {
               Toast.makeText(this, "Registered succesfully", Toast.LENGTH_SHORT).show();
           }


           Intent intent = new Intent(this, welcome_registered.class);
           intent.putExtra("userid",e);
           intent.putExtra("password",pw);
           startActivity(intent);
       }catch ( Exception ex)
        {
            Toast.makeText(this, "error "+ex, Toast.LENGTH_LONG).show();
        }

    }
}
