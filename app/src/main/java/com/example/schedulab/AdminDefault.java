package com.example.schedulab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.example.schedulab.databinding.ActivityAdminDefaultBinding;
import com.example.schedulab.databinding.ActivityStudentDefaultBinding;
import com.example.schedulab.AdminDrawerBase;
import com.google.android.material.navigation.NavigationView;

public class AdminDefault extends AdminDrawerBase {
    ActivityAdminDefaultBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminDefaultBinding.inflate(getLayoutInflater());
        allocateActivityTitle("Admin Dashboard");
        setContentView(binding.getRoot());
    }
}