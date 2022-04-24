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
//        addData();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);

        adapter2 = new CardItem2_adapter(Card_Item_ArrayList2,getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());//1 row ajah
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);// 1 row 2 column

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter2);
    }

//    void addData(){
//        Card_Item_ArrayList2 = new ArrayList<>();
//        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar satu", "", "Suprapto","8","https://lh3.googleusercontent.com/tJjqF1H_KvpHL7nEtznxVG-X5nqPKNbjwPr0za1HH3Y7zOZxFFjYkpOt-wY7sBm6blSEH41YQSebVp2eqBzlrdklcPKT7go5TD-y72o=w600"));
//        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar dua", "", "Jimin","9","https://lh3.googleusercontent.com/AKM1M174Sn1KWU1MbLbBhSjGZr98_g0oiYfjPioU0-0S8C8QUv1I37appvham1l6Tnlk-hqo7XWYqE7v0bdFGe-pcqlsSprlLXdozA=w600"));
//        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar tiga", "", "Koboi", "200","https://lh3.googleusercontent.com/sM5ed2y9mSlhrZ9S_4INmmtZ13a80sX42b-_QvlXixEf3tv200mnHKkhtxWm3h_IZj0ox3sBkUrRTtlpBx8pSz8MCSIFQU2yhH8tSQ=w600"));
//        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar empat", "", "Meong", "500","https://lh3.googleusercontent.com/_O9UhoFxQVS0qKSBOPtMm1qukS_T1bpcE-xV7YV605jgELmwW0lNjklJU8CMN47A26rxRJfWqcVh9JV_8jtAdU6d-zMmPiFgCaDy=w600"));
//        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar lima", "", "Door", "500","https://lh3.googleusercontent.com/nZJ1K5L9a8W8xP0P80OsERKe-YdKTxutXngnHdw45XTt39jPhNyVTDRK7GKGtU5rgIijIH_dyvSooD8KHpT3svCMrZIZxvipx3I9WuQ=w600"));
//    }
}