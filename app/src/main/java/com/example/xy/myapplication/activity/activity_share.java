package com.example.xy.myapplication.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.xy.myapplication.R;

/**
 * Created by xy on 2016/11/13.
 */
public class activity_share extends AppCompatActivity {

    EditText user=null;
    EditText password=null;
    ImageButton iamgebtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreferences);
        user= (EditText) findViewById(R.id.edit_user);
        password= (EditText) findViewById(R.id.edit_pwd);
        iamgebtn= (ImageButton) findViewById(R.id.image_btn);
        initView();
        iamgebtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()==MotionEvent.ACTION_DOWN)
                {
                    view.setBackgroundResource(R.drawable.bilibili);
                    SharedPreferences userInfo=getSharedPreferences("userinfo",0);
                    userInfo.edit().putString("name",user.getText().toString()).commit();
                    userInfo.edit().putString("password",password.getText().toString()).commit();
                } else if (motionEvent.getAction()==MotionEvent.ACTION_UP) {

                    view.setBackgroundResource(R.drawable.shape_btn);
                }
                return false;
            }
        });
    }
    public void initView()
    {
        SharedPreferences userInfo=getSharedPreferences("userinfo",0);
        String username=userInfo.getString("name","");
        String passwords=userInfo.getString("password","");
        user.setText(username);
        password.setText(passwords);
    }
}
