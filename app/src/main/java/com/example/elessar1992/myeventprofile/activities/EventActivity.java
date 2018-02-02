package com.example.elessar1992.myeventprofile.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.elessar1992.myeventprofile.R;
import com.squareup.picasso.Picasso;

/**
 * Created by elessar1992 on 1/29/18.
 */

public class EventActivity extends AppCompatActivity
{
    String mName;
    String mprice;
    String mrating;
    String mLatlng;
    String mpicture;
    String msuffix;
    String mprefix;
    private TextView name;
    private TextView rating;
    private TextView photourl;
    private TextView latlng;
    private ImageView eventimage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        getSupportActionBar().hide();
        name = (TextView) findViewById(R.id.name);
        //mName = this.getIntent().getStringExtra("Name");
        name.setText(this.getIntent().getStringExtra("Name"));

        rating = (TextView) findViewById(R.id.rating);
        //mrating = this.getIntent().getStringExtra("Rating");
        rating.setText(this.getIntent().getStringExtra("Rating"));

        photourl = (TextView) findViewById(R.id.photourl);
        //mprice = this.getIntent().getStringExtra("Price");
        photourl.setText(this.getIntent().getStringExtra("photourl"));

        latlng = (TextView) findViewById(R.id.latlng);
        //mName = this.getIntent().getStringExtra("Name");
        latlng.setText(this.getIntent().getStringExtra("latlng"));

        //msuffix = this.getIntent().getStringExtra("suffix");
        //mprefix = this.getIntent().getStringExtra("prefix");
        //String combined = msuffix + mprefix;

        //eventimage = (ImageView) findViewById(R.id.eventimage);
        //mpicture = this.getIntent().getStringExtra("photourl");

        //Glide.with(this).load(combined).into(eventimage);

        /*Picasso.with(this)
                .load(mpicture)
                .into(eventimage);*/




    }
}
