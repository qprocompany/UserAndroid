package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Confirmasi extends AppCompatActivity {
    TextView msgcin;
    TextView medno,NoAntrian,namadokter,politujuan,tanggal;
    ImageView img;
    ImageView ok;
    private static String data = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmasi);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        img = (ImageView) findViewById(R.id.imgconf) ;
        msgcin = (TextView) findViewById(R.id.message);
        medno = (TextView) findViewById(R.id.medno);
        NoAntrian = (TextView) findViewById(R.id.NoAntrian);
        namadokter = (TextView) findViewById(R.id.namadokter);
        politujuan = (TextView) findViewById(R.id.politujuan);
        tanggal=(TextView) findViewById(R.id.tanggal);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        if(PendaftaranDokter.message == "null") {
            Date todayDate = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            String todayString = formatter.format(todayDate);
            new MedNo(Login.username1).execute();
            if(PendaftaranDokterAfterPoli.tgljanjian1.equals(todayString)==true) {
                //new Regno(medno.getText().toString()).execute();
                msgcin.setText("Registrasi");
                NoAntrian.setText("xxxxx");
                //medno.getText().toString()
                Toast.makeText(Confirmasi.this,data,Toast.LENGTH_SHORT).show();
            }
            else {
                //new AppNo(Login.username1).execute();
                Toast.makeText(Confirmasi.this,data,Toast.LENGTH_SHORT).show();
                msgcin.setText("Appointment");
                NoAntrian.setText("-");
            }
            namadokter.setText(PendaftaranDokterAfterPoli.parname1);
            politujuan.setText(PendaftaranPoli.servname1);
            tanggal.setText(PendaftaranDokterAfterPoli.tgljanjian1);
            //msgcin.setText("Terima kasih, data Anda telah kami simpan." + "\n" + PendaftaranDokterAfterPoli.message + " : ");
        }
        else
        {
            new MedNo(Login.username1).execute();
            Date todayDate = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            String todayString = formatter.format(todayDate);
            if(PendaftaranDokter.tgljanjian.equals(todayString)==true) {
                //new Regno(Login.username1).execute();
                Toast.makeText(Confirmasi.this,data,Toast.LENGTH_SHORT).show();
                msgcin.setText("Registrasi");
                NoAntrian.setText("xxxx");
            }
            else {
                //new AppNo(Login.username1).execute();
                Toast.makeText(Confirmasi.this,data,Toast.LENGTH_SHORT).show();
                msgcin.setText("Appointment");
                NoAntrian.setText("-");
            }

            namadokter.setText(PendaftaranDokter.parname);
            politujuan.setText(PendaftaranDokter.servname);
            tanggal.setText(PendaftaranDokter.tgljanjian);
            //msgcin.setText("Terima kasih, data Anda telah kami simpan." + "\n"+ PendaftaranDokter.message + " : ");
        }
        ok = (ImageView) findViewById(R.id.ok);
        ok.setImageResource(R.drawable.confirmimage);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMainMenu();
            }
        });
    }

    class MedNo extends AsyncTask<String, String, String>
    {
        private String username;
        public MedNo(String username) {
            this.username = username;
        }
        @Override
        protected String doInBackground(String... strings) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            CallSoap cs = new CallSoap();
            String data = cs.InfoUser(username);
            String data1[] = data.split(",");
            return data1[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            medno.setText(s.toString());
            //Toast.makeText(Confirmasi.this,s,Toast.LENGTH_SHORT).show();
            data = s;
            /*try {
                BitMatrix bitMatrix = multiFormatWriter.encode(s, BarcodeFormat.QR_CODE, 200, 200);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                img.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }*/
        }
    }

    class Regno extends AsyncTask<String, String, String>
    {
        private String medno;
        public Regno(String medno) {
            this.medno = medno;
        }
        @Override
        protected String doInBackground(String... strings) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            CallSoap cs = new CallSoap();
            String medno1 = cs.RegNo(medno);
            return medno1;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
          //  Toast.makeText(Confirmasi.this,s,Toast.LENGTH_SHORT).show();
            /*try {
                BitMatrix bitMatrix = multiFormatWriter.encode(s, BarcodeFormat.QR_CODE, 200, 200);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                img.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }*/
        }
    }

    class AppNo extends AsyncTask<String, String, String>
    {
        private String medno;
        public AppNo(String medno) {
            this.medno = medno;
        }
        @Override
        protected String doInBackground(String... strings) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            CallSoap cs = new CallSoap();
            String medno1 = cs.AppointNo(medno);
            return medno1;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(Confirmasi.this,s,Toast.LENGTH_SHORT).show();
            /*try {
                BitMatrix bitMatrix = multiFormatWriter.encode(s, BarcodeFormat.QR_CODE, 200, 200);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                img.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }*/
        }
    }

    public void OpenMainMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
