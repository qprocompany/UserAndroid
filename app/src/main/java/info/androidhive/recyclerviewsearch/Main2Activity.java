package info.androidhive.recyclerviewsearch;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;

import retrofit2.http.Url;

public class Main2Activity extends AppCompatActivity{
    TextView EMAIL,NAMA,NOTELEP,MEDICAL,TGLLAHIR;
    Button LOGOUT;
    ImageButton gallery;
    CircularImageView photo;
    Uri imageUrl;

    private static final int PICK_IMAGE =100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        EMAIL = (TextView) findViewById(R.id.EMAIL);
        MEDICAL = (TextView) findViewById(R.id.MEDICAL);
        TGLLAHIR = (TextView) findViewById(R.id.TGLLAHIR);
        NAMA = (TextView) findViewById(R.id.NAMA);
        NOTELEP = (TextView) findViewById(R.id.NOTELEP);
        photo = (CircularImageView) findViewById(R.id.photo);
        new infouser().execute();

        LOGOUT = (Button) findViewById(R.id.LOGOUT);
        LOGOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        gallery = (ImageButton) findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opengallery();
            }
        });

    }
    private void opengallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUrl = data.getData();
            photo.setImageURI(imageUrl);

        }
    }

    class infouser extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... strings) {
            CallSoap cs = new CallSoap();

            String data = cs.InfoUser(Login.username1);
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String data[] = s.split(",");
            MEDICAL.setText(data[0].toString());
            NAMA.setText(data[1].toString());
            EMAIL.setText(data[2].toString());
            String data1[] = data[3].split(" ");
            TGLLAHIR.setText(data1[0].toString());
        }
    }
}
