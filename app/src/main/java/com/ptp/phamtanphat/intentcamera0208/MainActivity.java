package com.ptp.phamtanphat.intentcamera0208;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button btnchuphinh,btnchonhinh;
    ImageView imageViewcamera;
    int Request_Code_Camera = 123;
    int Request_Code_Image = 456;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnchonhinh = (Button) findViewById(R.id.buttonchonhinh);
        btnchuphinh = (Button) findViewById(R.id.buttonchuphinh);
        imageViewcamera = (ImageView) findViewById(R.id.imageviewcamera);

        btnchuphinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Goi MediaStore mo camera api duoi 23
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,Request_Code_Camera);
            }
        });
        btnchonhinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Request_Code_Image);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Request_Code_Camera && resultCode == RESULT_OK && data !=null){
            //Key mac dinh cua camera la data
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageViewcamera.setImageBitmap(bitmap);
        }
        if (requestCode == Request_Code_Image && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                // Tao bien inputsteam truy cap toi duong dan Uri
                InputStream inputStream = getContentResolver().openInputStream(uri);
                //Chuyen gia tri doc duoc thanh kieu cua hinh anh(Bitmap)
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewcamera.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
