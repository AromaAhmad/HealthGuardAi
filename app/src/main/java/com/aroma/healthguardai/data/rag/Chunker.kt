package com.aroma.healthguardai.data.rag

class Chunker {

    fun chunkText(
        text: String,
        chunkSize: Int = 500
    ): List<Chunk> {

        if (text.isBlank()) {
            return emptyList()
        }

        val chunks = mutableListOf<Chunk>()

        var start = 0
        var id = 0

        while (start < text.length) {

            val end = minOf(
                start + chunkSize,
                text.length
            )

            val chunkText = text
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

            start = end
        }

        return chunks
    }
}