package com.example.dadidoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dadidoapp.Adapter.CardCreator_adapter;
import com.example.dadidoapp.Adapter.CardItem2_adapter;
import com.example.dadidoapp.LayoutModel.Card_Creator_model;
import com.example.dadidoapp.LayoutModel.Card_Item_Model2;

import java.util.ArrayList;

public class MostExpensive extends Fragment {
    private RecyclerView recyclerView, recyclerView2;
    private CardItem2_adapter adapter2;
    private CardCreator_adapter adapterCreator;
    private ArrayList<Card_Item_Model2> Card_Item_ArrayList2;
    private ArrayList<Card_Creator_model> card_creator_ArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.activity_most_expensive, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        addData();
        addData2();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view3);
        recyclerView2 = (RecyclerView) view.findViewById(R.id.recycler_view4);

        adapter2 = new CardItem2_adapter(Card_Item_ArrayList2);
        adapterCreator = new CardCreator_adapter(card_creator_ArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);//1 row ajah
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);// 1 row 2 column

        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);

        recyclerView.setAdapter(adapter2);
        recyclerView2.setAdapter(adapterCreator);

    }
    void addData(){
        Card_Item_ArrayList2 = new ArrayList<>();
        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar satu", "", "Suprapto","8"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar dua", "", "Jimin","9"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar tiga", "", "Koboi", "200"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar empat", "", "Meong", "500"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar lima", "", "Door", "500"));
    }

    void addData2(){
        card_creator_ArrayList = new ArrayList<>();
        card_creator_ArrayList.add(new Card_Creator_model("Collection satu", "Creator satu", "60",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non dignissim sem. Quisque in ultrices sapien."));
        card_creator_ArrayList.add(new Card_Creator_model("Collection dua", "Creator dua", "50",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non dignissim sem. Quisque in ultrices sapien."));
        card_creator_ArrayList.add(new Card_Creator_model("Collection tiga", "Creator tiga", "8",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non dignissim sem. Quisque in ultrices sapien."));
        card_creator_ArrayList.add(new Card_Creator_model("Collection empat", "Creator empat", "700",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non dignissim sem. Quisque in ultrices sapien."));
        card_creator_ArrayList.add(new Card_Creator_model("Collection lima", "Creator lima", "56",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non dignissim sem. Quisque in ultrices sapien."));
    }
}