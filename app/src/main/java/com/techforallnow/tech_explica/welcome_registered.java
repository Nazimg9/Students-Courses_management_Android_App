package com.techforallnow.tech_explica;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class welcome_registered extends AppCompatActivity {
    TextView t1,t2;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_registered);
        actionBar= getSupportActionBar() ;
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#af0000")));
        t1=(TextView) findViewById(R.id.your_userid);
        t2=(TextView) findViewById(R.id.your_password);
        Intent intent=getIntent();
        String u=intent.getStringExtra("userid");
        String pw=intent.getStringExtra("password");
        t1.setText(u);
        t2.setText(pw);



    }


    public void done_clicked(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Login ", Toast.LENGTH_LONG).show();
    }
}
