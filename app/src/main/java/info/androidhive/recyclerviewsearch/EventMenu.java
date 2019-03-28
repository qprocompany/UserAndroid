package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class EventMenu extends AppCompatActivity {

    LinearLayout listevent, listevent2, listevent3, listevent4, listevent5, listevent6, listevent7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Event");

        listevent = (LinearLayout) findViewById(R.id.listevent);
        listevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventMenu.this, IsiEvent.class);
                startActivity(intent);
            }
        });

        listevent2 = (LinearLayout) findViewById(R.id.listevent2);
        listevent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventMenu.this, IsiEvent.class);
                startActivity(intent);
            }
        });

        listevent3 = (LinearLayout) findViewById(R.id.listevent3);
        listevent3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventMenu.this, IsiEvent.class);
                startActivity(intent);
            }
        });

        listevent4 = (LinearLayout) findViewById(R.id.listevent4);
        listevent4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventMenu.this, IsiEvent.class);
                startActivity(intent);
            }
        });

        listevent5 = (LinearLayout) findViewById(R.id.listevent5);
        listevent5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventMenu.this, IsiEvent.class);
                startActivity(intent);
            }
        });

        listevent6 = (LinearLayout) findViewById(R.id.listevent6);
        listevent6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventMenu.this, IsiEvent.class);
                startActivity(intent);
            }
        });

        listevent7 = (LinearLayout) findViewById(R.id.listevent7);
        listevent7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventMenu.this, IsiEvent.class);
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
