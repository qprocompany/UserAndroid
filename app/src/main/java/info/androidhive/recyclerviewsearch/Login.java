package info.androidhive.recyclerviewsearch;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private Button button;
    private Button register;
    public static String username1;
    RelativeLayout rellay1, rellay2;
    EditText username, password;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        button = (Button) findViewById(R.id.Login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pass = password.getText().toString();
                username1 = name;
                new loginapp(name,pass).execute();
            }
        });
        register = (Button) findViewById(R.id.RegisterApp);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenRegisterApp();
            }
        });
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        handler.postDelayed(runnable, 4000);
    }

    class loginapp extends AsyncTask<String, String, String>
    {
        private String username, password;
        public loginapp(String username, String password) {
            this.username = username;
            this.password = password;
        }
        @Override
        protected String doInBackground(String... strings) {
            CallSoap cs = new CallSoap();
            String data = cs.loginapps(username,password);
            return data;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(Login.this,s,Toast.LENGTH_SHORT).show();
            if(s.equals("True"))
                OpenMainActivity(username);
            else {
                //Toast.makeText(Login.this, "Your Username or password in Valid", Toast.LENGTH_SHORT).show();
                Toast.makeText(Login.this,"Your Username or Password is Wrong !!!",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void OpenMainActivity(String a){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void OpenRegisterApp(){
        Intent intent = new Intent(this, RegisterApps.class);
        startActivity(intent);
    }
    //  public void OpenLupaPassword(){
    //      Intent intent = new Intent(this, LupaPassword.class);
    //      startActivity(intent);
    //  }

}