package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConfirmasiPoli extends AppCompatActivity {

    public static String medno;
    public static String message = "null";
    public static String nomAnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmasi_poli);
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        String todayString = formatter.format(todayDate);

        if (todayString.equals(PendaftaranDokterAfterPoli.tgljanjian1)) {
            new pendaftaranpoli(PendaftaranDokterAfterPoli.penampung1, PendaftaranDokterAfterPoli.parname1, PendaftaranDokterAfterPoli.paramedid.substring(1)).execute();
        } else {
            new appointmentpoli(PendaftaranDokterAfterPoli.penampung1, PendaftaranDokterAfterPoli.tgljanjian1, PendaftaranDokterAfterPoli.workstation.substring(1)).execute();
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
                Toast.makeText(ConfirmasiPoli.this,"Maaf Terjadi ke gagalan, Silahkan Mencoba lagi",Toast.LENGTH_SHORT).show();
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
            else{
                Toast.makeText(ConfirmasiPoli.this,"Maaf Pendaftaran Anda Gagal, Silahkan Mencoba lagi",Toast.LENGTH_SHORT).show();
                //    Toast.makeText(PendaftaranDokterAfterPoli.this,s,Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void OpenMainMenu(){
        Intent intent = new Intent(this, ConfirmasiPoli.class);
        startActivity(intent);
    }
}
