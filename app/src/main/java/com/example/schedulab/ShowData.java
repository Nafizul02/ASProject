package com.example.schedulab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowData extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Courses");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        listView = (ListView) findViewById(R.id.courseList);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    Map<String, Object> courseMap = (HashMap<String, Object>) child.getValue();
                    String name = (String) courseMap.get("name");
                    String code = (String) courseMap.get("code");

                    Map<String, Object> prereqMap = (HashMap<String, Object>) courseMap.get("prereqs");
                    Collection<Object> pqsAsObjects = prereqMap.values();
                    ArrayList<String> prereqs = new ArrayList<String>();
                    //setting course values to strings
                    for (Object obj : pqsAsObjects) {
                        prereqs.add(obj.toString());
                    }

                    TestEntry myEntries = new TestEntry(name, code, prereqs);
                    //myEntries.setName(name);
                    //myEntries.setCode(code);
                    //myEntries.setSessions(session);
                    //myEntries.setPrereqs(prereqs);
                    arrayList.add(myEntries.getCode().toString());
                }
                arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            Log.w("warning", "loadPost:onCancelled", error.toException());
            }
        });




    }
}