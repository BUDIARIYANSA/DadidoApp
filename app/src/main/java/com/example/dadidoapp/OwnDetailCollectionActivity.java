package com.example.dadidoapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
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
    private EditText collection_name;
    private TextView creator_name;
    private TextView total_follower;
    private TextView cheapest;
    private EditText description;
    private TextView total_item;
    private Button buttonToCreateItem;
    private Button buttonToUpdate;
    private Button btnChangeBanner;
    private int button_clicked = 0;

    private String path;
    int SELECT_PICTURE = 200;

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
            }
        });

        Cheapest_item();
        dataCollection();
        getCard();

        buttonToUpdate = (Button) findViewById(R.id.buttonToUpdateCollection);
        buttonToUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(description.getText().toString().length() == 0 && description.getText().toString().isEmpty() &&
                        collection_name.getText().toString().length() == 0 && collection_name.getText().toString().isEmpty()){
                    Toast.makeText(OwnDetailCollectionActivity.this, "Fill the description and the collection title", Toast.LENGTH_SHORT).show();
                }else if(description.getText().toString().length() >= 0 && !description.getText().toString().isEmpty() &&
                        collection_name.getText().toString().length() == 0 && collection_name.getText().toString().isEmpty()){
                    Toast.makeText(OwnDetailCollectionActivity.this, "Fill the Collection Title", Toast.LENGTH_SHORT).show();
                }else if(description.getText().toString().length() == 0 && description.getText().toString().isEmpty() &&
                        collection_name.getText().toString().length() >= 0 && !collection_name.getText().toString().isEmpty()){
                    Toast.makeText(OwnDetailCollectionActivity.this, "Fill the description", Toast.LENGTH_SHORT).show();
                }else{
                    update_collection();
                }
            }
        });

        btnChangeBanner = (Button) findViewById(R.id.button_change_banner);
        btnChangeBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
                button_clicked = 1;
            }
        });

    }

    private void imageChooser() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        startActivityForResult(Intent.createChooser(pickIntent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_PICTURE) {

                Uri selectImageUri = data.getData();
                if (null != selectImageUri) {

                    imgbanner.setImageURI(selectImageUri);
                    path = getRealPathFromURI(selectImageUri,OwnDetailCollectionActivity.this);
                    Toast.makeText(OwnDetailCollectionActivity.this, path, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public String getRealPathFromURI(Uri contentUri, Context context) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            //Log.e("EditActivity", "getRealPathFromURI Exception : " + e.toString());
            return "";
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
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
                    collection_name = (EditText) findViewById(R.id.editTextTitleCollection);
                    description = (EditText) findViewById(R.id.editTextDescCollection);
                    imgbanner = (ImageView) findViewById(R.id.imageViewCBanner);
                    imgProfil = (ImageView) findViewById(R.id.imageViewProfileCreator2);

                    for (int i = 0; i < data.size(); i++) {
                        creator_name.setText(data.get(i).getUsername());
                        collection_name.setText(data.get(i).getCollectionName());
                        description.setText(data.get(i).getDescription());

                        String DPurl = global_var.webURL + data.get(i).getProfileURL();
                        String Bannerurl = global_var.webURL + data.get(i).getImageBanner();

                        Picasso.get().load(Bannerurl).into(imgbanner);
                        Picasso.get().load(DPurl).into(imgProfil);
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Creator>> call, Throwable t) {

            }
        });
    }
    public void update_collection(){
        ApiList apiList = RetrofitClient.getRetrofitClient().create(ApiList.class);
        String str_username = getPreference(OwnDetailCollectionActivity.this, "username");

        String collection_title = collection_name.getText().toString().trim();
        String collection_desc = description.getText().toString().trim();

        if(button_clicked == 1) {
            File file = new File(path);
            RequestBody requestf = RequestBody.create(MediaType.parse("image/*"), file);

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("CMD", "update_collection")
                    .addFormDataPart("username", str_username)
                    .addFormDataPart("collection_name", collection_title)
                    .addFormDataPart("collection_desc", collection_desc)
                    .addFormDataPart("choosefile", file.getName(), requestf)
                    .build();
            Call call = apiList.createItem(requestBody);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.isSuccessful()) {
                        String res = response.body().toString();
                        if (res.equals("success update Picture and collection")) {
                            Toast.makeText(OwnDetailCollectionActivity.this, res, Toast.LENGTH_SHORT).show();
                            button_clicked = 0;
                            Intent intent = new Intent(OwnDetailCollectionActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(OwnDetailCollectionActivity.this, res, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {

                }
            });
        }else if(button_clicked == 0){
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("CMD", "update_collection")
                    .addFormDataPart("username", str_username)
                    .addFormDataPart("collection_name", collection_title)
                    .addFormDataPart("collection_desc", collection_desc)
                    .build();
            Call call = apiList.createItem(requestBody);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.isSuccessful()) {
                        String res = response.body().toString();
                        if (res.equals("success update collection")) {
                            Toast.makeText(OwnDetailCollectionActivity.this, res, Toast.LENGTH_SHORT).show();
                            button_clicked = 0;
                            Intent intent = new Intent(OwnDetailCollectionActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(OwnDetailCollectionActivity.this, res, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Toast.makeText(OwnDetailCollectionActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
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

}
