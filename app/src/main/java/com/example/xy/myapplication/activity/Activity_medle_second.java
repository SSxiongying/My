package com.example.xy.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xy.myapplication.R;

/**
 * Created by xy on 2016/12/8.
 */
public class Activity_medle_second extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modle_second);
        TextView text_second= (TextView) findViewById(R.id.text_second);
        text_second.setText(this.toString());
        Button btn_second= (Button) findViewById(R.id.btn_second);
        btn_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Activity_medle_second.this,Activity_modle.class);
                startActivity(intent);
            }
        });
    }
}
