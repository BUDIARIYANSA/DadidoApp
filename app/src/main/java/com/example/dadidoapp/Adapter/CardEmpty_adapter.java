package com.example.dadidoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dadidoapp.LayoutModel.Card_Empty_Model;
import com.example.dadidoapp.R;

import java.util.ArrayList;

public class CardEmpty_adapter extends RecyclerView.Adapter<CardEmpty_adapter.cardEmptyViewHolder>{
    private ArrayList<Card_Empty_Model> dataList;
    private Context context;

    public CardEmpty_adapter(ArrayList<Card_Empty_Model> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardEmpty_adapter.cardEmptyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_empty, parent, false);
        return new CardEmpty_adapter.cardEmptyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardEmpty_adapter.cardEmptyViewHolder holder, int position) {
        final Card_Empty_Model dataItem = dataList.get(position);

        holder.txtTitle.setText(dataItem.getTitle_of_empty_card());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class cardEmptyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitle;
        private CardView card;

        public cardEmptyViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.text_empty_desc);

            card = (CardView) itemView.findViewById(R.id.creatorCard);
        }
    }
}
