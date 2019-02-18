package info.androidhive.recyclerviewsearch;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import info.androidhive.Adapter.JadwalDokterAdapter;

public class DetailDokter extends AppCompatActivity {
    CircularImageView image;
   // ImageView image;
    TextView nama,remark,speciality;
    String parid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dokter); 
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        parid = JadwalDokter.parid;
        image = (CircularImageView) findViewById(R.id.img_detail);
        nama = (TextView) findViewById(R.id.nama2);
        remark = (TextView) findViewById(R.id.txt_detail_description);
        speciality = (TextView) findViewById(R.id.tablelinier);
        Toast.makeText(DetailDokter.this,parid,Toast.LENGTH_SHORT);
        new jadwaldokter(parid).execute();
    }
    class jadwaldokter extends AsyncTask<String, String, String>
    {
        private String parid;
        public jadwaldokter(String parid) {
            this.parid = parid;
        }
        @Override
        protected String doInBackground(String... strings) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            CallSoap cs = new CallSoap();
            String data = cs.DokterDetail(parid);
            return data;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(DetailDokter.this,s,Toast.LENGTH_SHORT).show();
            String data[] = s.split("#");
            try {
                if (data[0].equals("-") == false)
                    nama.setText(data[0]);
                if (data[2].equals("-") == false)
                    remark.setText(data[2].substring(1));
                if (data[1].equals("-") == false)
                    speciality.setText(data[1].substring(1));
                if (data[3].equals("-") == false) {
                    Picasso.get().load(data[3].substring(1)).into(image);
                    String img = data[3].substring(1);
                    Bitmap bm = BitmapFactory.decodeFile(img);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] b = baos.toByteArray();
                    String s1 = Base64.encodeToString(b, Base64.DEFAULT);
                 //   Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_LONG).show();
                }
            }catch (Exception ec)
            {
                //Toast.makeText(DetailDokter.this,ec.toString(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
