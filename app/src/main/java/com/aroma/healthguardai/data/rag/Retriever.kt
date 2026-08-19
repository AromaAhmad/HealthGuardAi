package com.aroma.healthguardai.data.rag

import android.content.Context
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
            return emptyList()
        }

        val embeddingService = EmbeddingService(context)

        val questionEmbedding =
            embeddingService.createEmbedding(question)

        return embeddedChunks
            .map { embeddedChunk ->

                val score = cosineSimilarity(
                    questionEmbedding,
                    embeddedChunk.embedding
                )

                Pair(
                    embeddedChunk.chunk,
                    score
                )
            }
            .sortedByDescending { it.second }
            .take(topK)
            .map { it.first }
    }

    private fun cosineSimilarity(
        vectorA: List<Float>,
        vectorB: List<Float>
    ): Float {

        var dotProduct = 0f
        var magnitudeA = 0f
        var magnitudeB = 0f

        val size = minOf(
            vectorA.size,
            vectorB.size
        )

        for (i in 0 until size) {

            dotProduct += vectorA[i] * vectorB[i]

            magnitudeA += vectorA[i] * vectorA[i]

            magnitudeB += vectorB[i] * vectorB[i]
        }

        val denominator =
            sqrt(magnitudeA) * sqrt(magnitudeB)

        if (denominator == 0f) {
            return 0f
        }

        return dotProduct / denominator
    }
}