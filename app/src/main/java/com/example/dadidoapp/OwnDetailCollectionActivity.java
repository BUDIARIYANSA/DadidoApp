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
import androidx.recyclerview.widget.RecyclerView;

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

public class OwnDetailCollectionActivity extends AppCompatActivity {

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
                startActivity(new Intent(OwnDetailCollectionActivity.this, CreateItemActivity.class));
            }
        });

        addData();
        dataCollection();
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

    public void dataCollection() {
        ApiList apiList = RetrofitClient.getRetrofitClient().create(ApiList.class);
        String str_username = getPreference(OwnDetailCollectionActivity.this, "username");
        String str_password = getPreference(OwnDetailCollectionActivity.this, "password");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "collection_by_username")
                .addFormDataPart("username",str_username)
                .addFormDataPart("password",str_password)
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

}
