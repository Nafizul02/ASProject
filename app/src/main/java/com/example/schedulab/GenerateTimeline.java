package com.example.schedulab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.example.schedulab.databinding.ActivityGenerateTimelineBinding;
import com.example.schedulab.databinding.ActivityGenerateTimelineBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GenerateTimeline extends AppCompatActivity {

    User user;

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    String currentDate=sdf.format(new Date());

    DatabaseReference UserRef;
    DatabaseReference CourseRef;
    ArrayList<String> coursesTaken = new ArrayList<String>();
    String Uid = "n16PeY7fz4cIgWy7xtupfiMyVHA2";
    FirebaseUser fUser;

    private ActivityGenerateTimelineBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGenerateTimelineBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_generate_timeline);

        //fUser = FirebaseAuth.getInstance().getCurrentUser();
        //Uid = fUser.getUid();

        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        CourseRef = FirebaseDatabase.getInstance().getReference().child("Courses");
        /*
        Intent i=getIntent();
        Bundle bundle = i.getExtras();
        String s = bundle.getString("input");
*/
        UserRef.addValueEventListener(new ValueEventListener() {//THIS WORKS
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot users : snapshot.getChildren()){
                    Map<String, Object> courseMap = (HashMap<String, Object>) users.getValue();
                    String fullName = (String)courseMap.get("fullName");

                    Log.d("check",fullName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        UserRef.addValueEventListener(new ValueEventListener() {//THIS WORKS
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot users : snapshot.getChildren()){
                    Map<String, Object> userMap = (HashMap<String, Object>) users.getValue();
                    //String fullName = (String)userMap.get("fullName");
                    if(users.child("key").equals(Uid))
                     coursesTaken = (ArrayList<String>)  userMap.get("coursesTaken");

                    Log.d("check",coursesTaken.get(0));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        UserRef.child(Uid).addValueEventListener(new ValueEventListener() {//This doesn't work
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
                Log.d("checkCourseTaken", user.getFullName());

                //for(DataSnapshot users : snapshot.getChildren()){
                  //  if(users.child("key").equals())
                    //    Log.d("usercheck",users.toString());
                    //Map<String, Object> userMap = (HashMap<String, Object>) snapshot.getValue();
                    //ArrayList<String> coursesTaken = snapshot.child("coursesTaken").getValue(ArrayList<String>);
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //coursesTaken=user.getCoursesTaken();

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

        ArrayList<Course> allCourses = new ArrayList<Course>();
        allCourses.add(c1);
        allCourses.add(c2);
        allCourses.add(c3);
        allCourses.add(c4);
        allCourses.add(c5);
        allCourses.add(c6);
        allCourses.add(c7);
        allCourses.add(c8);
        allCourses.add(c9);
        String[] courseRequestsList={"CSCC24","CSCC63"};

        //ArrayList<String> coursesTaken = new ArrayList<String>();
        coursesTaken.add("CSCA08");
        coursesTaken.add("CSCA48");
        coursesTaken.add("CSCA67");


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
            return 0;
        else if (month<=8) //May,Jun,Jul,Aug
            return 1;
        else //Sep,Oct,Nov,Dec
            return 2;
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

    @SuppressLint("SuspiciousIndentation")
    public ArrayList<Pair<String, String>> bundleCourseAndSession(ArrayList<Course> coursesWanted){
        ArrayList<String> temp = new ArrayList<String>();
        temp=coursesTaken;
        int addYear=0;
        ArrayList<Pair<String,String>> courseBundle = new ArrayList<Pair<String,String>>();
        int sems = getSemester();
        while(coursesWanted.size()>0){
            //Log.d("courseWantedLoop","Size>0");
            for(int i=0;i<3;i++){
                if(!(coursesWanted.size()>0))
                    break;
                i+=sems;
                sems=0;
                String coursesTaken="";
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
                //Log.d("semesterLoop","i: "+i);

                Iterator itr = coursesWanted.iterator();
                ArrayList<String> toBeTemped = new ArrayList<String>();
                while(itr.hasNext()){
                    Course course = (Course)itr.next();
                    //Log.d("coursecheck","checking: "+course.getCode());


                    boolean sessionMatched = course.getSessions().get(session);
                    boolean eligible = canBeTaken(course,temp);
                    //Log.d("sessionreport",currentSession+": "+sessionMatched);
                    //Log.d("eligibilityreport","course can be taken: "+eligible);
                    if(sessionMatched && eligible){
                        coursesTaken+=course.getCode()+", ";
                        toBeTemped.add(course.getCode());
                        //Log.d("deletecourse","deleted "+course.getCode());
                        itr.remove();
                    }
                }
                for(String code : toBeTemped)
                    temp.add(code);
                Pair<String,String> pair = new Pair<String,String>(currentSession,coursesTaken.
                        substring(0,coursesTaken.length()-2));
                courseBundle.add(pair);
                if(i==2)
                    addYear++;
            }

            //coursesWanted.remove();
        }
        return courseBundle;
    }
}