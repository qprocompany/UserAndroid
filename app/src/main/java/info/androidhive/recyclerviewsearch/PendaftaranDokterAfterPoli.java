package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import info.androidhive.Adapter.JadwalDokterAdapter;
import info.androidhive.Adapter.SectionsPageAdapter;

public class PendaftaranDokterAfterPoli extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    GridView grid;

    private boolean mAutoHighlight;
    public static String penampung1,workstation,imagedokter1;
    public static String paramedid ="";

    public static String parname1;
    public static String tgljanjian1;

    static final ArrayList<String> jadwalList1 = new ArrayList<String>();
    static final ArrayList<String> jadwalListimage = new ArrayList<String>();
    static final ArrayList<String> jadwalListDays = new ArrayList<String>();
    static final ArrayList<String> jadwalListTime = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran_dokter_after_poli);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // toolbar fancy stuffr
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Dokter");

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        String id1 = PendaftaranPoli.poli1;
        penampung1 = Login.username1;
        new dokterlist(id1).execute();
        grid = (GridView) findViewById(R.id.list);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
        String date = dayOfMonth+"/"+(++monthOfYear)+"/"+year;

        String poli = PendaftaranPoli.poli1;
        tgljanjian1 = date;
        //Toast.makeText(PendaftaranDokterAfterPoli.this,workstation,Toast.LENGTH_SHORT).show();
        OpenMainMenu();
    }

    class dokterlist extends AsyncTask<String, String, String>
    {
        private String username;


        public dokterlist(String username) {

            this.username = username;

            // Do something ...
        }
        @Override
        protected String doInBackground(String... strings) {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            CallSoap cs = new CallSoap();
            String data1 = cs.DokterByPoli(username);
            return data1;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(PendaftaranDokterAfterPoli.this,s,Toast.LENGTH_SHORT).show();
            String data[] = s.split("%");
            jadwalList1.clear();
            jadwalListimage.clear();
            jadwalListDays.clear();
            jadwalListTime.clear();
            for(int i  = 0; i < data.length;i++)
            {
                if(data[i].equals("-")==false)
                {
                    String temp[] = data[i].split("#");
                    jadwalList1.add(temp[0].substring(1));
                    jadwalListimage.add(temp[3].substring(1));
                    jadwalListDays.add(temp[4].substring(1));
                    jadwalListTime.add(temp[5].substring(1));
                }
            }

            grid.setAdapter(new JadwalDokterAdapter(PendaftaranDokterAfterPoli.this, jadwalList1,jadwalListimage,jadwalListDays,jadwalListTime));
            grid.setOnItemClickListener((parent, view, position, id) ->  {
                        //Toast.makeText(getApplicationContext(),data[position], Toast.LENGTH_SHORT).show();
                        String temp[] = data[position].split("#");
                        parname1 = temp[0].substring(1);
                        paramedid = temp[2].substring(1);
                        workstation = temp[1].substring(1);
                        imagedokter1 = temp[3].substring(1);
                        Calendar now = Calendar.getInstance();
                        DatePickerDialog dpd = com.borax12.materialdaterangepicker.date.DatePickerDialog.newInstance(
                                PendaftaranDokterAfterPoli.this,
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH)
                        );
                        dpd.setAutoHighlight(mAutoHighlight);
                        dpd.show(getFragmentManager(), "Datepickerdialog");
                    }
            );
        }
    }



    public void OpenMainMenu(){
        Intent intent = new Intent(this, PasienByPoli.class);
        startActivity(intent);
    }
}
