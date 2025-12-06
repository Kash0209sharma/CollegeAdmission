package com.app.collegeadmissionapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.collegeadmissionapp.R
import com.app.collegeadmissionapp.adapter.UniversitiesAdapter
import com.app.collegeadmissionapp.data.University
import com.google.android.material.appbar.MaterialToolbar

class UniversitiesActivity : AppCompatActivity() {

    private lateinit var universitiesRecyclerView: RecyclerView
    private lateinit var universitiesAdapter: UniversitiesAdapter
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView(R.layout.activity_universities)

        // Handle system bars insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.universitiesRoot)) { view, insets ->
            insets
        }

        initViews()
        setupToolbar()
        setupRecyclerView()
    }

    private fun initViews() {
        universitiesRecyclerView = findViewById(R.id.universitiesRecyclerView)
        toolbar = findViewById(R.id.toolbar)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        val universitiesList = getUniversitiesList()
        universitiesAdapter = UniversitiesAdapter(universitiesList)
        universitiesRecyclerView.layoutManager = LinearLayoutManager(this)
        universitiesRecyclerView.adapter = universitiesAdapter
    }

    private fun getUniversitiesList(): List<University> {
        return listOf(
            University(
                1,
                "IIT Bombay",
                "Mumbai, Maharashtra",
                "NIRF Rank 1",
                "Through JEE Advanced",
                "₹2.5 lakh/year",
                "Excellent infrastructure, top faculty, global recognition"
            ),
            University(
                2,
                "IIT Delhi",
                "New Delhi",
                "NIRF Rank 2",
                "Through JEE Advanced",
                "₹2.5 lakh/year",
                "Prime location, research excellence, strong alumni network"
            ),
            University(
                3,
                "IIT Madras",
                "Chennai, Tamil Nadu",
                "NIRF Rank 3",
                "Through JEE Advanced",
                "₹2.5 lakh/year",
                "Innovation hub, entrepreneurship, world-class facilities"
            ),
            University(
                4,
                "IIT Kanpur",
                "Kanpur, Uttar Pradesh",
                "NIRF Rank 4",
                "Through JEE Advanced",
                "₹2.5 lakh/year",
                "Research-oriented, strong placement record"
            ),
            University(
                5,
                "IIT Kharagpur",
                "Kharagpur, West Bengal",
                "NIRF Rank 5",
                "Through JEE Advanced",
                "₹2.5 lakh/year",
                "Oldest IIT, largest campus, diverse departments"
            ),
            University(
                6,
                "IIT Roorkee",
                "Roorkee, Uttarakhand",
                "NIRF Rank 6",
                "Through JEE Advanced",
                "₹2.5 lakh/year",
                "Civil engineering excellence, heritage institution"
            ),
            University(
                7,
                "IIT Guwahati",
                "Guwahati, Assam",
                "NIRF Rank 7",
                "Through JEE Advanced",
                "₹2.5 lakh/year",
                "Beautiful campus, growing reputation, research facilities"
            ),
            University(
                8,
                "IIT Hyderabad",
                "Hyderabad, Telangana",
                "NIRF Rank 8",
                "Through JEE Advanced",
                "₹2.5 lakh/year",
                "AI & ML focus, modern infrastructure, innovation"
            ),
            University(
                9,
                "BITS Pilani",
                "Pilani, Rajasthan",
                "NIRF Rank 25",
                "Through BITSAT",
                "₹4.5 lakh/year",
                "Autonomous, industry connections, flexible curriculum"
            ),
            University(
                10,
                "NIT Trichy",
                "Tiruchirappalli, Tamil Nadu",
                "NIRF Rank 9",
                "Through JEE Main",
                "₹1.5 lakh/year",
                "Top NIT, excellent placements, strong academics"
            ),
            University(
                11,
                "NIT Surathkal",
                "Mangalore, Karnataka",
                "NIRF Rank 13",
                "Through JEE Main",
                "₹1.5 lakh/year",
                "Coastal campus, engineering excellence, sports facilities"
            ),
            University(
                12,
                "NIT Warangal",
                "Warangal, Telangana",
                "NIRF Rank 19",
                "Through JEE Main",
                "₹1.5 lakh/year",
                "Premier NIT, strong technical programs"
            ),
            University(
                13,
                "IIIT Hyderabad",
                "Hyderabad, Telangana",
                "NIRF Rank 62",
                "Through JEE Main",
                "₹2 lakh/year",
                "CS & IT focus, research intensive, top placements"
            ),
            University(
                14,
                "DTU Delhi",
                "Delhi",
                "NIRF Rank 36",
                "Through JEE Main",
                "₹1.5 lakh/year",
                "Delhi location, industry exposure, good infrastructure"
            ),
            University(
                15,
                "VIT Vellore",
                "Vellore, Tamil Nadu",
                "NIRF Rank 11",
                "Through VITEEE",
                "₹2 lakh/year",
                "International collaborations, research facilities"
            ),
            University(
                16,
                "Anna University",
                "Chennai, Tamil Nadu",
                "NIRF Rank 12",
                "Through TNEA",
                "₹50,000/year",
                "State university, strong engineering programs"
            ),
            University(
                17,
                "Jadavpur University",
                "Kolkata, West Bengal",
                "NIRF Rank 14",
                "Through WBJEE",
                "₹10,000/year",
                "Low fees, quality education, research culture"
            ),
            University(
                18,
                "PSG Tech",
                "Coimbatore, Tamil Nadu",
                "NIRF Rank 52",
                "Through TNEA",
                "₹1 lakh/year",
                "Industry oriented, good placements, autonomous"
            ),
            University(
                19,
                "Thapar University",
                "Patiala, Punjab",
                "NIRF Rank 27",
                "Through JEE Main",
                "₹3 lakh/year",
                "Deemed university, quality education, research focus"
            ),
            University(
                20,
                "SRM University",
                "Chennai, Tamil Nadu",
                "NIRF Rank 32",
                "Through SRMJEEE",
                "₹2.5 lakh/year",
                "Large campus, international exposure, facilities"
            ),
            University(
                21,
                "Manipal Institute",
                "Manipal, Karnataka",
                "NIRF Rank 47",
                "Through MET",
                "₹3.5 lakh/year",
                "Medical & engineering, campus life, placements"
            ),
            University(
                22,
                "BIT Mesra",
                "Ranchi, Jharkhand",
                "NIRF Rank 30",
                "Through JEE Main",
                "₹2 lakh/year",
                "Deemed university, strong alumni, quality education"
            ),
            University(
                23,
                "ICT Mumbai",
                "Mumbai, Maharashtra",
                "NIRF Rank 49",
                "Through JEE Main",
                "₹1 lakh/year",
                "Chemical engineering focus, government college"
            ),
            University(
                24,
                "COEP Pune",
                "Pune, Maharashtra",
                "NIRF Rank 56",
                "Through JEE Main",
                "₹80,000/year",
                "Heritage college, low fees, good placements"
            ),
            University(
                25,
                "NIT Rourkela",
                "Rourkela, Odisha",
                "NIRF Rank 16",
                "Through JEE Main",
                "₹1.5 lakh/year",
                "Metallurgy excellence, beautiful campus, research"
            )
        )
    }
}