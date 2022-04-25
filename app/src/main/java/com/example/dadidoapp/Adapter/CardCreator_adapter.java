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
import com.example.dadidoapp.RetrofitClient;
import com.example.dadidoapp.global_var;
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

        String DPurl = global_var.webURL + dataItem.getCreatorDP_URL();
        String Bannerurl = global_var.webURL + dataItem.getCollectionBannerURL();

        Picasso.get().load(DPurl).into(holder.creatordp);
        Picasso.get().load(Bannerurl).into(holder.collectionbanner);
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
                    int position = CardCreator_adapter.cardCreatorViewHolder.this.getLayoutPosition();
                    final Card_Creator_model dataItem = dataList3.get(position);

                    Intent intent = new Intent(card.getContext(), DetailCollectionActivity.class);
                    intent.putExtra("image_banner", dataItem.getCollectionBannerURL());
                    intent.putExtra("image_profile", dataItem.getCreatorDP_URL());
                    intent.putExtra("creator_name", dataItem.getCreatorName());
                    intent.putExtra("collection_title",dataItem.getCollectionTitle());
                    intent.putExtra("description",dataItem.getDescription());
                    intent.putExtra("total_follower",dataItem.getTotalFollower());

                    card.getContext().startActivity(intent);
                }
            });
        }
    }
}
