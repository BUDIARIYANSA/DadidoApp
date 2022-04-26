package com.example.dadidoapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextInputLayout passwordField;
    TextInputLayout usernameField;
    Button btnLogin;
    Button goRegis;
    CheckBox rememberme;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
        } else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(
                    Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        ActionBar actionBar = getSupportActionBar();
        Drawable dr = ContextCompat.getDrawable(MainActivity.this,R.drawable.daradi_logo2);
        Bitmap logo = ((BitmapDrawable) dr).getBitmap();
        // Scale it to 40 x 30
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(logo, 40, 30, true));
        actionBar.setHomeAsUpIndicator(d);// set drawable icon
        actionBar.setDisplayHomeAsUpEnabled(true);

        ActivityResultLauncher<Intent> intentLaunch = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        usernameField.getEditText().setText(result.getData().getStringExtra("username"));
                        passwordField.getEditText().setText(result.getData().getStringExtra("password"));
                    }
                }
        );

        passwordField = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        usernameField = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        goRegis = (Button) findViewById(R.id.goRegister);
        rememberme = (CheckBox) findViewById(R.id.checkBox_rememberMe);

        sharedPreferences=getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        editor=sharedPreferences.edit();

        String username=sharedPreferences.getString("username","");
        String password=sharedPreferences.getString("password","");
        String remember=sharedPreferences.getString("remember","");

        if (!remember.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();

            }
        });

        goRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                intentLaunch.launch(intent);
            }
        });
    }

    public void login() {
        ApiList apis = RetrofitClient.getRetrofitClient().create(ApiList.class);
        String username = usernameField.getEditText().getText().toString().trim();
        String password = passwordField.getEditText().getText().toString().trim();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "login_user")
                .addFormDataPart("username", username)
                .addFormDataPart("password", password)
                .build();

        Call call = apis.login(requestBody);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()) {
                    String res = response.body().toString();
                    if(res.equals("success")) {
                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                        if(rememberme.isChecked()){
                            sharedPreferences=getSharedPreferences("LoginPrefs", MODE_PRIVATE);
                            editor=sharedPreferences.edit();
                            editor.putString("username",usernameField.getEditText().getText().toString().trim());
                            editor.putString("password",passwordField.getEditText().getText().toString().trim());
                            editor.putString("remember","remember");
                            editor.commit();
                        }else{
                            sharedPreferences=getSharedPreferences("LoginPrefs", MODE_PRIVATE);
                            editor=sharedPreferences.edit();
                            editor.putString("username",usernameField.getEditText().getText().toString().trim());
                            editor.putString("password",passwordField.getEditText().getText().toString().trim());
                            editor.putString("remember","");
                            editor.commit();
                        }
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}