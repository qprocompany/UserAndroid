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

public class HistoryGridAdapter extends BaseAdapter {
    private Context context;
    private final ArrayList<String> namapoli,nama,tanggal;

    public HistoryGridAdapter(Context context, ArrayList<String> namapoli, ArrayList<String> nama,ArrayList<String> tanggal) {
        this.context = context;
        this.namapoli = namapoli;
        this.nama = nama;
        this.tanggal = tanggal;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.user_row_appoint, null);
        } else {
            gridView = (View) convertView;
        }
        // set value into textview
        TextView textView = (TextView) gridView.findViewById(R.id.NamaPoli);
        TextView day = (TextView) gridView
                .findViewById(R.id.Nama);
        TextView time = (TextView) gridView
                .findViewById(R.id.Tanggal);

        textView.setText(namapoli.get(position));
        day.setText(nama.get(position));
        time.setText(tanggal.get(position));
        return gridView;
    }


    @Override
    public int getCount() {
        return namapoli.size();
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
