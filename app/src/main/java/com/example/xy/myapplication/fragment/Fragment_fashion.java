package com.example.xy.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xy.myapplication.R;

/**
 * Created by xy on 2016/11/11.
 */
public class Fragment_fashion extends Fragment{
    TextView txt_fashion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_fragment_fashion,container,false);
        txt_fashion= (TextView) view.findViewById(R.id.txt_fashion);
        return view;
    }
}
