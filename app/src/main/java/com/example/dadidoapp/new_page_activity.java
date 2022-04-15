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

import com.example.dadidoapp.Adapter.cardItem_adapter;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;

import java.util.ArrayList;

public class new_page_activity extends Fragment {

    private RecyclerView recyclerView;
    private cardItem_adapter adapter;
    private ArrayList<Card_Item_Model> Card_Item_ArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.activity_new_page, parent, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        addData();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        adapter = new cardItem_adapter(Card_Item_ArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(adapter);
    }

    void addData(){
        Card_Item_ArrayList = new ArrayList<>();
        Card_Item_ArrayList.add(new Card_Item_Model("Gambar 1", "1414370309", "5.0","8"));
        Card_Item_ArrayList.add(new Card_Item_Model("Gambar 2", "1214234560", "0.7","9"));
        Card_Item_ArrayList.add(new Card_Item_Model("Gambar 3", "1214230345", "0.9", "200"));
        Card_Item_ArrayList.add(new Card_Item_Model("Gambar 4", "1214378098", "4", "500"));
        Card_Item_ArrayList.add(new Card_Item_Model("Gambar 5", "1214378098", "5", "500"));
    }
}