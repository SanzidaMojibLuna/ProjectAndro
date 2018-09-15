package com.example.user.projectandro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class second extends AppCompatActivity {

    private Button login,account;
    private EditText t1,t2;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        login = (Button) findViewById(R.id.log);
        account= (Button) findViewById(R.id.acc);

        t1 = (EditText) findViewById(R.id.Text1);
        t2 = (EditText) findViewById(R.id.Text2);


        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m1 = t1.getText().toString();
                String m2 = t2.getText().toString();
                if(!m1.isEmpty() && !m2.isEmpty()){
                    Toast.makeText(second.this,"success",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(second.this, "please fill up email and password field", Toast.LENGTH_SHORT).show();

                }

                signIn();
            }

        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(second.this, third.class);
                startActivity(i2);
            }
        });

    }
    void signIn(){
        email = t1.getText().toString().trim();
        password = t2.getText().toString().trim();
        progressDialog.setMessage("Please wait!!!");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"LogInSuccess",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(second.this,"Auth failed",Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
}
