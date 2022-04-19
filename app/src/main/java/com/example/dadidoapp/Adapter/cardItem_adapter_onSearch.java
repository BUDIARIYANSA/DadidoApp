package com.example.dadidoapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dadidoapp.DetailItemActivity;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;
import com.example.dadidoapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class cardItem_adapter_onSearch extends RecyclerView.Adapter<cardItem_adapter_onSearch.cardItemViewHolder> implements Filterable {

    private ArrayList<Card_Item_Model> dataList;
    private ArrayList<Card_Item_Model> dataListfull;
    private Context context;
    private String url_img;

    public cardItem_adapter_onSearch(ArrayList<Card_Item_Model> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
        dataListfull = new ArrayList<>(dataList);
    }

    @NonNull
    @Override
    public cardItem_adapter_onSearch.cardItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.carditem_one, parent, false);
        return new cardItem_adapter_onSearch.cardItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cardItem_adapter_onSearch.cardItemViewHolder holder, int position) {
        final Card_Item_Model dataItem = dataList.get(position);

        holder.txtJudulGambar.setText(dataItem.getNama_item());
        holder.txtTokenId.setText(dataItem.getToken_id());
        holder.txtTotalPrice.setText(dataItem.getTotal_price());
        holder.txtTotallike.setText(dataItem.getTotal_like());

        Picasso.get().load(dataItem.getPictureURL()).into(holder.imgview);

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
                    int position = cardItem_adapter_onSearch.cardItemViewHolder.this.getLayoutPosition();
                    final Card_Item_Model dataItem = dataList.get(position);

                    Intent intent = new Intent(card.getContext(), DetailItemActivity.class);

                    intent.putExtra("image_title", dataItem.getNama_item());
                    intent.putExtra("TokenId", dataItem.getToken_id());
                    intent.putExtra("TotalPrice", dataItem.getTotal_price());
                    intent.putExtra("TotalLike", dataItem.getTotal_like());
                    intent.putExtra("image_url", dataItem.getPictureURL());
                    card.getContext().startActivity(intent);

                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter(){
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Card_Item_Model> filteredlist = new ArrayList<>();

            if(charSequence == null || charSequence.length()==0){
                filteredlist.addAll(dataListfull);
            }else{
                String filterpattern = charSequence.toString().toLowerCase().trim();

                for (Card_Item_Model item : dataListfull){
                    if(item.getNama_item().toLowerCase().contains(filterpattern)){
                        filteredlist.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            dataList.clear();
            dataList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
