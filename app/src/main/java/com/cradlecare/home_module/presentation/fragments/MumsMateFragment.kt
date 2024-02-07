package com.cradlecare.home_module.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cradlecare.databinding.FragmentMumsMateBinding
import com.cradlecare.home_module.presentation.adapter.MessageAdapter
import com.cradlecare.home_module.presentation.view_model.MumsMateViewModel
import com.cradlecare.network_module.network_models.home_module.chat_bot_feature.ChatBotRequestBody
import com.cradlecare.utils.NetworkResult
import com.cradlecare.utils.models.home_module.Message
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType


@AndroidEntryPoint
class MumsMateFragment : Fragment() {
    private lateinit var binding: FragmentMumsMateBinding
    private var messageList: MutableList<Message> = mutableListOf()
    private lateinit var messageAdapter: MessageAdapter

    private val mumsMateViewModel: MumsMateViewModel by activityViewModels()

    val JSON: MediaType = "application/json; charset=utf-8".toMediaType()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMumsMateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setUpObservers()
    }

    private fun setUpObservers() {
        mumsMateViewModel.chatBotResponse.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {

                }

                is NetworkResult.Success -> {
                    lifecycleScope.launch {
                        addToChat("Typing...", Message.SENT_BY_BOT)
                        delay(3000)
                        it.data?.body()?.let { chatResponse ->
                            chatResponse.choices?.forEach { choice ->
                                choice?.text?.let { text ->
                                    addResponse(text.trim())
                                }
                            }

                        }
                    }

                }

                is NetworkResult.Error -> {

                }
            }
        }
    }

    private fun setupUi() {

        messageAdapter = MessageAdapter(messageList)
        binding.recyclerView.adapter = messageAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.welcomeText.isVisible = true

        binding.sendBtn.setOnClickListener {
            binding.welcomeText.isVisible = false
            runBlocking{
                var requestBody = ChatBotRequestBody(
                    prompt = binding.messageEditText.text?.trim().toString()
                )
                addToChat(binding.messageEditText.text?.trim().toString(), Message.SENT_BY_ME)
                var jsonObject = JsonObject()
                jsonObject.addProperty("model", requestBody.model)
                jsonObject.addProperty("prompt", requestBody.prompt)
                jsonObject.addProperty("max_tokens", requestBody.maxTokens)
                jsonObject.addProperty("temperature", requestBody.temperature)
                mumsMateViewModel.postChatBotPrompt(jsonObject)
                binding.messageEditText.setText("")
            }
        }
    }


    private fun addToChat(message: String, sentBy: String) {
        lifecycleScope.launch {

            messageList.add(Message(message, sentBy))
            messageAdapter.notifyDataSetChanged()
            binding.recyclerView.smoothScrollToPosition(messageAdapter.itemCount)
        }
    }

    private fun addResponse(response: String) {
        messageList.removeAt(messageList.size - 1)
        addToChat(response, Message.SENT_BY_BOT)
    }
}


/*
// screen
private lateinit var recyclerView: RecyclerView
    private lateinit var welcomeTextView: MaterialTextView
    private lateinit var messageEditText: TextInputEditText
    private lateinit var messageList: MutableList<Message>
    private lateinit var messageAdapter: MessageAdapter

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.openai.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }

    private val service: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageList = mutableListOf()

        recyclerView = findViewById(R.id.recycler_view)
        welcomeTextView = findViewById(R.id.welcome_text)
        messageEditText = findViewById(R.id.message_edit_text)

        messageAdapter = MessageAdapter(messageList)
        recyclerView.adapter = messageAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<View>(R.id.send_btn).setOnClickListener {
            val question = messageEditText.text.toString().trim()
            addToChat(question, Message.SENT_BY_ME)
            messageEditText.setText("")
            callAPI(question)
            welcomeTextView.visibility = View.GONE
        }
    }

    private fun addToChat(message: String, sentBy: String) {
        runOnUiThread {
            messageList.add(Message(message, sentBy))
            messageAdapter.notifyDataSetChanged()
            recyclerView.smoothScrollToPosition(messageAdapter.itemCount)
        }
    }

    private fun addResponse(response: String) {
        messageList.removeAt(messageList.size - 1)
        addToChat(response, Message.SENT_BY_BOT)
    }

    private fun callAPI(question: String) {
        addToChat("Typing...", Message.SENT_BY_BOT)

        val requestBody = mapOf(
            "model" to "text-davinci-003",
            "prompt" to question,
            "max_tokens" to 4000,
            "temperature" to 0
        )

        service.getCompletion(requestBody).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val result = response.body()?.choices?.get(0)?.text
                    addResponse(result?.trim() ?: "Failed to parse response")
                } else {
                    addResponse("Failed to load response due to ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                addResponse("Failed to load response due to ${t.message}")
            }
        })
    }
}
*/


/*
//message data class
data class Message(
    var message: String,
    var sentBy: String
) {
    companion object {
        const val SENT_BY_ME = "me"
        const val SENT_BY_BOT = "bot"
    }
}


*/


/*

//adapter code
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yourappname.databinding.ChatItemBinding

class MessageAdapter(private val messageList: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val message = messageList[position]
        if (message.sentBy == Message.SENT_BY_ME) {
            holder.binding.leftChatView.visibility = View.GONE
            holder.binding.rightChatView.visibility = View.VISIBLE
            holder.binding.rightTextView.text = message.message
        } else {
            holder.binding.rightChatView.visibility = View.GONE
            holder.binding.leftChatView.visibility = View.VISIBLE
            holder.binding.leftTextView.text = message.message
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ChatItemBinding.bind(itemView)
    }
}

*/