package com.example.restaurantorg.orgrestaurant;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StaffLoginActivity<spemail, sharedPreferences> extends AppCompatActivity implements View.OnClickListener {

    private TextView login;
    private TextView fpassword;
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private FirebaseAuth mAuth;
    private final AppCompatActivity activity = StaffLoginActivity.this;
    public static final  String SHARED_PREFS="share_prefs";
    public static final String EMAIL_KEY="";
    public static final String PASSWORD_KEY="";

    private SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.AppThemeStaffLogin);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.button);
        btnLogin.setOnClickListener(this);


        login=(TextView) findViewById(R.id.tv_register);
        login.setOnClickListener(this);

        fpassword=(TextView)findViewById(R.id.tv_fpassword);
        fpassword.setOnClickListener(this);
        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
// ...
        if(sharedPreferences.getString(EMAIL_KEY,"")!=null){
            Intent intent= new Intent(StaffLoginActivity.this,StaffTablesActivity.class);
        }

// Initialize clasaaa Auth
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            Intent i = new Intent(StaffLoginActivity.this, StaffTablesActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            // User is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }


        mAuth = FirebaseAuth.getInstance();


    }




    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;

            case R.id.button: loginStaff();
                    break;

            case  R.id.tv_fpassword:
               // forgotPassword();


        }
    }



    private void loginStaff() {
        String email= etEmail.getText().toString().trim();
        String password= etPassword.getText().toString().trim();
        if (email.isEmpty()) {
            etEmail.setError("You have to complete all the fields");
            etEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etPassword.setError("You have to complete all the fields");
            etPassword.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("The e-mail adress isn't valid");
            etEmail.requestFocus();
            return;
        }
        if (password.length() < 6) {
            etPassword.setError("Password needs to be at least 6 characters");
            etPassword.requestFocus();
            return;

        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    startActivity(new Intent(StaffLoginActivity.this,StaffTablesActivity.class));
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(EMAIL_KEY, email);
                    editor.putString(PASSWORD_KEY, password);
                    editor.apply();

                }
                else
                {
                    Toast.makeText(StaffLoginActivity.this, "Your email or password doesn't exist", Toast.LENGTH_LONG).show();
                }
            }
        });




    }



}
