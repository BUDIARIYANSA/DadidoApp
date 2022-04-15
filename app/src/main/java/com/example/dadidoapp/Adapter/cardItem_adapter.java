package com.example.dadidoapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dadidoapp.DetailItemActivity;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;
import com.example.dadidoapp.R;

import java.util.ArrayList;

public class cardItem_adapter extends RecyclerView.Adapter<cardItem_adapter.cardItemViewHolder> {

    private ArrayList<Card_Item_Model> dataList;

    public cardItem_adapter(ArrayList<Card_Item_Model> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public cardItem_adapter.cardItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.carditem_one, parent, false);
        return new cardItem_adapter.cardItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cardItem_adapter.cardItemViewHolder holder, int position) {
        holder.txtJudulGambar.setText(dataList.get(position).getNama_item());
        holder.txtTokenId.setText(dataList.get(position).getToken_id());
        holder.txtTotalPrice.setText(dataList.get(position).getTotal_price());
        holder.txtTotallike.setText(dataList.get(position).getTotal_like());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class cardItemViewHolder extends RecyclerView.ViewHolder{
        private TextView txtJudulGambar, txtTokenId, txtTotalPrice, txtTotallike;
        private CardView card;

        public cardItemViewHolder(View itemView) {
            super(itemView);
            txtJudulGambar = (TextView) itemView.findViewById(R.id.textView_judul_gambar);
            txtTokenId = (TextView) itemView.findViewById(R.id.textView_token_id);
            txtTotalPrice = (TextView) itemView.findViewById(R.id.textView_total_price);
            txtTotallike = (TextView) itemView.findViewById(R.id.textView_total_like);

            card = (CardView) itemView.findViewById(R.id.card_model_one);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    card.getContext().startActivity(new Intent(card.getContext(), DetailItemActivity.class));
                }
            });
        }
    }
}
