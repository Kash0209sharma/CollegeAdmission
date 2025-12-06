package com.app.collegeadmissionapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.collegeadmissionapp.R
import com.app.collegeadmissionapp.data.University

class UniversitiesAdapter(private val universities: List<University>) :
    RecyclerView.Adapter<UniversitiesAdapter.UniversityViewHolder>() {

    inner class UniversityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val universityRank: TextView = view.findViewById(R.id.universityRank)
        val universityName: TextView = view.findViewById(R.id.universityName)
        val universityLocation: TextView = view.findViewById(R.id.universityLocation)
        val nirfRank: TextView = view.findViewById(R.id.nirfRank)
        val admission: TextView = view.findViewById(R.id.admission)
        val fees: TextView = view.findViewById(R.id.fees)
        val highlights: TextView = view.findViewById(R.id.highlights)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_university, parent, false)
        return UniversityViewHolder(view)
    }

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        val university = universities[position]
        holder.universityRank.text = "#${university.rank}"
        holder.universityName.text = university.name
        holder.universityLocation.text = university.location
        holder.nirfRank.text = university.nirfRank
        holder.admission.text = "Admission: ${university.admission}"
        holder.fees.text = "Fees: ${university.fees}"
        holder.highlights.text = university.highlights
    }

    override fun getItemCount() = universities.size
}