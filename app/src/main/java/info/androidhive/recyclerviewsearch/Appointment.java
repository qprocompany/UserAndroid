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
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;

import java.util.ArrayList;

import info.androidhive.Adapter.HistoryTodayTomorrowAdapter;
import info.androidhive.Adapter.JadwalDokterAdapter;
import info.androidhive.Adapter.SectionsPageAdapter;

public class Appointment extends AppCompatActivity {
    GridView list;
    Button tomorrow,today,history;

    private static ArrayList<String> poli;
    private static ArrayList<String> nama;
    private static ArrayList<String> tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        poli = new ArrayList<String>();
        nama = new ArrayList<String>();
        tanggal = new ArrayList<String>();
        list = (GridView) findViewById(R.id.list_data);
        tomorrow = (Button) findViewById(R.id.Appointment);
        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new appointment(Login.username1).execute();
            }
        });

        today = (Button) findViewById(R.id.Registration);
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new regist(Login.username1).execute();
            }
        });

        history = (Button) findViewById(R.id.History);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new history(Login.username1).execute();
            }
        });
        // white background notification bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        final View iconView = bottomNavigationView.findViewById(android.support.design.R.id.icon);
        final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
        final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
        layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
        iconView.setLayoutParams(layoutParams);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

//        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(1);
//        for (int i = 0; i < menuView.getChildCount(); i++) {
//            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
//            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
//            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9, zdisplayMetrics);
//            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9, displayMetrics);
//            iconView.setLayoutParams(layoutParams);
//        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.ic_Home:
                        Intent intent1 = new Intent(Appointment.this, MainActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_Account:
                        Intent intent2 = new Intent(Appointment.this, Main2Activity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_test:

                        break;

                }
                return false;
            }
        });
    }

    class appointment extends AsyncTask<String, String, String>
    {
        private String val3;
        public appointment(String medicalno) {
            this.val3 = medicalno;
        }
        @Override
        protected String doInBackground(String... strings) {

            CallSoap cs = new CallSoap();
            String medno = cs.InfoUser(val3);
            String[] temp = medno.split(",");
            String data = cs.HistoryTomorrow(temp[0]);
            return data;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(Appointment.this,s.toString(),Toast.LENGTH_SHORT);
            poli.clear();
            nama.clear();
            tanggal.clear();
            if(s.equals("No Data"))
            {
                poli.add("");
                nama.add(s);
                tanggal.add("");
            }
            else {
                String data = s.replaceAll("[a-zA-Z0-9/:#., ]*","");
                if(data.length()<1)
                {
                    String data2[] = s.split("#");
                    poli.add(data2[0]);
                    nama.add(data2[1]);
                    tanggal.add(data2[2]);
                }
                else {
                    String data1[] = s.split("%");
                    for (int i = 0; i < data.length()+1; i++) {
                        String data2[] = data1[i].split("#");
                        poli.add(data2[2]);
                        nama.add(data2[1]);
                        tanggal.add(data2[0]);
                    }
                }
            }
            list.setAdapter(new HistoryTodayTomorrowAdapter(Appointment.this, poli,nama,tanggal));
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(Appointment.this,poli.get(position),Toast.LENGTH_SHORT);
                }
            });
        }
    }

    class regist extends AsyncTask<String, String, String>
    {
        private String val3;
        public regist(String medicalno) {
            this.val3 = medicalno;
        }
        @Override
        protected String doInBackground(String... strings) {

            CallSoap cs = new CallSoap();
            //String medno[] = cs.InfoUser(val3).split(",");
            String medno = cs.InfoUser(val3);
            String[] temp = medno.split(",");
            String data = cs.HistoryForToday(temp[0]);
            return data;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(Appointment.this,s,Toast.LENGTH_SHORT).show();
            poli.clear();
            nama.clear();
            tanggal.clear();

            if(s.equals("No Data"))
            {
                poli.add("");
                nama.add(s);
                tanggal.add("");
            }
            else {
                String data = s.replaceAll("[a-zA-Z0-9/:#., ]*","");
                //Toast.makeText(Appointment.this,data,Toast.LENGTH_SHORT).show();
                if(data.length() < 1) {
                    String data1[] = s.split("#");
                    poli.add(data1[1]);
                    nama.add(data1[2]);
                    tanggal.add(data1[0]);
                }
                else {
                    String data1[] = s.split("%");
                    for (int i = 0; i <= data.length(); i++) {
                        String data2[] = data1[i].split("#");
                        poli.add(data2[1]);
                        nama.add(data2[2]);
                        tanggal.add(data2[0]);
                    }
                }
            }
            list.setAdapter(new HistoryTodayTomorrowAdapter(Appointment.this, poli,nama,tanggal));
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(Appointment.this,poli.get(position),Toast.LENGTH_SHORT);
                }
            });
        }
    }

    class history extends AsyncTask<String, String, String>
    {
        private String val3;
        public history(String medicalno) {
            this.val3 = medicalno;
        }
        @Override
        protected String doInBackground(String... strings) {

            CallSoap cs = new CallSoap();
            String medno = cs.InfoUser(val3);
            String[] temp = medno.split(",");
            String data = cs.History(temp[0]);
            return data;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(Appointment.this,s.toString(),Toast.LENGTH_SHORT).show();
            poli.clear();
            nama.clear();
            tanggal.clear();
            if(s.equals("No Data"))
            {
                poli.add("");
                nama.add(s);
                tanggal.add("");
            }
            else {
                String data = s.replaceAll("[a-zA-Z0-9/:#., ]*","");
                if(data.length()<1)
                {
                    String data2[] = s.split("#");
                    poli.add(data2[1]);
                    nama.add(data2[2]);
                    tanggal.add(data2[0]);
                }
                else {
                    String data1[] = s.split("%");
                    for (int i = 0; i < data.length()+1; i++) {
                        String data2[] = data1[i].split("#");
                        poli.add(data2[1]);
                        nama.add(data2[2]);
                        tanggal.add(data2[0]);
                    }
                }
            }
            list.setAdapter(new HistoryTodayTomorrowAdapter(Appointment.this, poli,nama,tanggal));
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(Appointment.this,poli.get(position),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

