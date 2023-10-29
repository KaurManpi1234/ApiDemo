package com.example.apidemo;

import static android.content.Intent.getIntent;

import static androidx.core.app.NotificationCompat.getExtras;

import android.app.Notification;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.time.Instant;

public class RoverViewHolder extends RecyclerView.ViewHolder {
    private TextView imageId;
    private TextView cameraName;
    private ImageView cameraImage;
    private TextView date;

    private View view;
    private RoverPicture roverPicture;

    public RoverViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;

        this.imageId = (TextView) itemView.findViewById(R.id.imgId);
        this.cameraName = (TextView) itemView.findViewById(R.id.cameraName);
        this.cameraImage = (ImageView) itemView.findViewById(R.id.cameraImage);
        this.date = (TextView) itemView.findViewById(R.id.date);
    }
    public void updateView(RoverPicture roverPicture){

        this.roverPicture = roverPicture;

        this.imageId.setText("N°: "+String.valueOf(roverPicture.getId()));
        this.cameraName.setText("Camera: "+roverPicture.getRover().getName());
        this.date.setText("Date: "+roverPicture.getDate());

        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).override(250,250);


        Glide.with(this.view).load(Uri.parse(roverPicture.getImage())).apply(options).centerCrop().circleCrop().into(this.cameraImage);

    }



    public View getItemView(){
        return this.itemView;
    }

    public RoverPicture getRoverPicture(){
        return this.roverPicture;
    }
    }

