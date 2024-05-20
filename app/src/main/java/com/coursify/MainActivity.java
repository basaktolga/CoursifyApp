package com.coursify;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView welcomeText;
    private RecyclerView courseRecyclerView;
    private List<Course> courseList;
    private CourseAdapter adapter; // declare adapter here

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Course newCourse = (Course) data.getSerializableExtra("NEW_COURSE");
                        courseList.add(newCourse);
                        adapter.notifyDataSetChanged();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeText = findViewById(R.id.welcomeText);
        courseRecyclerView = findViewById(R.id.course_list);

        String username = getIntent().getStringExtra("username");
        welcomeText.setText("Hello, " + username + "!");

        // Initialize course list
        courseList = new ArrayList<>();
        // Add some dummy data
        courseList.add(new Course("Mobile App Development", "An in-depth look into developing mobile applications for Android and iOS. Topics include UI design, data persistence, web services, maps and location services, and touch gestures.", R.drawable.cs310courseimage));
        courseList.add(new Course("Discrete Math", "This course introduces students to the fundamentals of discrete mathematics, including logic, set theory, relations, and combinatorics.", R.drawable.discretemathcourse));
        courseList.add(new Course("Cryptography", "A deep dive into the techniques for secure communication in the presence of adversaries. Topics include encryption, digital signatures, and hash functions.", R.drawable.cryptographycourse));
        courseList.add(new Course("Linear Algebra", "This course covers the key concepts of linear algebra, such as vectors, matrices, linear transformations, eigenvalues, and eigenvectors.", R.drawable.linearalgebra));
        courseList.add(new Course("Calculus I", "This introductory course to calculus covers topics such as limits, derivatives, integrals, and their applications.", R.drawable.calculussss));
        courseList.add(new Course("Calculus II", "A continuation of Calculus I, exploring topics such as techniques of integration, parametric equations, and infinite series.", R.drawable.calculussss));
        courseList.add(new Course("Database Systems", "An overview of the principles of database systems, focusing on data models, database design, and SQL.", R.drawable.dbmscourse));
        courseList.add(new Course("Data Structure", "This course covers the design and implementation of data structures including arrays, stacks, queues, linked lists, binary trees, heaps, balanced trees, and hash tables.", R.drawable.datastructures));
        courseList.add(new Course("Blockchain", "An introduction to the concept of blockchain and its applications in cryptocurrencies, decentralized apps, and smart contracts.", R.drawable.blockchain));
        courseList.add(new Course("Machine Learning", "This course provides a broad introduction to machine learning, data mining, and statistical pattern recognition.", R.drawable.machinelearning));

        // Setup recycler view
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CourseAdapter(this, courseList); // initialize adapter here
        courseRecyclerView.setAdapter(adapter);

        Button addButton = findViewById(R.id.add_course_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCourseActivity.class);
                mStartForResult.launch(intent);
            }
        });
    }
}
