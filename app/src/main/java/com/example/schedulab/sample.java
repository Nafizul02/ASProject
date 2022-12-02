package com.example.schedulab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class sample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);


        Intent i=getIntent();
        Bundle bundle = i.getExtras();
        String s = bundle.getString("input");
    }
}