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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 EditText loginid,login_pw;
 RadioGroup radiostdgroup;
 RadioButton std,admin;
 TextView t;

 ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar= getSupportActionBar() ;
      actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#af0000")));
        loginid=(EditText)findViewById(R.id.loginid_etx);
        login_pw=(EditText)findViewById(R.id.login_pw_etx);
        std=(RadioButton)findViewById(R.id.std_rdb);
        admin=(RadioButton)findViewById(R.id.admin_rdb);
        radiostdgroup=(RadioGroup)findViewById(R.id.radiogroup);
        t=(TextView)findViewById(R.id.temp);
    }

    public void login_clicked(View view)
    {
        int id=radiostdgroup.getCheckedRadioButtonId();
        String u=loginid.getText().toString().trim();
        String pw=login_pw.getText().toString().trim();

        if (id==std.getId())
        {   try {
            @SuppressLint("WrongConstant")
            SQLiteDatabase db = openOrCreateDatabase("tech_explica.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            String sql = "SELECT * FROM non_enrolled_students";
            Cursor c =db.rawQuery(sql,null);
            c.moveToFirst();
            String uid="";
            String pwd="";
            while (!c.isAfterLast()){

                uid=c.getString(1);
                pwd=c.getString(3);


                if (uid.equals(u) && pwd.equals(pw))
                {   Intent intent=new Intent(getApplicationContext(),course_enrollment.class);
                    intent.putExtra("userid",uid);
                    Toast.makeText(this, "logged In", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    break;

                }

                c.moveToNext();
            }

               }
               catch (Exception ex){
                   Toast.makeText(this, "error"+ex, Toast.LENGTH_LONG).show();
               }

        }else if (id==admin.getId())
           {
                String admin_name="admin";
                String admin_pwd="admin";
                if (admin_name.equals(u) && admin_pwd.equals(pw)){


                    Toast.makeText(this, "logged in as Admin", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),Admin_panel.class);
                    startActivity(intent);
                }
            }

    }


    public void register_clicked(View view)
    {
        Intent intent=new Intent(this,Register_activity.class);
        startActivity(intent);
        Toast.makeText(this, "fill up the details", Toast.LENGTH_LONG).show();
    }
}
