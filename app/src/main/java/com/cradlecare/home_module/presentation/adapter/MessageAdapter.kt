package com.cradlecare.home_module.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.cradlecare.databinding.ItemChatBotMessagingBinding
import com.cradlecare.utils.models.home_module.Message

class MessageAdapter(private val messageList: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemChatBotMessagingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val message = messageList[position]
        with(holder.binding){
            if (message.sentBy == Message.SENT_BY_ME) {
                leftChatView.isVisible =false
                rightChatView.isVisible = true
                userImage.isVisible = true
                botImage.isVisible = false
                rightChatTextView.text = message.message
            } else {
                rightChatView.isVisible =false
                leftChatView.isVisible = true
                userImage.isVisible = false
                botImage.isVisible = true
                leftChatTextView.text = message.message
            }
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemChatBotMessagingBinding.bind(itemView)
    }
}
