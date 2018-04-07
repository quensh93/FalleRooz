// Copyright (c) 2016, iGap - www.iGap.im
// iGap is a Hybrid instant messaging service .
// RooyeKhat Media Co . - www.RooyeKhat.co
// All rights reserved.

package srp.com.fallrooz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import srp.com.fallrooz.R;

import static srp.com.fallrooz.util.Global.iranSans;

public class BloodTypeAdapter extends RecyclerView.Adapter<BloodTypeAdapter.ViewHolder> {

    private Context mContext;

    private ArrayList<String> data;
    private ArrayList<Integer> icData;
    private ViewHolder viewholder;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View roww;
        private ImageView img;
        private TextView txt;

        public ViewHolder(View row) {
            super(row);
            roww = row;

            img = row.findViewById(R.id.img);
            txt = row.findViewById(R.id.txt);
            txt.setTypeface(iranSans);
        }
    }

    public BloodTypeAdapter(Context context)
    {
        this.mContext = context;
        data = new ArrayList<String>();
        icData = new ArrayList<Integer>();

        data.add("A");
        data.add("B");
        data.add("O");
        data.add("AB");

        icData.add(R.mipmap.a);
        icData.add(R.mipmap.b);
        icData.add(R.mipmap.o);
        icData.add(R.mipmap.ab);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_month, parent, false);
        viewholder = new ViewHolder(v);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder h, final int position) {
        h.img.setImageResource(icData.get(position));
        h.txt.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
