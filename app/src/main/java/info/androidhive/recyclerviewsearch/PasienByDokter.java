package info.androidhive.recyclerviewsearch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class PasienByDokter extends AppCompatActivity {
    RadioButton personal,family;
    TextView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien_by_dokter);
        personal = findViewById(R.id.personal);
        family = findViewById(R.id.family);
        next = findViewById(R.id.next1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PasienByDokter.this,"Test",Toast.LENGTH_SHORT).show();
                if(personal.isChecked())
                {
                    OpenNext();
                }
            }
        });
    }

    public void OpenNext(){
        Intent intent = new Intent(this, Confirmasi.class);
        startActivity(intent);
    }
}
