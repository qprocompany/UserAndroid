package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.Toast;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;

import java.util.ArrayList;

import info.androidhive.Adapter.JadwalDokterAdapter;
import info.androidhive.Adapter.SectionsPageAdapter;

public class JadwalDokter extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private CalendarView calendarView;
    public static String date = "null";
    private GridView listView;
    private JadwalDokterAdapter mAdapter;
    private static ArrayList<String> jadwalList,jadwalListday;
    public static String parid;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_dokter);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // toolbar fancy stuffr
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Jadwal Dokter");
        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if( month < 10)
                {
                    if(dayOfMonth < 10) {
                        date = "0" + dayOfMonth + "/" + "0"+(++month) + "/" + year;
                    }
                    else
                    {
                        date =  dayOfMonth + "/"+ "0" +(++month) + "/" + year;
                    }
                }
                else
                {
                    if(dayOfMonth < 10) {
                        date = "0" + dayOfMonth + "/" +(++month) + "/" + year;
                    }
                    else
                    {
                        date = dayOfMonth + "/" +(++month) + "/" + year;
                    }
                }
                new jadwaldokter(date).execute();
            }
        });
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
                        break;
                    case R.id.ic_Home:
                        Intent intent2 = new Intent(JadwalDokter.this, MainActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.ic_test:
                        //   Intent intent1 = new Intent(PendaftaranPoli.this, PendaftaranDokter.class);
                        //  startActivity(intent1);
                        break;
                }
                return false;
            }
        });
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
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    class jadwaldokter extends AsyncTask<String, String, String>
    {
        private String val3;
        public jadwaldokter(String a) {
            this.val3 = a;
        }
        @Override
        protected String doInBackground(String... strings) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            CallSoap cs = new CallSoap();
            data = cs.JadwalHarian(val3);
            return data;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(JadwalDokter.this,s.toString(),Toast.LENGTH_SHORT).show();
            jadwalList = new ArrayList<String>();
            jadwalListday = new ArrayList<String>();
            jadwalList.clear();
            jadwalListday.clear();
            String data1[] = s.split("%");
            for (int i = 0; i < data1.length-1; i++) {
                String data2[] = data1[i].split("#");
                jadwalList.add(data2[1].substring(1));
                jadwalListday.add(" ");
            }
            listView = (GridView) findViewById(R.id.list_view);
            listView.setAdapter(new JadwalDokterAdapter(JadwalDokter.this, jadwalList,jadwalListday));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String temp[] = s.split("%");
                    String temp1[] = temp[position].split("#");
                    parid = temp1[3].substring(1).toString();
                    //Toast.makeText(JadwalDokter.this,parid,Toast.LENGTH_SHORT).show();
                    OpenMainMenu();
                }
            });
        }
    }
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
    }
    public void OpenMainMenu(){
        Intent intent = new Intent(this, DetailDokter.class);
        startActivity(intent);
    }
}

