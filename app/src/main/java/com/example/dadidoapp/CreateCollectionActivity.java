package com.example.dadidoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCollectionActivity extends AppCompatActivity {

    private Button Uploud_Btn;
    private ImageView img;
    private Button Create_Btn;
    private TextInputLayout c_name;
    private TextInputLayout c_desc;
    private int counter;

    int SELECT_PICTURE = 200;

    private String path;

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
        setContentView(R.layout.create_collection_main);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_light);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Create Your New Collection");

        Uploud_Btn=(Button)findViewById(R.id.SelectImageBtn);
        Create_Btn=(Button)findViewById(R.id.buttoncreate);
        c_name = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);
        c_desc = (TextInputLayout) findViewById(R.id.textInputLayoutdesc);
        img=(ImageView)findViewById(R.id.img);

        counter = 0;

        Uploud_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
                counter = 1;
            }

        });

        Create_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter == 1) {
                    upload_image();
                }

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

                    img.setImageURI(selectImageUri);
                    path = getRealPathFromURI(selectImageUri,CreateCollectionActivity.this);
                    Toast.makeText(CreateCollectionActivity.this, path, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public String getRealPathFromURI(Uri contentUri, Context context) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            //Log.e("EditActivity", "getRealPathFromURI Exception : " + e.toString());
            return "";
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static String getPreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, "");
    }

    public void upload_image(){
        ApiList apiList = RetrofitClient.getRetrofitClient().create(ApiList.class);

        String username = getPreference(getApplicationContext(), "username");

        File file = new File(path);
        RequestBody requestf = RequestBody.create(MediaType.parse("image/*"), file);
        String str_cname = c_name.getEditText().getText().toString().trim();
        String str_cdesc = c_desc.getEditText().getText().toString().trim();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "create_collection")
                .addFormDataPart("username", username)
                .addFormDataPart("collection_name",str_cname)
                .addFormDataPart("description",str_cdesc)
                .addFormDataPart("choosefile", file.getName(),requestf)
                .build();
        System.out.println(requestBody.toString());
        Call call = apiList.createItem(requestBody);
        System.out.println(call.toString());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()){
                    String res = response.body().toString();
                    Toast.makeText(CreateCollectionActivity.this, res, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CreateCollectionActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(CreateCollectionActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
