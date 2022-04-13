package com.example.dadidoapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dadidoapp.LayoutModel.Card_Item_Model;
import com.example.dadidoapp.LayoutModel.Card_Item_Model2;
import com.example.dadidoapp.R;

import java.util.ArrayList;

public class CardItem2_adapter extends RecyclerView.Adapter<CardItem2_adapter.cardItemViewHolder>{
    private ArrayList<Card_Item_Model2> dataList2;

    public CardItem2_adapter(ArrayList<Card_Item_Model2> dataList) {
        this.dataList2 = dataList;
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
        holder.txtjudul_item.setText(dataList2.get(position).getJudul_item());
        holder.txtTop_number.setText(dataList2.get(position).getTop_number());
        holder.txtcreator_name.setText(dataList2.get(position).getCreator_name());
        holder.txtitem_price.setText(dataList2.get(position).getItem_price());
    }

    @Override
    public int getItemCount() {
        return (dataList2 != null) ? dataList2.size() : 0;
    }

    public class cardItemViewHolder extends RecyclerView.ViewHolder{
        private TextView txtjudul_item, txtTop_number, txtcreator_name, txtitem_price;

        public cardItemViewHolder(View itemView) {
            super(itemView);
            txtjudul_item = (TextView) itemView.findViewById(R.id.title_image2);
            txtTop_number = (TextView) itemView.findViewById(R.id.textTop);
            txtcreator_name = (TextView) itemView.findViewById(R.id.created_by);
            txtitem_price = (TextView) itemView.findViewById(R.id.price2);
        }
    }
}
