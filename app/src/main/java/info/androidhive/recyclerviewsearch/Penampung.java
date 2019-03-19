package info.androidhive.recyclerviewsearch;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import info.androidhive.Fragment.FragmentAppoin;
import info.androidhive.Fragment.FragmentHistory;
import info.androidhive.Fragment.FragmentToday;

public class Penampung extends AppCompatActivity {


    private static final String TAG = "Penampung";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager,viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_penampung);
        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

//        Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

//        viewpager = (ViewPager) findViewById(R.id.container1);
//        setViewPager(viewpager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        TabLayout tabLayout1 = (TabLayout) findViewById(R.id.tabs1);
        tabLayout.setupWithViewPager(mViewPager);
//        tabLayout1.setupWithViewPager(viewpager);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        final View iconView = bottomNavigationView.findViewById(android.support.design.R.id.icon);
        final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
        final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
        layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
        iconView.setLayoutParams(layoutParams);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.ic_Home:
                        Intent intent = new Intent(Penampung.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_Account:
                        Intent intent2 = new Intent(Penampung.this, Main2Activity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_test:

                        break;

                }
                return false;
            }
        });
    }



    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentHistory(), "History");
        adapter.addFragment(new FragmentToday(), "Registrasi");
        adapter.addFragment(new FragmentAppoin(), "Appointment");
        viewPager.setAdapter(adapter);
    }

//    private void setViewPager(ViewPager viewPager) {
//        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
//        adapter.addFragment(new FragmentHistory(), "TEST");
//        adapter.addFragment(new FragmentToday(), "TEST");
//        adapter.addFragment(new FragmentAppoin(), "TEST");
//        viewPager.setAdapter(adapter);
//    }

}
