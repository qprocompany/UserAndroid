package info.androidhive.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import info.androidhive.Adapter.HistoryGridAdapter;
import info.androidhive.recyclerviewsearch.CallSoap;
import info.androidhive.recyclerviewsearch.Login;
import info.androidhive.recyclerviewsearch.R;


public class FragmentToday extends Fragment {
    private static final String TAG = "Tab1Fragment";

    private Button btnTEST;

    GridView dokterlist;

    final ArrayList<String> jadwalList1 = new ArrayList<String>();
    final ArrayList<String> jadwalListday = new ArrayList<String>();
    final ArrayList<String> jadwalListimage = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today,container,false);
//        btnTEST = (Button) view.findViewById(R.id.btnTEST3);

//        btnTEST.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 3",Toast.LENGTH_SHORT).show();
//            }
//        });

        dokterlist = (GridView) view.findViewById(R.id.registgrid);
        new listdokter().execute();
        return view;
    }

    class listdokter extends AsyncTask<String, String, String>
    {
        private String servunit;

        @Override
        protected String doInBackground(String... strings) {
            CallSoap cs = new CallSoap();
            String data = cs.InfoUser(Login.username1);
            String data2[] = data.split(",");
            String Medno = data2[0].toString();
            String data1 = cs.HistoryForToday(Medno);
            return data1;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
            jadwalList1.clear();
            jadwalListday.clear();
            jadwalListimage.clear();

            if(s.equals("No Data")) {
                jadwalList1.add("");
                jadwalListday.add(s);
                jadwalListimage.add("");
            }
            else {
                String data[] = s.split("%");

                for (int i = 0; i < data.length; i++) {
                    String data1[] = data[i].split("#");
                    jadwalList1.add(data1[1]);
                    jadwalListday.add(data1[2]);
                    jadwalListimage.add(data1[0]);
                }
            }
            dokterlist.setAdapter(new HistoryGridAdapter(getContext(), jadwalList1,jadwalListday,jadwalListimage));
        }
    }
}
