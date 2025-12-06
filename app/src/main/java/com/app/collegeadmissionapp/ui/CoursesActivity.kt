package com.app.collegeadmissionapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.collegeadmissionapp.adapter.CoursesAdapter
import com.app.collegeadmissionapp.R
import com.app.collegeadmissionapp.data.Course
import com.google.android.material.appbar.MaterialToolbar

class CoursesActivity : AppCompatActivity() {

    private lateinit var coursesRecyclerView: RecyclerView
    private lateinit var coursesAdapter: CoursesAdapter
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView(R.layout.activity_courses)

        // Handle system bars insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.coursesRoot)) { view, insets ->
            insets
        }

        initViews()
        setupToolbar()
        setupRecyclerView()
    }

    private fun initViews() {
        coursesRecyclerView = findViewById(R.id.coursesRecyclerView)
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
        val coursesList = getCoursesList()
        coursesAdapter = CoursesAdapter(coursesList)
        coursesRecyclerView.layoutManager = LinearLayoutManager(this)
        coursesRecyclerView.adapter = coursesAdapter
    }

    private fun getCoursesList(): List<Course> {
        return listOf(
            Course(
                "Computer Science Engineering (CSE)",
                "Study of computers, algorithms, software development, AI, and data structures",
                "High demand, excellent salary packages, diverse career opportunities",
                "IITs, NITs, BITS Pilani, IIIT Hyderabad"
            ),
            Course(
                "Electronics & Communication (ECE)",
                "Focus on electronic devices, circuits, communication systems, and signal processing",
                "Growing field with opportunities in telecom, embedded systems, IoT",
                "IIT Bombay, IIT Delhi, NIT Trichy, BITS Pilani"
            ),
            Course(
                "Mechanical Engineering",
                "Study of mechanics, thermodynamics, manufacturing, and material science",
                "Traditional core branch with diverse industry applications",
                "IIT Madras, IIT Kharagpur, NIT Surathkal, BITS Pilani"
            ),
            Course(
                "Electrical Engineering",
                "Power systems, electrical machines, control systems, and renewable energy",
                "Essential for power sector, automation, and smart grid technologies",
                "IIT Bombay, IIT Kanpur, NIT Trichy, BITS Pilani"
            ),
            Course(
                "Civil Engineering",
                "Construction, structural design, transportation, and urban planning",
                "Infrastructure development, sustainable construction projects",
                "IIT Madras, IIT Delhi, NIT Trichy, BITS Pilani"
            ),
            Course(
                "Chemical Engineering",
                "Chemical processes, petroleum, pharmaceuticals, and materials",
                "Process industries, research, environmental engineering",
                "IIT Bombay, IIT Madras, ICT Mumbai, NIT Trichy"
            ),
            Course(
                "Aerospace Engineering",
                "Aircraft, spacecraft design, aerodynamics, and propulsion systems",
                "ISRO, DRDO, aerospace companies, research organizations",
                "IIT Bombay, IIT Madras, IIT Kharagpur, IIST Trivandrum"
            ),
            Course(
                "Information Technology (IT)",
                "Software development, networking, databases, and web technologies",
                "High placement rates, startup opportunities, tech companies",
                "IIIT Hyderabad, NIT Trichy, IIIT Allahabad, DTU Delhi"
            ),
            Course(
                "Biotechnology",
                "Biology, genetics, medical research, and pharmaceutical applications",
                "Healthcare, research, pharmaceutical companies",
                "IIT Kharagpur, IIT Roorkee, NIT Warangal, BITS Pilani"
            ),
            Course(
                "Metallurgical Engineering",
                "Extraction, processing, and properties of metals and materials",
                "Steel plants, automotive, research institutions",
                "IIT Bombay, IIT Kharagpur, NIT Trichy, NIT Rourkela"
            ),
            Course(
                "Production Engineering",
                "Manufacturing processes, automation, quality control",
                "Manufacturing industries, process optimization",
                "NIT Trichy, BITS Pilani, NIT Warangal, PSG Tech"
            ),
            Course(
                "Instrumentation Engineering",
                "Measurement systems, sensors, control systems, automation",
                "Process industries, automation companies, research",
                "NIT Trichy, NIT Warangal, BITS Pilani, ICT Mumbai"
            ),
            Course(
                "Petroleum Engineering",
                "Oil and gas exploration, drilling, reservoir engineering",
                "Oil companies, consultancy, energy sector",
                "IIT Dhanbad, UPES Dehradun, PDPU Gandhinagar"
            ),
            Course(
                "Mining Engineering",
                "Mineral extraction, mine planning, safety, and geology",
                "Mining companies, consultancy, government organizations",
                "IIT Dhanbad, IIT Kharagpur, NIT Rourkela"
            ),
            Course(
                "Artificial Intelligence & ML",
                "Machine learning, deep learning, neural networks, AI systems",
                "Tech giants, startups, research, data science roles",
                "IIT Hyderabad, IIIT Hyderabad, IIT Delhi, BITS Pilani"
            ),
            Course(
                "Data Science & Engineering",
                "Big data, analytics, statistical modeling, visualization",
                "Data analyst, business intelligence, analytics companies",
                "IIT Madras, IIIT Bangalore, BITS Pilani"
            ),
            Course(
                "Cyber Security",
                "Network security, cryptography, ethical hacking, digital forensics",
                "Cybersecurity firms, government agencies, consulting",
                "IIT Kanpur, IIIT Allahabad, NIT Trichy"
            ),
            Course(
                "Robotics Engineering",
                "Robot design, automation, AI integration, control systems",
                "Robotics companies, manufacturing, research labs",
                "IIT Kanpur, IIT Bombay, BITS Pilani"
            )
        )
    }
}