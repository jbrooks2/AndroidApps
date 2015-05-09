package com.detroitteatime.uidemo2;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<RowModel> data;
    private static LayoutInflater inflater=null;
    Holder holder;

    public CustomAdapter(Context context, List<RowModel> data){
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View rowView = null;
        if(view == null){
            rowView = inflater.inflate(R.layout.row, null);
            holder = new Holder();
            holder.rb = (RatingBar)rowView.findViewById(R.id.ratingBar);
            holder.tv = (TextView)rowView.findViewById(R.id.rowLabel);
            rowView.setTag(holder);

            RatingBar.OnRatingBarChangeListener l=
                    new RatingBar.OnRatingBarChangeListener() {
                        public void onRatingChanged(RatingBar ratingBar,
                                                    float rating, boolean fromTouch) {
                            data.get(i).setStars(rating);
                        }
                    };
        }else{
            rowView = view;
        }
        //fill row with data
        RowModel rm = data.get(i);
        holder = (Holder)rowView.getTag();
        holder.rb.setRating(rm.getStars());
        holder.tv.setText(rm.getText());

        return rowView;
    }

    private class Holder{
        TextView tv;
        RatingBar rb;
    }


    @Override
    public int getCount() {
        if(data.size()<=0)
            return 1;
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
