package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddItemActivity extends AppCompatActivity {
    EditText name,price,quantity;
    CircleImageView circleImageView;
    Button add_item,update;
    Data data;
    dbmanager db;
    int SELECT_PICTURE=101;
    ImageView back_image;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        name=findViewById(R.id.edit_name);
        price=findViewById(R.id.edit_price);
        quantity=findViewById(R.id.edit_quantity);
        add_item=findViewById(R.id.add_item);
        circleImageView=findViewById(R.id.profile);
        update=findViewById(R.id.update);
        back_image=findViewById(R.id.back_img);
        db= new dbmanager(getApplicationContext());
        Intent getIntent=getIntent();

       if(getIntent.getSerializableExtra("data") != null){
            data=(Data) getIntent.getSerializableExtra("data");
          // Log.d("addedItems",data.toString());
           name.setText(data.getName());
            price.setText(data.getPrice());
            quantity.setText(data.getQuantity());
          // byte[] foodImage = data.getImage();
           //Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
          // circleImageView.setImageBitmap(bitmap);
           Update_data();
        }
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Select_image();
            }
        });
        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //imageViewToByte(circleImageView);
                if(TextUtils.isEmpty(name.getText())||TextUtils.isEmpty(price.getText())||TextUtils.isEmpty(quantity.getText()))
                {
                    Toast.makeText(getApplicationContext(), "Empty Credentials", Toast.LENGTH_SHORT).show();
                }
                else{
               new dbmanager(AddItemActivity.this).add_record(name.getText().toString(),price.getText().toString(),quantity.getText().toString(),imageViewToByte(circleImageView));
                startActivity(new Intent(getApplicationContext(),MainActivity.class));}
            }
        });
        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public static byte[] imageViewToByte(CircleImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    private void Select_image() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    try {
                        InputStream inputStream=getContentResolver().openInputStream(selectedImageUri);
                        Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                        circleImageView.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {

                        e.printStackTrace();
                    }}}}}
  /*  public void checkPermission(String permission, int requestCode)
    {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(AddItemActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(AddItemActivity.this, new String[] { permission }, requestCode);
        }
        else {
            Toast.makeText(AddItemActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }*/

    private void Update_data() {
        Log.e("Check","Enter in function");
        add_item.setVisibility(View.GONE);
        update.setVisibility(View.VISIBLE);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mName=name.getText().toString().trim();
                data.setName(mName);
                String rupee=price.getText().toString().trim();
                data.setPrice(rupee);
                String quant=quantity.getText().toString().trim();
                data.setQuantity(quant);
               // byte[] foodImage = data.getImage();
                //Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
                //circleImageView.setImageBitmap(bitmap);
                ///data.setImage(foodImage);
                long temp= db.updateItem(data);
                startActivity(new Intent(getApplicationContext(),BottomHome.class));
                finish();



            }
        });
    }
}