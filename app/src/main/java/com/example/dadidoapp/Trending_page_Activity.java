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
import com.example.dadidoapp.Model.Item;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        TrendingList(view);
    }

    void addData(){
        Card_Item_ArrayList2 = new ArrayList<>();
        Card_Item_ArrayList2.add(new Card_Item_Model2( "1","Gambar satu", "#1","dece441","10","/Image/kresm10/goldman.png"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("2","Gambar dua", "#2","dece441","10","/Image/ardo123/coolman.png"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("3","Gambar tiga", "#3","dece441","10","/Image/dece441/beach.jpg"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("4","Gambar empat", "#4","dece441","10","/Image/kresm10/go-explore-949112.jpg"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("5","Gambar lima", "#5","dece441","10","/Image/dece441/fireman.jpg"));
    }

    public void TrendingList(View view) {
        ApiList apiList = RetrofitClient.getRetrofitClient().create(ApiList.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "most_expensive")
                .build();

        Call<ArrayList<Item>> call = apiList.mostExpensive(requestBody);
        call.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {
                if(response.isSuccessful()) {
                    ArrayList<Item> data = response.body();
                    Card_Item_ArrayList2 = new ArrayList<>();
                    for (int i = 0; i < data.size(); i++) {
                        Card_Item_ArrayList2.add(new Card_Item_Model2 (data.get(i).getId().toString(), data.get(i).getFileName(),
                                "#"+(i+1), data.get(i).getUsername(), data.get(i).getPrice().toString(), data.get(i).getUrl()));
                    }

                    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);
                    adapter2 = new CardItem2_adapter(Card_Item_ArrayList2,getContext());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());//1 row ajah
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter2);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {

            }
        });
    }
}