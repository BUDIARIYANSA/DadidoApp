package com.example.dadidoapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dadidoapp.ApiList;
import com.example.dadidoapp.DetailCollectionActivity;
import com.example.dadidoapp.LayoutModel.Card_Favorite_Model;
import com.example.dadidoapp.LayoutModel.Card_Item_Model;
import com.example.dadidoapp.LayoutModel.Card_Item_Model2;
import com.example.dadidoapp.R;
import com.example.dadidoapp.RetrofitClient;
import com.example.dadidoapp.global_var;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.hanks.library.bang.SmallBangView;

public class CardFavorite_adapter extends RecyclerView.Adapter<CardFavorite_adapter.cardFavoriteViewHolder> {

    private ArrayList<Card_Favorite_Model> dataList;
    private Context context;
    private String url_img;

    public CardFavorite_adapter(ArrayList<Card_Favorite_Model> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }


    @NonNull
    @Override
    public CardFavorite_adapter.cardFavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardfavorite, parent, false);
        return new CardFavorite_adapter.cardFavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardFavorite_adapter.cardFavoriteViewHolder holder, int position) {
        final Card_Favorite_Model dataItem = dataList.get(position);

        holder.image_title.setText(dataItem.getImage_title());
        holder.creator_name.setText(dataItem.getCreator_name());

        String itemUrl = global_var.webURL + dataItem.getImage_url();
        Picasso.get().load(itemUrl).into(holder.imgview);
    }

    @Override
    public int getItemCount()  { return (dataList != null) ? dataList.size() : 0;

    }

    public class cardFavoriteViewHolder extends RecyclerView.ViewHolder {
        private TextView image_title, creator_name;
        private CardView card;
        private ImageView imgview;
        private SmallBangView imgFav;
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;

        private static final String PREFS_NAME = "LoginPrefs";

        public cardFavoriteViewHolder(View FavoriteView) {
            super(FavoriteView);
            image_title = (TextView) FavoriteView.findViewById(R.id.textimg);
            creator_name = (TextView) FavoriteView.findViewById(R.id.textname);

            card = (CardView) FavoriteView.findViewById(R.id.card_model_favorite);
            imgview = (ImageView) FavoriteView.findViewById(R.id.imageViewItemIMG);
            imgFav = (SmallBangView) FavoriteView.findViewById(R.id.imageViewAnimation);
            imgFav.setSelected(true);
            imgFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = cardFavoriteViewHolder.this.getLayoutPosition();
                    final Card_Favorite_Model dataItem = dataList.get(position);

                    if (imgFav.isSelected()) {
                        ApiList apiList = RetrofitClient.getRetrofitClient().create(ApiList.class);
                        RequestBody requestBody = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("CMD", "unfav_item")
                                .addFormDataPart("username", getPreference(context.getApplicationContext(), "username"))
                                .addFormDataPart("password", getPreference(context.getApplicationContext(), "password"))
                                .addFormDataPart("image_url", dataItem.getImage_url())
                                .addFormDataPart("image_title", dataItem.getImage_title())
                                .build();
                        Call call = apiList.favItem(requestBody);
                        call.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                if (response.isSuccessful()) {
                                    String res = response.body().toString();
                                    if (res.equals("success")) {
                                        imgFav.setSelected(false);
                                        imgFav.likeAnimation();

                                        Toast.makeText(context.getApplicationContext(), "Unfavorited", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context.getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {

                            }
                        });

                    } else {
                        ApiList apiList = RetrofitClient.getRetrofitClient().create(ApiList.class);
                        RequestBody requestBody = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("CMD", "fav_item")
                                .addFormDataPart("username", getPreference(context.getApplicationContext(), "username"))
                                .addFormDataPart("password", getPreference(context.getApplicationContext(), "password"))
                                .addFormDataPart("image_url", dataItem.getImage_url())
                                .addFormDataPart("image_title", dataItem.getImage_title())
                                .build();
                        Call call = apiList.favItem(requestBody);
                        call.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                if (response.isSuccessful()) {
                                    String res = response.body().toString();
                                    if (res.equals("success")) {
                                        imgFav.setSelected(true);
                                        imgFav.likeAnimation();
                                        Intent intent = new Intent(card.getContext(), DetailCollectionActivity.class);
                                        intent.putExtra("image_url", dataItem.getImage_url());
                                        intent.putExtra("image_title", dataItem.getImage_title());

                                        Toast.makeText(context.getApplicationContext(), "Favorited", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context.getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {

                            }
                        });
                    }
                }
            });
        }
        public String getPreference(Context context, String key) {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            return settings.getString(key, "");
        }

    }

}
