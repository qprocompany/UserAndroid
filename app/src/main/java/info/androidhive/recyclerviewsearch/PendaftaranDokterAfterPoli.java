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
    String penampung1,workstation;

    public static String message = "null";
    private static String paramedid ="";

    public static String parname1;
    public static String tgljanjian1;
    public static String medno;

    static final ArrayList<String> jadwalList1 = new ArrayList<String>();
    static final ArrayList<String> jadwalListimage = new ArrayList<String>();
    static final ArrayList<String> jadwalListDays = new ArrayList<String>();
    static final ArrayList<String> jadwalListTime = new ArrayList<String>();
    public static String nomAnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran_dokter_after_poli);

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
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        String todayString = formatter.format(todayDate);
        String poli = PendaftaranPoli.poli1;
        tgljanjian1 = date;
        //Toast.makeText(PendaftaranDokterAfterPoli.this,paramedid,Toast.LENGTH_SHORT).show();
        /*if(tgljanjian1.compareTo(todayString) >= 5)
        {
            Toast.makeText(PendaftaranDokterAfterPoli.this,"Tanggal Registrasi Paling Telat Hari Ini",Toast.LENGTH_SHORT).show();
        }
        else {*/
        if (todayString.equals(date)) {
            new pendaftaranpoli(penampung1, poli, paramedid.substring(1)).execute();
        } else {
            new appointmentpoli(penampung1, date, workstation.substring(1)).execute();
        }
        //}
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
                        parname1 = temp[0];
                        paramedid = temp[2];
                        workstation = temp[1];
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

    class appointmentpoli extends AsyncTask<String, String, String>
    {
        private String username,date,work;


        public appointmentpoli(String username, String date, String work1) {

            this.username = username;
            this.date = date;
            this.work = work1;
        }
        @Override
        protected String doInBackground(String... strings) {
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
            if(s.equals("True"))
            {
                //   Toast.makeText(PendaftaranDokterAfterPoli.this,s,Toast.LENGTH_SHORT).show();
                message = "Appointment";
                OpenMainMenu();
            }
            else{
                //     Toast.makeText(PendaftaranDokterAfterPoli.this,s,Toast.LENGTH_SHORT).show();
                Toast.makeText(PendaftaranDokterAfterPoli.this,"Maaf Terjadi ke gagalan, Silahkan Mencoba lagi",Toast.LENGTH_SHORT).show();
            }
        }
    }

    class pendaftaranpoli extends AsyncTask<String, String, String>
    {
        private String username1,servunit,paramid;

        public pendaftaranpoli(String username,String servunit,String paramid) {

            this.username1 = username;
            this.servunit = servunit;
            this.paramid = paramid;
            // Do something ...
        }
        @Override
        protected String doInBackground(String... strings) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            CallSoap cs = new CallSoap();

            String data = cs.InfoUser(username1);

            String data2[] = data.split(",");

            String Medno = data2[0].toString();
            String name1 = data2[1].toString();
            String email = data2[2].toString();

            medno=Medno;
            String data1 = cs.Registration(Medno,servunit,"personal",paramid);

            return data1;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String val[] = s.split("_");
            //Toast.makeText(PendaftaranDokterAfterPoli.this,s,Toast.LENGTH_SHORT).show();
            if(val[0].equals("True"))
            {
                nomAnt = val[1];
                message = "Pendaftaran";
                OpenMainMenu();
            }
            /*else if(s.indexOf("duplicate") > 0)
            {
                Toast.makeText(PendaftaranDokterAfterPoli.this,s,Toast.LENGTH_SHORT).show();
                //Toast.makeText(PendaftaranDokterAfterPoli.this,"Data Anda Telah Terdaftar",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(PendaftaranDokterAfterPoli.this,"Maaf Pendaftaran Anda Gagal, Silahkan Mencoba lagi",Toast.LENGTH_SHORT).show();
                //    Toast.makeText(PendaftaranDokterAfterPoli.this,s,Toast.LENGTH_SHORT).show();
            }*/
        }
    }

    public void OpenMainMenu(){
        Intent intent = new Intent(this, PasienByPoli.class);
        startActivity(intent);
    }
}
