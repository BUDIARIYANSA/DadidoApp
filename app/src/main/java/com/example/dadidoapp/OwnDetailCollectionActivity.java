package com.example.dadidoapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dadidoapp.Adapter.CardFavorite_adapter;
import com.example.dadidoapp.Adapter.cardItem_adapter;
import com.example.dadidoapp.LayoutModel.Card_Creator_model;
import com.example.dadidoapp.LayoutModel.Card_Favorite_Model;
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

public class OwnDetailCollectionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private cardItem_adapter adapter;
    private ArrayList<Card_Item_Model> Card_Item_ArrayList;
    private ImageView imgbanner;
    private ImageView imgProfil;
    private TextView collection_name;
    private TextView creator_name;
    private TextView total_follower;
    private TextView cheapest;
    private TextView description;
    private TextView total_item;
    private Button buttonToCreateItem;

    private String str_collection_name;

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
        setContentView(R.layout.activity_own_detail_collection);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_light);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("My Own Collection");

        buttonToCreateItem = (Button) findViewById(R.id.buttonToCreateItem);
        buttonToCreateItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OwnDetailCollectionActivity.this, CreateItemActivity.class);
                startActivity(intent);
                intent.putExtra("Collection_name",str_collection_name);
            }
        });

        Cheapest_item();
        dataCollection();
        getCard();

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

    public void dataCollection() {
        ApiList apiList = RetrofitClient.getRetrofitClient().create(ApiList.class);
        String str_username = getPreference(OwnDetailCollectionActivity.this, "username");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "collection_by_username")
                .addFormDataPart("username",str_username)
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
                        get_string_coll(data.get(i).getCollectionName());
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

    public void Cheapest_item() {
        ApiList apiList2 = RetrofitClient.getRetrofitClient().create(ApiList.class);
        String str_username = getPreference(OwnDetailCollectionActivity.this, "username");
        String str_password = getPreference(OwnDetailCollectionActivity.this, "password");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "min_price")
                .addFormDataPart("username",str_username)
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

    void getCard(){
        ApiList apiList3 = RetrofitClient.getRetrofitClient().create(ApiList.class);
        String username = getPreference(getApplicationContext(), "username");

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

                    adapter = new cardItem_adapter(Card_Item_ArrayList,OwnDetailCollectionActivity.this);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(OwnDetailCollectionActivity.this);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(OwnDetailCollectionActivity.this, 2, GridLayoutManager.VERTICAL, false);

                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(OwnDetailCollectionActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {
                Toast.makeText(OwnDetailCollectionActivity.this, "Error : " + t, Toast.LENGTH_SHORT).show();
            }
        });


    }

    void get_string_coll(String str){
        str_collection_name = str;
    }

}
