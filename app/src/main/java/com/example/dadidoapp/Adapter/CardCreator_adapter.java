package com.example.dadidoapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dadidoapp.DetailCollectionActivity;
import com.example.dadidoapp.DetailItemActivity;
import com.example.dadidoapp.LayoutModel.Card_Creator_model;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;
import com.example.dadidoapp.LayoutModel.Card_Item_Model2;
import com.example.dadidoapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardCreator_adapter extends RecyclerView.Adapter<CardCreator_adapter.cardCreatorViewHolder>{

    private ArrayList<Card_Creator_model> dataList3;
    private Context context;

    public CardCreator_adapter(ArrayList<Card_Creator_model> dataList3, Context context) {
        this.dataList3 = dataList3;
        this.context = context;
    }

    @NonNull
    @Override
    public CardCreator_adapter.cardCreatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardcreator, parent, false);
        return new CardCreator_adapter.cardCreatorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardCreator_adapter.cardCreatorViewHolder holder, int position) {
        final Card_Creator_model dataItem = dataList3.get(position);

        holder.txtCollectionTitle.setText(dataItem.getCollectionTitle());
        holder.txtCreatorName.setText(dataItem.getCreatorName());
        holder.txtTotalFollower.setText(dataItem.getTotalFollower());
        holder.txtDescription.setText(dataItem.getDescription());

        Picasso.get().load(dataItem.getCreatorDP_URL()).into(holder.creatordp);
        Picasso.get().load(dataItem.getCollectionBannerURL()).into(holder.collectionbanner);
    }

    @Override
    public int getItemCount() {
        return (dataList3 != null) ? dataList3.size() : 0;
    }

    public class cardCreatorViewHolder extends RecyclerView.ViewHolder{
        private TextView txtCollectionTitle, txtCreatorName, txtTotalFollower, txtDescription;
        private CardView card;
        private ImageView creatordp, collectionbanner;

        public cardCreatorViewHolder(View itemView) {
            super(itemView);
            txtCollectionTitle = (TextView) itemView.findViewById(R.id.textViewCollectionTitle);
            txtCreatorName = (TextView) itemView.findViewById(R.id.textViewCreatorName);
            txtTotalFollower = (TextView) itemView.findViewById(R.id.textViewFollowerCount);
            txtDescription = (TextView) itemView.findViewById(R.id.textViewCollectionDescription);
            collectionbanner = (ImageView) itemView.findViewById(R.id.imageViewBannerCreator);
            creatordp = (ImageView) itemView.findViewById(R.id.imageViewProfileCreator);

            card = (CardView) itemView.findViewById(R.id.creatorCard);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    card.getContext().startActivity(new Intent(card.getContext(), DetailCollectionActivity.class));
                }
            });
        }
    }
}
