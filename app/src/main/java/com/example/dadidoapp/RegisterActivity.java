package com.example.dadidoapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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

public class RegisterActivity extends AppCompatActivity {

    TextInputLayout usernameField;
    TextInputLayout emailField;
    TextInputLayout fullnameField;
    TextInputLayout passwordField;
    TextInputLayout confirmPasswordField;
    Button btnRegister;
    Button goLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        Drawable dr = ContextCompat.getDrawable(RegisterActivity.this,R.drawable.daradi_logo2);
        Bitmap logo = ((BitmapDrawable) dr).getBitmap();
        // Scale it to 50 x 50
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(logo, 40, 30, true));
        actionBar.setHomeAsUpIndicator(d);// set drawable icon
        actionBar.setDisplayHomeAsUpEnabled(true);

        usernameField = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);
        emailField = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        fullnameField = (TextInputLayout) findViewById(R.id.textInputLayoutFullname);
        passwordField = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        confirmPasswordField = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
        btnRegister = (Button) findViewById(R.id.buttonRegister);
        goLogin = (Button) findViewById(R.id.goLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameField.getEditText().getText().toString().trim();
                String fullname = fullnameField.getEditText().getText().toString().trim();
                String email = emailField.getEditText().getText().toString().trim();
                String password = passwordField.getEditText().getText().toString().trim();
                String confirmPassword = confirmPasswordField.getEditText().getText().toString().trim();

                if(!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Password and Confirm Password not same!", Toast.LENGTH_SHORT).show();
                } else {
                    register(username, fullname, email, password);
                }
            }
        });

        goLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.this.finish();
            }
        });
    }

    public void register(String username, String fullname, String email, String password) {
        ApiList apis = RetrofitClient.getRetrofitClient().create(ApiList.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "register_user")
                .addFormDataPart("username", username)
                .addFormDataPart("fullname", fullname)
                .addFormDataPart("email", email)
                .addFormDataPart("password", password)
                .build();

        Call call = apis.register(requestBody);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()) {
                    String res = response.body().toString();
                    Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to send / get data from server!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}