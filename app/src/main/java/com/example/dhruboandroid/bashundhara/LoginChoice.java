package com.example.dhruboandroid.bashundhara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginChoice extends AppCompatActivity {

    private Button emailLogin;
    private Button phoneLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_choice);



        emailLogin = findViewById(R.id.btn_emaillogin);
        phoneLogin = findViewById(R.id.btn_phonelogin);


        emailLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginChoice.this, LoginActivity.class));
                finish();
            }
        });


        phoneLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginChoice.this, PhoneLogin.class));
                finish();
            }
        });
    }
}
