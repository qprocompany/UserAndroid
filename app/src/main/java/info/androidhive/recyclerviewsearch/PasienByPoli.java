package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class PasienByPoli extends AppCompatActivity {
    RadioButton personal,family;
    TextView next;
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
