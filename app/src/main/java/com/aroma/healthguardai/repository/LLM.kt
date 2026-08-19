package com.aroma.healthguardai.repository

interface LLM {

    suspend fun askWithContext(
        question: String,
        context: String
    ): String
}