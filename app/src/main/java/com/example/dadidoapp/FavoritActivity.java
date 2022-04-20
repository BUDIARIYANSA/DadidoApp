package com.example.dadidoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.dadidoapp.Adapter.CardFavorite_adapter;
import com.example.dadidoapp.Adapter.cardItem_adapter;
import com.example.dadidoapp.LayoutModel.Card_Favorite_Model;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class FavoritActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private CardFavorite_adapter adapter;
    private ArrayList<Card_Favorite_Model> Card_Favorite_ArrayList;

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
        Drawable dr = ContextCompat.getDrawable(FavoritActivity.this, R.drawable.daradi_logo2);
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
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;
                    case R.id.mFavorite: //startActivity(new Intent(FavoritActivity.this, FavoritActivity.class)); finish();
                        break;
                }
                return true;
            }
        });

        addData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_favorite);

        adapter = new CardFavorite_adapter(Card_Favorite_ArrayList,FavoritActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FavoritActivity.this, LinearLayoutManager.VERTICAL,false);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(DetailItemActivity.this, 2, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

    }

    void addData(){
        Card_Favorite_ArrayList = new ArrayList<>();
        Card_Favorite_ArrayList.add(new Card_Favorite_Model("Gambar 1", "Hizkia Agung Purnama", "https://i.pinimg.com/736x/b9/ae/1c/b9ae1c820c0162c268611941084dd614.jpg"));
        Card_Favorite_ArrayList.add(new Card_Favorite_Model("Gambar 2", "Andreas Jantu", "https://i.pinimg.com/736x/9d/22/c6/9d22c6839b684d30075ab1ae321ef058.jpg"));
        Card_Favorite_ArrayList.add(new Card_Favorite_Model("Gambar 3", "Daniel Ariyansa", "https://media.raritysniper.com/azuki/3309-600.webp?cacheId=2"));
        Card_Favorite_ArrayList.add(new Card_Favorite_Model("Gambar 4", "Muhammad Iqbal", "https://lh3.googleusercontent.com/QA8lHQmySHMAL8K9aXetIAlZT0WBtVG7tPQR7u8uWeeFnBqsCAe_c5hok0MGRKpAqTRnzYTHiLzVcwDOvP6Q4tEfXzVZJLtvdmVzvz8=w1400-k"));
        Card_Favorite_ArrayList.add(new Card_Favorite_Model("Gambar 5", "Azka Prasetyo", "https://i.pinimg.com/736x/98/64/74/986474493cc4ffac916d651659e1f6a7.jpg"));
    }

}