package com.aroma.healthguardai.repository

import android.content.Context
import android.util.Log
import com.google.mediapipe.tasks.genai.llminference.LlmInference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.system.measureTimeMillis

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

            // Measure model copy time (first run only)
            if (!modelFile.exists()) {

                val copyMs = measureTimeMillis {
                    context.assets.open(modelFileName).use { input ->
                        modelFile.outputStream().use { output ->
                            input.copyTo(output)
                        }
                    }
                }

                Log.d("BENCH", "Model asset copy: $copyMs ms")

            } else {
                Log.d(
                    "BENCH",
                    "Model file already exists; copy skipped"
                )
            }

            // Configure the on-device Gemma model
            val options =
                LlmInference.LlmInferenceOptions.builder()
                    .setModelPath(modelFile.absolutePath)
                    .setMaxTokens(512)
                    .build()

            // Measure model initialization time separately
            val loadMs = measureTimeMillis {
                llmInference =
                    LlmInference.createFromOptions(
                        context,
                        options
                    )
            }

            Log.d(
                "BENCH",
                "Gemma initialization: $loadMs ms"
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