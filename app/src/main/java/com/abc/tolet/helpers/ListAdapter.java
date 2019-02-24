package com.abc.tolet.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abc.tolet.R;
import com.abc.tolet.model.Banquet_Post;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Banquet_Post> {

    Context context;
    ArrayList<Banquet_Post> bm;
    //ArrayList<String> name;
    View view;
    LayoutInflater layoutInflater;
    int mresource;
    int x;

    public ListAdapter(Context context, int mresource,ArrayList<Banquet_Post> bm, int x) {
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
    public View getView(final int i,  View cview,  ViewGroup viewGroup) {

        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ListContentB holder = new ListContentB();
        if(cview == null) {
            view = new View(context);
            view = layoutInflater.inflate(R.layout.bank_item, null);
            holder.imagebtn = view.findViewById(R.id.imagebtn);
            holder.textview = view.findViewById(R.id.textview);
            holder.type = view.findViewById(R.id.type);
            holder.capacity = view.findViewById(R.id.capacity);
            holder.rent = view.findViewById(R.id.rent);
            holder.phn = view.findViewById(R.id.phn);
            holder.address = view.findViewById(R.id.address);
            holder.layout = view.findViewById(R.id.layout);
            holder.imagebtn.setImageBitmap(bm.get(i).getPic());
            holder.textview.setText(bm.get(i).getName());
            holder.type.setText(bm.get(i).getAc());
            holder.capacity.setText(Integer.toString(bm.get(i).getCapacity()));
            holder.rent.setText(Integer.toString(bm.get(i).getRent()));
            holder.phn.setText(Long.toString(bm.get(i).getPhn()));
            holder.address.setText(bm.get(i).getAddress());
            view.setTag(holder);
        }
        /*holder.imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context, Banquet_Details.class);

//                    Bundle args = new Bundle();
                String name = bm.get(i).getName();
                String ac = bm.get(i).getAc();
                int capacity = bm.get(i).getCapacity();
                String address = bm.get(i).getAddress();
                int rent = bm.get(i).getRent();
                long phn = bm.get(i).getPhn();
                intent.putExtra("name", name);
                intent.putExtra("ac", ac);
                intent.putExtra("capacity", capacity);
                intent.putExtra("address", address);
                intent.putExtra("rent", rent);
                intent.putExtra("phn", phn);
                //intent.putParcelableArrayListExtra("list",bm.get(i));
                context.startActivity(intent);
            }
        });*/
        return view;
    }

    class ListContentB{
        ImageButton imagebtn;
        TextView textview,type,capacity,rent,phn,address;
        LinearLayout layout;
    }
}
