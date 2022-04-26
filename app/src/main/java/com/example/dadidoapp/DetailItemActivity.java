package com.example.dadidoapp;

import androidx.annotation.ColorRes;
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
import android.widget.Toast;

import com.example.dadidoapp.Adapter.cardItem_adapter;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;
import com.example.dadidoapp.Model.Collection;
import com.example.dadidoapp.Model.Item;
import com.example.dadidoapp.Model.ItemCollection;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.hanks.library.bang.SmallBangView;

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
    private SmallBangView imgFav;
    private Button button_item_activity;
    private TextView tgl_transaksi;
    private Button button_buy;
    private ApiList apiList = RetrofitClient.getRetrofitClient().create(ApiList.class);
    private static final String PREFS_NAME = "LoginPrefs";

    private String str_file_name;
    private String str_TokenId;
    private String str_TotalPrice;
    private String str_TotalLike;
    private String str_creatorName;
    private String str_ImageUrl;

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

        imgFav = (SmallBangView) findViewById(R.id.imageViewAnimation);
        tgl_transaksi = (TextView) findViewById(R.id.textViewLastBoughtDate);

        Intent intent = getIntent();
        str_file_name=intent.getStringExtra("image_title");
        str_TokenId=intent.getStringExtra("TokenId");
        str_TotalPrice=intent.getStringExtra("TotalPrice");
        str_TotalLike=intent.getStringExtra("TotalLike");
        str_creatorName=intent.getStringExtra("creator_name");
        str_ImageUrl = intent.getStringExtra("image_url");

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

        imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imgFav.isSelected()) {
                    imgFav.setSelected(false);
                } else {
                    imgFav.setSelected(true);
                    imgFav.likeAnimation();
                }
            }
        });

        button_buy = (Button) findViewById(R.id.button_buy2);
        button_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailItemActivity.this, PaymentActivity.class);
                intent.putExtra("total_price",str_TotalPrice);
                intent.putExtra("file_name",str_file_name);
                intent.putExtra("Item_id",str_TokenId);
                startActivity(intent);
            }
        });

        //below this call lists of cards
        getData();
    }
    public static String getPreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, "");
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
                    String owner="";
                    String sell_status="";
                    ArrayList<ItemCollection> data = response.body();
                    for (int i = data.size()-1 ; i>=0;i--){
                        collection_name.setText(data.get(i).getCollectionName());
                        owner_name.setText(data.get(i).getOwnBy());
                        System.out.println(data.get(i).getOwnBy());
                        owner = data.get(i).getOwnBy();
                        tgl_transaksi.setText(data.get(i).getLast_activity());
                        sell_status = data.get(i).getSell_status();
                    }

                    String user = getPreference(DetailItemActivity.this,"username");
                    button_buy = (Button) findViewById(R.id.button_buy2);
                    if(sell_status.equals("0")&& !user.equals(owner)){
                        button_buy.setEnabled(false);
                        button_buy.setText("Not For Sale");
                        button_buy.setBackgroundResource(R.color.red);
                    }else if(user.equals(owner) && sell_status.equals("0")){
                        button_buy.setText("Open Sale");
                        button_buy.setBackgroundResource(R.color.tea_green);
                        button_buy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(DetailItemActivity.this, "Item Updated to 1", Toast.LENGTH_LONG).show();
                                set_item_status(str_TokenId);
                            }
                        });
                    }else if(user.equals(owner) && sell_status.equals("1")){
                        button_buy.setText("Close Sale");
                        button_buy.setBackgroundResource(R.color.tea_green);
                        button_buy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(DetailItemActivity.this, "Item Updated to 0", Toast.LENGTH_LONG).show();
                                set_item_status(str_TokenId);
                            }
                        });
                    }else{
                        button_buy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(DetailItemActivity.this, PaymentActivity.class);
                                intent.putExtra("total_price",str_TotalPrice);
                                intent.putExtra("file_name",str_file_name);
                                intent.putExtra("Item_id",str_TokenId);
                                String owner = owner_name.getText().toString();
                                intent.putExtra("owned_by",owner);
                                startActivity(intent);
                            }
                        });
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemCollection>> call, Throwable t) {

            }
        });
    }

    void set_item_status(String tokenId) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "set_status_sell")
                .addFormDataPart("id", tokenId)
                .build();
        Call call = apiList.status_sell(requestBody);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    String res = response.body().toString();
                    if(res.equals("Update Successful")){
                        detailItem(str_TokenId);
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

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