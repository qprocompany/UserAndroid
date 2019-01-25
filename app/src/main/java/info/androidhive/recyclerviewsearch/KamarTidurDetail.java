package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class KamarTidurDetail extends AppCompatActivity {
    public ImageView thumbnail;
    String[] datakamar;
    private static Integer qty=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamar_tidur_detail);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView detail = (TextView) findViewById(R.id.txt_detail_description);
        TextView classname = (TextView) findViewById(R.id.txt_detail_description_classname);
        TextView classcode = (TextView) findViewById(R.id.txt_detail_description_classcode);
        ImageView ac = (ImageView) findViewById(R.id.img_ac);
        ImageView bath = (ImageView) findViewById(R.id.img_bathroom);
        ImageView ref = (ImageView) findViewById(R.id.img_refigen);
        ImageView tv = (ImageView) findViewById(R.id.img_tv);
        ImageView wifi = (ImageView) findViewById(R.id.img_wifi);
        //TextView qtytext = (TextView) findViewById(R.id.qtytext);
        TextView txtfas = (TextView) findViewById(R.id.namatxt);
        TextView harga = (TextView) findViewById(R.id.pengalamantxt);
        setSupportActionBar(toolbar);
        thumbnail = (ImageView) findViewById(R.id.img_detail);
        datakamar = Fasilitas.data1.toString().split("=");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);
        Picasso.get()
                .load(datakamar[0])
                .resize(1000,500)
                .into(thumbnail);
        classname.setText(datakamar[1]);
        classcode.setText(datakamar[3]);
        if(datakamar[9].equals("null"))
        {
            detail.setText("-");
        }
        else
        {
            detail.setText(datakamar[9]);
        }
        txtfas.setText("Fasilitas");

        if(datakamar[4].equals("true")) {
            Picasso.get()
                    .load(R.drawable.airc)
                    .resize(1000, 1000)
                    .into(ac);
        }
        else
        {
            Picasso.get()
                    .load(R.drawable.aircdisable)
                    .resize(1000, 1000)
                    .into(ac);
        }
        if(datakamar[5].equals("true")) {
            Picasso.get()
                    .load(R.drawable.kamarmandi)
                    .resize(1000, 1000)
                    .into(bath);
        }
        else
        {
            Picasso.get()
                    .load(R.drawable.kamarmandidisable)
                    .resize(1000, 1000)
                    .into(bath);
        }
        if(datakamar[6].equals("true")) {
            Picasso.get()
                    .load(R.drawable.refrigerator)
                    .resize(1000, 1000)
                    .into(ref);
        }
        else
        {
            Picasso.get()
                    .load(R.drawable.refrigeratordisable)
                    .resize(1000, 1000)
                    .into(ref);
        }
        if(datakamar[7].equals("true")) {
            Picasso.get()
                    .load(R.drawable.televisi)
                    .resize(1100, 1000)
                    .into(tv);
        }
        else
        {
            Picasso.get()
                    .load(R.drawable.televisidisable)
                    .resize(1100, 1000)
                    .into(tv);
        }
        if(datakamar[8].equals("true")) {
            Picasso.get()
                    .load(R.drawable.wifi)
                    .resize(1200, 1000)
                    .into(wifi);
        }
        else
        {
            Picasso.get()
                    .load(R.drawable.wifidisable)
                    .resize(1200, 1000)
                    .into(wifi);
        }

        qty = Fasilitas.qty;
        if(qty.equals("null"))
        {
            TableLayout tb = (TableLayout) findViewById(R.id.tablelinier);
            TableRow tr = new TableRow(this);
            ImageView imageView = new ImageView(this);
            Picasso.get().load(R.drawable.whitee).resize(100, 100).into(imageView);
            imageView.setPadding(20, 0, 0, 0);
            tr.addView(imageView);
            tb.addView(tr);
        }
        else {
            TableLayout tb = (TableLayout) findViewById(R.id.tablelinier);
            TableRow tr = new TableRow(this);
            for (int i = 0; i < qty; i++) {
                ImageView imageView = new ImageView(this);
                Picasso.get().load(R.drawable.jumlahorang).resize(100, 100).into(imageView);
                imageView.setPadding(20, 0, 0, 0);
                tr.addView(imageView);
            }
            tb.addView(tr);
        }
        if(datakamar[10].equals("null"))
        {
            harga.setText("Rp. -");
        }
        else
        {
            harga.setText("Rp. " + datakamar[10]);
        }
    }
    @Override
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
                Intent intent = new Intent(this, Fasilitas.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
