package info.androidhive.recyclerviewsearch;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import info.androidhive.Adapter.BedAdapter;
import info.androidhive.Model.Bed;

public class Fasilitas extends AppCompatActivity implements BedAdapter.ContactsAdapterListener {
    private static final String TAG = Fasilitas.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Bed> bedList;
    private BedAdapter mAdapter;
    private SearchView searchView;
    public static String data1;
    public static int qty;

    // url to fetch contacts json
    //st.maria
    //private static final String URL = "http://36.91.120.14/SPHAIRA_TRAIN_ADT/Services/BedInfoAndroidNew.ashx";
    //qpro db
    private  final String URL = "http://192.168.80.63/Sphaira_LIVE_ADT/Services/BedInfoAndroidNew.ashx";
    //rsud palembang
    //private  final String URL = "http://192.168.80.112/SPHAIRA_ADT/Services/BedInfoAndroidNew.ashx";
    //public  final String URL = "http://202.152.26.123/SPHAIRA_ADT/Services/BedInfoAndroidNew.ashx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasilitas);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // toolbar fancy stuffr
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);
        recyclerView = findViewById(R.id.recycler_view);
        bedList = new ArrayList<>();
        mAdapter = new BedAdapter(this, bedList, this);
        // white background notification bar
        whiteNotificationBar(recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);
        fetchContacts();

    }

    /**
     * fetches json by making http calls
     */
    private void fetchContacts() {
        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(getApplicationContext(), "Couldn't fetch the contacts! Pleas try again.", Toast.LENGTH_LONG);
                            return;
                        }
                        List<Bed> items = new Gson().fromJson(response.toString(), new TypeToken<List<Bed>>() {
                        }.getType());
                        // adding contacts to contacts list
                        bedList.clear();
                        bedList.addAll(items);
                        // refreshing recycler view
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.e(TAG, "Error: " + error.getMessage());
              //  Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        MyApplication.getInstance().addToRequestQueue(request);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
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
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }
    @Override
    public void onContactSelected(Bed bed) {
        String data = bed.getPictureFileName() + "=" + bed.getClassName() + "=" + bed.getClassCode() + "=" + bed.getClassCode1() + "=" + bed.getIsHasAC() + "=" + bed.getIsHasPrivateBathRoom() + "=" + bed.getIsHasRefrigerator() + "=" + bed.getIsHasTV() + "=" + bed.getIsHasWifi() + "=" + bed.getRemarks() + "=" + bed.getDisplayPrice();
        qty = bed.getPatientPerRoomQty();
        data1 = data.toString();
      //  Toast.makeText(Fasilitas.this,bed.getPictureFileName(),Toast.LENGTH_SHORT).show();
        Intent intent2 = new Intent(Fasilitas.this, KamarTidurDetail.class);
        startActivity(intent2);
    }
}
