package me.shreyasr.charms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CharmSelectAdapter extends BaseAdapter implements AdapterView.OnItemSelectedListener {

    final List<Charm> charms = new ArrayList<Charm>();
    private ViewGroup charmRoot;
    private Spinner spinner;

    public CharmSelectAdapter(ViewGroup charmRoot, Spinner spinner) {
        this.charmRoot = charmRoot;
        this.spinner = spinner;
        charms.add(new TestCharm(0, 0));
        charms.add(new MediaCharm(0, 0));
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        LayoutInflater inflater = (LayoutInflater) ApplicationWrapper.getInstance().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CharmHolder.addCharm(charmRoot, charms.get(position).create(), inflater, true);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
