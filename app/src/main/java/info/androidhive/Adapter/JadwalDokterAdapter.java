package info.androidhive.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import info.androidhive.recyclerviewsearch.R;

public class JadwalDokterAdapter extends BaseAdapter {
    private Context context;
    private final ArrayList<String> mobileValues,mobileValuesday,jadwalListimage,jadwalListDays,jadwalListTime;

    public JadwalDokterAdapter(Context context, ArrayList<String> mobileValues, ArrayList<String> mobileValuesday,ArrayList<String> jadwalListimage,ArrayList<String> jadwalListDays,ArrayList<String> jadwalListTime) {
        this.context = context;
        this.mobileValues = mobileValues;
        this.mobileValuesday = mobileValuesday;
        this.jadwalListimage = jadwalListimage;
        this.jadwalListDays = jadwalListDays;
        this.jadwalListTime = jadwalListTime;
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
        ImageView img = gridView.findViewById(R.id.imagedokter);

        textView.setText(mobileValues.get(position));
        day.setText(mobileValuesday.get(position));
        Picasso.get()
                .load(jadwalListimage.get(position))
                .into(img);

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

