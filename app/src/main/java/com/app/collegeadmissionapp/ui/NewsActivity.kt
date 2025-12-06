package com.app.collegeadmissionapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.collegeadmissionapp.ui.ChatbotActivity
import com.app.collegeadmissionapp.adapter.NewsAdapter
import com.app.collegeadmissionapp.ui.ProfileActivity
import com.app.collegeadmissionapp.R
import com.app.collegeadmissionapp.data.NewsItem
import com.app.collegeadmissionapp.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    // 2. Declare the binding variable
    private lateinit var binding: ActivityNewsBinding

    // Declare adapter (still needed)
    private lateinit var newsAdapter: NewsAdapter

    // Note: newsRecyclerView and bottomNav are accessed via 'binding'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // 3. Inflate the layout and initialize the binding object
        binding = ActivityNewsBinding.inflate(layoutInflater)

        // 4. Set the root view from the binding object
        setContentView(binding.root)

        // Handle system bars insets for CoordinatorLayout
        // Access the root view using binding.newsRoot (assuming 'newsRoot' is the ID of your root layout)
        ViewCompat.setOnApplyWindowInsetsListener(binding.newsRoot) { view, insets ->
            // Don't apply padding to root, let children handle it
            insets
        }

        // 5. initViews() is no longer needed/will be merged
        setupRecyclerView()
        setupBottomNav()
    }

    // initViews() is no longer needed/removed as views are accessed via binding

    private fun setupRecyclerView() {
        val newsList = getNewsList()
        newsAdapter = NewsAdapter(newsList)

        // Access views using the 'binding' object
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.newsRecyclerView.adapter = newsAdapter
    }

    private fun setupBottomNav() {
        // Access views using the 'binding' object
        binding.bottomNavigation.selectedItemId = R.id.nav_news

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, DashboardActivity::class.java)) // Assuming Dashboard is your home
                    finish()
                    true
                }
                R.id.nav_news -> true
                R.id.nav_chatbot -> {
                    startActivity(Intent(this, ChatbotActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    private fun getNewsList(): List<NewsItem> {
        return listOf(
            NewsItem(
                "JEE Main 2025 Registration Extended",
                "NTA extends JEE Main registration deadline to January 15, 2025",
                "2 hours ago"
            ),
            NewsItem(
                "JEE Advanced 2025 Eligibility Criteria",
                "IIT announces new eligibility criteria for JEE Advanced 2025",
                "5 hours ago"
            ),
            NewsItem(
                "BITSAT 2025 Dates Announced",
                "BITS Pilani announces BITSAT 2025 exam dates for all centers",
                "1 day ago"
            ),
            NewsItem(
                "New IITs to Offer B.Tech Programs",
                "Ministry of Education announces 3 new IITs to start from 2025",
                "2 days ago"
            ),
            NewsItem(
                "JoSAA Counseling Process Updates",
                "Important changes in JoSAA 2025 counseling procedure",
                "3 days ago"
            ),
            NewsItem(
                "VITEEE 2025 Syllabus Released",
                "VIT releases updated syllabus for VITEEE 2025 examination",
                "4 days ago"
            )
        )
    }
}