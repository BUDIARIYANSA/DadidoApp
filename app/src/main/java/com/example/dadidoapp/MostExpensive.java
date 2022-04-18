package com.example.dadidoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dadidoapp.Adapter.CardCreator_adapter;
import com.example.dadidoapp.Adapter.CardItem2_adapter;
import com.example.dadidoapp.Adapter.cardItem_adapter;
import com.example.dadidoapp.LayoutModel.Card_Creator_model;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;
import com.example.dadidoapp.LayoutModel.Card_Item_Model2;
import com.example.dadidoapp.Model.Creator;
import com.example.dadidoapp.Model.Item;
import com.google.gson.Gson;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostExpensive extends Fragment {
    private RecyclerView recyclerView, recyclerView2;
    private CardItem2_adapter adapter2;
    private CardCreator_adapter adapterCreator;
    private ArrayList<Card_Item_Model2> Card_Item_ArrayList2;
    private ArrayList<Card_Creator_model> card_creator_ArrayList;
    private ApiList apiList = RetrofitClient.getRetrofitClient().create(ApiList.class);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.activity_most_expensive, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getData(view);
        getData2(view);
    }

    public void getData(View view) {
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
                        Card_Item_ArrayList2.add(new Card_Item_Model2(data.get(i).getFileName(),
                                "1214234560", data.get(i).getPrice().toString(), "100", data.get(i).getUrl()));
                    }

                    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view3);
                    adapter2 = new CardItem2_adapter(Card_Item_ArrayList2,getContext());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter2);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {

            }
        });
    }

    public void getData2(View view) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "data_creator")
                .build();
        Call<ArrayList<Creator>> call = apiList.cardCreator(requestBody);
        call.enqueue(new Callback<ArrayList<Creator>>() {
            @Override
            public void onResponse(Call<ArrayList<Creator>> call, Response<ArrayList<Creator>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Creator> data = response.body();
                    card_creator_ArrayList = new ArrayList<>();

                    for (int i = 0; i < data.size(); i++) {
                        card_creator_ArrayList.add(new Card_Creator_model(data.get(i).getCollectionName(), data.get(i).getFullName(), "60",
                                data.get(i).getDescription(), data.get(i).getProfileURL(), data.get(i).getImageBanner()));
                    }

                    recyclerView2 = (RecyclerView) view.findViewById(R.id.recycler_view4);

                    adapterCreator = new CardCreator_adapter(card_creator_ArrayList,getContext());

                    RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);//1 row ajah
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);// 1 row 2 column

                    recyclerView2.setLayoutManager(layoutManager2);
                    recyclerView2.setAdapter(adapterCreator);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Creator>> call, Throwable t) {

            }
        });
    }

}