package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PasienByPoli extends AppCompatActivity {
    RadioButton personal,family;
    TextView next;

    public static String tgljanjian;
    public static String message = "null";
    public static String medno;
    public static String nomAnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien_by_poli);
        personal = findViewById(R.id.personal);
        family = findViewById(R.id.family);
        next = findViewById(R.id.next2);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PasienByPoli.this,"Text",Toast.LENGTH_SHORT).show();
                if(personal.isChecked())
                {
                    Date todayDate = Calendar.getInstance().getTime();
                    SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
                    String todayString = formatter.format(todayDate);
                    String date = PendaftaranDokterAfterPoli.tgljanjian1;
                    if (todayString.equals(date)) {
                        tgljanjian = date;
                        //new pendaftarandokter1(PendaftaranDokterAfterPoli.penampung1, PendaftaranDokterAfterPoli.paramedid, PendaftaranDokterAfterPoli.paramedid).execute();
                        new pendaftarandokter1(PendaftaranDokterAfterPoli.penampung1, PendaftaranPoli.poli1, PendaftaranDokterAfterPoli.paramedid).execute();
                        //Toast.makeText(PasienByPoli.this,PendaftaranPoli.poli1,Toast.LENGTH_SHORT).show();
                    } else {
                        tgljanjian = date;
                        Toast.makeText(PasienByPoli.this,PendaftaranDokterAfterPoli.workstation.substring(1),Toast.LENGTH_SHORT).show();
                        new appointmentdokter(PendaftaranDokterAfterPoli.penampung1, date, PendaftaranDokterAfterPoli.workstation).execute();
                    }
                }
            }
        });
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
            //Toast.makeText(PasienByPoli.this,s,Toast.LENGTH_SHORT).show();
            if(s.equals("True"))
            {
                message = "Appointment";
                //Toast.makeText(PendaftaranDokter.this,message,Toast.LENGTH_SHORT).show();
                OpenNext();
            }
            else{
                Toast.makeText(PasienByPoli.this,s,Toast.LENGTH_SHORT).show();
                //Toast.makeText(PasienByPoli.this,"Maaf Terjadi ke gagalan, Silahkan Mencoba lagi",Toast.LENGTH_SHORT).show();
            }
        }
    }

    class pendaftarandokter1 extends AsyncTask<String, String, String>
    {
        private String username,servunit,param;
        public pendaftarandokter1(String username,String servunit,String paramid) {
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


            if(s.substring(0,s.indexOf("_")).equals("True"))
            //if(s.equals("True"))
            {
                nomAnt = s.substring(s.indexOf("_")+1);
                message = "Pendaftaran";
                //Toast.makeText(PendaftaranDokter.this,message,Toast.LENGTH_SHORT).show();
                OpenNext();
            }
            else{
                Toast.makeText(PasienByPoli.this,s,Toast.LENGTH_SHORT).show();
                //Toast.makeText(PasienByPoli.this,"Maaf Pendaftaran Anda Gagal, Silahkan Mencoba lagi",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void OpenNext(){
        Intent intent = new Intent(this, ConfirmasiPoli.class);
        startActivity(intent);
    }
}
