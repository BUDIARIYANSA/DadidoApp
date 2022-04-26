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

        adapter2 = new CardItem2_adapter(Card_Item_ArrayList2,getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());//1 row ajah
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);// 1 row 2 column

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter2);
    }

    void addData(){
        Card_Item_ArrayList2 = new ArrayList<>();
        Card_Item_ArrayList2.add(new Card_Item_Model2( "1","Gambar satu", "#1","dece441","10","/Image/kresm10/goldman.png"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("2","Gambar dua", "#2","dece441","10","/Image/ardo123/coolman.png"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("3","Gambar tiga", "#3","dece441","10","/Image/dece441/beach.jpg"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("4","Gambar empat", "#4","dece441","10","/Image/kresm10/go-explore-949112.jpg"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("5","Gambar lima", "#5","dece441","10","/Image/dece441/fireman.jpg"));
    }
}