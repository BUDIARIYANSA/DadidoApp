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
import android.widget.Toast;

import com.example.dadidoapp.Adapter.CardCreator_adapter;
import com.example.dadidoapp.Adapter.cardItem_adapter;
import com.example.dadidoapp.LayoutModel.Card_Creator_model;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;
import com.example.dadidoapp.Model.Creator;
import com.example.dadidoapp.Model.Item;
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
    private TextView total_item;
    private TextView cheapest;
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

        Intent intent = getIntent();
        String str_coll_name = intent.getStringExtra("collection_title");
        String str_creator_name = intent.getStringExtra("creator_name");

        actionBar.setTitle(str_coll_name);//this is for actionbar

        dataCollection(str_creator_name);
        Cheapest_item(str_creator_name);
        getCard(str_creator_name);

    }

    public void dataCollection(String username) {
        ApiList apiList = RetrofitClient.getRetrofitClient().create(ApiList.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "collection_by_username")
                .addFormDataPart("username",username)
                .build();
        Call<ArrayList<Creator>> call = apiList.cardCreator(requestBody);
        call.enqueue(new Callback<ArrayList<Creator>>() {
            @Override
            public void onResponse(Call<ArrayList<Creator>> call, Response<ArrayList<Creator>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Creator> data = response.body();
                    creator_name = (TextView) findViewById(R.id.textViewCreatorNameDetail);
                    collection_name = (TextView) findViewById(R.id.textViewCollectionNameDetail);
                    description = (TextView) findViewById(R.id.textView8);
                    imgbanner = (ImageView) findViewById(R.id.imageViewCBanner);
                    imgProfil = (ImageView) findViewById(R.id.imageViewProfileCreator2);

                    for (int i = 0; i < data.size(); i++) {
                        creator_name.setText(data.get(i).getUsername());
                        collection_name.setText(data.get(i).getCollectionName());
                        description.setText(data.get(i).getDescription());
                        Picasso.get().load(data.get(i).getImageBanner()).into(imgbanner);
                        Picasso.get().load(data.get(i).getProfileURL()).into(imgProfil);
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Creator>> call, Throwable t) {

            }
        });
    }

    public void Cheapest_item(String username) {
        ApiList apiList2 = RetrofitClient.getRetrofitClient().create(ApiList.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "min_price")
                .addFormDataPart("username",username)
                .build();
        Call<ArrayList<Creator>> call = apiList2.cardCreator(requestBody);
        call.enqueue(new Callback<ArrayList<Creator>>() {
            @Override
            public void onResponse(Call<ArrayList<Creator>> call, Response<ArrayList<Creator>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Creator> data = response.body();
                    cheapest = (TextView) findViewById(R.id.textViewCheapestPrice);

                    for (int i = 0; i < data.size(); i++) {
                        cheapest.setText(data.get(i).getCheapestPrice());
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Creator>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    void getCard(String username){
        ApiList apiList3 = RetrofitClient.getRetrofitClient().create(ApiList.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "item_by_username")
                .addFormDataPart("username", username)
                .build();

        Call<ArrayList<Item>> call = apiList3.newItem(requestBody);
        call.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {
                if(response.isSuccessful()) {
                    ArrayList<Item> data = response.body();
                    Card_Item_ArrayList = new ArrayList<>();
                    for (int i = 0; i < data.size(); i++) {
                        Card_Item_ArrayList.add(new Card_Item_Model(data.get(i).getFileName(),
                                data.get(i).getId().toString(), data.get(i).getPrice().toString(), "100", data.get(i).getUrl()));

                    }
                    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                    total_item = (TextView) findViewById(R.id.textViewTotalItemsInCollection);

                    total_item.setText(String.valueOf(data.size()));

                    adapter = new cardItem_adapter(Card_Item_ArrayList,DetailCollectionActivity.this);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailCollectionActivity.this);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(DetailCollectionActivity.this, 2, GridLayoutManager.VERTICAL, false);

                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(DetailCollectionActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {
                Toast.makeText(DetailCollectionActivity.this, "Error : " + t, Toast.LENGTH_SHORT).show();
            }
        });


    }

}