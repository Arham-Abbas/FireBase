package com.arham.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    Button button, button2;
    EditText editText, editText2;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button = (Button) findViewById(R.id.button3);
        button2 = (Button) findViewById(R.id.button4);
        editText = (EditText) findViewById(R.id.editTextText3);
        editText2 = (EditText) findViewById(R.id.editTextText4);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        firebaseAuth = FirebaseAuth.getInstance();
        editText2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string, string2;
                string = editText.getText().toString();
                if(string.isEmpty())
                    editText.setError("Enter Email");
                else {
                    string2 = editText2.getText().toString();
                    if(string2.isEmpty())
                        editText2.setError("Enter Password");
                    else {
                        progressBar.setVisibility(View.VISIBLE);
                        firebaseAuth.createUserWithEmailAndPassword(string, PasswordEncryptor.encrypt(string2)).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.INVISIBLE);
                                if(task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.VISIBLE);
                                    firebaseAuth.signInWithEmailAndPassword(string, PasswordEncryptor.encrypt(string2)).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            progressBar.setVisibility(View.INVISIBLE);
                                            if(task.isSuccessful()) {
                                                Toast.makeText(Register.this, "Logged in as: " + string, Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(Register.this, Logout.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                            else
                                                Toast.makeText(Register.this, "Unable to log in", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else
                                    Toast.makeText(Register.this, "Unable to register", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}