package com.example.schedulab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schedulab.databinding.ActivityAdminAddBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminAdd extends AdminDrawerBase {

    private static final String TAG = "AdminAdd";
    private static final String KEY_NAME = "name";
    private static final String KEY_CODE = "code";
    private static final String KEY_SESSIONS = "sessions";
    private static final String KEY_PREREQ = "prereqs";

    private CheckBox mFallCheck, mWinterCheck, mSummerCheck;

    private EditText editTextName, editTextCode, editTextPreReq;
            //editTextSessions, editTextPreReq;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Courses");

    private Button back_to_all;


    ActivityAdminAddBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminAddBinding.inflate(getLayoutInflater());
        allocateActivityTitle("Add Course");
        setContentView(binding.getRoot());

        back_to_all = findViewById(R.id.back);
        back_to_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_add = new Intent(AdminAdd.this, AllCourses.class);
                startActivity(intent_add);
            }
        });

        editTextName = findViewById(R.id.course_name);
        editTextCode = findViewById(R.id.course_code);
        editTextPreReq = findViewById(R.id.course_prereq);

        mFallCheck = findViewById(R.id.check_fall);
        mWinterCheck = findViewById(R.id.check_winter);
        mSummerCheck = findViewById(R.id.check_summer);

    }

    public void saveEntry(View v){

        String name = editTextName.getText().toString();
        String code = editTextCode.getText().toString();
        String PQinput = editTextPreReq.getText().toString();

        Boolean checkFall = mFallCheck.isChecked();
        Boolean checkWinter = mWinterCheck.isChecked();
        Boolean checkSummer = mSummerCheck.isChecked();

        /* check if the course name or course code already exists */

        

        //should change to something more dynamic later
        String[] PQarray = PQinput.split("\\s*,\\s*"); //removes empty spaces at beginning and end of each single string
        List<String> cPreReqs = Arrays.asList(PQarray);


        Map<String, Boolean> sessions = new HashMap<>();
        sessions.put("aFall", checkFall);
        sessions.put("bWinter", checkWinter);
        sessions.put("cSummer", checkSummer);

        Entry entry = new Entry();
        entry.setName(name);
        entry.setCode(code);
        entry.setPrereqs(cPreReqs);
        entry.setSessions(sessions);


    //following is to push AND pop-up for user to say course has been added
        root.push().setValue(entry)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        editTextName.setText("");
                        editTextCode.setText("");
                        editTextPreReq.setText("");

                        mFallCheck.setChecked(false);
                        mWinterCheck.setChecked(false);
                        mSummerCheck.setChecked(false);

                        Toast.makeText(AdminAdd.this, "Course Added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdminAdd.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}