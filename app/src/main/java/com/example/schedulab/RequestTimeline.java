package com.example.schedulab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;
import java.util.Locale;


public class RequestTimeline extends AppCompatActivity implements  View.OnClickListener {

    private Button Generate;
    public String s;
    private EditText input;
    private DatabaseReference coursesRef, userRef;
    private FirebaseUser fUser;
    private String Uid;
    private int a;
    private boolean b;
    Context myContext = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_request_timeline);

        input = (EditText) findViewById(R.id.CourseG);

        Generate = (Button) findViewById(R.id.Generate);
        Generate.setOnClickListener(this);

    }

    public void Back(View view) {
        Intent i = new Intent(RequestTimeline.this, MainActivity.class);
        startActivity(i);
    }


    @Override
    public void onClick(View view) {
        b=true;
        s = input.getText().toString().toUpperCase().trim();

        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        coursesRef = FirebaseDatabase.getInstance().getReference().child("Courses");
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        Uid = fUser.getUid();
        if (s.equals("")) {
            input.setError("Course code is required");
            input.requestFocus();
            return;
        } else {
            checkValid(s);
        }

    }

    public void checkValid (String s){
        String[] parts = s.split(",");
        for (String part : parts) {
            part.toUpperCase().trim();
            Query query = coursesRef.orderByChild("code").equalTo(part.toUpperCase().trim());
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.exists()) {
                        input.setError("Please enter the correct code");
                        input.requestFocus();
                        b=false;
                    } else  {
                        if (part.equals(getLast(s).toUpperCase())&&b==true) {
                            Intent i = new Intent(myContext,sample.class);
                            i.putExtra("input", s);
                            System.out.println(s);
                            startActivity(i);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(RequestTimeline.this, "Course failed to add", Toast.LENGTH_LONG);
                }
            });
            if(!b) {
                input.setError("Please enter the correct code");
                input.requestFocus();
                break;
            }
        }
    }
    public String getLast(String s) {
        a=getNum(s);
        int b=0;
        String last=null;
        String[] parts = s.split(",");
        for (String part : parts) {
            b++;
            if (a==b) {
                last = part.toUpperCase().trim();
            }
        }
        return last;
    }
    public int getNum(String s) {
        int b=0;
        String[] parts = s.split(",");
        for (String part : parts) {
            b++;
        }
        return b;
    }
}


