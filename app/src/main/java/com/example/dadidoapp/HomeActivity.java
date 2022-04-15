package com.example.dadidoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button mostExpensive_btn;
    Button new_btn;
    Button trending_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mostExpensive_btn = (Button)findViewById(R.id.button_mostExpensive);
        new_btn = (Button)findViewById(R.id.button_New);
        trending_btn = (Button)findViewById(R.id.button_Trending);

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
    }
}