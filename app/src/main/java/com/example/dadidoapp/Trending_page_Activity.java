package com.example.dadidoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dadidoapp.Adapter.CardItem2_adapter;
import com.example.dadidoapp.LayoutModel.Card_Item_Model2;

import java.util.ArrayList;

public class Trending_page_Activity extends Fragment {

    private RecyclerView recyclerView;
    private CardItem2_adapter adapter2;
    private ArrayList<Card_Item_Model2> Card_Item_ArrayList2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.activity_trending_page, parent, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        addData();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);

        adapter2 = new CardItem2_adapter(Card_Item_ArrayList2);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());//1 row ajah
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);// 1 row 2 column

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter2);
    }

    void addData(){
        Card_Item_ArrayList2 = new ArrayList<>();
        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar satu", "Top 1", "Suprapto","8"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar dua", "Top 2", "Jimin","9"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar tiga", "Top 3", "Koboi", "200"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar empat", "Top 4", "Meong", "500"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar lima", "Top 5", "Door", "500"));
    }
}