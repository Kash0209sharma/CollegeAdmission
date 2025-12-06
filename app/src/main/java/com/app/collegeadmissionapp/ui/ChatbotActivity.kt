package com.app.collegeadmissionapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.collegeadmissionapp.adapter.ChatAdapter
import com.app.collegeadmissionapp.data.ChatMessage
import com.app.collegeadmissionapp.databinding.ActivityChatbotBinding
import com.app.collegeadmissionapp.viewmodel.ChatViewModel
import kotlinx.coroutines.launch
import kotlin.math.max

class ChatbotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatbotBinding
    private lateinit var chatAdapter: ChatAdapter

    // Initialize ViewModel
    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = ActivityChatbotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.chatbotRoot) { view, insets ->
            val ime = insets.getInsets(WindowInsetsCompat.Type.ime())
            val navBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.setPadding(0, 0, 0, max(ime.bottom, navBar.bottom))
            insets
        }

        setupRecyclerView()
        setupObservers() // NEW: Set up Flow observation
        setupClickListeners()
        // addWelcomeMessage() is no longer needed; let the Firestore fetch history
    }

    private fun setupRecyclerView() {
        // Initialize adapter with an empty list; it will be updated by the observer
        chatAdapter = ChatAdapter(emptyList())
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.chatRecyclerView.adapter = chatAdapter
    }

    private fun setupObservers() {
        // Collect messages from the ViewModel's StateFlow
        lifecycleScope.launch {
            viewModel.messages.collect { messages ->
                // Update the adapter with the new list of messages from Firestore
                chatAdapter.updateData(messages)

                // Scroll to the bottom if there are messages
                if (messages.isNotEmpty()) {
                    binding.chatRecyclerView.scrollToPosition(messages.size - 1)
                }
            }
        }
    }

    private fun setupClickListeners() {
        binding.sendButton.setOnClickListener {
            sendMessage()
        }
    }

    // UPDATED: This now delegates the message to the ViewModel
    private fun sendMessage() {
        val message = binding.messageInput.text.toString().trim()
        if (message.isEmpty()) return

        // 1. Clear input immediately
        binding.messageInput.text.clear()

        // 2. Delegate sending and response simulation to the ViewModel
        viewModel.sendUserMessage(message)
    }

    // The getBotResponse and addWelcomeMessage functions are moved to the ViewModel/Repository
    // so they are removed from the Activity.
}