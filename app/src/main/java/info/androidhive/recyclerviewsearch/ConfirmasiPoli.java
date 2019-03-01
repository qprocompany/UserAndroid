package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConfirmasiPoli extends AppCompatActivity {

    TextView date,regno,antrian,nama,poliname,ruang,start, end;
    ImageView img,next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmasi_poli);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        date = findViewById(R.id.textView4);
        regno = findViewById(R.id.textView6);
        antrian = findViewById(R.id.textView9);
        nama = findViewById(R.id.textViewName);
        poliname = findViewById(R.id.textView14);
        ruang = findViewById(R.id.textView16);
        start = findViewById(R.id.textView18);
        end = findViewById(R.id.textView19);
        next = findViewById(R.id.imageView4);
        img = findViewById(R.id.imageView2);

        date.setText(PendaftaranDokterAfterPoli.tgljanjian1);

        if (PasienByPoli.message.equals("Pendaftaran"))
        {
            new Regno().execute();
            antrian.setText(PasienByPoli.nomAnt);
        }
        else{
            new AppNo().execute();
            antrian.setText("-");
        }

        Picasso.get()
                .load(PendaftaranDokterAfterPoli.imagedokter1)
                .into(img);
        nama.setText(PendaftaranDokterAfterPoli.parname1);
        poliname.setText(PendaftaranPoli.servname1);
        ruang.setText("Ruang A-14");
        start.setText("08:00");
        end.setText("17:00");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMainMenu();
            }
        });
    }

    class Regno extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... strings) {
            CallSoap cs = new CallSoap();
            String data = cs.InfoUser(Login.username1);
            String info[] = data.split(",");
            String data1 = cs.RegNo(info[0].toString());
            return data1;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(PendaftaranDokterAfterPoli.this,s,Toast.LENGTH_SHORT).show();
            regno.setText(s);
        }
    }

    class AppNo extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... strings) {
            CallSoap cs = new CallSoap();
            String data = cs.InfoUser(Login.username1);
            String info[] = data.split(",");
            String data1 = cs.AppointNo(info[0].toString());
            return data1;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(PendaftaranDokterAfterPoli.this,s,Toast.LENGTH_SHORT).show();
            regno.setText(s);
        }
    }

    public void OpenMainMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
