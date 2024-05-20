package com.coursify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class AddCourseActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private EditText courseNameInput;
    private EditText courseDescriptionInput;
    private Button addButton;
    private Button addImageButton;
    private ImageView courseImagePreview;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        courseNameInput = findViewById(R.id.courseNameInput);
        courseDescriptionInput = findViewById(R.id.courseDescriptionInput);
        addButton = findViewById(R.id.addButton);
        addImageButton = findViewById(R.id.addImageButton);
        courseImagePreview = findViewById(R.id.courseImagePreview);

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = courseNameInput.getText().toString();
                String courseDescription = courseDescriptionInput.getText().toString();

                // The selectedImageUri is used instead of the resourceId
                Course newCourse = new Course(courseName, courseDescription, R.drawable.coursifylecture);

                // Create intent & pass course back to MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("NEW_COURSE", newCourse);
                setResult(Activity.RESULT_OK, resultIntent);
                finish(); // finish activity to return to MainActivity
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE) {
            if(resultCode == Activity.RESULT_OK){
                selectedImageUri = data.getData();
                courseImagePreview.setImageURI(selectedImageUri);
            }
        }
    }
}
