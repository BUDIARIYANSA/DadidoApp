package com.example.dadidoapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dadidoapp.Model.Profile;
import com.example.dadidoapp.Model.User;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static final String PREFS_NAME = "LoginPrefs";

    private ImageView profile_url;
    private Button update, changeprofile;
    private ImageButton location;
    private TextInputLayout username;
    private TextInputLayout email;
    private TextInputLayout home_address;
    private TextInputLayout fullname;
    private TextInputLayout oldpass;
    private TextInputLayout newpass;
    private TextInputLayout confirm_newpass;
    private ApiList apiList = RetrofitClient.getRetrofitClient().create(ApiList.class);
    private ImageView img_profile;
    private String str_location;

    int SELECT_PICTURE = 200;

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
        setContentView(R.layout.profile_main);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_light);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Profile");

        profile_url = (ImageView) findViewById(R.id.imageProfile);
        username = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);
        email = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        fullname = (TextInputLayout) findViewById(R.id.textInputLayoutFullmame);
        home_address = (TextInputLayout) findViewById(R.id.textInputLayoutHomeAddress);
        oldpass = (TextInputLayout) findViewById(R.id.textInputLayoutOldPassword);
        newpass = (TextInputLayout) findViewById(R.id.textInputLayoutNewPassword);
        confirm_newpass = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmNewPassword);
        location = (ImageButton) findViewById(R.id.imageButton_location);

        Intent intent = getIntent();
        str_location = intent.getStringExtra("map_position");

        loadDataProfile(str_location);




        update = (Button) findViewById(R.id.buttonUpdate);
        changeprofile = (Button) findViewById(R.id.buttonChangeProfile);
        img_profile = (ImageView) findViewById(R.id.imageProfile);

       changeprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { imageChooser(); }

        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataProfile();
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });
    }

    private void imageChooser() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        startActivityForResult(Intent.createChooser(pickIntent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_PICTURE) {

                Uri selectImageUri = data.getData();
                if (null != selectImageUri) {

                    img_profile.setImageURI(selectImageUri);
                }
            }
        }
    }

    public void updateDataProfile() {

        String user_name = username.getEditText().getText().toString().trim();
        String user_fullname = fullname.getEditText().getText().toString().trim();
        String user_email = email.getEditText().getText().toString().trim();
        String user_home_address = home_address.getEditText().getText().toString().trim();
        String old_pass = oldpass.getEditText().getText().toString().trim();
        String new_pass = newpass.getEditText().getText().toString().trim();
        String confirm_newPass = confirm_newpass.getEditText().getText().toString().trim();

        if(TextUtils.isEmpty(user_name)) {
            Toast.makeText(this, "Username can't empty..!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(user_fullname)) {
            Toast.makeText(this, "Fullname can't empty..!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(user_email)) {
            Toast.makeText(this, "Email can't empty..!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(old_pass)) {
            Toast.makeText(this, "Old password can't empty..!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(new_pass)) {
            Toast.makeText(this, "New password can't empty..!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(confirm_newPass)) {
            Toast.makeText(this, "Confirm password can't empty..!", Toast.LENGTH_SHORT).show();
        } else {
            if(!(new_pass).equals(confirm_newPass)) {
                Toast.makeText(ProfilActivity.this, "New password and confirm new password not same!", Toast.LENGTH_SHORT).show();
            } else {
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("CMD", "update_profile")
                        .addFormDataPart("username", user_name)
                        .addFormDataPart("email", user_email)
                        .addFormDataPart("fullname", user_fullname)
                        .addFormDataPart("home_address", user_home_address)
                        .addFormDataPart("old_password", old_pass)
                        .addFormDataPart("new_password", new_pass)
                        .build();

                Call call = apiList.updateProfile(requestBody);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        if(response.isSuccessful()) {
                            String res = response.body().toString();
                            if(res.equals("success")) {
                                setPreference(ProfilActivity.this, "username", user_name);
                                setPreference(ProfilActivity.this, "password", new_pass);

                                Toast.makeText(ProfilActivity.this, "Update profile successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ProfilActivity.this, HomeActivity.class));
                                finish();
                            } else {
                                Toast.makeText(ProfilActivity.this, "Update profile failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ProfilActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(ProfilActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public void loadDataProfile(String str_location) {
        String str_username = getPreference(ProfilActivity.this, "username");
        String str_password = getPreference(ProfilActivity.this, "password");

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "profile_user")
                .addFormDataPart("username", str_username)
                .addFormDataPart("password", str_password)
                .build();
        Call<ArrayList<Profile>> call = apiList.profile(requestBody);
        call.enqueue(new Callback<ArrayList<Profile>>() {
            @Override
            public void onResponse(Call<ArrayList<Profile>> call, Response<ArrayList<Profile>> response) {
                if(response.isSuccessful()) {
                    ArrayList<Profile> data = response.body();

                    String DPurl = global_var.webURL + data.get(0).getProfileURL();
                    Picasso.get().load(DPurl).into(profile_url);
                    username.getEditText().setText(data.get(0).getUsername());
                    fullname.getEditText().setText(data.get(0).getFullname());
                    email.getEditText().setText(data.get(0).getEmail());
                    oldpass.getEditText().setText(data.get(0).getPassword());


                    if (str_location == null) {
                        home_address.getEditText().setText(data.get(0).getHomeAddress());
                    } else {
                        home_address.getEditText().setText(str_location);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Profile>> call, Throwable t) {

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
