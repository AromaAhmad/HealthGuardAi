package com.aroma.healthguardai.repository

import android.content.Context
import com.google.mediapipe.tasks.genai.llminference.LlmInference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class OnDeviceLLMRepository(
    private val context: Context
) {

    private var llmInference: LlmInference? = null

    private val modelFileName =
        "Gemma3-1B-IT_multi-prefill-seq_q4_ekv2048.task"

    suspend fun initialize() {

        withContext(Dispatchers.IO) {

            val modelFile = File(
                context.filesDir,
                modelFileName
            )

            // Copy model from assets to internal storage
            if (!modelFile.exists()) {

                context.assets.open(modelFileName).use { input ->

                    modelFile.outputStream().use { output ->

                        input.copyTo(output)
                    }
                }
            }

            // Create LLM using the real filesystem path
            val options =
                LlmInference.LlmInferenceOptions.builder()
                    .setModelPath(
                        modelFile.absolutePath
                    )
                    .setMaxTokens(512)
                    .build()

            llmInference =
                LlmInference.createFromOptions(
                    context,
                    options
                )
        }
    }

    suspend fun generateResponse(
        prompt: String
    ): String {

        return withContext(Dispatchers.Default) {

            val inference = llmInference
                ?: throw IllegalStateException(
                    "LLM is not initialized. Call initialize() first."
                )

            inference.generateResponse(prompt)
        }
    }
}