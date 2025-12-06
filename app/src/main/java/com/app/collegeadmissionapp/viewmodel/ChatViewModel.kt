package com.app.collegeadmissionapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.collegeadmissionapp.data.ChatMessage
import com.app.collegeadmissionapp.repository.ChatRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

class ChatViewModel : ViewModel() {

    private val userId: String = FirebaseAuth.getInstance().currentUser?.uid ?: "guest_user"
    private val repository = ChatRepository(userId)

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    private val TYPING_INDICATOR_ID = "BOT_TYPING_PLACEHOLDER"

    init {
        viewModelScope.launch {
            repository.getChatMessages().collectLatest { firestoreMessages ->
                val isTypingVisible = _messages.value.any { it.messageId == TYPING_INDICATOR_ID }

                var updatedList = firestoreMessages

                if (isTypingVisible) {
                    updatedList = firestoreMessages + createTypingIndicator()
                }

                _messages.value = updatedList
            }
        }
    }

    fun sendUserMessage(text: String) {
        val userMessage = ChatMessage(
            message = text,
            isUser = true,  // ✅ User message → RIGHT side
            userId = userId,
            timestamp = Date()
        )

        viewModelScope.launch {
            // 1. Save user message to Firestore
            repository.sendMessage(userMessage)

            // 2. Show typing indicator
            addTypingIndicator()

            // 3. Get bot response
            delay(500)
            val botResponse = repository.getBotResponseFromKB(text)

            // 4. Remove typing and send bot message
            removeTypingIndicatorAndSendFinal(botResponse)
        }
    }

    private fun addTypingIndicator() {
        _messages.update { currentList ->
            if (currentList.none { it.messageId == TYPING_INDICATOR_ID }) {
                currentList + createTypingIndicator()
            } else {
                currentList
            }
        }
    }

    private fun removeTypingIndicatorAndSendFinal(response: String) {
        viewModelScope.launch {
            // Remove typing indicator
            _messages.update { currentList ->
                currentList.filter { it.messageId != TYPING_INDICATOR_ID }
            }

            // Create bot message - LEFT side
            val botMessage = ChatMessage(
                message = response,
                isUser = false,  // ✅ Bot message → LEFT side
                userId = "bot",
                timestamp = Date()
            )

            repository.sendMessage(botMessage)
        }
    }

    private fun createTypingIndicator(): ChatMessage {
        return ChatMessage(
            messageId = TYPING_INDICATOR_ID,
            message = "Typing...",
            isUser = false,  // ✅ Typing indicator → LEFT side
            userId = "bot",
            timestamp = Date()
        )
    }
}