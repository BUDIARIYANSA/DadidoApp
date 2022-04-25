package com.example.dadidoapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dadidoapp.DetailItemActivity;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;
import com.example.dadidoapp.LayoutModel.Card_Item_Model2;
import com.example.dadidoapp.R;
import com.example.dadidoapp.global_var;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class cardItem_adapter extends RecyclerView.Adapter<cardItem_adapter.cardItemViewHolder> {

    private ArrayList<Card_Item_Model> dataList;
    private Context context;
    private String url_img;

    public cardItem_adapter(ArrayList<Card_Item_Model> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
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
        final Card_Item_Model dataItem = dataList.get(position);

        holder.txtJudulGambar.setText(dataItem.getNama_item());
        holder.txtTokenId.setText(dataItem.getToken_id());
        holder.txtTotalPrice.setText(dataItem.getTotal_price());
        holder.txtTotallike.setText(dataItem.getTotal_like());

        String itemUrl = global_var.webURL + dataItem.getPictureURL();
        Picasso.get().load(itemUrl).into(holder.imgview);

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class cardItemViewHolder extends RecyclerView.ViewHolder{
        private TextView txtJudulGambar, txtTokenId, txtTotalPrice, txtTotallike;
        private CardView card;
        private ImageView imgview;

        public cardItemViewHolder(View itemView) {
            super(itemView);
            txtJudulGambar = (TextView) itemView.findViewById(R.id.textView_judul_gambar);
            txtTokenId = (TextView) itemView.findViewById(R.id.textView_token_id);
            txtTotalPrice = (TextView) itemView.findViewById(R.id.textView_total_price);
            txtTotallike = (TextView) itemView.findViewById(R.id.textView_total_like);

            card = (CardView) itemView.findViewById(R.id.card_model_one);

            imgview = (ImageView) itemView.findViewById(R.id.imageViewItemIMG);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = cardItem_adapter.cardItemViewHolder.this.getLayoutPosition();
                    final Card_Item_Model dataItem = dataList.get(position);

                    Intent intent = new Intent(card.getContext(), DetailItemActivity.class);
                    String itemUrl = global_var.webURL + dataItem.getPictureURL();

                    intent.putExtra("image_title", dataItem.getNama_item());
                    intent.putExtra("TokenId", dataItem.getToken_id());
                    intent.putExtra("TotalPrice", dataItem.getTotal_price());
                    intent.putExtra("TotalLike", dataItem.getTotal_like());
                    intent.putExtra("image_url", itemUrl);
                    card.getContext().startActivity(intent);

                }
            });
        }
    }
}
