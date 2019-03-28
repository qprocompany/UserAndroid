package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import info.androidhive.Adapter.JadwalDokterAdapter;
import info.androidhive.Adapter.JadwalPoliAdapter;
import info.androidhive.Adapter.ListDokterJadwalDokter;
import info.androidhive.Model.Poli;

public class JadwalDokter extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = PendaftaranPoli.class.getSimpleName();

    Spinner spinner;

    private GridView dokterlist;

    static final ArrayList<String> jadwalListPoli = new ArrayList<String>();

    static final ArrayList<String> jadwalList1 = new ArrayList<String>();
    static final ArrayList<String> jadwalListday = new ArrayList<String>();
    static final ArrayList<String> jadwalListimage = new ArrayList<String>();
    static final ArrayList<String> jadwalListdays = new ArrayList<String>();
    static final ArrayList<String> jadwalListtime = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_dokter);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Jadwal Dokter");

        dokterlist = (GridView) findViewById(R.id.list_view);

        spinner = findViewById(R.id.spinnerdokter);
        new listpoli().execute();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(JadwalDokter.this, jadwalListPoli.get(position), Toast.LENGTH_SHORT).show();
        new listdokter(jadwalListPoli.get(position)).execute();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    class listpoli extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... strings) {
            CallSoap cs = new CallSoap();
            String data1 = cs.Poli("a");
            return data1;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            String data[] = s.split(",");
            for(int i = 0; i < data.length;i++){
                jadwalListPoli.add(data[i]);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(JadwalDokter.this, android.R.layout.simple_spinner_item, jadwalListPoli);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setScrollContainer(true);
            spinner.setOnItemSelectedListener(JadwalDokter.this);
        }
    }

    class listdokter extends AsyncTask<String, String, String>
    {
        private String servunit;

        public listdokter(String servunit) {
            this.servunit = servunit;
        }
        @Override
        protected String doInBackground(String... strings) {
            CallSoap cs = new CallSoap();
            String data1 = cs.JadwalHarian(servunit);
            return data1;
        }




        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(JadwalDokter.this,s,Toast.LENGTH_SHORT).show();
            String data[] = s.split("%");
            jadwalList1.clear();
            jadwalListday.clear();
            jadwalListimage.clear();
            jadwalListdays.clear();
            jadwalListtime.clear();
            String date = "";
            for(int i  = 0; i < data.length;i++)
            {
                if(data[i].equals("-")!=true) {
                    String temp[] = data[i].split("#");
                    jadwalList1.add(temp[1].substring(1));
                    jadwalListday.add(temp[0].substring(1));
                    jadwalListimage.add(temp[2].substring(1));
                    jadwalListdays.add("Senin - Sabtu");
                    jadwalListtime.add("08:00 - 20:00");
                }
            }

            dokterlist.setAdapter(new ListDokterJadwalDokter(JadwalDokter.this, jadwalList1,jadwalListday,jadwalListimage,jadwalListdays,jadwalListtime));
            dokterlist.setOnItemClickListener((parent, view, position, id) ->  {
                        Toast.makeText(JadwalDokter.this,data[position],Toast.LENGTH_SHORT).show();
                    }
            );
        }
    }


}

