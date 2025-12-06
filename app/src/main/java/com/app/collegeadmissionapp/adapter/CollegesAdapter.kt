package com.app.collegeadmissionapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.collegeadmissionapp.R
import com.app.collegeadmissionapp.data.College

class CollegesAdapter(private val colleges: List<College>) :
    RecyclerView.Adapter<CollegesAdapter.CollegeViewHolder>() {

    inner class CollegeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val collegeRank: TextView = view.findViewById(R.id.collegeRank)
        val collegeName: TextView = view.findViewById(R.id.collegeName)
        val collegeLocation: TextView = view.findViewById(R.id.collegeLocation)
        val collegeType: TextView = view.findViewById(R.id.collegeType)
        val placement: TextView = view.findViewById(R.id.placement)
        val avgPackage: TextView = view.findViewById(R.id.avgPackage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollegeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_college, parent, false)
        return CollegeViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollegeViewHolder, position: Int) {
        val college = colleges[position]
        holder.collegeRank.text = "#${college.rank}"
        holder.collegeName.text = college.name
        holder.collegeLocation.text = college.location
        holder.collegeType.text = college.type
        holder.placement.text = "Placement: ${college.placement}"
        holder.avgPackage.text = "Avg Package: ${college.avgPackage}"
    }

    override fun getItemCount() = colleges.size
}