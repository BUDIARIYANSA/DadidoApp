package com.example.dadidoapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dadidoapp.Model.Wallet;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {
    private TextView txtbalance, txtitem_name, txtItem_id, txt_owner_name, txt_price;
    private Button pay;
    private static final String PREFS_NAME = "LoginPrefs";

    private String str_item_name ;
    private String str_price ;
    private String str_Item_id ;
    private String str_own_by ;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //Showing back button
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_light);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Payment");

        getFunds();

        pay = (Button) findViewById(R.id.buttonPayNow);
        txtbalance = (TextView) findViewById(R.id.texttitleBalance);
        txtitem_name = (TextView) findViewById(R.id.texttitlename);
        txtItem_id = (TextView) findViewById(R.id.textIdItem);
        txt_owner_name = (TextView) findViewById(R.id.texttitleOwner);
        txt_price = (TextView) findViewById(R.id.texttitleItemPrice);

        Intent intent = getIntent();
        str_item_name = intent.getStringExtra("file_name");
        str_price = intent.getStringExtra("total_price");
        str_Item_id = intent.getStringExtra("Item_id");
        str_own_by = intent.getStringExtra("owned_by");

        txtitem_name.setText(str_item_name);
        txtItem_id.setText(str_Item_id);
        txt_price.setText(str_price);
        txt_owner_name.setText(str_own_by);
    }

    public void getFunds() {
        ApiList apiList = RetrofitClient.getRetrofitClient().create(ApiList.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "get_funds")
                .addFormDataPart("username", getPreference(getApplicationContext(), "username"))
                .addFormDataPart("password", getPreference(getApplicationContext(), "password"))
                .build();
        Call<ArrayList<Wallet>> call = apiList.getWallet(requestBody);
        call.enqueue(new Callback<ArrayList<Wallet>>() {
            @Override
            public void onResponse(Call<ArrayList<Wallet>> call, Response<ArrayList<Wallet>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Wallet> data = response.body();
                    DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                    df.setMaximumFractionDigits(2);
                    txtbalance.setText(df.format(Float.parseFloat(data.get(0).getTotalFund())) + " " + data.get(0).getWalletType().toUpperCase(Locale.ROOT));
                    pay = (Button) findViewById(R.id.buttonPayNow);
                    String balan = data.get(0).getTotalFund();
                    pay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Buy_item(balan);
                        }
                    });
                } else {
                    Toast.makeText(PaymentActivity.this, "Failed to load data..!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Wallet>> call, Throwable t) {
                Toast.makeText(PaymentActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static String getPreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, "");
    }

    void Buy_item(String bal) {
        ApiList apiList = RetrofitClient.getRetrofitClient().create(ApiList.class);
        String usern = getPreference(PaymentActivity.this, "username");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "buying_item")
                .addFormDataPart("item_id", str_Item_id)
                .addFormDataPart("username_buyer", usern)
                .addFormDataPart("username_seller", str_own_by)
                .addFormDataPart("purchase_price", str_price)
                .addFormDataPart("balance", bal)
                .build();
        Call call = apiList.status_sell(requestBody);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    String res = response.body().toString();
                    if(res.equals("Success Buy")){
                        Intent intent = new Intent(PaymentActivity.this,HomeActivity.class);
                        startActivity(intent);
                        Toast.makeText(PaymentActivity.this, res, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(PaymentActivity.this, res, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

}
