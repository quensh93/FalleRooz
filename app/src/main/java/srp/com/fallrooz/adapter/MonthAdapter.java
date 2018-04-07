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

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.ViewHolder> {

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

    public MonthAdapter(Context context)
    {
        this.mContext = context;
        data = new ArrayList<String>();
        icData = new ArrayList<Integer>();
        data.add("فروردین");
        data.add("اردیبهشت");
        data.add("خرداد");
        data.add("تیر");
        data.add("مرداد");
        data.add("شهریور");
        data.add("مهر");
        data.add("آبان");
        data.add("آذر");
        data.add("دی");
        data.add("بهمن");
        data.add("اسفند");


        icData.add(R.mipmap.m1);
        icData.add(R.mipmap.m2);
        icData.add(R.mipmap.m3);
        icData.add(R.mipmap.m4);
        icData.add(R.mipmap.m5);
        icData.add(R.mipmap.m6);
        icData.add(R.mipmap.m7);
        icData.add(R.mipmap.m8);
        icData.add(R.mipmap.m9);
        icData.add(R.mipmap.m10);
        icData.add(R.mipmap.m11);
        icData.add(R.mipmap.m12);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_month, parent, false);
        viewholder = new ViewHolder(v);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder h, final int position) {

        h.txt.setText(data.get(position));
        h.img.setImageResource(icData.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
