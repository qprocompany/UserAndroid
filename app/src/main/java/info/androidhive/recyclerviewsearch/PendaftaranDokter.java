package info.androidhive.recyclerviewsearch;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.androidhive.Adapter.DokterAdapter;
import info.androidhive.Model.Dokter;

import static info.androidhive.recyclerviewsearch.R.*;

public class PendaftaranDokter extends AppCompatActivity implements DokterAdapter.ContactsAdapterListener,DatePickerDialog.OnDateSetListener {
    private static final String TAG = PendaftaranDokter.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Dokter> DokterList;
    private DokterAdapter mAdapter;
    private SearchView searchView;
    private Dokter Dokter;
    private boolean mAutoHighlight;
    public static String message = "null";
    public static String parname;
    public static String servname;
    public static String tgljanjian;
    public static String medno;
    public static String nomAnt;

    AlertDialog ag;

    private static String penampung1;
    private static String workstation;
    private static String servunit;
    private static String paramid;

    public static String date;

    // url to fetch contacts json
    //st.maria
    //private static final String URL = "http://36.91.120.14/SPHAIRA_TRAIN_ADT/Services/GetPhysicianSrv.ashx";
    //qpro db
    private  final String URL = "http://192.168.80.63/Sphaira_LIVE_ADT/Services/GetPhysicianSrv.ashx";
    //rsud palembang
    //private  final String URL = "http://192.168.80.112/SPHAIRA_ADT/Services/GetPhysicianSrv.ashx";
    //public  final String URL = "http://202.152.26.123/SPHAIRA_ADT/Services/GetPhysicianSrv.ashx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_fasilitas);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = findViewById(id.toolbar);
        setSupportActionBar(toolbar);

        penampung1 = Login.username1;
        //Toast.makeText(PendaftaranDokter.this,penampung1,Toast.LENGTH_SHORT).show();

        // toolbar fancy stuffr
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(string.toolbar_title_dokter);

        recyclerView = findViewById(id.recycler_view);
        DokterList = new ArrayList<>();
        mAdapter = new DokterAdapter(this, DokterList, this);
        Dokter = new Dokter();

        // white background notification bar
        whiteNotificationBar(recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);

        fetchContacts();



    }

    private void fetchContacts() {
        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(getApplicationContext(), "Couldn't fetch the contacts! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        List<Dokter> items = new Gson().fromJson(response.toString(), new TypeToken<List<Dokter>>() {
                        }.getType());

                        // adding contacts to contacts list
                        DokterList.clear();
                        DokterList.addAll(items);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, Pendaftaran.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
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
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth,int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
        //String date = "You picked the following date: From- "+dayOfMonth+"/"+(++monthOfYear)+"/"+year+" To "+dayOfMonthEnd+"/"+(++monthOfYearEnd)+"/"+yearEnd;
        date = dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        String todayString = formatter.format(todayDate);

        //Toast.makeText(PendaftaranDokter.this,paramid.toString(),Toast.LENGTH_SHORT).show();
        /*if(tgljanjian.compareTo(todayString) >= 5)
        {
            Toast.makeText(PendaftaranDokter.this,"Tanggal Registrasi Paling Telat Hari Ini",Toast.LENGTH_SHORT).show();
        }
        else {*/
        if (todayString.equals(date)) {
            tgljanjian = date;
            new pendaftarandokter(penampung1, servunit, paramid).execute();
        } else {
            tgljanjian = date;
            new appointmentdokter(penampung1, date, workstation).execute();
        }
        //}
    }

    class appointmentdokter extends AsyncTask<String, String, String>
    {
        private String username,date,work;
        public appointmentdokter(String username, String date, String work1) {
            this.username = username;
            this.date = date;
            this.work = work1;
        }
        @Override
        protected String doInBackground(String... strings) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            CallSoap cs = new CallSoap();
            String data = cs.InfoUser(username);
            String data2[] = data.split(",");
            String Medno = data2[0].toString();
            String name1 = data2[1].toString();
            String email = data2[2].toString();
            medno=Medno;
            String data1 = cs.Appointment(Medno, date, name1, email,work);
            return data1;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //Toast.makeText(PendaftaranDokter.this,s,Toast.LENGTH_SHORT).show();
            if(s.substring(0,s.indexOf("_")).equals("True"))
            {
                message = "Appointment";
                //Toast.makeText(PendaftaranDokter.this,message,Toast.LENGTH_SHORT).show();
                OpenMainMenu();
            }
            else{
                   Toast.makeText(PendaftaranDokter.this,s,Toast.LENGTH_SHORT).show();
                //Toast.makeText(PendaftaranDokter.this,"Maaf Terjadi ke gagalan, Silahkan Mencoba lagi",Toast.LENGTH_SHORT).show();
            }
        }
    }

    class pendaftarandokter extends AsyncTask<String, String, String>
    {
        private String username,servunit,param;
        public pendaftarandokter(String username,String servunit,String paramid) {
            this.username = username;
            this.servunit = servunit;
            this.param = paramid;
            // Do something ...
        }
        @Override
        protected String doInBackground(String... strings) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            CallSoap cs = new CallSoap();
            String data = cs.InfoUser(username);
            String data2[] = data.split(",");
            String Medno = data2[0];
            medno = Medno;
            String data1 = cs.Registration(Medno,servunit,"personal",param);
            return data1;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //Toast.makeText(PendaftaranDokter.this,s,Toast.LENGTH_SHORT).show();
            if(s.substring(0,s.indexOf("_")).equals("True"))
            {
                nomAnt = s.substring(s.indexOf("_")+1);
                message = "Pendaftaran";
                //Toast.makeText(PendaftaranDokter.this,message,Toast.LENGTH_SHORT).show();
                OpenMainMenu();
            }
            else{
                Toast.makeText(PendaftaranDokter.this,s,Toast.LENGTH_SHORT).show();
                //Toast.makeText(PendaftaranDokter.this,"Maaf Pendaftaran Anda Gagal, Silahkan Mencoba lagi",Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onContactSelected(Dokter dokter1) {
        workstation = dokter1.getWorkStationCode().toString();
        servunit = dokter1.getServiceUnitID().toString();
        paramid = dokter1.getParamedicID().toString();
        parname = dokter1.getParamedicName();
        servname = dokter1.getServiceUnitName();
        //  Toast.makeText(PendaftaranDokter.this,dokter1.getPictureFileName(),Toast.LENGTH_SHORT).show();
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = com.borax12.materialdaterangepicker.date.DatePickerDialog.newInstance(
                PendaftaranDokter.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setAutoHighlight(mAutoHighlight);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }
    public void OpenMainMenu(){
        Intent intent = new Intent(this, PasienByDokter.class);
        startActivity(intent);
    }
}
