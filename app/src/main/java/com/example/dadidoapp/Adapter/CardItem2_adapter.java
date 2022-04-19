package com.example.dadidoapp.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardItem2_adapter extends RecyclerView.Adapter<CardItem2_adapter.cardItemViewHolder>{
    private ArrayList<Card_Item_Model2> dataList2;
    private Context context;

    public CardItem2_adapter(ArrayList<Card_Item_Model2> dataList, Context context) {
        this.dataList2 = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardItem2_adapter.cardItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.carditem_two, parent, false);
        return new CardItem2_adapter.cardItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardItem2_adapter.cardItemViewHolder holder, int position) {
        final Card_Item_Model2 dataItem = dataList2.get(position);

        holder.txtjudul_item.setText(dataItem.getJudul_item());
        holder.txtTop_number.setText(dataItem.getTop_number());
        holder.txtcreator_name.setText(dataItem.getCreator_name());
        holder.txtitem_price.setText(dataItem.getItem_price());

        Picasso.get().load(dataItem.getImageURL()).into(holder.imgview);

        Intent intent = new Intent(holder.card.getContext(), DetailItemActivity.class);
        intent.putExtra("image_url", dataItem.getImageURL());
    }

    @Override
    public int getItemCount() {
        return (dataList2 != null) ? dataList2.size() : 0;
    }

    public class cardItemViewHolder extends RecyclerView.ViewHolder{
        private TextView txtjudul_item, txtTop_number, txtcreator_name, txtitem_price;
        private CardView card;
        private ImageView imgview;

        public cardItemViewHolder(View itemView) {
            super(itemView);
            txtjudul_item = (TextView) itemView.findViewById(R.id.title_image2);
            txtTop_number = (TextView) itemView.findViewById(R.id.textTop);
            txtcreator_name = (TextView) itemView.findViewById(R.id.created_by);
            txtitem_price = (TextView) itemView.findViewById(R.id.price2);

            card = (CardView) itemView.findViewById(R.id.card_model_two);

            imgview = (ImageView) itemView.findViewById(R.id.imageViewItemIMG2);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = cardItemViewHolder.this.getLayoutPosition();
                    final Card_Item_Model2 dataItem = dataList2.get(position);

                    Intent intent = new Intent(card.getContext(), DetailItemActivity.class);

                    intent.putExtra("image_title", dataItem.getJudul_item());
                    //intent.putExtra("TokenId", txtTokenId.getText().toString().trim());
                    intent.putExtra("creator_name", dataItem.getCreator_name());
                    intent.putExtra("TotalPrice", dataItem.getItem_price());
                    intent.putExtra("image_url", dataItem.getImageURL());
                    //intent.putExtra("TotalLike", txtTotallike.getText().toString().trim());

                    card.getContext().startActivity(intent);
                }
            });
        }
    }
}
