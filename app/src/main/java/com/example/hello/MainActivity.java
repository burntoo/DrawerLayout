package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LottieAnimationView lottieAnimationView = findViewById(R.id.animationInternet);
        MaterialToolbar toolbar = findViewById(R.id.appBar);
        MaterialToolbar toolbar1 = findViewById(R.id.appBar1);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        NavigationView navigationView1 = findViewById(R.id.navigation_view1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                drawerLayout.closeDrawer(GravityCompat.START);

                if (id == R.id.weather){
                    Intent intent = new Intent(MainActivity.this, Weather.class);
                    startActivity(intent);
                }
                if (id == R.id.view){
                    Intent viewIntent = new Intent(MainActivity.this, ProgrammaticViews.class);
                    startActivity(viewIntent);
                }
                if (id == R.id.home){
                    Intent viewIntent = new Intent(MainActivity.this, Home.class);
                    startActivity(viewIntent);
                }
                if (id == R.id.design1){
                    Intent viewIntent = new Intent(MainActivity.this, Design1.class);
                    startActivity(viewIntent);
                }
                if (id == R.id.design3){
                    Intent viewIntent = new Intent(MainActivity.this, Design3.class);
                    startActivity(viewIntent);
                }
                if (id == R.id.login){
                    Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
                }
                if (id == R.id.logout){
                    Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
    }
}