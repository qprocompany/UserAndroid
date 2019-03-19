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
import info.androidhive.Fragment.FragmentBilling;
import info.androidhive.Fragment.FragmentHistory;
import info.androidhive.Fragment.FragmentResume;
import info.androidhive.Fragment.FragmentToday;


public class Penampung2 extends AppCompatActivity {


    private static final String TAG = "Penampung2";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.fragment_history);
        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager1 = (ViewPager) findViewById(R.id.container2);
        setupViewPager(mViewPager1);

        TabLayout tabLayout2 = (TabLayout) findViewById(R.id.tabs1);
        tabLayout2.setupWithViewPager(mViewPager1);

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

    }


    private void setupViewPager(ViewPager viewPager1) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentBilling(), "Billing");
        adapter.addFragment(new FragmentResume(), "Resume");
        viewPager1.setAdapter(adapter);
    }
}
