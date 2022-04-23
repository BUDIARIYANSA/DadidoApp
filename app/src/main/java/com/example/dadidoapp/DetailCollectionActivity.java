package com.example.dadidoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dadidoapp.Adapter.CardCreator_adapter;
import com.example.dadidoapp.Adapter.cardItem_adapter;
import com.example.dadidoapp.LayoutModel.Card_Creator_model;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;
import com.example.dadidoapp.Model.Creator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCollectionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private cardItem_adapter adapter;
    private ArrayList<Card_Item_Model> Card_Item_ArrayList;
    private ImageView imgbanner;
    private ImageView imgProfil;
    private TextView collection_name;
    private TextView creator_name;
    private TextView total_follower;
    private TextView description;
    private Button buttonToCreateItem;

    private Button btn_add_new_item;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static final String PREFS_NAME = "LoginPrefs";


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //Showing back button
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_collection);
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_light);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        collection_name = (TextView) findViewById(R.id.textViewCollectionNameDetail);
        creator_name = (TextView) findViewById(R.id.textViewCreatorNameDetail);
        total_follower = (TextView) findViewById(R.id.textViewFollowerCountDetail);
        description = (TextView) findViewById(R.id.textView8);
        imgProfil = (ImageView) findViewById(R.id.imageViewProfileCreator2);
        imgbanner = (ImageView) findViewById(R.id.imageViewCBanner);

        Intent intent = getIntent();
        String str_coll_name = intent.getStringExtra("collection_title");
        String str_creator_name = intent.getStringExtra("creator_name");
        String str_total_follower = intent.getStringExtra("total_follower");
        String str_description = intent.getStringExtra("description");
        String str_imgProfil_url = intent.getStringExtra("image_profile");
        String str_imgbanner_url = intent.getStringExtra("image_banner");

        actionBar.setTitle(str_coll_name);//this is for actionbar

        collection_name.setText(str_coll_name);
        creator_name.setText(str_creator_name);
        total_follower.setText(str_total_follower);
        description.setText(str_description);
        Picasso.get().load(str_imgbanner_url).into(imgbanner);
        Picasso.get().load(str_imgProfil_url).into(imgProfil);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new cardItem_adapter(Card_Item_ArrayList,DetailCollectionActivity.this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(DetailCollectionActivity.this, 2, GridLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    void addData(){
        Card_Item_ArrayList = new ArrayList<>();
        Card_Item_ArrayList.add(new Card_Item_Model("Gambar 1", "1414370309", "5.0","8","https://i.pinimg.com/736x/b9/ae/1c/b9ae1c820c0162c268611941084dd614.jpg"));
        Card_Item_ArrayList.add(new Card_Item_Model("Gambar 2", "1214234560", "0.7","9","https://i.pinimg.com/736x/9d/22/c6/9d22c6839b684d30075ab1ae321ef058.jpg"));
        Card_Item_ArrayList.add(new Card_Item_Model("Gambar 3", "1214230345", "0.9", "200","https://media.raritysniper.com/azuki/3309-600.webp?cacheId=2"));
        Card_Item_ArrayList.add(new Card_Item_Model("Gambar 4", "1214378098", "4", "500","https://lh3.googleusercontent.com/QA8lHQmySHMAL8K9aXetIAlZT0WBtVG7tPQR7u8uWeeFnBqsCAe_c5hok0MGRKpAqTRnzYTHiLzVcwDOvP6Q4tEfXzVZJLtvdmVzvz8=w1400-k"));
        Card_Item_ArrayList.add(new Card_Item_Model("Gambar 5", "1214378098", "5", "500","https://i.pinimg.com/736x/98/64/74/986474493cc4ffac916d651659e1f6a7.jpg"));
    }
    public static boolean setPreference(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static String getPreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, "");
    }

}