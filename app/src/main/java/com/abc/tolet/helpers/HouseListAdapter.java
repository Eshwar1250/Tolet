package com.abc.tolet.helpers;

import android.content.Context;
//import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

//import com.abc.tolet.Homepage;
//import com.abc.tolet.House;
import com.abc.tolet.House;
import com.abc.tolet.HouseDetails;
import com.abc.tolet.R;
//import com.abc.tolet.model.Banquet_Post;
import com.abc.tolet.model.Banquet_Post;
import com.abc.tolet.model.House_Post;

import java.io.Serializable;
import java.util.ArrayList;

public class HouseListAdapter extends ArrayAdapter<House_Post> implements Serializable {
    private Context context;
    private ArrayList<House_Post> bm;
    //ArrayList<String> name;
    private View view;
    private LayoutInflater layoutInflater;
    private int mresource;
    private int x;
    private House_Post hpost;



    public HouseListAdapter(Context context, int mresource, ArrayList<House_Post> bm, int x) {
        super(context, mresource, bm);
        this.context = context;
        this.bm = bm;
        this.mresource=mresource;
        this.x = x;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(final  int i, View cview, ViewGroup viewGroup) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ListContent holder = new ListContent();
        if(cview == null) {
            view = new View(context);
            view = layoutInflater.inflate(R.layout.single_post, null);
            holder.imagebtn = view.findViewById(R.id.imagebtn);
            holder.textview = view.findViewById(R.id.textview);
            holder.layout = view.findViewById(R.id.layout);
            holder.imagebtn.setImageBitmap(bm.get(i).getPic());
            holder.textview.setText(bm.get(i).getName());
            //view.setTag(holder);
        }


           holder.imagebtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent(context , HouseDetails.class);

//                    Bundle args = new Bundle();
                    String name = bm.get(i).getName();
                    String thouse = bm.get(i).getType();
                    String bhk = bm.get(i).getBhk();
                    String address = bm.get(i).getAddress();
                    int rent = bm.get(i).getRent();
                    long phn = bm.get(i).getPhn();
                    intent.putExtra("name",  name);
                    intent.putExtra("type",  thouse);
                    intent.putExtra("bhk",  bhk);
                    intent.putExtra("address", address);
                    intent.putExtra("rent",  rent);
                    intent.putExtra("phn",  phn);
                    //intent.putParcelableArrayListExtra("list",bm.get(i));
                    context.startActivity(intent);
                }
            });
        return view;
    }
}

class ListContent{
    ImageButton imagebtn;
    TextView textview;
    LinearLayout layout;
}
