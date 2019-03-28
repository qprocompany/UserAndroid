package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import info.androidhive.Adapter.JadwalDokterAdapter;

public class ConfirmasiDokter extends AppCompatActivity {

    TextView date,regno,antrian,nama,poliname,ruang,start, end;
    ImageView img,next;

    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmasi_dokter);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        date = findViewById(R.id.textView4);
        regno = findViewById(R.id.textView6);
        antrian = findViewById(R.id.textView9);
        nama = findViewById(R.id.textViewName);
        poliname = findViewById(R.id.textView14);
        ruang = findViewById(R.id.textView16);
        start = findViewById(R.id.textView18);
        end = findViewById(R.id.textView19);
        ok = findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMainMenu();
            }
        });
      //  next = findViewById(R.id.imageView4);

        date.setText(PendaftaranDokter.date);
        if (PasienByDokter.message.equals("Pendaftaran"))
        {
            new Regno().execute();
            antrian.setText(PasienByDokter.nomAnt);
        }
        else{
            new AppNo().execute();
            antrian.setText("-");
        }
        img = findViewById(R.id.imageView2);
        Picasso.get()
                .load(PendaftaranDokter.image)
                .into(img);
        nama.setText(PendaftaranDokter.parname);
        poliname.setText(PendaftaranDokter.servname);
        ruang.setText("Ruang A-14");
        start.setText("08:00");
        end.setText("17:00");
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                OpenMainMenu();
//            }
//        });
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
            String data1 = cs.AppointNo(info[0]);
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
