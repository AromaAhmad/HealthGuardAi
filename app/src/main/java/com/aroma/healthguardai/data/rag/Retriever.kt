package com.aroma.healthguardai.data.rag

import android.content.Context
import android.util.Log
import kotlin.math.sqrt

class Retriever(
    private val context: Context
) {

    fun retrieve(
        question: String,
        embeddedChunks: List<EmbeddedChunk>,
        topK: Int = 3
    ): List<Chunk> {

        if (embeddedChunks.isEmpty()) {
            Log.d("RAG_DEBUG", "No embedded chunks available")
            return emptyList()
        }

        val embeddingService = EmbeddingService(context)
        val questionEmbedding =
            embeddingService.createEmbedding(question)

        val scoredChunks = embeddedChunks.map { embeddedChunk ->
            val score = cosineSimilarity(
                questionEmbedding,
                embeddedChunk.embedding
            )

            Pair(embeddedChunk.chunk, score)
        }.sortedByDescending { it.second }

        Log.d(
            "RAG_DEBUG",
            "Question: $question | Total chunks: ${embeddedChunks.size}"
        )

        scoredChunks.take(topK).forEachIndexed { index, result ->
            Log.d(
                "RAG_DEBUG",
                "Rank ${index + 1} | Score: ${result.second} | Chunk ID: ${result.first.id} | Text: ${result.first.text}"
            )
        }

        return scoredChunks
            .take(topK)
            .map { it.first }
    }

    private fun cosineSimilarity(
        vectorA: List<Float>,
        vectorB: List<Float>
    ): Float {

        if (vectorA.isEmpty() || vectorB.isEmpty()) {
            return 0f
        }

        var dotProduct = 0f
        var magnitudeA = 0f
        var magnitudeB = 0f

        val size = minOf(vectorA.size, vectorB.size)

        for (i in 0 until size) {
            dotProduct += vectorA[i] * vectorB[i]
            magnitudeA += vectorA[i] * vectorA[i]
            magnitudeB += vectorB[i] * vectorB[i]
        }

        val denominator = sqrt(magnitudeA) * sqrt(magnitudeB)

        if (denominator == 0f) {
            return 0f
        }

        return dotProduct / denominator
    }
}
