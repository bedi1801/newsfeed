package com.example.dashmesh.newsfeeder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<RssItem> {

    Context context;
    public CustomArrayAdapter(Context context, int resourceId,
                                 List<RssItem> items) {
        super(context, resourceId, items);
        this.context = context;


    }

    /*private view holder class*/
    private class ViewHolder {
        TextView txtTitle;
        TextView txtDesc;
        TextView txtPub;
        //TextView thumbnail;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RssItem rssitems = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listview, null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(R.id.text4);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.text1);
            holder.txtPub = (TextView) convertView.findViewById(R.id.text5);
            //holder.thumbnail= (TextView) convertView.findViewById(R.id.thumb);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtDesc.setText(rssitems.getDes());
        holder.txtTitle.setText(rssitems.getTitle());
        holder.txtPub.setText(rssitems.getPubDate());
        //holder.thumbnail.setText(rssitems.getThumbnail());

        return convertView;
    }
}