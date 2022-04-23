package com.example.dadidoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dadidoapp.Adapter.cardItem_adapter;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailItemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private cardItem_adapter adapter;
    private ArrayList<Card_Item_Model> Card_Item_ArrayList;

    private ImageView imgview;
    private TextView collection_name;
    private TextView file_name;
    private TextView owner_name;
    private TextView description;
    private TextView total_fav;
    private TextView total_favorite;
    private TextView total_price;
    private TextView tokenid;

    private Button button_item_activity;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //Showing Back Button
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
        setContentView(R.layout.activity_detail_item);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_light);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        imgview = (ImageView) findViewById(R.id.imageView2);
        collection_name = (TextView) findViewById(R.id.textViewCollectionName);
        file_name = (TextView) findViewById(R.id.textViewFileName);
        total_price = (TextView) findViewById(R.id.textViewCurrPrice);
        tokenid = (TextView) findViewById(R.id.textViewTokenId);
        total_fav = (TextView) findViewById(R.id.textViewFavTotal);
        description = (TextView) findViewById(R.id.textViewDescription);

        Intent intent = getIntent();
        String str_file_name=intent.getStringExtra("image_title");
        String str_TokenId=intent.getStringExtra("TokenId");
        String str_TotalPrice=intent.getStringExtra("TotalPrice");
        String str_TotalLike=intent.getStringExtra("TotalLike");
        String str_creatorName=intent.getStringExtra("creator_name");
        String str_ImageUrl = intent.getStringExtra("image_url");

        actionBar.setTitle(str_file_name);//this is for actionbar

        tokenid.setText(str_TokenId);
        file_name.setText(str_file_name);
        total_price.setText(str_TotalPrice);
        total_fav.setText(str_TotalLike);
        description.setText(str_creatorName);

        Picasso.get().load(str_ImageUrl).into(imgview);

        button_item_activity = (Button) findViewById(R.id.button_item_activity);

        button_item_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DetailItemActivity.this,HistoryItemActivity.class);
                startActivity(intent);

            }
        });


        //below this call lists of cards
        addData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view4);

        adapter = new cardItem_adapter(Card_Item_ArrayList,DetailItemActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailItemActivity.this, LinearLayoutManager.HORIZONTAL,false);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(DetailItemActivity.this, 2, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

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
}