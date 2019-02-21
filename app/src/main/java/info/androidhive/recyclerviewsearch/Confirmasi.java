package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
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

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Confirmasi extends AppCompatActivity {
    TextView msgcin;
    TextView medno,NoAntrian,namadokter,politujuan,tanggal;

    ImageView ok;

    //Save to FILE
    private File file;
    private boolean mFlashSupported;
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;
    public static final String TAG = "YOUR-TAG-NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmasi);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        msgcin = (TextView) findViewById(R.id.message);
        medno = (TextView) findViewById(R.id.medno);
        NoAntrian = (TextView) findViewById(R.id.NoAntrian);
        namadokter = (TextView) findViewById(R.id.namadokter);
        politujuan = (TextView) findViewById(R.id.politujuan);
        tanggal=(TextView) findViewById(R.id.tanggal);

        if(PendaftaranDokter.message == "null") {
            Date todayDate = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            String todayString = formatter.format(todayDate);
            msgcin.setText(todayString);
            NoAntrian.setText("014");
            if(PendaftaranDokterAfterPoli.tgljanjian1.equals(todayString)) {
                //new MedNo(Login.username1).execute();
                new Regno(PendaftaranDokterAfterPoli.medno).execute();
                msgcin.setText("Registrasi");
                NoAntrian.setText("014");
            }
            else {
                //new MedNo(Login.username1).execute();
                msgcin.setText("Appointment");
                NoAntrian.setText("-");
            }
            //Toast.makeText(Confirmasi.this,PendaftaranDokter.medno,Toast.LENGTH_SHORT).show();
            //medno.setText(PendaftaranDokterAfterPoli.medno);
            namadokter.setText(PendaftaranDokterAfterPoli.parname1.substring(1));
            politujuan.setText(PendaftaranPoli.servname1);

            try {
                String date = PendaftaranDokterAfterPoli.tgljanjian1;
                SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");
                Date newDate = format.parse(date);
                format = new SimpleDateFormat("dd MMMM yyyy");
                String date1 = format.format(newDate);
                tanggal.setText(date1);
            }catch (Exception e)
            {
                Toast.makeText(Confirmasi.this,e.toString(),Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Date todayDate = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            String todayString = formatter.format(todayDate);
            if (PendaftaranDokter.tgljanjian.equals(todayString)) {
                //new MedNo(Login.username1).execute();
                new Regno(PendaftaranDokter.medno).execute();
                msgcin.setText("Registrasi");
                NoAntrian.setText("xxxx");
            } else {
                //new MedNo(Login.username1).execute();
                msgcin.setText("Appointment");
                NoAntrian.setText("-");
            }

            medno.setText(PendaftaranDokter.medno);
            namadokter.setText(PendaftaranDokter.parname);
            politujuan.setText(PendaftaranDokter.servname);

            try {
                String date = PendaftaranDokter.tgljanjian;
                SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");
                Date newDate = format.parse(date);
                format = new SimpleDateFormat("dd MMMM yyyy");
                String date1 = format.format(newDate);
                tanggal.setText(date1);
            }catch (Exception e)
            {
                Toast.makeText(Confirmasi.this,e.toString(),Toast.LENGTH_SHORT).show();
            }
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

    class Regno extends AsyncTask<String, String, String>
    {
        private String medno;
        public Regno(String medno) {
            this.medno = medno;
        }
        @Override
        protected String doInBackground(String... strings) {
            CallSoap cs = new CallSoap();
            String medno1 = cs.RegNo(medno);
            return medno1;
        }


    }



    public void saveTempBitmap(Bitmap bitmap) {
        if (isExternalStorageWritable()) {
            saveImage(bitmap);
        }else{
            //prompt the user or do something
        }
    }

    private void saveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/DCIM/QRCode");
        myDir.mkdirs();

        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String fname = "QRCodeRS"+ timeStamp +".jpg";

        File file = new File(myDir, fname);
        if (file.exists()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Checks if external storage is available for read and write
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public void OpenMainMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
