package com.app.collegeadmissionapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.TaskStackBuilder

class ShortcutRouterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shortcutId = intent.getStringExtra("shortcut_id")

        if (shortcutId == "chatbot") {

            TaskStackBuilder.create(this)
                .addNextIntent(Intent(this, DashboardActivity::class.java))
                .addNextIntent(Intent(this, ChatbotActivity::class.java))
                .startActivities()
        }

        if (shortcutId == "news") {

            TaskStackBuilder.create(this)
                .addNextIntent(Intent(this, DashboardActivity::class.java))
                .addNextIntent(Intent(this, NewsActivity::class.java))
                .startActivities()
        }



        finish() // close the router activity immediately
    }
}
