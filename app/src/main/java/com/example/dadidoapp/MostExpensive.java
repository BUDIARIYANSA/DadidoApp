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

        adapter2 = new CardItem2_adapter(Card_Item_ArrayList2,getContext());
        adapterCreator = new CardCreator_adapter(card_creator_ArrayList,getContext());

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
        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar satu", "", "Suprapto","8","https://lh3.googleusercontent.com/tJjqF1H_KvpHL7nEtznxVG-X5nqPKNbjwPr0za1HH3Y7zOZxFFjYkpOt-wY7sBm6blSEH41YQSebVp2eqBzlrdklcPKT7go5TD-y72o=w600"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar dua", "", "Jimin","9","https://lh3.googleusercontent.com/AKM1M174Sn1KWU1MbLbBhSjGZr98_g0oiYfjPioU0-0S8C8QUv1I37appvham1l6Tnlk-hqo7XWYqE7v0bdFGe-pcqlsSprlLXdozA=w600"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar tiga", "", "Koboi", "200","https://lh3.googleusercontent.com/sM5ed2y9mSlhrZ9S_4INmmtZ13a80sX42b-_QvlXixEf3tv200mnHKkhtxWm3h_IZj0ox3sBkUrRTtlpBx8pSz8MCSIFQU2yhH8tSQ=w600"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar empat", "", "Meong", "500","https://lh3.googleusercontent.com/_O9UhoFxQVS0qKSBOPtMm1qukS_T1bpcE-xV7YV605jgELmwW0lNjklJU8CMN47A26rxRJfWqcVh9JV_8jtAdU6d-zMmPiFgCaDy=w600"));
        Card_Item_ArrayList2.add(new Card_Item_Model2("Gambar lima", "", "Door", "500","https://lh3.googleusercontent.com/nZJ1K5L9a8W8xP0P80OsERKe-YdKTxutXngnHdw45XTt39jPhNyVTDRK7GKGtU5rgIijIH_dyvSooD8KHpT3svCMrZIZxvipx3I9WuQ=w600"));
    }

    void addData2(){
        card_creator_ArrayList = new ArrayList<>();
        card_creator_ArrayList.add(new Card_Creator_model("CryptoTeddies", "Numo", "60",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non dignissim sem. Quisque in ultrices sapien.",
                "https://lh3.googleusercontent.com/bTxADvEugM_Ot0hMbLxIAfYbDWX6QAaXLWKeKUIDGcz62VRvUd9gRLJqg3r4RIODCHEFuJMVj4qPgB-9sN3aCnfTng2euVM621Syiw=s130",
                "https://lh3.googleusercontent.com/-114aDz8vMbSCOkiv7Id2fnMDTe-eq8iHfurg2xh6d3IaoBb8ylfVizLWZq2wmQze-Ii3mpNKrqrP48i4Q1yf9pzYwHYpuxjKHiLCA=h600"));
        card_creator_ArrayList.add(new Card_Creator_model("CryptoCities", "Crypto_Cities", "50",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non dignissim sem. Quisque in ultrices sapien.",
                "https://lh3.googleusercontent.com/VyxHil3DawP25ZoSk7HkSJslwUDLFO3_LC9rj-w0jYUKxkcGy-VLVGk4n-I02t8CwJRt4Bk7mEwhGAgVCsjQyZfzi30RCQoPkXqY=s130",
                "https://lh3.googleusercontent.com/RPT6v17Bh3mO_kYFwmrJSnOC_hstEgKoIpEnkp84bqp99I5W1nCvE6hOUnKYt7ZMBQCuI0N8WjyyQNYtzTb6Wcp__YYSr7lPGgASfw=h600"));
        card_creator_ArrayList.add(new Card_Creator_model("GREG MIKE - Mad Cans (3D)", "GREG_MIKE", "8",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non dignissim sem. Quisque in ultrices sapien.",
                "https://lh3.googleusercontent.com/25y-FHo9ltvx-yoRMKEf8AgEJEABfPzojkEpDUAWSz8aKq05rZKCLkTpafbBFYOApQLPhjDWCKy_lguOkoircI0xonevDDV8QNvOBQ=s130",
                "https://lh3.googleusercontent.com/8uNnqSBxAnbg_8iFh80xwJzutDI1g7rsrWqK3ZyFGl54CGy7NKuGWejgdJU_aPWGOJNZYQNC3BiKpmt8AAn9q7dqXtJkcuNKaUjPWw=h600"));
        card_creator_ArrayList.add(new Card_Creator_model("Crypto Coven", "crypto_coven", "700",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non dignissim sem. Quisque in ultrices sapien.",
                "https://lh3.googleusercontent.com/E8MVasG7noxC0Fa_duhnexc2xze1PzT1jzyeaHsytOC4722C2Zeo7EhUR8-T6mSem9-4XE5ylrCtoAsceZ_lXez_kTaMufV5pfLc3Fk=s130",
                "https://lh3.googleusercontent.com/M42Xf9Vbu_yodzKVFA1I6TYXIx5Hz699gEtp2lDg9vGT7g-S4z_5cx2iYPub1kytnOlexV5WDdGOmpGeuH4-N0CYXi7FaC_iqEm4gQ=h600"));
        card_creator_ArrayList.add(new Card_Creator_model("Azuki", "TeamAzuki", "56",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non dignissim sem. Quisque in ultrices sapien.",
                "https://lh3.googleusercontent.com/H8jOCJuQokNqGBpkBN5wk1oZwO7LM8bNnrHCaekV2nKjnCqw6UB5oaH8XyNeBDj6bA_n1mjejzhFQUP3O1NfjFLHr3FOaeHcTOOT=s130",
                "https://lh3.googleusercontent.com/O0XkiR_Z2--OPa_RA6FhXrR16yBOgIJqSLdHTGA0-LAhyzjSYcb3WEPaCYZHeh19JIUEAUazofVKXcY2qOylWCdoeBN6IfGZLJ3I4A=h600"));
    }
}