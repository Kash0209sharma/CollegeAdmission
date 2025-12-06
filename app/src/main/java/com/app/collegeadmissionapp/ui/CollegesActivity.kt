package com.app.collegeadmissionapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.collegeadmissionapp.adapter.CollegesAdapter
import com.app.collegeadmissionapp.R
import com.app.collegeadmissionapp.data.College
import com.google.android.material.appbar.MaterialToolbar

class CollegesActivity : AppCompatActivity() {

    private lateinit var collegesRecyclerView: RecyclerView
    private lateinit var collegesAdapter: CollegesAdapter
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView(R.layout.activity_colleges)

        // Handle system bars insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.collegesRoot)) { view, insets ->
            insets
        }

        initViews()
        setupToolbar()
        setupRecyclerView()
    }

    private fun initViews() {
        collegesRecyclerView = findViewById(R.id.collegesRecyclerView)
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
        val collegesList = getCollegesList()
        collegesAdapter = CollegesAdapter(collegesList)
        collegesRecyclerView.layoutManager = LinearLayoutManager(this)
        collegesRecyclerView.adapter = collegesAdapter
    }

    private fun getCollegesList(): List<College> {
        return listOf(
            College(1, "IIT Bombay", "Mumbai, Maharashtra", "Government", "95%+", "₹15-30 LPA"),
            College(2, "IIT Delhi", "New Delhi", "Government", "95%+", "₹14-28 LPA"),
            College(3, "IIT Madras", "Chennai, Tamil Nadu", "Government", "96%+", "₹16-32 LPA"),
            College(4, "IIT Kanpur", "Kanpur, Uttar Pradesh", "Government", "94%+", "₹14-26 LPA"),
            College(
                5,
                "IIT Kharagpur",
                "Kharagpur, West Bengal",
                "Government",
                "95%+",
                "₹15-28 LPA"
            ),
            College(6, "IIT Roorkee", "Roorkee, Uttarakhand", "Government", "93%+", "₹13-25 LPA"),
            College(7, "IIT Guwahati", "Guwahati, Assam", "Government", "92%+", "₹12-24 LPA"),
            College(8, "IIT Hyderabad", "Hyderabad, Telangana", "Government", "94%+", "₹14-27 LPA"),
            College(9, "BITS Pilani", "Pilani, Rajasthan", "Private", "90%+", "₹12-25 LPA"),
            College(
                10,
                "NIT Trichy",
                "Tiruchirappalli, Tamil Nadu",
                "Government",
                "90%+",
                "₹10-20 LPA"
            ),
            College(11, "NIT Surathkal", "Mangalore, Karnataka", "Government", "88%+", "₹9-18 LPA"),
            College(12, "NIT Warangal", "Warangal, Telangana", "Government", "87%+", "₹8-17 LPA"),
            College(
                13,
                "IIIT Hyderabad",
                "Hyderabad, Telangana",
                "Government",
                "92%+",
                "₹15-30 LPA"
            ),
            College(14, "DTU Delhi", "Delhi", "Government", "85%+", "₹8-16 LPA"),
            College(15, "VIT Vellore", "Vellore, Tamil Nadu", "Private", "85%+", "₹6-15 LPA"),
            College(
                16,
                "Anna University",
                "Chennai, Tamil Nadu",
                "Government",
                "80%+",
                "₹5-12 LPA"
            ),
            College(
                17,
                "Jadavpur University",
                "Kolkata, West Bengal",
                "Government",
                "82%+",
                "₹6-14 LPA"
            ),
            College(18, "PSG Tech", "Coimbatore, Tamil Nadu", "Private", "83%+", "₹6-13 LPA"),
            College(19, "Thapar University", "Patiala, Punjab", "Private", "84%+", "₹7-14 LPA"),
            College(20, "SRM University", "Chennai, Tamil Nadu", "Private", "80%+", "₹5-12 LPA"),
            College(21, "Manipal Institute", "Manipal, Karnataka", "Private", "81%+", "₹6-13 LPA"),
            College(22, "BIT Mesra", "Ranchi, Jharkhand", "Private", "82%+", "₹6-14 LPA"),
            College(23, "ICT Mumbai", "Mumbai, Maharashtra", "Government", "86%+", "₹8-15 LPA"),
            College(24, "COEP Pune", "Pune, Maharashtra", "Government", "84%+", "₹7-14 LPA"),
            College(25, "NIT Rourkela", "Rourkela, Odisha", "Government", "85%+", "₹8-16 LPA"),
            College(26, "IIT BHU", "Varanasi, Uttar Pradesh", "Government", "93%+", "₹13-25 LPA"),
            College(27, "IIT Indore", "Indore, Madhya Pradesh", "Government", "91%+", "₹12-23 LPA"),
            College(28, "NIT Karnataka", "Surathkal, Karnataka", "Government", "87%+", "₹9-18 LPA"),
            College(
                29,
                "IIIT Bangalore",
                "Bangalore, Karnataka",
                "Government",
                "90%+",
                "₹14-26 LPA"
            ),
            College(30, "BITS Goa", "Goa", "Private", "88%+", "₹11-22 LPA"),
            College(
                31,
                "IIIT Allahabad",
                "Allahabad, Uttar Pradesh",
                "Government",
                "88%+",
                "₹10-20 LPA"
            ),
            College(32, "NIT Calicut", "Calicut, Kerala", "Government", "86%+", "₹8-17 LPA"),
            College(33, "NIT Nagpur", "Nagpur, Maharashtra", "Government", "84%+", "₹7-15 LPA"),
            College(34, "VNIT Nagpur", "Nagpur, Maharashtra", "Government", "83%+", "₹7-14 LPA"),
            College(
                35,
                "MANIT Bhopal",
                "Bhopal, Madhya Pradesh",
                "Government",
                "82%+",
                "₹6-13 LPA"
            ),
            College(36, "SVNIT Surat", "Surat, Gujarat", "Government", "84%+", "₹7-15 LPA"),
            College(37, "NIT Durgapur", "Durgapur, West Bengal", "Government", "81%+", "₹6-13 LPA"),
            College(
                38,
                "NIT Jamshedpur",
                "Jamshedpur, Jharkhand",
                "Government",
                "80%+",
                "₹6-12 LPA"
            ),
            College(39, "NSUT Delhi", "Delhi", "Government", "85%+", "₹8-16 LPA"),
            College(
                40,
                "IIITDM Jabalpur",
                "Jabalpur, Madhya Pradesh",
                "Government",
                "82%+",
                "₹7-14 LPA"
            ),
            College(41, "Amity University", "Noida, Uttar Pradesh", "Private", "75%+", "₹4-10 LPA"),
            College(42, "LPU", "Jalandhar, Punjab", "Private", "75%+", "₹4-10 LPA"),
            College(43, "Jamia Millia Islamia", "Delhi", "Government", "80%+", "₹6-12 LPA"),
            College(44, "AMU Aligarh", "Aligarh, Uttar Pradesh", "Government", "79%+", "₹5-11 LPA"),
            College(45, "PEC Chandigarh", "Chandigarh", "Government", "83%+", "₹7-14 LPA"),
            College(46, "LNMIIT Jaipur", "Jaipur, Rajasthan", "Private", "82%+", "₹7-13 LPA"),
            College(47, "SSN College", "Chennai, Tamil Nadu", "Private", "81%+", "₹6-13 LPA"),
            College(48, "MSRIT Bangalore", "Bangalore, Karnataka", "Private", "80%+", "₹6-12 LPA"),
            College(49, "RV College", "Bangalore, Karnataka", "Private", "79%+", "₹5-12 LPA"),
            College(50, "BMS College", "Bangalore, Karnataka", "Private", "78%+", "₹5-11 LPA")
        )
    }
}