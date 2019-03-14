package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class EventMenu extends AppCompatActivity {

    LinearLayout listevent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_menu);

        listevent = (LinearLayout) findViewById(R.id.listevent);
        listevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventMenu.this, IsiEvent.class);
                startActivity(intent);
            }
        });
    }
}
