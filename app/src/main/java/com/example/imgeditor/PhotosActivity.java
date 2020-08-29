package com.example.imgeditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class PhotosActivity extends AppCompatActivity {

    int int_position;
    private GridView gridView;
    GridViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView)findViewById(R.id.gv_folder);
        int_position = getIntent().getIntExtra("value", 0);
        adapter = new GridViewAdapter(PhotosActivity.this,MainActivity.al_images,int_position);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String path=MainActivity.al_images.get(int_position).al_imagepath.get(i);


              Intent intent=new Intent(PhotosActivity.this,EditActivity.class);
              intent.putExtra("position",path);
              startActivity(intent);

            }
        });

    }
}