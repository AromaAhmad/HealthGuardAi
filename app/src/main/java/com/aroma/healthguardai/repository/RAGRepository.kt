package com.aroma.healthguardai.repository

import android.content.Context
import android.net.Uri
import com.aroma.healthguardai.data.pdf.PdfTextExtractor
import com.aroma.healthguardai.data.rag.Chunk
import com.aroma.healthguardai.data.rag.Chunker
import com.aroma.healthguardai.data.rag.EmbeddedChunk
import com.aroma.healthguardai.data.rag.EmbeddingService
import com.aroma.healthguardai.data.rag.Retriever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RAGRepository(
    private val context: Context
) {

    private val pdfTextExtractor = PdfTextExtractor(context)
    private val chunker = Chunker()
    private val embeddingService = EmbeddingService(context)
    private val retriever = Retriever(context)

    private var embeddedChunks: List<EmbeddedChunk> = emptyList()

    suspend fun processDocument(uri: Uri): Int =
        withContext(Dispatchers.Default) {

            // 1. Extract PDF text
            val extractedText =
                pdfTextExtractor.extractText(uri)

            // 2. Split text into chunks
            val chunks: List<Chunk> =
                chunker.chunkText(extractedText)

            // 3. Create embeddings
            embeddedChunks =
                chunks.map { chunk ->

                    EmbeddedChunk(
                        chunk = chunk,
                        embedding =
                            embeddingService.createEmbedding(
                                chunk.text
                            )
                    )
                }

            embeddedChunks.size
        }

    suspend fun retrieveRelevantChunks(
        question: String,
        topK: Int = 3
    ): List<Chunk> =
        withContext(Dispatchers.Default) {

            retriever.retrieve(
                question = question,
                embeddedChunks = embeddedChunks,
                topK = topK
            )
        }
}