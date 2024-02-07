package com.cradlecare.utils.models.home_module

data class Message(
    var message: String,
    var sentBy: String
) {
    companion object {
        const val SENT_BY_ME = "me"
        const val SENT_BY_BOT = "bot"
    }
}
