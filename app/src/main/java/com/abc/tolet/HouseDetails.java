package com.abc.tolet;

//import android.content.Intent;
//import android.os.Parcelable;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.abc.tolet.model.House_Post;
import com.abc.tolet.sql.HouseHelper;

import java.io.Serializable;
//import java.util.ArrayList;

public class HouseDetails extends AppCompatActivity  {
    private House_Post hpost;

    private TextView own,type,room,address,phn,rent;
    private ImageView img;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);
        Intent intent = getIntent();


        Bundle extras = getIntent().getExtras();
        hpost = (House_Post)getIntent().getSerializableExtra("list");
        own = findViewById(R.id.own);
        type = findViewById(R.id.type);
        room = findViewById(R.id.bhk);
        address = findViewById(R.id.address);
        phn = findViewById(R.id.phn);
        rent = findViewById(R.id.rent);
        img = findViewById(R.id.img);

        own.setText(extras.getString("name"));
       // type.setText(extras.getString("type"));
       // room.setText(extras.getString("bhk"));
        address.setText(extras.getString("address"));
        phn.setText(Long.toString(extras.getLong("phn")));
        rent.setText(Integer.toString(extras.getInt("rent")));
        //img.setImageBitmap(hpost.getPic());
    }
}
