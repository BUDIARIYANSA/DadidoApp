package com.example.dadidoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    Button mostExpensive_btn;
    Button new_btn;
    Button trending_btn;
    FloatingActionButton mCollection;
    Toolbar toolbar;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mProfile:
                Intent intent = new Intent(HomeActivity.this, ProfilActivity.class);
                startActivity(intent);
                return true;
            case R.id.mLogout:
                sharedPreferences=getSharedPreferences("LoginPrefs", MODE_PRIVATE);
                editor=sharedPreferences.edit();
                editor.putString("username","");
                editor.putString("password","");
                editor.commit();
                Intent intent2 = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mostExpensive_btn = (Button)findViewById(R.id.button_mostExpensive);
        new_btn = (Button)findViewById(R.id.button_New);
        trending_btn = (Button)findViewById(R.id.button_Trending);
        mCollection = (FloatingActionButton) findViewById(R.id.mCollection);

        androidx.fragment.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.DisplayFragment, new MostExpensive());
        ft.commit();

        mostExpensive_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.fragment.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.DisplayFragment, new MostExpensive());
                ft.commit();
            }
        });

        new_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.fragment.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.DisplayFragment, new new_page_activity());
                ft.commit();
            }
        });
        //these transaction still crashes
        trending_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.fragment.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.DisplayFragment, new Trending_page_Activity());
                ft.commit();
            }
        });

        mCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mHome: //startActivity(new Intent(HomeActivity.this, HomeActivity.class)); finish();
                        break;
                    case R.id.mFavorite: startActivity(new Intent(HomeActivity.this, FavoritActivity.class)); finish();
                        break;
                }
                return true;
            }
        });



    }
}