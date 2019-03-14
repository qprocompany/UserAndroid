package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class PromoActivity extends AppCompatActivity {

    LinearLayout listpromo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);

        listpromo = (LinearLayout) findViewById(R.id.listpromo);
        listpromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PromoActivity.this, IsiPromo.class);
                startActivity(intent);
            }
        });

    }


}
