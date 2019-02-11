package info.androidhive.recyclerviewsearch;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;

import info.androidhive.Adapter.SectionsPageAdapter;
import info.androidhive.Fragment.Tab1Fragment;
import ss.com.bannerslider.Slider;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;


public class MainActivity extends AppCompatActivity {
    //   private Slider slider;
    private SectionsPageAdapter mSectionsPageAdapter;
    FlipperLayout flipperLayout;
    private ViewPager mViewPager;
    TextView usernames;
    private static String usernames1;
    private String mPackageName;
    private BottomNavigationView BottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        Slider.init(new PicassoImageLoadingService(this));

        /*Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
            usernames.setText(value);
            //The key argument here must match that used in the other activity
        }*/
       // usernames = (TextView) findViewById(R.id.usernames);
      //  usernames.setText(Login.username1);
      //  usernames1 = usernames.toString();


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        final View iconView = bottomNavigationView.findViewById(android.support.design.R.id.icon);
        final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
        final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
        layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
        iconView.setLayoutParams(layoutParams);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

//        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(1);
//        for (int i = 0; i < menuView.getChildCount(); i++) {
//            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
//            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
//            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9, zdisplayMetrics);
//            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9, displayMetrics);
//            iconView.setLayoutParams(layoutParams);
//        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.ic_Home:

                        break;

                    case R.id.ic_Account:
                        Intent intent2 = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_test:
                        Intent intent1 = new Intent(MainActivity.this, Appointment.class);
                        startActivity(intent1);
                        break;

                }
                return false;
            }
        });


        //  setupViews();

        //     flipperLayout = findViewById(R.id.flipper_layout);

        //    setLayout();

        // Here, thisActivity is the current activity
// Here, thisActivity is the current activity

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "Membutuhkan Izin Lokasi", Toast.LENGTH_SHORT).show();
            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);
            }
        } else {
            // Permission has already been granted
            // Toast.makeText(this, "Izin Lokasi diberikan", Toast.LENGTH_SHORT).show();
        }




        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        // TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        // tabLayout.setupWithViewPager(mViewPager);
        //  tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_contacts);
        //  tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab_contacts);
        //  tabLayout.getTabAt(2).setIcon(R.drawable.ic_tab_contacts);

       // BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(1);
       // for (int i = 0; i < menuView.getChildCount(); i++) {
       //     final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
       //     final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
       //     final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
       //     layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9, displayMetrics);
       //     layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9, displayMetrics);
       //     iconView.setLayoutParams(layoutParams);
        }


     //   BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottombavigation);
      //  BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(1);
       // for (int i = 0; i < menuView.getChildCount(); i++) {
        //    final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
         //   final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
          //  final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
          // layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9, displayMetrics);
          //  layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9, displayMetrics);
          // iconView.setLayoutParams(layoutParams);
      //  }

   // }

     private void setLayout() {
        String url[] = new String[]{
                //"http://192.168.80.63/SPHAIRA_LIVE_ADT/Promo/promo%20rawat%20inap.jpg",
                //"http://192.168.80.63/SPHAIRA_LIVE_ADT/Promo/img_0.jpg"
                //"http://192.168.80.63/SPHAIRA_LIVE_ADT/Promo/promo%20melahirkan.jpg"
        };
        for (int i = 0; i < 3; i++) {
            FlipperView view = new FlipperView(getBaseContext());
            view.setImageUrl(url[i])
                    .setDescription("Cool" + (i + 1));
            flipperLayout.addFlipperView(view);
            view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                @Override
                public void onFlipperClick(FlipperView flipperView) {
                    Toast.makeText(MainActivity.this
                            , "Here " + (flipperLayout.getCurrentPagePosition() + 1)
                            , Toast.LENGTH_SHORT).show();
                }
            });
        }
       }





    // private void setupViews() {
    //    setupToolbar();
    //  setupPageIndicatorChooser();
    //   setupSettingsUi();
    //   slider = findViewById(R.id.banner_slider1);

    //delay for testing empty view functionality
    //    slider.postDelayed(new Runnable() {
    //     @Override
    //       public void run() {
    //         slider.setAdapter(new MainSliderAdapter());
    //        slider.setSelectedSlide(0);
    //     }
    //    }, 1500);

    //  }









    private void setupViewPager(ViewPager viewPager) {
       SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment());
       // adapter.addFragment(new Tab2Fragment());
       // adapter.addFragment(new Tab3Fragment());
        viewPager.setAdapter(adapter);
    }

}