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
import android.widget.Toast;

public class add_new_course extends AppCompatActivity {
    EditText cn,du,fn,fe;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course);
        actionBar= getSupportActionBar() ;
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#af0000")));
        cn=(EditText)findViewById(R.id.cname_etx);
        du=(EditText)findViewById(R.id.duration_etx);
        fn=(EditText)findViewById(R.id.fname_etx);
        fe=(EditText)findViewById(R.id.fee_etx);
    }

    public void course_added(View view) {
        String c=cn.getText().toString().trim();
        int d=Integer.parseInt(du.getText().toString().trim());
        String f=fn.getText().toString().trim();
        int ff=Integer.parseInt(fe.getText().toString().trim());
        try {
            @SuppressLint("WrongConstant")
            SQLiteDatabase db = openOrCreateDatabase("tech_explica.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            String sql = "CREATE TABLE IF NOT EXISTS courses" +
                    "(cname TEXT PRIMARY KEY NOT NULL,duration INTEGER NOT NULL,faculty TEXT,fees INTEGER NOT NULL)";

            db.execSQL(sql);
            ContentValues values = new ContentValues();
            values.put("cname", c);
            values.put("duration",d);
            values.put("faculty",f);
            values.put("fees",ff);
            long re = db.insert("courses", null, values);
            if (re>0)

            {
                Toast.makeText(this, "Course added succesfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),Admin_panel.class);
                startActivity(intent);
            }

        }catch ( Exception ex)
        {
            Toast.makeText(this, "error "+ex, Toast.LENGTH_LONG).show();
        }
}

    public void course_updated(View view) {

        String c=cn.getText().toString().trim();
        int d=Integer.parseInt(du.getText().toString().trim());
        String f=fn.getText().toString().trim();
        int ff=Integer.parseInt(fe.getText().toString().trim());
        try {
            @SuppressLint("WrongConstant")
            SQLiteDatabase db=openOrCreateDatabase("tech_explica.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            ContentValues values = new ContentValues();
            values.put("cname",c);
            values.put("duration",d);
            values.put("faculty",f);
            values.put("fees",ff);
            long re = db.update("courses",values,"cname="+"'"+c+"'",null);
            if (re>0)

            {
                Toast.makeText(this, "Course updated successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),Admin_panel.class);
                startActivity(intent);
            }

        }catch ( Exception ex)
        {
            Toast.makeText(this, "error "+ex, Toast.LENGTH_LONG).show();
        }
    }

    public void course_deleted(View view) {
        try {
            String c=cn.getText().toString().trim();

            @SuppressLint("WrongConstant")
            SQLiteDatabase db = openOrCreateDatabase("tech_explica.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            int re = db.delete("courses", "cname="+"'"+c+"'", null);
            if (re > 0) {
                Toast.makeText(this, c+" course deleted  successfully", Toast.LENGTH_SHORT).show();
            }
        }

        catch(Exception ex)
        {
            Toast.makeText(this, "Error"+ex, Toast.LENGTH_SHORT).show();
        }
    }
}