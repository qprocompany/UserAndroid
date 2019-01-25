package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterApps extends AppCompatActivity {

    EditText name, email,password,tgl,bulan,tahun,medical;
    RadioButton male,female;
    Button submit;

    String date,sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_apps);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        name = (EditText) findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        tgl = (EditText) findViewById(R.id.tanggal);
        bulan = (EditText) findViewById(R.id.bulan);
        tahun = (EditText) findViewById(R.id.tahun);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        medical = (EditText) findViewById(R.id.medicalno);

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(male.isChecked() == true)
                {
                    sex = "Pria";
                }
                else if(female.isChecked() == true)
                {
                    sex = "Wanita";
                }
                String Email = "0",Pass = "0",Name = "0",Date = "0",med = "0";
                Email = email.getText().toString();
                Pass = password.getText().toString();
                Name = name.getText().toString();
                Date = tgl.getText().toString()+"/"+bulan.getText().toString()+"/"+tahun.getText().toString();
                med = medical.getText().toString();
                //Toast.makeText(RegisterApps.this,Date,Toast.LENGTH_SHORT).show();
                new submited(Email,Pass,Name,sex,Date,med).execute();
            }
        });
    }

    class submited extends AsyncTask<String, String, String>
    {
        private String username, password,name ,sex1, dob,medno;

        public submited(String a, String b, String d, String e, String f, String g) {
            this.username = a;
            this.password = b;
            this.name = d;
            this.sex1 = e;
            this.dob = f;
            this.medno = g;
        }

        @Override
        protected String doInBackground(String... strings) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            CallSoap cs = new CallSoap();

            String data = "";
            data = cs.RegisterApps(username,password,name,sex1,dob,medno);
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(RegisterApps.this,"test",Toast.LENGTH_SHORT).show();
            if(s.equals("Success"))
                OpenMainActivity();
            else
                Toast.makeText(RegisterApps.this,s.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    public void OpenMainActivity(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
