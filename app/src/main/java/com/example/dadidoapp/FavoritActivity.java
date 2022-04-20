package com.example.dadidoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class FavoritActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);

        //below these is for search bar
        MenuItem item = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Type what Item you want to search");
        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(FavoritActivity.this, SearchSuggestionActivity.class);
                intent.putExtra("SearchItem",s);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });//these are second method of search (don't delete please in case daniel use this)
//        item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
//            @Override
//            public boolean onMenuItemActionExpand(MenuItem menuItem) {
//
//                return true;
//            }
//
//            @Override
//            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
//
//                return true;
//            }
//        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mProfile:
                Intent intent = new Intent(FavoritActivity.this, ProfilActivity.class);
                startActivity(intent);
                return true;
            case R.id.mLogout:
                sharedPreferences=getSharedPreferences("LoginPrefs", MODE_PRIVATE);
                editor=sharedPreferences.edit();
                editor.putString("username","");
                editor.putString("password","");
                editor.putString("remember","");
                editor.commit();
                Intent intent2 = new Intent(FavoritActivity.this, MainActivity.class);
                startActivity(intent2);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        ActionBar actionBar = getSupportActionBar();
        Drawable dr = ContextCompat.getDrawable(FavoritActivity.this,R.drawable.daradi_logo2);
        Bitmap logo = ((BitmapDrawable) dr).getBitmap();
        // Scale it to 50 x 50
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(logo, 40, 30, true));
        actionBar.setHomeAsUpIndicator(d);// set drawable icon
        actionBar.setDisplayHomeAsUpEnabled(true);

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mHome:
                        Intent intent = new Intent(FavoritActivity.this, HomeActivity.class);
                        startActivity(intent); finish();
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;
                    case R.id.mFavorite: //startActivity(new Intent(FavoritActivity.this, FavoritActivity.class)); finish();
                        break;
                }
                return true;
            }
        });
    }


}