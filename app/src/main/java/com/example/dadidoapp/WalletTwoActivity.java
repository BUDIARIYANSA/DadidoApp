package com.example.dadidoapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletTwoActivity extends AppCompatActivity {
    private Button buttonDeposit;
    private TextInputLayout totalFunds;
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
        setContentView(R.layout.activity_wallet_two);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_light);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Adding Fund wallet");

        totalFunds = (TextInputLayout) findViewById(R.id.textInputLayoutMoney);
        buttonDeposit = (Button) findViewById(R.id.buttonDeposit);
        buttonDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String total_funds = Float.toString(Float.parseFloat(totalFunds.getEditText().getText().toString().trim()) / 800000000);
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("CMD", "add_funds")
                        .addFormDataPart("username", getPreference(getApplicationContext(), "username"))
                        .addFormDataPart("password", getPreference(getApplicationContext(), "password"))
                        .addFormDataPart("total_funds", total_funds)
                        .build();
                Call call = apiList.deposit(requestBody);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        if (response.isSuccessful()) {
                            String res = response.body().toString();
                            if(res.equals("success")) {
                                startActivity(new Intent(WalletTwoActivity.this, WalletActivity.class));
                                finish();
                                Toast.makeText(WalletTwoActivity.this, "Deposit funds Successful!!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(WalletTwoActivity.this, "Deposit funds failed!!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(WalletTwoActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(WalletTwoActivity.this, "Error : "+t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public static boolean setPreference(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static String getPreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, "");
    }
}
