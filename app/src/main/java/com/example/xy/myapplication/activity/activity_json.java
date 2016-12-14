package com.example.xy.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.xy.myapplication.R;
import com.example.xy.myapplication.bean.JsonUser;
import com.example.xy.myapplication.bean.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by xy on 2016/11/14.
 */
public class activity_json extends AppCompatActivity {

    TextView textjson;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        textjson= (TextView) findViewById(R.id.txt_json);
        String str_data="{resultCode:200,data:[{name:bobo,age:12,address:遂宁},{name:huahua,age:15,address:广安}," +
                "{name:xixi,age:18,address:达州}]}";//数组
        ArrayList<User> arrayList=new ArrayList<>();
        JsonUser jsonUser=new JsonUser();
        try {
            JSONObject jsonObject=new JSONObject(str_data);
            jsonObject.getString("resultCode");
            Log.e("aaa",jsonObject.getString("resultCode"));
            User user=new User();
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            for (int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                String str_name = jsonObject1.getString("name");
                String str_age = jsonObject1.getString("age");
                String str_address = jsonObject1.getString("address");
                user.setName(str_name);
                user.setAge(str_age);
                user.setAddress(str_address);
                arrayList.add(user);
            }
            jsonUser.setUserList(arrayList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        try {
//            JSONObject jsonObject=new JSONObject(str_data);
//            String str_name=jsonObject.getString("name");
//            String str_age=jsonObject.getString("age");
//            String str_address=jsonObject.getString("address");
//            textjson.setText(str_name+"!!！"+str_age+"!!!"+str_address);
//            Log.e("sss",str_data);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }
}
