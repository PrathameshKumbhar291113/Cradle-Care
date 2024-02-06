package com.cradlecare.network_module.models.chat_gpt_module


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatGptPromptResponse(
    @SerializedName("choices")
    var choices: List<Choice?>?,
    @SerializedName("created")
    var created: Int?, // 1707247286
    @SerializedName("id")
    var id: String?, // cmpl-8pLIUHSDstSDBrxFoEThUANOFJvWv
    @SerializedName("model")
    var model: String?, // gpt-3.5-turbo-instruct
    @SerializedName("object")
    var objectX: String?, // text_completion
    @SerializedName("usage")
    var usage: Usage?
) : Parcelable {
    @Parcelize
    data class Choice(
        @SerializedName("finish_reason")
        var finishReason: String?, // stop
        @SerializedName("index")
        var index: Int?, // 0
        @SerializedName("logprobs")
        var logprobs: String?, // null
        @SerializedName("text")
        var text: String? // fun main() {    println("Hello, world!")}// Output: Hello, world!
    ) : Parcelable

    @Parcelize
    data class Usage(
        @SerializedName("completion_tokens")
        var completionTokens: Int?, // 21
        @SerializedName("prompt_tokens")
        var promptTokens: Int?, // 4
        @SerializedName("total_tokens")
        var totalTokens: Int? // 25
    ) : Parcelable
}