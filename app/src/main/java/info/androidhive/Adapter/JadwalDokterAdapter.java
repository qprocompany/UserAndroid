package info.androidhive.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import info.androidhive.recyclerviewsearch.R;

public class JadwalDokterAdapter extends BaseAdapter {
    private Context context;
    private final ArrayList<String> mobileValues,mobileValuesday;

    public JadwalDokterAdapter(Context context, ArrayList<String> mobileValues, ArrayList<String> mobileValuesday) {
        this.context = context;
        this.mobileValues = mobileValues;
        this.mobileValuesday = mobileValuesday;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.user_row_jadwal, null);
        } else {
            gridView = (View) convertView;
        }
        // set value into textview
        TextView textView = (TextView) gridView
                .findViewById(R.id.name);
        TextView day = (TextView) gridView
                .findViewById(R.id.day);

        textView.setText(mobileValues.get(position));
        day.setText(mobileValuesday.get(position));
        return gridView;
    }


    @Override
    public int getCount() {
        return mobileValues.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}

