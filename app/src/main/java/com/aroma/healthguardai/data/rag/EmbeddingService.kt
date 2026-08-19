package com.aroma.healthguardai.data.rag

import android.content.Context
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.text.textembedder.TextEmbedder

class EmbeddingService(
    context: Context
) {

    private val textEmbedder: TextEmbedder

    init {

        val baseOptions = BaseOptions.builder()
            .setModelAssetPath("universal_sentence_encoder.tflite")
            .build()

        val options = TextEmbedder.TextEmbedderOptions.builder()
            .setBaseOptions(baseOptions)
            .build()

        textEmbedder = TextEmbedder.createFromOptions(
            context,
            options
        )
    }

    fun createEmbedding(text: String): List<Float> {

        val result = textEmbedder.embed(text)

        return result.embeddingResult()
            .embeddings()
            .first()
            .floatEmbedding()
            .toList()
    }
}