package me.shreyasr.charms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CharmSelectAdapter extends BaseAdapter {

    final List<Charm> charms = new ArrayList<Charm>();

    public CharmSelectAdapter() {
        charms.add(new TestCharm(0, 0));
        charms.add(new MediaCharm(null, 0, 0));
    }

    @Override
    public int getCount() {
        return charms.size();
    }

    @Override
    public Charm getItem(int position) {
        return charms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) ApplicationWrapper.getInstance().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;
        if (convertView == null)
            view = inflater.inflate(R.layout.spinner_view, parent, false);
        else
            view = convertView;
        ((TextView)view.findViewById(R.id.spinner_view_text)).setText(charms.get(position).name);
        ((ImageView)view.findViewById(R.id.spinner_view_image)).setImageResource(charms.get(position).image);
        return view;
    }
}
