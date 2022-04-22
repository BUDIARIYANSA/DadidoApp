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
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dadidoapp.Model.Wallet;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletActivity extends AppCompatActivity {
    private Button buttonAddFunds;
    private TextView totalFunds;
    private ApiList apiList = RetrofitClient.getRetrofitClient().create(ApiList.class);
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String PREFS_NAME = "LoginPrefs";

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_light);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        totalFunds = (TextView) findViewById(R.id.totalFunds);

        getFunds();

        buttonAddFunds = (Button) findViewById(R.id.buttonAddFunds);
        buttonAddFunds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WalletActivity.this, WalletTwoActivity.class));
            }
        });
    }

    public void getFunds() {
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
                    totalFunds.setText(df.format(Float.parseFloat(data.get(0).getTotalFund())) + " " + data.get(0).getWalletType().toUpperCase(Locale.ROOT));
                } else {
                    Toast.makeText(WalletActivity.this, "Failed to load data..!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Wallet>> call, Throwable t) {
                Toast.makeText(WalletActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static String getPreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, "");
    }
}
