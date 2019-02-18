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

public class ListDokterJadwalDokter extends BaseAdapter {
    private Context context;
    private final ArrayList<String> mobileValues,mobileValuesday,jadwalListimage,days,time;

    public ListDokterJadwalDokter(Context context, ArrayList<String> mobileValues, ArrayList<String> mobileValuesday,ArrayList<String> jadwalListimage,ArrayList<String> days,ArrayList<String> time) {
        this.context = context;
        this.mobileValues = mobileValues;
        this.mobileValuesday = mobileValuesday;
        this.jadwalListimage = jadwalListimage;
        this.days = days;
        this.time = time;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.user_row_jadwal_dokter_list, null);
        } else {
            gridView = (View) convertView;
        }
        // set value into textview
        TextView textView = (TextView) gridView
                .findViewById(R.id.name1);
        TextView day = (TextView) gridView
                .findViewById(R.id.phone1);
        ImageView img = gridView.findViewById(R.id.thumbnail1);
        TextView day1 = gridView.findViewById(R.id.day1);
        TextView time1 = gridView.findViewById(R.id.time1);

        textView.setText(mobileValues.get(position));
        day.setText(mobileValuesday.get(position));
        Picasso.get()
                .load(jadwalListimage.get(position))
                .into(img);

        day1.setText(days.get(position));
        time1.setText(time.get(position));
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

