package com.example.schedulab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class AdminDefault extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_default);
        Toolbar toolbar = findViewById(R.id.mainToolBar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.admin_drawer_layout);
        navigationView = findViewById(R.id.nav_admin_view);
        //
        navigationView.bringToFront();
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_Open, R.string.close_menu);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_admin_all:
                        Log.i("Admin_menu_drawer_tag", "All courses clicked");
                        startActivity(new Intent(AdminDefault.this, AllCourses.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_add:
                        Log.i("Admin_menu_drawer_tag", "Add clicked");
                        startActivity(new Intent(AdminDefault.this, AdminAdd.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_edit:
                        Log.i("Admin_menu_drawer_tag", "Edit clicked");
                        startActivity(new Intent(AdminDefault.this, AdminEdit.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_delete:
                        Log.i("Admin_menu_drawer_tag", "Delete clicked");
                        startActivity(new Intent(AdminDefault.this, AdminDelete.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_logout:
                        Log.i("Admin_menu_drawer_tag", "Logout clicked");
                        startActivity(new Intent(AdminDefault.this, AdminLogin.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (getSupportActionBar() != null) {
            androidx.appcompat.widget.Toolbar tbar = findViewById(R.id.mainToolBar);
            setSupportActionBar(tbar);
            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_Open, R.string.close_menu);

            actionBarDrawerToggle.syncState();
        }
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}