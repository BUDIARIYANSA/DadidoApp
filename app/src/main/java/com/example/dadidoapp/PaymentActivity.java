package com.example.dadidoapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {
    private TextView txtbalance, txtitem_name, txtItem_id, txt_owner_name, txt_price;
    private Button pay;
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

        pay = (Button) findViewById(R.id.buttonPayNow);
        txtbalance = (TextView) findViewById(R.id.texttitleBalance);
        txtitem_name = (TextView) findViewById(R.id.texttitlename);
        txtItem_id = (TextView) findViewById(R.id.textIdItem);
        txt_owner_name = (TextView) findViewById(R.id.texttitleOwner);
        txt_price = (TextView) findViewById(R.id.texttitleItemPrice);




    }

    public static String getPreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, "");
    }

}
