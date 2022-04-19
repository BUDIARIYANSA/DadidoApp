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
import android.widget.Toast;

import com.example.dadidoapp.Adapter.cardItem_adapter;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;
import com.example.dadidoapp.Model.Item;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class new_page_activity extends Fragment {

    private RecyclerView recyclerView;
    private cardItem_adapter adapter;
    private ArrayList<Card_Item_Model> Card_Item_ArrayList;
    private ArrayList<String> url = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.activity_new_page, parent, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getData(view);
    }

    public void getData(View view){
        ApiList apis = RetrofitClient.getRetrofitClient().create(ApiList.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "new_item")
                .build();

        Call<ArrayList<Item>> call = apis.newItem(requestBody);
        call.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {
                if(response.isSuccessful()) {
                    ArrayList<Item> data = response.body();
                    Card_Item_ArrayList = new ArrayList<>();
                    for (int i = 0; i < data.size(); i++) {
                        Card_Item_ArrayList.add(new Card_Item_Model(data.get(i).getFileName(),
                                data.get(i).getId().toString(), data.get(i).getPrice().toString(), "100", data.get(i).getUrl()));
                    }
                    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

                    adapter = new cardItem_adapter(Card_Item_ArrayList,getContext());

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);

                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}