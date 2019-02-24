package com.abc.tolet;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import com.abc.tolet.helpers.ListAdapter;
import com.abc.tolet.model.Banquet_Post;
import com.abc.tolet.sql.BanquetHelper;
import java.util.ArrayList;

public class Banquetpage extends AppCompatActivity {

    ListView listView;
    private final AppCompatActivity activity = Banquetpage.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banquetpage);
        listView = findViewById(R.id.gridview);

        Post();

    }

    private void Post(){
        BanquetHelper bhelp = new BanquetHelper(activity);
        int x = bhelp.getCount();
        ArrayList<Banquet_Post> list;
        list = bhelp.getAllUser();
        ListAdapter grid = new ListAdapter(this,R.layout.activity_banquetpage,list,x);
        listView.setAdapter(grid);
    }
}
