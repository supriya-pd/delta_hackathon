package com.example.imgeditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.UUID;

public class EditActivity extends AppCompatActivity {

    EditView editView;
    Button saveBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editView=findViewById(R.id.customView);
        saveBTN=findViewById(R.id.saveBTN);

        Intent intent=getIntent();
        String path=intent.getStringExtra("position");
        Log.d("path",path);

        Drawable d=Drawable.createFromPath(path);
        editView.setBackground(d);


        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editView.setDrawingCacheEnabled(true);

                String imgSaved = MediaStore.Images.Media.insertImage(
                        getContentResolver(), editView.getDrawingCache(),
                        UUID.randomUUID().toString()+".png", "drawing");
                //feedback
                if(imgSaved!=null){
                    Toast savedToast = Toast.makeText(getApplicationContext(),
                            "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                    savedToast.show();
                }
                else{
                    Toast unsavedToast = Toast.makeText(getApplicationContext(),
                            "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                    unsavedToast.show();
                }
            }
        });


    }
}