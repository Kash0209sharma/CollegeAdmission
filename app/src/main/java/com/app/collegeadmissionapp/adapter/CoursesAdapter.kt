package com.app.collegeadmissionapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.collegeadmissionapp.R
import com.app.collegeadmissionapp.data.Course

class CoursesAdapter(private val courses: List<Course>) :
    RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val courseName: TextView = view.findViewById(R.id.courseName)
        val courseDescription: TextView = view.findViewById(R.id.courseDescription)
        val courseOpportunities: TextView = view.findViewById(R.id.courseOpportunities)
        val topColleges: TextView = view.findViewById(R.id.topColleges)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        holder.courseName.text = course.name
        holder.courseDescription.text = course.description
        holder.courseOpportunities.text = "Opportunities: ${course.opportunities}"
        holder.topColleges.text = "Top Colleges: ${course.topColleges}"
    }

    override fun getItemCount() = courses.size
}