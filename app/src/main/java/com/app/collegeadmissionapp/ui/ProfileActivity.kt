package com.app.collegeadmissionapp.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import com.app.collegeadmissionapp.R
import com.app.collegeadmissionapp.databinding.ActivityProfileBinding
import com.app.collegeadmissionapp.databinding.SettingsBottomSheetBinding
import com.app.collegeadmissionapp.viewmodel.AuthViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseUser

class ProfileActivity : AppCompatActivity() {

    // Initialize AuthViewModel
    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var pref: SharedPreferences
    private lateinit var binding: ActivityProfileBinding
    private var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.profileRoot) { view, insets ->
            insets
        }

        pref = getSharedPreferences("AppPrefs", MODE_PRIVATE)

        // **NEW: Check auth status and load data**
        setupObservers()
        currentUser = authViewModel.getCurrentUser()
        if (currentUser == null) {
            // Should not happen if MainActivity check is correct, but safe guard navigation
            performLogout()
        } else {
            // Load initial data (email/name from Firebase, phone from Firestore)
            loadUserData(currentUser!!)
        }

        setupClickListeners()
        setupBottomNav()
    }

    private fun setupObservers() {
        // Observer for Firestore User Data
        authViewModel.userData.observe(this) { userData ->
            // Update UI with data fetched from Firestore (like phone)
            if (userData.isNotEmpty()) {
                val phone = userData["phone"] as? String ?: "XXXXXXXXXX"
                binding.phoneText.text = "+91 $phone"
            }
        }

        // Observer for Logout State
        authViewModel.authState.observe(this) { state ->
            if (state == com.app.collegeadmissionapp.viewmodel.AuthState.LoggedOut) {
                // This handles the navigation after Firebase logs out
                val sharedPref = getSharedPreferences("AppPrefs", MODE_PRIVATE)
                sharedPref.edit().putBoolean("isLoggedIn", false).apply()

                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
    }

    // **UPDATED: Load data from Firebase and Firestore**
    private fun loadUserData(user: FirebaseUser) {
        // Load data available directly from the Firebase User object
        binding.nameText.text = user.displayName ?: "User"
        binding.emailText.text = user.email ?: "user@example.com"

        // Load additional data (like phone number) from Firestore
        authViewModel.loadUserData(user.uid)

        // Use SharedPreferences for phone number as a fallback until Firestore data is fetched,
        // though now Firestore is the primary source via the observer.
        // For immediate display, you might still want to load the last known phone:
        val sharedPref = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        binding.phoneText.text = "+91 ${sharedPref.getString("phone", "XXXXXXXXXX")}"
    }

    private fun setupClickListeners() {
        binding.editProfileCard.setOnClickListener {
            // Navigate to edit profile (You'll need to create this activity)
            Toast.makeText(this, "Edit Profile functionality goes here", Toast.LENGTH_SHORT).show()
        }

        binding.settingsCard.setOnClickListener {
            showSettingsBottomSheet()
        }

        binding.aboutCard.setOnClickListener {
            // Show about dialog
            showAboutDialog()
        }

        binding.logoutButton.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun setupBottomNav() {
        binding.bottomNavigation.selectedItemId = R.id.nav_profile
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    finish()
                    true
                }
                R.id.nav_news -> {
                    startActivity(Intent(this, NewsActivity::class.java))
                    // finish() // Do not finish here if you want to be able to navigate back
                    true
                }
                R.id.nav_chatbot -> {
                    startActivity(Intent(this, ChatbotActivity::class.java))
                    // finish() // Do not finish here if you want to be able to navigate back
                    true
                }
                R.id.nav_profile -> true
                else -> false
            }
        }
    }

    private fun showAboutDialog() {
        AlertDialog.Builder(this)
            .setTitle("About")
            .setMessage("College Admission App v1.0\n\nYour gateway to engineering admissions in India.")
            .setPositiveButton("OK", null)
            .show()
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { _, _ ->
                performLogout()
            }
            .setNegativeButton("No", null)
            .show()
    }

    // **UPDATED: Delegate logout to ViewModel/Firebase**
    private fun performLogout() {
        // 1. Call the ViewModel to sign out from Firebase
        authViewModel.logout()

        // Navigation will be handled by the authState observer
        // The shared preferences flag is cleared in the observer.
    }

    private fun showSettingsBottomSheet() {
        // ... (This function remains unchanged)
        val bottomSheetBinding = SettingsBottomSheetBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

        bottomSheetDialog.setOnShowListener { dialog ->
            val sheet = (dialog as BottomSheetDialog)
                .findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

            // Make sure R.drawable.bottom_sheet_background exists in your resources
            sheet?.setBackgroundResource(R.drawable.bottom_sheet_background)
        }

        val switchDarkMode = bottomSheetBinding.switchDarkMode
        val switchNotifications = bottomSheetBinding.switchNotifications
        val languageRadioGroup = bottomSheetBinding.languageRadioGroup
        val radioGujarati = bottomSheetBinding.radioGujarati
        val radioHindi = bottomSheetBinding.radioHindi
        val radioEnglish = bottomSheetBinding.radioEnglish

        switchDarkMode.isChecked = pref.getBoolean("darkMode", false)
        switchNotifications.isChecked = pref.getBoolean("notification", true)

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            pref.edit().putBoolean("darkMode", isChecked).apply()
            // Optionally apply dark mode changes here
        }

        switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            pref.edit().putBoolean("notification", isChecked).apply()
        }

        when (pref.getString("language", "English")) {
            "Gujarati" -> radioGujarati.isChecked = true
            "Hindi" -> radioHindi.isChecked = true
            else -> radioEnglish.isChecked = true
        }

        languageRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedLanguage = when (checkedId) {
                bottomSheetBinding.radioGujarati.id -> "Gujarati"
                bottomSheetBinding.radioHindi.id -> "Hindi"
                bottomSheetBinding.radioEnglish.id -> "English"
                else -> "English"
            }
            pref.edit().putString("language", selectedLanguage).apply()
        }

        bottomSheetDialog.show()
    }
}