package com.example.schedulab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.example.schedulab.databinding.ActivityStudentDefaultBinding;
import com.google.android.material.navigation.NavigationView;

public class StudentDefault extends DrawerBase {
    ActivityStudentDefaultBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentDefaultBinding.inflate(getLayoutInflater());
        allocateActivityTitle("Student Dashboard");
        setContentView(binding.getRoot());
    }
}