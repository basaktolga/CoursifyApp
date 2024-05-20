package com.coursify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CourseDetailActivity extends AppCompatActivity {
    private TextView courseNameTextView;
    private TextView courseDescriptionTextView;
    private ImageView courseImageView; // changed from View to ImageView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        courseNameTextView = findViewById(R.id.course_name);
        courseDescriptionTextView = findViewById(R.id.course_description);
        courseImageView = findViewById(R.id.course_image);

        String courseName = getIntent().getStringExtra("courseName");
        String courseDescription = getIntent().getStringExtra("courseDescription");
        int courseImage = getIntent().getIntExtra("courseImage", 0); // default value is necessary

        courseNameTextView.setText(courseName);
        courseDescriptionTextView.setText(courseDescription);
        courseImageView.setImageResource(courseImage);
    }
}