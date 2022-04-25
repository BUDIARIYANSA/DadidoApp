package com.example.dadidoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dadidoapp.Adapter.cardItem_adapter;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;
import com.example.dadidoapp.Model.Collection;
import com.example.dadidoapp.Model.Item;
import com.example.dadidoapp.Model.ItemCollection;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private ApiList apiList = RetrofitClient.getRetrofitClient().create(ApiList.class);
    private static final String PREFS_NAME = "LoginPrefs";

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
        owner_name = (TextView) findViewById(R.id.textViewOwner);

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

        detailItem(str_TokenId);

        button_item_activity = (Button) findViewById(R.id.button_item_activity);

        button_item_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DetailItemActivity.this,HistoryItemActivity.class);
                startActivity(intent);

            }
        });


        //below this call lists of cards
        getData();
    }

    void detailItem(String tokenId) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "get_detail_item")
                .addFormDataPart("id", tokenId)
                .build();

        Call<ArrayList<ItemCollection>> call = apiList.itemDetail(requestBody);
        call.enqueue(new Callback<ArrayList<ItemCollection>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemCollection>> call, Response<ArrayList<ItemCollection>> response) {
                if(response.isSuccessful()) {
                    ArrayList<ItemCollection> data = response.body();
                    collection_name.setText(data.get(0).getCollectionName());
                    owner_name.setText(data.get(0).getOwnBy());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemCollection>> call, Throwable t) {

            }
        });
    }

    void getData(){
        Intent intent = getIntent();
        String id = intent.getStringExtra("TokenId");

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "get_more_item")
                .addFormDataPart("id", id)
                .build();

        Call<ArrayList<Item>> call = apiList.itemById(requestBody);
        call.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {
                if (response.isSuccessful()) {
                    Card_Item_ArrayList = new ArrayList<>();
                    ArrayList<Item> data = response.body();

                    for (int i = 0; i < data.size(); i++) {
                        Card_Item_ArrayList.add(new Card_Item_Model(data.get(i).getFileName(), data.get(i).getId().toString(), data.get(i).getPrice().toString(),"8",data.get(i).getUrl()));
                    }

                    recyclerView = (RecyclerView) findViewById(R.id.recycler_view4);

                    adapter = new cardItem_adapter(Card_Item_ArrayList,DetailItemActivity.this);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailItemActivity.this, LinearLayoutManager.HORIZONTAL,false);
                    //GridLayoutManager gridLayoutManager = new GridLayoutManager(DetailItemActivity.this, 2, GridLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(layoutManager);

                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {

            }
        });
    }
}