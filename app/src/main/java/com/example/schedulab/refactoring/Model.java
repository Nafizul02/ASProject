package com.example.schedulab.refactoring;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.schedulab.MainActivity;
import com.example.schedulab.showData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Model implements Contract.Model {



    private FirebaseAuth mAuth;
    private int a = 0;


    @Override
    public boolean checkLogin(String email, String password) {
        //gets string and password from presenter and uses database reference to authenticate then sends back boolean

        mAuth = FirebaseAuth.getInstance();


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users"); //update URL!!!
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Map<String, Object> studentMap = (HashMap<String, Object>) dataSnapshot.child(currentuser).getValue();
                            String type = (String) studentMap.get("type");
                            if (type.equals("Student")) {

                                a = 1;

                                //startActivity(new Intent(MainActivity.this, showData.class));
                            }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }


        });
        return a==1;
    }
}

