
package com.aroma.healthguardai.data.rag

class Chunker {

    fun chunkText(
        text: String,
        chunkSize: Int = 500,
        overlap: Int = 100
    ): List<Chunk> {

        if (text.isBlank()) {
            return emptyList()
        }

        require(chunkSize > 0) {
            "chunkSize must be greater than 0"
        }

        require(overlap >= 0 && overlap < chunkSize) {
            "overlap must be between 0 and chunkSize - 1"
        }

        val chunks = mutableListOf<Chunk>()
        val cleanText = text.trim()

        var start = 0
        var id = 0

        while (start < cleanText.length) {
            val end = minOf(
                start + chunkSize,
                cleanText.length
            )

            val chunkText = cleanText
                .substring(start, end)
                .trim()

            if (chunkText.isNotEmpty()) {
                chunks.add(
                    Chunk(
                        id = id,
                        text = chunkText
                    )
                )
                id++
            }

            if (end == cleanText.length) {
                break
            }

            start = end - overlap
        }

        return chunks
    }
}
