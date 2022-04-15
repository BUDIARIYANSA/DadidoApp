package com.example.dadidoapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dadidoapp.LayoutModel.Card_Creator_model;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;
import com.example.dadidoapp.R;

import java.util.ArrayList;

public class CardCreator_adapter extends RecyclerView.Adapter<CardCreator_adapter.cardCreatorViewHolder>{

    private ArrayList<Card_Creator_model> dataList3;

    public CardCreator_adapter(ArrayList<Card_Creator_model> dataList3) {
        this.dataList3 = dataList3;
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
        holder.txtCollectionTitle.setText(dataList3.get(position).getCollectionTitle());
        holder.txtCreatorName.setText(dataList3.get(position).getCreatorName());
        holder.txtTotalFollower.setText(dataList3.get(position).getTotalFollower());
        holder.txtDescription.setText(dataList3.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return (dataList3 != null) ? dataList3.size() : 0;
    }

    public class cardCreatorViewHolder extends RecyclerView.ViewHolder{
        private TextView txtCollectionTitle, txtCreatorName, txtTotalFollower, txtDescription;

        public cardCreatorViewHolder(View itemView) {
            super(itemView);
            txtCollectionTitle = (TextView) itemView.findViewById(R.id.textViewCollectionTitle);
            txtCreatorName = (TextView) itemView.findViewById(R.id.textViewCreatorName);
            txtTotalFollower = (TextView) itemView.findViewById(R.id.textViewFollowerCount);
            txtDescription = (TextView) itemView.findViewById(R.id.textViewCollectionDescription);
        }
    }
}
