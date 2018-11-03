package com.techforallnow.tech_explica;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class course_enrollment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
        ArrayList<String> courses=new ArrayList<String>();
        Spinner spin;
        TextView tc,td,tf,tfe;
    String course_name;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_enrollment);
        actionBar= getSupportActionBar() ;
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#af0000")));

        spin=(Spinner)findViewById(R.id.spin);
        tc=(TextView)findViewById(R.id.tc);
        td=(TextView)findViewById(R.id.td);
        tf=(TextView)findViewById(R.id.tf);
        tfe=(TextView)findViewById(R.id.tfe);
        spin.setOnItemSelectedListener(this);

        try{
            @SuppressLint("WrongConstant")
            SQLiteDatabase db=openOrCreateDatabase("tech_explica.db",SQLiteDatabase.CREATE_IF_NECESSARY,null);
            String sql="SELECT * FROM courses";
            String q="SELECT COUNT(*) FROM courses";

            Cursor c=db.rawQuery(sql,null);
            c.moveToFirst();
            while (!c.isAfterLast())
            {
                courses.add(c.getString(0));
                c.moveToNext();
            }

        }catch (Exception ex){
            Toast.makeText(this, "Error"+ex, Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter aa=new ArrayAdapter(this,android.R.layout.simple_list_item_1,courses);
        aa.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spin.setAdapter(aa);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

      course_name= courses.get(position);
       // Toast.makeText(getApplicationContext(),courses.get(position), Toast.LENGTH_SHORT).show();
       try {
           @SuppressLint("WrongConstant")
           SQLiteDatabase db = openOrCreateDatabase("tech_explica.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
           String sql = "SELECT * FROM courses";
           Cursor c = db.rawQuery(sql, null);
           c.moveToFirst();
           String st = "";
           String cn = "", du = "", fc = "", fe = "";
           while (!c.isAfterLast()) {
               st = c.getString(0);
               if (st.equals(courses.get(position))) {
                   cn = c.getString(0);
                   du = c.getString(1);
                   fc = c.getString(2);
                   fe = c.getString(3);
                   break;
               }

               c.moveToNext();

           }
           tc.setText(":"+cn);
           td.setText(":"+du+" weeks");
           tf.setText(":"+fc);
           tfe.setText(":"+"Rs. "+fe);

       }catch (Exception ex)
       {
           Toast.makeText(this, "not found"+ex, Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void buy_course(View view) {
        Intent intent2=getIntent();
        String uid=intent2.getStringExtra("userid");
        Intent intent3=getIntent();
        String cname_to_send=intent3.getStringExtra("cname");
        Intent intent=new Intent(this,payment.class);
       intent.putExtra("userid",uid);


       intent.putExtra("cn",course_name);

        startActivity(intent);

    }

    public void back_button(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}
