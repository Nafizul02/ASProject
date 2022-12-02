package com.example.schedulab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.example.schedulab.databinding.ActivityGenerateTimelineBinding;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



public class GenerateTimeline extends AppCompatActivity {

    User user;
    ArrayList<Pair<String,String>> tableData = new ArrayList<Pair<String, String>>();
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    String currentDate=sdf.format(new Date());
    ArrayList<String> coursesTaken = new ArrayList<String>();
    DatabaseReference UserRef;
    DatabaseReference CourseRef;
    String Uid = "baJghyfNtINRCrOFxN5QFVjiTSj2";
    FirebaseUser fUser;

    private interface FirebaseCallback{
        void onCallback(ArrayList<String> list);
    }

    private ActivityGenerateTimelineBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGenerateTimelineBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_generate_timeline);

        final RecyclerView recyclerView = findViewById(R.id.timelineView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(GenerateTimeline.this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(new TimelineAdapter(tableData,this));

        //fUser = FirebaseAuth.getInstance().getCurrentUser();
        //Uid = fUser.getUid();

        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        CourseRef = FirebaseDatabase.getInstance().getReference().child("Courses");
        /*
        Intent i=getIntent();
        Bundle bundle = i.getExtras();
        String s = bundle.getString("input");
        */



        /*
        public void getCoursesTakenFromFirebae
        UserRef.addValueEventListener(new ValueEventListener() {//THIS WORKS
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot users : snapshot.getChildren()){
                    Map<String, Object> userMap = (HashMap<String, Object>) users.getValue();
                    //String fullName = (String)userMap.get("fullName");
                    if(users.child("key").equals(Uid)) {
                        coursesTaken = (ArrayList<String>) userMap.get("coursesTaken");
                        Log.d("gotcoursesTaken",coursesTaken.get(0));

                    }
                    //Log.d("check",coursesTaken.get(0));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
*/






        String s = "CSCB07,CSCB63,CSCB09";
        ArrayList<Course> allCourses = new ArrayList<Course>();

        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //coursesTaken = (ArrayList<String>) snapshot.child(Uid).child("coursesTaken").getValue();
                Map<String, Object> studentMap = (HashMap<String, Object>) snapshot.child(Uid).getValue();
                coursesTaken = (ArrayList<String>)studentMap.get("coursesTaken");
                Log.d("courseTakenSize",coursesTaken.size()+"");


            CourseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //clear old items from list to add new data
                    Iterable<DataSnapshot> children = snapshot.getChildren();
                    //getting all children from users root
                    for(DataSnapshot child : children){
                        Course newCourse = child.getValue(Course.class);
                        allCourses.add(newCourse);
                    }
                    String[] courseRequestsList = s.trim().split(",");

                    CourseRequest req =new CourseRequest(courseRequestsList, coursesTaken, allCourses);
                    ArrayList<Course> reqCourse = new ArrayList<Course>();
                    reqCourse = req.computeCoursesToTake();
                    for(Course c: reqCourse)
                        Log.d("GenerateTimeline","Courses required: "+c.getCode());


                    Log.d("time",currentDate);
                    Log.d("bundle","starting course-session grouping");

                    tableData = bundleCourseAndSession(reqCourse,coursesTaken);
                    Log.d("bundle","finished course-session grouping. size: "+tableData.size());
                    for(Pair pair : tableData)
                        Log.d("GenerateTableData",pair.first.toString()+" "+pair.second.toString());

                    recyclerView.setAdapter(new TimelineAdapter(tableData, GenerateTimeline.this));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //Log.d("checkAllCourses", allCourses.get(0).getCode());
        //coursesTaken=user.getCoursesTaken();
/*
        ArrayList<String> c1pre = new ArrayList<String>();//A08
        c1pre.add("none");
        Map<String, Boolean> c1sess = new HashMap<>();
        c1sess.put("aFall", true);
        c1sess.put("bWinter", true);
        c1sess.put("cSummer", false);
        Course c1 = new Course("CSCA08","A08",c1pre,c1sess);

        ArrayList<String> c2pre = new ArrayList<String>();//A48
        c2pre.add("CSCA08");
        Map<String, Boolean> c2sess = new HashMap<>();
        c2sess.put("aFall", false);
        c2sess.put("bWinter", true);
        c2sess.put("cSummer", true);
        Course c2 = new Course("CSCA48","A48",c2pre,c2sess);

        ArrayList<String> c3pre = new ArrayList<String>();//B07
        c3pre.add("CSCA48");
        Map<String, Boolean> c3sess = new HashMap<>();
        c3sess.put("aFall", true);
        c3sess.put("bWinter", true);
        c3sess.put("cSummer", false);
        Course c3 = new Course("CSCB07","B07",c3pre,c3sess);

        ArrayList<String> c4pre = new ArrayList<String>();//B09
        c4pre.add("CSCA48");
        Map<String, Boolean> c4sess = new HashMap<>();
        c4sess.put("aFall", false);
        c4sess.put("bWinter", true);
        c4sess.put("cSummer", true);
        Course c4 = new Course("CSCB09","B09",c4pre,c4sess);

        ArrayList<String> c5pre = new ArrayList<String>();//A67
        c5pre.add("none");
        Map<String, Boolean> c5sess = new HashMap<>();
        c5sess.put("aFall", true);
        c5sess.put("bWinter", true);
        c5sess.put("cSummer", false);
        Course c5 = new Course("CSCA67","A67",c5pre,c5sess);

        ArrayList<String> c6pre = new ArrayList<String>();//B36
        c6pre.add("CSCA48");
        c6pre.add("CSCA67");
        Map<String, Boolean> c6sess = new HashMap<>();
        c6sess.put("aFall", true);
        c6sess.put("bWinter", false);
        c6sess.put("cSummer", true);
        Course c6 = new Course("CSCB36","B36",c6pre,c6sess);

        ArrayList<String> c7pre = new ArrayList<String>();//B63
        c7pre.add("CSCB36");
        Map<String, Boolean> c7sess = new HashMap<>();
        c7sess.put("aFall", true);
        c7sess.put("bWinter", true);
        c7sess.put("cSummer", false);
        Course c7 = new Course("CSCB63","B63",c7pre,c7sess);

        ArrayList<String> c8pre = new ArrayList<String>();//C24
        c8pre.add("CSCB07");
        c8pre.add("CSCB09");
        Map<String, Boolean> c8sess = new HashMap<>();
        c8sess.put("aFall", false);
        c8sess.put("bWinter", true);
        c8sess.put("cSummer", true);
        Course c8 = new Course("CSCC24","C24",c8pre,c8sess);

        ArrayList<String> c9pre = new ArrayList<String>();//C63
        c9pre.add("CSCB63");
        c9pre.add("CSCB36");
        Map<String, Boolean> c9sess = new HashMap<>();
        c9sess.put("aFall", true);
        c9sess.put("bWinter", true);
        c9sess.put("cSummer", false);
        Course c9 = new Course("CSCC63","C63",c9pre,c9sess);
*/
        //ArrayList<Course> allCourses = new ArrayList<Course>();
        /*
        allCourses.add(c1);
        allCourses.add(c2);
        allCourses.add(c3);
        allCourses.add(c4);
        allCourses.add(c5);
        allCourses.add(c6);
        allCourses.add(c7);
        allCourses.add(c8);
        allCourses.add(c9);




        //ArrayList<String> coursesTaken = new ArrayList<String>();
        coursesTaken.add("CSCA08");
        coursesTaken.add("CSCA48");
        coursesTaken.add("CSCA67");

         */
        /*String[] courseRequestsList = s.trim().split(",");

        if (coursesTaken.size()==0)
            Log.d("coursesTaken", "0 courses taken");
        CourseRequest req =new CourseRequest(courseRequestsList, coursesTaken, allCourses);
        ArrayList<Course> reqCourse = new ArrayList<Course>();
        reqCourse = req.computeCoursesToTake();
        for(Course c: reqCourse)
            Log.d("GenerateTimeline","Courses required: "+c.getCode());

        Log.d("time",currentDate);

        ArrayList<Pair<String,String>> tableData = bundleCourseAndSession(reqCourse);
        for(Pair pair : tableData)
            Log.d("GenerateTableData",pair.first.toString()+" "+pair.second.toString());
        */
        binding.backbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GenerateTimeline.this, FirstFragment.class));
            }
        });


    }

    public int getSemester(){
        int month = Integer.parseInt(currentDate.substring(0,2));
        if(month<=4) //Jan,Feb,Mar,April
            return 1;
        else if (month<=8) //May,Jun,Jul,Aug
            return 2;
        else //Sep,Oct,Nov,Dec
            return 0;
    }

    public boolean canBeTaken(Course course, ArrayList<String> coursesTaken){
        List<String> prereq = new ArrayList<String>();
        prereq = course.getPrereqs();
        String courseCode="none";
        for(String code : prereq){
            courseCode=code;
            if(code.equals("None") || code.equals("none")) {
                //Log.d("basicCourse",code+" has no prereq");
                return true;
            }
            if(!coursesTaken.contains(code)) {
                //Log.d("courseNotTaken",code+" was never taken");
                return false;
            }
        }
        //Log.d("eligibleSuccess",courseCode+" can be taken");
        return true;
    }
    // 6 courses per semester???????
    public ArrayList<Pair<String, String>> bundleCourseAndSession(ArrayList<Course> coursesWanted,ArrayList<String> coursesTaken){
        ArrayList<String> temp = new ArrayList<String>();
        temp.addAll(coursesTaken);
        int addYear=0;
        ArrayList<Pair<String,String>> courseBundle = new ArrayList<Pair<String,String>>();
        int sems = getSemester();
        while(coursesWanted.size()>0){
            Log.d("courseWantedLoop",coursesWanted.size()+""+coursesWanted.get(0));
            for(int i=0;i<3;i++){
                if(!(coursesWanted.size()>0))
                    break;
                i+=sems;
                sems=0;
                String cTaken="";
                String currentSession;
                String session;
                if(i==0){
                    currentSession="Winter ";
                    session="bWinter";
                }
                else if(i==1) {
                    currentSession = "Summer ";
                    session="cSummer";
                }
                else {
                    currentSession = "Fall ";
                    session="aFall";
                }
                int currentYear = Integer.parseInt(currentDate.substring(6));
                currentSession+=currentYear+addYear;
                currentSession+=" :";
                Log.d("semesterLoop","i: "+i);

                Iterator itr = coursesWanted.iterator();
                ArrayList<String> toBeTemped = new ArrayList<String>();
                while(itr.hasNext()){
                    Course course = (Course)itr.next();
                    //Log.d("coursecheck","checking: "+course.getCode());


                    boolean sessionMatched = course.getSessions().get(session);
                    boolean eligible = canBeTaken(course,temp);
                    Log.d("sessionreport",currentSession+": "+sessionMatched);
                    Log.d("eligibilityreport","course can be taken: "+eligible);
                    if(sessionMatched && eligible){
                        cTaken+=course.getCode()+", ";
                        toBeTemped.add(course.getCode());
                        Log.d("deletecourse","deleted "+course.getCode());
                        itr.remove();
                    }
                }
                for(String code : toBeTemped)
                    temp.add(code);
                if(cTaken.length()>3)
                    cTaken=cTaken.substring(0,cTaken.length()-2);
                Pair<String,String> pair = new Pair<String,String>(currentSession,cTaken);
                courseBundle.add(pair);
                if(i==2)
                    addYear++;
            }

            //coursesWanted.remove();
        }
        return courseBundle;
    }
}