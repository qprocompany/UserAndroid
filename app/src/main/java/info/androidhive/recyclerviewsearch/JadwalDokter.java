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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.GridView;
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
import info.androidhive.Model.Poli;

public class JadwalDokter extends AppCompatActivity implements JadwalPoliAdapter.ContactsAdapterListener {

    private static final String TAG = PendaftaranPoli.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Poli> PoliList;
    private JadwalPoliAdapter mAdapter;

    private GridView dokterlist;

    static final ArrayList<String> jadwalList1 = new ArrayList<String>();
    static final ArrayList<String> jadwalListday = new ArrayList<String>();
    static final ArrayList<String> jadwalListimage = new ArrayList<String>();

    // url to fetch contacts json
    //st.Maria
    //private static final String URL = "http://36.91.120.14/SPHAIRA_TRAIN_ADT/Services/RegistrationAndroid.ashx";
    //Qpro DB
    public  final String URL = "http://192.168.80.63/Sphaira_LIVE_ADT/Services/RegistrationAndroid.ashx";
    //RSUD Palembang
    //public  final String URL = "http://192.168.80.112/SPHAIRA_ADT/Services/RegistrationAndroid.ashx";
    //public  final String URL = "http://202.152.26.123/SPHAIRA_ADT/Services/RegistrationAndroid.ashx";

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
        recyclerView = findViewById(R.id.polirs);
        PoliList = new ArrayList<>();
        mAdapter = new JadwalPoliAdapter(this, PoliList, this);

        whiteNotificationBar(recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);

        fetchContacts();

        // white background notification bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.testbawah);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_Account:
                        Intent intent = new Intent(JadwalDokter.this, Main2Activity.class);
                        startActivity(intent);
                        break;
                    case R.id.ic_Home:
                        Intent intent2 = new Intent(JadwalDokter.this, MainActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.ic_test:
                        Intent intent1 = new Intent(JadwalDokter.this, Appointment.class);
                        startActivity(intent1);
                        break;
                }
                return false;
            }
        });
    }

    private void fetchContacts() {
        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            //    Toast.makeText(getApplicationContext(), "Couldn't fetch the contacts! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        List<Poli> items = new Gson().fromJson(response.toString(), new TypeToken<List<Poli>>() {
                        }.getType());

                        // adding contacts to contacts list
                        PoliList.clear();
                        PoliList.addAll(items);

                        // refreshing recycler view
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        MyApplication.getInstance().addToRequestQueue(request);
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onContactSelected(Poli poli) {
        Toast.makeText(JadwalDokter.this,poli.getServiceUnitName(),Toast.LENGTH_SHORT).show();
        new listdokter(poli.getServiceUnitName()).execute();
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
            //Toast.makeText(PendaftaranDokter.this,s,Toast.LENGTH_SHORT).show();
            String data[] = s.split("%");
            jadwalList1.clear();
            jadwalListday.clear();
            jadwalListimage.clear();
            String date = "";
            for(int i  = 0; i < data.length;i++)
            {
                if(data[i].equals("-")==false)
                {
                    String temp[] = data[i].split("#");
                    //if(temp[4].substring(1) != date) {
                        //jadwalList1.add(date);
                        //jadwalListday.add("");
                        //jadwalListimage.add("");
                     //   if(temp[1] != "-")
                            jadwalList1.add(temp[1].substring(1));
                    //    if(temp[0] != "-")
                            jadwalListday.add(temp[0].substring(1));
                    //    if(temp[2] != "-")
                            jadwalListimage.add(temp[2].substring(1));
                    //}
                    //else{
                    //    if(temp[1] != "-")
                    //        jadwalList1.add(temp[1].substring(1));
                     //   if(temp[0] != "-")
                     //       jadwalListday.add(temp[0].substring(1));
                    //    if(temp[2] != "-")
                    //        jadwalListimage.add(temp[2].substring(1));
                    //}
                }
            }

            dokterlist.setAdapter(new JadwalDokterAdapter(JadwalDokter.this, jadwalList1,jadwalListday,jadwalListimage));
            dokterlist.setOnItemClickListener((parent, view, position, id) ->  {
                        Toast.makeText(JadwalDokter.this,data[position],Toast.LENGTH_SHORT).show();
                    }
            );
        }
    }
}

