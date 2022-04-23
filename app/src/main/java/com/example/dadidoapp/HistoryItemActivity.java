package com.example.dadidoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.dadidoapp.Adapter.CardHistory_adapter;
import com.example.dadidoapp.Adapter.cardItem_adapter;
import com.example.dadidoapp.LayoutModel.Card_History_Model;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;

import java.util.ArrayList;

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
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view4);

        adapter = new CardHistory_adapter(Card_History_ArrayList,HistoryItemActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HistoryItemActivity.this, LinearLayoutManager.HORIZONTAL,false);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(DetailItemActivity.this, 2, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    void addData(){
        Card_History_ArrayList = new ArrayList<>();
        Card_History_ArrayList.add(new Card_History_Model("Sale", "0.7 Dadido Coin", "Budi Ariyansa","Leonardo Ade","7 Januari 2022"));
        Card_History_ArrayList.add(new Card_History_Model("Tranfer", " ", "Budi Ariyansa","Leonardo Ade","7 Januari 2022"));
        Card_History_ArrayList.add(new Card_History_Model("Mounted", " ", "Null", "Budi Ariyansa","5 Januari 2022"));
    }
}