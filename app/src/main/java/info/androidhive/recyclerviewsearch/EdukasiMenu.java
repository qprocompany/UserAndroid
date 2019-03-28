package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class EdukasiMenu extends AppCompatActivity {

    LinearLayout listeducation,listeducation2,listeducation3,listeducation4,listeducation5,listeducation6,listeducation7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edukasi_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edukasi");

        listeducation = (LinearLayout) findViewById(R.id.listeducation);
        listeducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EdukasiMenu.this, IsiEdukasi.class);
                startActivity(intent);
            }
        });

        listeducation2 = (LinearLayout) findViewById(R.id.listeducation2);
        listeducation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EdukasiMenu.this, IsiEdukasi.class);
                startActivity(intent);
            }
        });

        listeducation3 = (LinearLayout) findViewById(R.id.listeducation3);
        listeducation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EdukasiMenu.this, IsiEdukasi.class);
                startActivity(intent);
            }
        });

        listeducation4 = (LinearLayout) findViewById(R.id.listeducation4);
        listeducation4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EdukasiMenu.this, IsiEdukasi.class);
                startActivity(intent);
            }
        });

        listeducation5 = (LinearLayout) findViewById(R.id.listeducation5);
        listeducation5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EdukasiMenu.this, IsiEdukasi.class);
                startActivity(intent);
            }
        });

        listeducation6 = (LinearLayout) findViewById(R.id.listeducation6);
        listeducation6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EdukasiMenu.this, IsiEdukasi.class);
                startActivity(intent);
            }
        });

        listeducation7 = (LinearLayout) findViewById(R.id.listeducation7);
        listeducation7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EdukasiMenu.this, IsiEdukasi.class);
                startActivity(intent);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
