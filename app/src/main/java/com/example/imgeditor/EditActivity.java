package com.example.imgeditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

public class EditActivity extends AppCompatActivity {

    EditView editView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editView=new EditView(this);
        setContentView(editView);

        Intent intent=getIntent();
        String path=intent.getStringExtra("position");
        Log.d("path",path);

        Drawable d=Drawable.createFromPath(path);
        editView.setBackground(d);

        


    }
}