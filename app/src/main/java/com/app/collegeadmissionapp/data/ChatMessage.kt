package com.app.collegeadmissionapp.data

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class ChatMessage(
    var messageId: String? = null,
    var message: String = "",
    var isUser: Boolean = false,
    var userId: String = "",
    @ServerTimestamp
    var timestamp: Date? = null
)
