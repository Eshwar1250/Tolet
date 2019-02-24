package com.abc.tolet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.abc.tolet.helpers.HouseListAdapter;
import com.abc.tolet.helpers.ListAdapter;
import com.abc.tolet.model.Banquet_Post;
import com.abc.tolet.model.House_Post;
import com.abc.tolet.sql.BanquetHelper;
import com.abc.tolet.sql.HouseHelper;

import java.util.ArrayList;
//import com.abc.tolet.ImageAdapter;

public class Homepage extends AppCompatActivity {

    private BottomNavigationView bottom;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        listView = findViewById(R.id.gridview);

        Post();

        bottom= findViewById(R.id.bottom);
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.post){
                    Intent intentChoose = new Intent(getApplicationContext(), Choose.class);
                    startActivity(intentChoose);
                }
                else if (item.getItemId()==R.id.halls){
                    Intent intentChoose = new Intent(getApplicationContext(), Banquetpage.class);
                    startActivity(intentChoose);
                }
                else if (item.getItemId()==R.id.home){
                    finish();
                    startActivity(new Intent(getApplicationContext() , Homepage.class));
                    //listView.smoothScrollToPosition(0);

                }
                return false;
            }
        });

    }

    private final AppCompatActivity activity = Homepage.this;
    private void Post(){
        HouseHelper hhelp = new HouseHelper(activity);
        int x = hhelp.getCount();
        ArrayList<House_Post> list;
        list = hhelp.getAllUser();
        HouseListAdapter grid = new HouseListAdapter(this,R.layout.activity_homepage,list,x);
        listView.setAdapter(grid);



    }
}
