package com.aroma.healthguardai.data.rag

data class EmbeddedChunk(
    val chunk: Chunk,
    val embedding: List<Float>
)