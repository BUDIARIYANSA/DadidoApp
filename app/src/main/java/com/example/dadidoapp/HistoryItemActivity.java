package com.example.dadidoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dadidoapp.Adapter.CardHistory_adapter;
import com.example.dadidoapp.Adapter.cardItem_adapter;
import com.example.dadidoapp.LayoutModel.Card_History_Model;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;
import com.example.dadidoapp.Model.ItemCollection;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryItemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardHistory_adapter adapter;
    private ArrayList<Card_History_Model> Card_History_ArrayList;

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
        setContentView(R.layout.activity_history_item);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_light);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("History Item");//this is for actionbar

        addData();
    }

    void addData(){
        Intent intent = getIntent();
        ApiList apiList = RetrofitClient.getRetrofitClient().create(ApiList.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "item_history")
                .addFormDataPart("id", intent.getStringExtra("tokenId"))
                .build();
        Call<ArrayList<ItemCollection>> call = apiList.itemDetail(requestBody);
        call.enqueue(new Callback<ArrayList<ItemCollection>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemCollection>> call, Response<ArrayList<ItemCollection>> response) {
                if (response.isSuccessful()) {
                    Card_History_ArrayList = new ArrayList<>();
                    ArrayList<ItemCollection> data = response.body();

                    for (int i = 0; i < data.size(); i++) {
                        Card_History_ArrayList.add(new Card_History_Model(data.get(i).getEvent(), data.get(i).getOwnBy(), data.get(i).getLast_activity()));
                    }
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_history);

                    adapter = new CardHistory_adapter(Card_History_ArrayList,HistoryItemActivity.this);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HistoryItemActivity.this, LinearLayoutManager.VERTICAL,false);
                    // GridLayoutManager gridLayoutManager = new GridLayoutManager(DetailItemActivity.this, 2, GridLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(layoutManager);

                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(HistoryItemActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemCollection>> call, Throwable t) {
                Toast.makeText(HistoryItemActivity.this, "Error : "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}