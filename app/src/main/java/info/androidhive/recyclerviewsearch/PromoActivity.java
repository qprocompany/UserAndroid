package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class PromoActivity extends AppCompatActivity {

    LinearLayout listpromo,listpromo2,listpromo3,listpromo4,listpromo5,listpromo6,listpromo7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Promo");



        listpromo = (LinearLayout) findViewById(R.id.listpromo);
        listpromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PromoActivity.this, IsiPromo.class);
                startActivity(intent);
            }
        });

        listpromo2 = (LinearLayout) findViewById(R.id.listpromo2);
        listpromo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PromoActivity.this, IsiPromo.class);
                startActivity(intent);
            }
        });

        listpromo3 = (LinearLayout) findViewById(R.id.listpromo3);
        listpromo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PromoActivity.this, IsiPromo.class);
                startActivity(intent);
            }
        });

        listpromo4 = (LinearLayout) findViewById(R.id.listpromo4);
        listpromo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PromoActivity.this, IsiPromo.class);
                startActivity(intent);
            }
        });

        listpromo5 = (LinearLayout) findViewById(R.id.listpromo5);
        listpromo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PromoActivity.this, IsiPromo.class);
                startActivity(intent);
            }
        });

        listpromo6 = (LinearLayout) findViewById(R.id.listpromo6);
        listpromo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PromoActivity.this, IsiPromo.class);
                startActivity(intent);
            }
        });

        listpromo7 = (LinearLayout) findViewById(R.id.listpromo7);
        listpromo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PromoActivity.this, IsiPromo.class);
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
