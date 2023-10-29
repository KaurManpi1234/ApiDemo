package com.example.apidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class RoverItemViewActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView roverName;
    private TextView cameraLongName;
    private TextView cameraShortName;
    private TextView photoId;
    private TextView date;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rover_item_view);
        imageView = findViewById(R.id.imageRoverView);
        roverName = findViewById(R.id.roverRoverView);
        cameraLongName = findViewById(R.id.cameraLongNameRoverView);
        cameraShortName = findViewById(R.id.cameraShortNameRoverView);
        photoId = findViewById(R.id.photoIdRoverView);
        date = findViewById(R.id.dateRoverView);

        roverName.setText("Rover: "+getIntent().getExtras().getString("roverName"));
        cameraLongName.setText("Camera name: "+getIntent().getExtras().getString("cameraLongName"));
        cameraShortName.setText("Camera short name: "+getIntent().getExtras().getString("cameraShortName"));
        photoId.setText("Photo id: "+getIntent().getExtras().getString("photoId"));
        date.setText("Date: "+getIntent().getExtras().getString("date"));

        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).override(250,250);

        Glide.with(this).load(Uri.parse(getIntent().getExtras().getString("imageUrl"))).apply(options).centerCrop().into(this.imageView);

    }
}