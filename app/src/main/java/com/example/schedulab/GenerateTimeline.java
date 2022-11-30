package com.example.schedulab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.schedulab.databinding.ActivityGenerateTimelineBinding;

public class GenerateTimeline extends AppCompatActivity {
    private ActivityGenerateTimelineBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGenerateTimelineBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_generate_timeline);
        binding.backbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GenerateTimeline.this, FirstFragment.class));
            }
        });
    }
}