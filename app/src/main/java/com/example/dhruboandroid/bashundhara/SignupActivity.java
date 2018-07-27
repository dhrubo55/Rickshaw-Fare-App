package com.example.dhruboandroid.bashundhara;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputName, inputConfirmPassword;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    private static final String TAG = "ProfileActivity Atiar= ";

    // private FirebaseDatabase firedbinstance;
    // private DatabaseReference firedbReference;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_signup);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        //firedbinstance = FirebaseDatabase.getInstance();
        //firedbReference = firedbinstance.getReference("users");

        btnSignIn =  findViewById(R.id.sign_in_button);
        btnSignUp =  findViewById(R.id.sign_up_button);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        inputName = findViewById(R.id.name);
        inputConfirmPassword = findViewById(R.id.confirm_pass);



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // button for taking the user entered values to register the user in Firebase and db.
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                final String name = inputName.getText().toString().trim();
                String confirmPassword = inputConfirmPassword.getText().toString().trim();


                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(confirmPassword)){
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Password dont match", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){
                                    Log.d(TAG,task.getResult().getUser().getUid());
                                    Toast.makeText(SignupActivity.this, "User ID = "+task.getResult().getUser().getUid(), Toast.LENGTH_LONG).show();
                                }
                                if (task.isComplete()){
                                    Log.d(TAG,task.getResult().getUser().getUid());
                                    Toast.makeText(SignupActivity.this, "User ID = "+task.getResult().getUser().getUid(), Toast.LENGTH_LONG).show();
                                }

                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                }else{
                                  //  SharedPrefarences.setPreference(ProfileActivity.getContext(),"name",name);
                                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                    //intent.putExtra("Email",email);
                                    intent.putExtra("Name",name);
                                    startActivity(intent);
                                    finish();
                                }


                            }
                        });



/*                SharedPrefarences.setPreference(getApplicationContext(),"Name",name);
                SharedPrefarences.setPreference(getApplicationContext(),"Email",email);*/
//
//                FirebaseUser user = auth.getCurrentUser();
//                id = user.getUid();
//                createUser(name,email,id,"","","");
//
//

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}