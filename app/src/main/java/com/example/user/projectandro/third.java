package com.example.user.projectandro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class third extends AppCompatActivity  {
    private EditText mailadd,pas,fn,ln,phn;
    private RadioGroup rad;
    private RadioButton typebtn;
    private ProgressDialog prog;
    public Button sub;
    private DatabaseReference refdata;
    private FirebaseAuth mauth;
    private String fname,lname,femail,fpass,fphn,type;
    private StudentInfo student;
    private int selectid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        mailadd = (EditText) findViewById(R.id.address);
        pas = (EditText) findViewById(R.id.past);
        fn = (EditText) findViewById(R.id.n1);
        ln = (EditText) findViewById(R.id.n2);
        phn = (EditText) findViewById(R.id.num);

        rad = (RadioGroup) findViewById(R.id.radioid);

        sub = (Button) findViewById(R.id.submit);


        mauth = FirebaseAuth.getInstance();
        refdata = FirebaseDatabase.getInstance().getReference();
        prog=new ProgressDialog(this);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllInputData();
                createStudent();
                createAccount();
                save();
            }
        });
    }

    void getAllInputData(){
        fname = fn.getText().toString();
        lname = ln.getText().toString();
        femail=mailadd.getText().toString();
        fpass=pas.getText().toString();
        fphn=phn.getText().toString();
        selectid=rad.getCheckedRadioButtonId();
        typebtn=(RadioButton)findViewById(selectid);
        type=typebtn.getText().toString();
        Toast.makeText(third.this,"This is "+type,Toast.LENGTH_SHORT).show();

    }
    void createStudent(){
        student = new StudentInfo(fname,lname,fphn,type);
    }
   void createAccount() {
       if (!femail.isEmpty() && !fpass.isEmpty()) {
           prog.setMessage("Please wait!!");
           prog.show();
           mauth.createUserWithEmailAndPassword(femail, fpass)
                   .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               Log.d("third", "createUserWithEmail:success");
                               FirebaseUser user = mauth.getCurrentUser();
                               Toast.makeText(third.this, "Successful",
                                       Toast.LENGTH_SHORT).show();

                           } else {
                               //Log.w("third", "createUserWithEmail:failure", task.getException());
                               Toast.makeText(third.this, "UnSuccessful! E-mail and Password feild can't be empty!",
                                       Toast.LENGTH_SHORT).show();
                           }
                            prog.dismiss();
                       }
                   });
       }
   }
void save(){
    if(type.equals("User")){
        Toast.makeText(third.this,"Type is "+type,Toast.LENGTH_SHORT).show();
        StudentInfo s = new StudentInfo(fname, lname, fphn, type);
        DatabaseReference d = FirebaseDatabase.getInstance().getReference("User");
        String s1 = d.push().getKey();
        d.child(s1).setValue(s);
        ArrayList<ArrayList<String>> mainArrayList = new ArrayList<ArrayList<String>>();
        ArrayList<String> subArrayList = new ArrayList<String>();
        subArrayList.add(fname);
        subArrayList.add(lname);
        subArrayList.add(fphn);
        subArrayList.add(type);
        mainArrayList.add(subArrayList);
        mauth = FirebaseAuth.getInstance();
        //prog.setMessage("please wait");
        refdata = FirebaseDatabase.getInstance().getReference();
        Intent userpage = new Intent(third.this,User.class);
        startActivity(userpage);
    }

    else if(type.equals("Lawyer")){
        Toast.makeText(third.this,"Type is "+type,Toast.LENGTH_SHORT).show();
        StudentInfo s2 = new StudentInfo(fname, lname, fphn, type);
        DatabaseReference d1 = FirebaseDatabase.getInstance().getReference("Lawyer");
        String s3 = d1.push().getKey();
        d1.child(s3).setValue(s2);
        ArrayList<ArrayList<String>> lawArrayList = new ArrayList<ArrayList<String>>();
        ArrayList<String> lawArrayList1 = new ArrayList<String>();
        lawArrayList1.add(fname);
        lawArrayList1.add(lname);
        lawArrayList1.add(fphn);
        lawArrayList1.add(type);
        lawArrayList.add(lawArrayList1);
        mauth = FirebaseAuth.getInstance();
        //prog.setMessage("please wait");
        refdata = FirebaseDatabase.getInstance().getReference();
        Intent lawyerpage = new Intent(third.this,Lawyer.class);
        startActivity(lawyerpage);

    }

    else if(type.equals("NGO")){
        Toast.makeText(third.this,"Type is "+type,Toast.LENGTH_SHORT).show();
        StudentInfo ngo = new StudentInfo(fname, lname, fphn, type);
        DatabaseReference dngo = FirebaseDatabase.getInstance().getReference("NGO");
        String sngo = dngo.push().getKey();
        dngo.child(sngo).setValue(ngo);
        ArrayList<ArrayList<String>> ngoArrayList = new ArrayList<ArrayList<String>>();
        ArrayList<String> ngoArrayList1 = new ArrayList<String>();
        ngoArrayList1.add(fname);
        ngoArrayList1.add(lname);
        ngoArrayList1.add(fphn);
        ngoArrayList1.add(type);
        ngoArrayList.add(ngoArrayList1);
        mauth = FirebaseAuth.getInstance();
        //prog.setMessage("please wait");
        refdata = FirebaseDatabase.getInstance().getReference();
        Intent ngopage = new Intent(third.this,NGO.class);
        startActivity(ngopage);


    }
    else if(type.equals("Govt Org")){
        Toast.makeText(third.this,"Type is "+type,Toast.LENGTH_SHORT).show();
        StudentInfo gov = new StudentInfo(fname, lname, fphn, type);
        DatabaseReference dgov = FirebaseDatabase.getInstance().getReference("Govt org");
        String sgov = dgov.push().getKey();
        dgov.child(sgov).setValue(gov);
        ArrayList<ArrayList<String>> govArrayList = new ArrayList<ArrayList<String>>();
        ArrayList<String> govArrayList1 = new ArrayList<String>();
        govArrayList1.add(fname);
        govArrayList1.add(lname);
        govArrayList1.add(fphn);
        govArrayList1.add(type);
        govArrayList.add(govArrayList1);
        mauth = FirebaseAuth.getInstance();
        refdata = FirebaseDatabase.getInstance().getReference();
        Intent govtpage = new Intent(third.this,Govt.class);
        startActivity(govtpage);

    }
    else
    {
        Toast.makeText(third.this,"Type is not checked.Please check the type"+type,Toast.LENGTH_SHORT).show();
    }

}
    }

