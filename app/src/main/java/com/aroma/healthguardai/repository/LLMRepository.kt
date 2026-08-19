package com.aroma.healthguardai.repository

import com.aroma.healthguardai.BuildConfig
import com.aroma.healthguardai.data.remote.Content
import com.aroma.healthguardai.data.remote.GeminiApiClient
import com.aroma.healthguardai.data.remote.GeminiRequest
import com.aroma.healthguardai.data.remote.Part

class LLMRepository  : LLM  {

    private val api = GeminiApiClient.api

    override suspend fun askWithContext(
        question: String,
        context: String
    ): String {

        val prompt = """
            You are a health document assistant.

            Answer the user's question using ONLY the information
            provided in the document context below.

            If the answer is not present in the context,
            say that the information is not available in the document.

            Document context:
            $context

            User question:
            $question
        """.trimIndent()

        val request = GeminiRequest(
            contents = listOf(
                Content(
                    parts = listOf(
                        Part(text = prompt)
                    )
                )
            )
        )

        val response = api.generateContent(
            apiKey = BuildConfig.GEMINI_API_KEY,
            request = request
        )

        return response
            .candidates
            ?.firstOrNull()
            ?.content
            ?.parts
            ?.firstOrNull()
            ?.text
            ?: "No response received."
    }
}