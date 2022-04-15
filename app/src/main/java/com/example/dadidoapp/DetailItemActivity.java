package com.example.dadidoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.dadidoapp.Adapter.CardItem2_adapter;
import com.example.dadidoapp.Adapter.cardItem_adapter;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;
import com.example.dadidoapp.LayoutModel.Card_Item_Model2;

import java.util.ArrayList;

public class DetailItemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private cardItem_adapter adapter;
    private ArrayList<Card_Item_Model> Card_Item_ArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        addData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view4);

        adapter = new cardItem_adapter(Card_Item_ArrayList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(DetailItemActivity.this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(adapter);
    }

    void addData(){
        Card_Item_ArrayList = new ArrayList<>();
        Card_Item_ArrayList.add(new Card_Item_Model("Gambar satu", "12", "Suprapto","8"));
        Card_Item_ArrayList.add(new Card_Item_Model("Gambar dua", "23", "Jimin","9"));
        Card_Item_ArrayList.add(new Card_Item_Model("Gambar tiga", "23", "Koboi", "200"));
        Card_Item_ArrayList.add(new Card_Item_Model("Gambar empat", "43", "Meong", "500"));
        Card_Item_ArrayList.add(new Card_Item_Model("Gambar lima", "56", "Door", "500"));
    }
}