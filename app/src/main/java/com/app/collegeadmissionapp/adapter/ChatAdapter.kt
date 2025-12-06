package com.app.collegeadmissionapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.collegeadmissionapp.data.ChatMessage
import com.app.collegeadmissionapp.databinding.ItemChatBotBinding
import com.app.collegeadmissionapp.databinding.ItemChatTypingBinding
import com.app.collegeadmissionapp.databinding.ItemChatUserBinding
import com.google.android.material.snackbar.Snackbar

class ChatAdapter(private var messages: List<ChatMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_USER = 1
        private const val VIEW_TYPE_BOT = 2
        private const val VIEW_TYPE_TYPING = 3
        private const val TYPING_INDICATOR_ID = "BOT_TYPING_PLACEHOLDER"
    }

    inner class UserMessageViewHolder(private val binding: ItemChatUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatMessage) {
            binding.userMessageText.text = message.message
        }
    }

    inner class BotMessageViewHolder(private val binding: ItemChatBotBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatMessage) {
            binding.chatMessageText.text = message.message
            binding.chatMessageText.setOnLongClickListener {
                // Copy message to clipboard
                val clipboard =
                    binding.root.context.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                val clip = android.content.ClipData.newPlainText("Chatbot Message", message.message)
                clipboard.setPrimaryClip(clip)

                // Show a toast
                Snackbar.make(binding.root, "Copied", Snackbar.LENGTH_SHORT).show()
                true
            }
        }
    }

    inner class TypingIndicatorViewHolder(private val binding: ItemChatTypingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            // Start dot animation
            animateDots()
        }

        private fun animateDots() {
            val dots = listOf(binding.dot1, binding.dot2, binding.dot3)
            dots.forEachIndexed { index, dot ->
                dot.animate()
                    .alpha(1f)
                    .setDuration(500)
                    .setStartDelay((index * 200).toLong())
                    .withEndAction {
                        dot.animate()
                            .alpha(0.5f)
                            .setDuration(500)
                            .start()
                    }
                    .start()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages[position]
        return when {
            message.messageId == TYPING_INDICATOR_ID -> VIEW_TYPE_TYPING
            message.isUser -> VIEW_TYPE_USER  // User messages → right
            else -> VIEW_TYPE_BOT  // Bot messages → left
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_USER -> {
                val binding = ItemChatUserBinding.inflate(inflater, parent, false)
                UserMessageViewHolder(binding)
            }
            VIEW_TYPE_BOT -> {
                val binding = ItemChatBotBinding.inflate(inflater, parent, false)
                BotMessageViewHolder(binding)
            }
            VIEW_TYPE_TYPING -> {
                val binding = ItemChatTypingBinding.inflate(inflater, parent, false)
                TypingIndicatorViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        when (holder) {
            is UserMessageViewHolder -> holder.bind(message)
            is BotMessageViewHolder -> holder.bind(message)
            is TypingIndicatorViewHolder -> holder.bind()
        }
        Log.d("VIEWTYPE", "isUser: ${message.isUser}")

    }

    override fun getItemCount() = messages.size

    fun updateData(newMessages: List<ChatMessage>) {
        this.messages = newMessages
        notifyDataSetChanged()
    }
}