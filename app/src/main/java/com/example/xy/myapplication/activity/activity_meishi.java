package com.example.xy.myapplication.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xy.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xy on 2016/11/29.
 */
public class activity_meishi extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editText;
    TextView textView;
    Button button;
    Handler handler;{
        handler=new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what)
                {
                    case 0:
                        break;
                }
            }
        };
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meishi);
        editText= (EditText) findViewById(R.id.edit_meishi);
        textView= (TextView) findViewById(R.id.texts);
        button= (Button) findViewById(R.id.btn_querys);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String meiData = new GetMeiData().getMeiData(editText.getText().toString());
                        Log.e("activity_meishi", meiData);
                        handler.sendEmptyMessage(0);
//                        try {
//                            JSONObject jsonObject=new JSONObject(meiData);
//                        } catch (JSONException e) {
//                            e.printStackTrace();d
//                        }
                    }
                }).start();
            }
        });
    }
}
