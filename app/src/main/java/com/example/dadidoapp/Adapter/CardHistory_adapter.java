package com.example.dadidoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dadidoapp.LayoutModel.Card_Favorite_Model;
import com.example.dadidoapp.LayoutModel.Card_History_Model;
import com.example.dadidoapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardHistory_adapter extends RecyclerView.Adapter<CardHistory_adapter.cardHistoryViewHolder> {

    private ArrayList<Card_History_Model> dataList;
    private Context context;

    public CardHistory_adapter(ArrayList<Card_History_Model> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;

    }

    @NonNull
    @Override
    public CardHistory_adapter.cardHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_history_item, parent, false);
        return new CardHistory_adapter.cardHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHistory_adapter.cardHistoryViewHolder holder, int position) {
        final Card_History_Model dataItem = dataList.get(position);

        holder.textSale.setText(dataItem.getEvent_history());
        holder.textOwn.setText(dataItem.getOwner());
        holder.textDate.setText(dataItem.getDate_history());

    }

    @Override
    public int getItemCount() { return (dataList != null) ? dataList.size() : 0;

    }

    public class cardHistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView textSale, textOwn, textDate;
        private CardView card;

        public cardHistoryViewHolder(View HistoryView) {
            super(HistoryView);
            textSale = (TextView) HistoryView.findViewById(R.id.textSale);
            textOwn= (TextView) HistoryView.findViewById(R.id.textOwn);
            textDate = (TextView) HistoryView.findViewById(R.id.textDate);

            card = (CardView) HistoryView.findViewById(R.id.card_model_history);
        }

    }
}
