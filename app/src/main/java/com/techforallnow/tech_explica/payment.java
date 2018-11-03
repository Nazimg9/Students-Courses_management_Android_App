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
import android.widget.Toast;

public class payment extends AppCompatActivity {
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        actionBar= getSupportActionBar() ;
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#af0000")));


    }

    public void paid(View view) {
        try {
            Intent intent = getIntent();
            String uid = intent.getStringExtra("userid");
            String cr = intent.getStringExtra("cn");
            String n="Nazim";
           // Toast.makeText(this, uid+" "+cr, Toast.LENGTH_SHORT).show();

            @SuppressLint("WrongConstant")
            SQLiteDatabase db = openOrCreateDatabase("tech_explica.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
           /* String sql = "CREATE TABLE IF NOT EXISTS non_enrolled_students" +
                    "(name TEXT NOT NULL,email TEXT PRIMARY KEY NOT NULL,mobile_no INTEGER,password TEXT NOT NULL,enrollcourse TEXT)";

            db.execSQL(sql);*/
            ContentValues values = new ContentValues();
            values.put("enrollcourse",cr);

            long re = db.update("non_enrolled_students", values, "email="+"'"+uid+"'", null);
            if (re > 0)

            {
                Toast.makeText(this, "Course Purchased successfully", Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(getApplicationContext(), course_enrollment.class);
                startActivity(intent3);
            }


        }catch (Exception ex)
        {
            Toast.makeText(this, "error"+ex, Toast.LENGTH_SHORT).show();
        }
    }

}
