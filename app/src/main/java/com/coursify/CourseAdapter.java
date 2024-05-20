package com.coursify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private List<Course> courseList;
    private LayoutInflater mInflater;

    public CourseAdapter(Context context, List<Course> courseList) {
        this.courseList = courseList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.course_item, parent, false);
        return new CourseViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        Course current = courseList.get(position);
        holder.courseName.setText(current.getName());
        holder.courseDescription.setText(current.getDescription());
        holder.courseImage.setImageResource(current.getImageResourceId()); // add this line
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView courseName;
        public final TextView courseDescription;
        public final ImageView courseImage; // changed from View to ImageView
        final CourseAdapter mAdapter;

        public CourseViewHolder(View itemView, CourseAdapter adapter) {
            super(itemView);
            courseName = itemView.findViewById(R.id.course_name);
            courseDescription = itemView.findViewById(R.id.course_description);
            courseImage = itemView.findViewById(R.id.course_image); // ensure R.id.course_image is ImageView in your layout
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get position of the clicked item
            int position = getLayoutPosition();
            Course course = courseList.get(position);

            // Start CourseDetailActivity
            Intent intent = new Intent(view.getContext(), CourseDetailActivity.class);
            intent.putExtra("courseName", course.getName());
            intent.putExtra("courseDescription", course.getDescription());
            intent.putExtra("courseImage", course.getImageResourceId()); // add this line
            view.getContext().startActivity(intent);
        }
    }
}
