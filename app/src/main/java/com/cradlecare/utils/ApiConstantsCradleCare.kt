package com.cradlecare.utils

import com.cradlecare.BuildConfig

object ApiConstantsCradleCare{
    const val BASE_URL = ""
    const val CHAT_BOT_BASE_URL = "https://api.openai.com"
    const val CHAT_BOT_API_KEY = BuildConfig.CHAT_BOT_API_KEY




    /*
    response from gpt-3.5-turbo-instruct
    {
    "id": "cmpl-8pLHFSeRDgBazG2r65MEBykdvZFqY",
    "object": "text_completion",
    "created": 1707247209,
    "model": "gpt-3.5-turbo-instruct",
    "choices": [
        {
            "text": "\n\nJava:\n```\npublic class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, world!\");\n    }\n}\n```\n\nKotlin:\n```\nfun main() {\n    println(\"Hello, world!\")\n}\n```",
            "index": 0,
            "logprobs": null,
            "finish_reason": "stop"
        }
    ],
    "usage": {
        "prompt_tokens": 6,
        "completion_tokens": 52,
        "total_tokens": 58
    }
}
    */
}