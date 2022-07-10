package com.example.restaurantorg.orgrestaurant;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantorg.orgrestaurant.Models.StaffMember;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private Button register;
    private EditText editTextName, editTextEmail, editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        register = (Button) findViewById(R.id.btn_register);
        register.setOnClickListener((this));
        editTextEmail = (EditText) findViewById(R.id.et_email);
        editTextName = (EditText) findViewById(R.id.et_name);
        editTextPassword = (EditText) findViewById(R.id.et_password);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                registerStaff();
                break;


        }

    }

    private void registerStaff() {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String name = editTextName.getText().toString().trim();

        if (name.isEmpty()) {
            editTextName.setError("You have to complete all the fields");
            editTextName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("You have to complete all the fields");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("You have to complete all the fields");
            editTextPassword.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("The e-mail adress isn't valid");
            editTextEmail.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Password needs to be at least 6 characters");
            editTextPassword.requestFocus();
            return;

        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            StaffMember staffMember = new StaffMember(name, email);
                            FirebaseDatabase.getInstance().getReference("StaffMember")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(staffMember).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Registration succesful ", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Registration wasn't succesful", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else
                        {Toast.makeText(RegisterActivity.this, "Registration wasn't succesful", Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }
}