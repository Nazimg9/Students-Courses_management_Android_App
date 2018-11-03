package com.techforallnow.tech_explica;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Admin_panel extends AppCompatActivity {
    TextView t;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        actionBar= getSupportActionBar() ;
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#af0000")));
        t=(TextView)findViewById(R.id.textbox);
    }

    public void new_course(View view) {
        Intent intent=new Intent(this,add_new_course.class);
        startActivity(intent);

    }

    public void non_enrolled_students(View view) {
        try {
            @SuppressLint("WrongConstant")
            SQLiteDatabase db = openOrCreateDatabase("tech_explica.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            String sql = "SELECT * FROM non_enrolled_students WHERE enrollcourse IS NULL;";
            Cursor c = db.rawQuery(sql, null);
            c.moveToFirst();
            String st = "";
            while (!c.isAfterLast()) {
                st += "\n Student name: " + c.getString(0);
                st += "\n Email : " + c.getString(1);
                st += "\n Mobile no. : " + c.getString(2);

                c.moveToNext();

            }
            t.setText(st);
        }catch (Exception ex)
        {
            Toast.makeText(this, "no record founded"+ex, Toast.LENGTH_SHORT).show();
        }

    }

    public void enrolled_students(View view) {
        try {
            @SuppressLint("WrongConstant")
            SQLiteDatabase db = openOrCreateDatabase("tech_explica.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            String sql = "SELECT * FROM non_enrolled_students WHERE enrollcourse IS NOT NULL";
            Cursor c = db.rawQuery(sql, null);
            c.moveToFirst();
            String st = "";
            while (!c.isAfterLast()) {
                st += "\n Student name: " + c.getString(0);
                st += "\n Email : " + c.getString(1);
                st += "\n Mobile no. : " + c.getString(2);
                st += "\n Enrolled course : " + c.getString(4)+"\n";

                c.moveToNext();

            }
            t.setText(st);
        }catch (Exception ex)
        {
            Toast.makeText(this, "no record founded"+ex, Toast.LENGTH_SHORT).show();
        }

    }


    public void all_courses(View view) {

       try {
           @SuppressLint("WrongConstant")
           SQLiteDatabase db = openOrCreateDatabase("tech_explica.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
           String sql = "SELECT * FROM courses";
           Cursor c = db.rawQuery(sql, null);
           c.moveToFirst();
           String st = "";
           while (!c.isAfterLast()) {
               st += "\n Course name: " + c.getString(0);
               st += "\n Duration: " + c.getString(1) + " weeks";
               st += "\n Faculty : " + c.getString(2);
               st += "\n Course Fee: " + "Rs." + c.getString(3)+"\n";
               c.moveToNext();
           }
           t.setText(st);
       }catch (Exception ex)
       {
           Toast.makeText(this, "no record founded"+ex, Toast.LENGTH_SHORT).show();
       }

    }
}

