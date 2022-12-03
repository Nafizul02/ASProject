package com.example.schedulab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class StudentDefault extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_default);

        drawerLayout = findViewById(R.id.student_drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        actionBarDrawerToggle =  new ActionBarDrawerToggle( this, drawerLayout, R.string.menu_Open,R.string.close_menu);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.nav_courses:
                        Log.i("Menu_drawer_tag", "Courses clicked");                ///////////////////
                        startActivity(new Intent(StudentDefault.this, AddCourse.class));///////////CHANGE////////////
                        drawerLayout.closeDrawer(GravityCompat.START);                       ///////////////////
                        break;
                    case R.id.nav_add:
                        Log.i("Menu_drawer_tag", "Add clicked");
                        startActivity(new Intent(StudentDefault.this, AddCourse.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_req:
                        Log.i("Menu_drawer_tag", "Request clicked");
                        startActivity(new Intent(StudentDefault.this, RequestTimeline.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_logout:
                        Log.i("Menu_drawer_tag", "Logout clicked");
                        startActivity(new Intent(StudentDefault.this, MainActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });
    }
}