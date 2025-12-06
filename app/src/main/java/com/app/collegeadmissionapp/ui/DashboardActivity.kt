package com.app.collegeadmissionapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import com.app.collegeadmissionapp.ui.ChatbotActivity
import com.app.collegeadmissionapp.ui.CollegesActivity
import com.app.collegeadmissionapp.ui.CoursesActivity
import com.app.collegeadmissionapp.ui.NewsActivity
import com.app.collegeadmissionapp.ui.ProfileActivity
import com.app.collegeadmissionapp.R
import com.app.collegeadmissionapp.ui.UniversitiesActivity
import com.app.collegeadmissionapp.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle system bars insets for CoordinatorLayout
        ViewCompat.setOnApplyWindowInsetsListener(binding.dashboardRoot) { view, insets ->
            insets
        }

        setupUserInfo()
        setupClickListeners()
    }

    private fun setupUserInfo() {
        val sharedPref = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val userName = sharedPref.getString("name", "Student") ?: "Student"
        binding.welcomeText.text = "Welcome, $userName!"
    }

    private fun setupClickListeners() {
        binding.courseCard.setOnClickListener {
            startActivity(Intent(this, CoursesActivity::class.java))
        }

        binding.branchesCard.setOnClickListener {
            // Same as courses for now - you can create separate BranchesActivity if needed
            startActivity(Intent(this, CoursesActivity::class.java))
        }

        binding.nationalExamCard.setOnClickListener {
            // Navigate to National Exam details (can be created later)
        }

        binding.stateExamCard.setOnClickListener {
            // Navigate to State Exam details (can be created later)
        }

        binding.universitiesCard.setOnClickListener {
            startActivity(Intent(this, UniversitiesActivity::class.java))
        }

        binding.collegesCard.setOnClickListener {
            startActivity(Intent(this, CollegesActivity::class.java))
        }

        binding.counselingCard.setOnClickListener {
            // Navigate to counseling info (can be created later)
        }

        binding.documentCard.setOnClickListener {
            // Navigate to documents (can be created later)
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    false
                }
                R.id.nav_news -> {
                    startActivity(Intent(this, NewsActivity::class.java))
                    false
                }
                R.id.nav_chatbot -> {
                    startActivity(Intent(this, ChatbotActivity::class.java))
                    false
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    false
                }
                else -> false
            }
        }
    }
}