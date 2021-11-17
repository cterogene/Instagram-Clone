package com.example.instagramclone;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private EditText etUsermane;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(ParseUser.getCurrentUser() != null){
            GoMainActivity();
        }

        etUsermane = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnsignup = findViewById(R.id.btnsignup);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"OnClick Login Button");
                String Username = etUsermane.getText().toString();
                String Password = etPassword.getText().toString();
                loginUser(Username, Password);
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupUser ();
            }
        });
    }

    private void signupUser() {
        String Username = etUsermane.getText().toString();
        String Password = etPassword.getText().toString();
        Log.i(TAG, "Attemp to SignUp Parse");
        ParseUser user = new ParseUser();
        user.setUsername(Username);
        user.setPassword(Password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Toast.makeText(LoginActivity.this, "SignUp Succesfull", Toast.LENGTH_LONG).show();
                    GoMainActivity();
                }
                else{
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attemp to login Parse");
        ParseUser.logInInBackground(username,password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Login issues" + e);
                    Toast.makeText(LoginActivity.this, "Error, username or password incorrect", Toast.LENGTH_LONG).show();
                    return;
                }
                GoMainActivity();
                Toast.makeText(LoginActivity.this, "login success !", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void GoMainActivity() {
        Intent i  = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();


    }

}