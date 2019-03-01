package info.androidhive.recyclerviewsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ConfirmasiDokter extends AppCompatActivity {

    TextView date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmasi_dokter);
        date = findViewById(R.id.textView4);
        date.setText(PendaftaranDokter.date);
    }
}
