package com.app.collegeadmissionapp.repository

import com.app.collegeadmissionapp.data.ChatMessage
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit

class ChatRepository(userId: String) {

    private val db = FirebaseFirestore.getInstance()
    private val chatCollection = db.collection("chats")
        .document(userId) // Use the user's ID to keep chats separate
        .collection("messages")

    private val knowledgeBaseCollection = db.collection("knowledgeBase") // NEW: KB Collection

    // 1. Send a message to Firestore (Used for both User and FINAL Bot messages)
    suspend fun sendMessage(message: ChatMessage) {
        // Ensure timestamp is added if missing (though ViewModel handles this)
        chatCollection.add(message).await()
    }

    // 2. Stream chat messages using Kotlin Flow
    fun getChatMessages(): Flow<List<ChatMessage>> = callbackFlow {
        val subscription = chatCollection
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error) // Close flow on error
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val messages = snapshot.toObjects(ChatMessage::class.java)
                    trySend(messages) // Send the updated list of messages
                }
            }

        awaitClose { subscription.remove() }
    }
    private fun extractKeywords(query: String): List<String> {
        val removeWords = listOf("what", "is", "the", "a", "an", "in", "of", "to", "how")
        return query
            .lowercase()
            .replace("?", "")
            .split(" ")
            .filter { it.isNotBlank() && it !in removeWords }
    }
    // 3. NEW: Search the Knowledge Base for an answer
    suspend fun getBotResponseFromKB(query: String): String {
        val keywords = extractKeywords(query)

        if (keywords.isEmpty()) return "Please try asking your question differently."

        try {
            val snapshot = knowledgeBaseCollection
                .whereArrayContainsAny("keywords", keywords)
                .limit(1)
                .get()
                .await()

            return snapshot.documents.firstOrNull()?.getString("response")
                ?: "I couldn't find an answer related to '$query'. Please try rephrasing."

        } catch (e: Exception) {
            return "An error occurred while fetching the answer."
        }
    }

}