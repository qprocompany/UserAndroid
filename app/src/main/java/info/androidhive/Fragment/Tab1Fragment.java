package info.androidhive.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import info.androidhive.Adapter.HistoryTodayTomorrowAdapter;
import info.androidhive.Adapter.SectionsPageAdapter;
import info.androidhive.recyclerviewsearch.Appointment;
import info.androidhive.recyclerviewsearch.CallSoap;
import info.androidhive.recyclerviewsearch.Fasilitas;
import info.androidhive.recyclerviewsearch.JadwalDokter;
import info.androidhive.recyclerviewsearch.MapsActivity;
import info.androidhive.recyclerviewsearch.Pendaftaran;
import info.androidhive.recyclerviewsearch.PicassoImageLoadingService;
import info.androidhive.recyclerviewsearch.R;
import ss.com.bannerslider.Slider;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;


public class Tab1Fragment extends Fragment {
    LinearLayout Daftar, Jadwal,  Maps, fasilitas,keluar,keluarga,keluargadalam,emergency;
    FlipperLayout flipperLayout;


    View v;

    private CountDownTimer countDownTimer;

    private boolean timerStarted = false;

    private ImageButton buttonStart;

    public TextView textView;

    private final long startTime = 4 * 1000;

    private final long interval = 1 * 1000;

    private ViewPager mViewPager;
    private SectionsPageAdapter mSectionsPageAdapter;

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getFragmentManager());
        adapter.addFragment(new Tab1Fragment());
        //adapter.addFragment(new Tab2Fragment());
        //adapter.addFragment(new Tab3Fragment());
        viewPager.setAdapter(adapter);
    }

    public Tab1Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Slider.init(new PicassoImageLoadingService(getContext()));




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment1_layout, container, false);
        flipperLayout = (FlipperLayout) v.findViewById(R.id.flipper_layout) ;
        setLayout();
        LinearLayout Maps = (LinearLayout) v.findViewById(R.id.Maps);
        Maps.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                //Intent intent= getActivity().getPackageManager().getLaunchIntentForPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });



        LinearLayout fasilitas = (LinearLayout) v.findViewById(R.id.fasilitas);
        fasilitas.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), Fasilitas.class);
                startActivity(intent);
            }
        });


        buttonStart = (ImageButton)  v.findViewById(R.id.button);
        textView = (TextView) (TextView) v.findViewById(R.id.textView);
        countDownTimer = new CountDownTimerActivity(startTime, interval);
        textView.setText(textView.getText() + String.valueOf(startTime/1000));
        textView.setVisibility(View.GONE);

        //DI TAHAN
        buttonStart.setOnTouchListener(new View.OnTouchListener()  {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    countDownTimer.start();
                    timerStarted = true;
                    textView.setVisibility(View.VISIBLE);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    countDownTimer.cancel();
                    timerStarted = false;
                    textView.setVisibility(View.INVISIBLE);

                }
                return false;
            }
        });

        //ON LONG CLICK (1X PENCET LAMA)
        //buttonStart.setOnLongClickListener(new View.OnLongClickListener()  {
//            @Override
//            public boolean onLongClick(View view) {
//                if (!timerStarted) {
//                    countDownTimer.start();
//                    timerStarted = true;
//                    textView.setVisibility(View.VISIBLE);
//                } else {
//                    countDownTimer.cancel();
//                    timerStarted = false;
//                    textView.setVisibility(View.INVISIBLE);
//
//                }
//                return false;
//            }


        //ON CLICK  (1X PENCET)
//        buttonStart.setOnClickListener(new View.OnClickListener()  {
//            @Override
//                public void onClick(View v) {
//                    if (!timerStarted) {
//                        countDownTimer.start();
//                        timerStarted = true;
//                        textView.setVisibility(View.VISIBLE);
//                    } else {
//                        countDownTimer.cancel();
//                        timerStarted = false;
//                        textView.setVisibility(View.INVISIBLE);
//
//                    }
//
//                }
//        });



        LinearLayout jadwal = (LinearLayout) v.findViewById(R.id.Jadwal);
        jadwal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), JadwalDokter.class);
                startActivity(intent);
            }
        });

        LinearLayout Daftar = (LinearLayout) v.findViewById(R.id.Daftar);
        Daftar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), Pendaftaran.class);
                startActivity(intent);
            }
        });

        return v;
    }

    public class CountDownTimerActivity extends CountDownTimer {
        public CountDownTimerActivity(long startTime, long interval) {
            super(startTime, interval);
        }
        @Override
        public void onFinish() {
            textView.setText("EMERGENCY");
            String number = "7777777777";
            Uri call = Uri.parse("tel:" + number);
            Intent surf = new Intent(Intent.ACTION_DIAL, call);
            startActivity(surf);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            textView.setText("" + millisUntilFinished/1000);
        }

    }
    private void setLayout() {
        new promo().execute();
    }

    class promo extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... strings) {

            CallSoap cs = new CallSoap();
            String data = cs.Promo("a");
            return data;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String url[] = s.split(",");
            for (int i = 0; i < url.length; i++) {
                FlipperView view = new FlipperView(getActivity().getBaseContext());
                view.setImageUrl(url[i])
                        .setDescription("Here" + (i + 1));
                flipperLayout.addFlipperView(view);
                view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                    @Override
                    public void onFlipperClick(FlipperView flipperView) {

                        Toast.makeText(getActivity()
                                , "Here " + (flipperLayout.getCurrentPagePosition() + 1)
                                , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}