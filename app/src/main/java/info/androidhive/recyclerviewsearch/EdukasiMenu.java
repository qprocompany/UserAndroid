package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class EdukasiMenu extends AppCompatActivity {

    LinearLayout listeducation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edukasi_menu);

        listeducation = (LinearLayout) findViewById(R.id.listeducation);
        listeducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EdukasiMenu.this, IsiEdukasi.class);
                startActivity(intent);
            }
        });
    }
}
