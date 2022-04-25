package com.example.dadidoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateItemActivity extends AppCompatActivity {
    private Button SelectBtn;
    private Button UploadBtn;
    private TextInputLayout f_name;
    private TextInputLayout f_desc;
    private TextInputLayout f_sell_p;

    ImageView img;
    int SELECT_PICTURE = 200;

    private TextView collection_name;
    private String str_coll_name;

    private String path;

    private static final String PREFS_NAME = "LoginPrefs";

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //Showing Back Button
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
        setContentView(R.layout.create_item_main);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_light);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Create New Item");

        SelectBtn = (Button)findViewById(R.id.SelectImageBtn);
        UploadBtn = (Button)findViewById(R.id.buttoncreate);
        f_name = (TextInputLayout) findViewById(R.id.textInputLayoutFileName);
        f_desc = (TextInputLayout) findViewById(R.id.textInputLayoutdesc);
        f_sell_p = (TextInputLayout) findViewById(R.id.textInputLayoutSell);
        img = (ImageView)findViewById(R.id.img);

        SelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        UploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload_image();
            }
        });
    }

    private void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_PICTURE) {

                Uri selectImageUri = data.getData();
                if (null != selectImageUri) {

                    img.setImageURI(selectImageUri);
                    path = getRealPathFromURI(selectImageUri,CreateItemActivity.this);
                    Toast.makeText(CreateItemActivity.this, path, Toast.LENGTH_SHORT).show();
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
        String str_fname = f_name.getEditText().getText().toString().trim();
        String str_fdesc = f_desc.getEditText().getText().toString().trim();
        String str_fsellp = f_sell_p.getEditText().getText().toString().trim();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("CMD", "create_item")
                .addFormDataPart("username", username)
                .addFormDataPart("item_name",str_fname)
                .addFormDataPart("description",str_fdesc)
                .addFormDataPart("price",str_fsellp)
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
                    Toast.makeText(CreateItemActivity.this, res, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CreateItemActivity.this, OwnDetailCollectionActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(CreateItemActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}


