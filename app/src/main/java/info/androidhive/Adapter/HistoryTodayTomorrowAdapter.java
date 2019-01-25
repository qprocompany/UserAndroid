package info.androidhive.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import info.androidhive.recyclerviewsearch.R;

public class HistoryTodayTomorrowAdapter extends BaseAdapter {
    private Context context;
    private final ArrayList<String> Poli,Nama,Tanggal;

    public HistoryTodayTomorrowAdapter(Context context, ArrayList<String> poli,ArrayList<String> nama,ArrayList<String> tanggal) {
        this.context = context;
        this.Poli = poli;
        this.Nama = nama;
        this.Tanggal = tanggal;
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
        TextView textView1 = (TextView) gridView.findViewById(R.id.Tanggal);
        TextView textView2 = (TextView) gridView.findViewById(R.id.Nama);

        textView.setText(Nama.get(position));
        textView1.setText(Poli.get(position));
        textView2.setText(Tanggal.get(position));

        return gridView;
    }


    @Override
    public int getCount() {
        return Poli.size();
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
