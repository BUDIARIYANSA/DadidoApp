package com.example.dadidoapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    finish();
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