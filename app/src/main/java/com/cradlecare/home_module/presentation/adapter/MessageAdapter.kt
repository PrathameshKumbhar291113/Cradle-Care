package com.cradlecare.home_module.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        if (message.sentBy == Message.SENT_BY_ME) {
            holder.binding.leftChatView.visibility = View.GONE
            holder.binding.rightChatView.visibility = View.VISIBLE
            holder.binding.rightChatTextView.text = message.message
        } else {
            holder.binding.rightChatView.visibility = View.GONE
            holder.binding.leftChatView.visibility = View.VISIBLE
            holder.binding.leftChatTextView.text = message.message
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemChatBotMessagingBinding.bind(itemView)
    }
}
