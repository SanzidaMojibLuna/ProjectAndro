package com.example.user.projectandro;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class third extends AppCompatActivity  {
    public EditText mailadd,pas,fn,ln,phn;
    public CheckBox box1,box2,box3,box4;
    public Button sub;
    private DatabaseReference refDatabase;
    private FirebaseAuth mAuth;
    private ProgressDialog prog;
    private String fname,lname,femail,fpass,fphn;
    private StudentInfo student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        mailadd = (EditText) findViewById(R.id.address);
        pas = (EditText) findViewById(R.id.past);
        fn = (EditText) findViewById(R.id.n1);
        ln = (EditText) findViewById(R.id.n2);
        phn = (EditText) findViewById(R.id.num);

        box1 = (CheckBox)findViewById(R.id.use);
        box2 = (CheckBox)findViewById(R.id.lawyer);
        box3 = (CheckBox)findViewById(R.id.ngo);
        box4 = (CheckBox)findViewById(R.id.govt);

        sub = (Button)findViewById(R.id.submit);
       // sub.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
        refDatabase= FirebaseDatabase.getInstance().getReference();

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllInputData();
                createStudent();
                createAccountAndSaveInfo();
            }
        });
    }

    void getAllInputData(){
        fname = fn.getText().toString();
        lname = ln.getText().toString();
        femail=mailadd.getText().toString();
        fpass=pas.getText().toString();
        fphn=phn.getText().toString();

    }
    void createStudent(){
        student = new StudentInfo(fname,lname,femail,fpass,fphn);
    }
    void createAccountAndSaveInfo(){
       // prog.setMessage("Please wait!!");
      //  prog.show();
        mAuth.createUserWithEmailAndPassword(femail, fpass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("third", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            refDatabase = FirebaseDatabase.getInstance().getReference();
                            refDatabase.child(user.getUid()).setValue(student);
                        }
                        else {
                            //Log.w("third", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(third.this, "Successful",
                                    Toast.LENGTH_SHORT).show();
                        }
                      //  prog.dismiss();
                    }
                });
    }

    }

