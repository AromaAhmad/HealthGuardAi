package com.aroma.healthguardai.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aroma.healthguardai.repository.LLMRepository
import com.aroma.healthguardai.repository.OnDeviceLLMRepository
import com.aroma.healthguardai.repository.RAGRepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val ragRepository =
        RAGRepositoryProvider.get(
            application.applicationContext
        )

    private val llmRepository =
        LLMRepository()

    private val onDeviceLLMRepository =
        OnDeviceLLMRepository(
            application.applicationContext
        )

    private val _answer =
        MutableStateFlow("")

    val answer: StateFlow<String> =
        _answer.asStateFlow()

    private val _isLoading =
        MutableStateFlow(false)

    val isLoading: StateFlow<Boolean> =
        _isLoading.asStateFlow()

    fun askQuestion(
        question: String,
        model: String
    ) {

        if (question.isBlank()) return

        viewModelScope.launch {

            _isLoading.value = true
            _answer.value = ""

            try
            {if (model == "Gemma") {
                onDeviceLLMRepository.initialize()
            }

                // 1. Retrieve relevant chunks
                val relevantChunks =
                    ragRepository.retrieveRelevantChunks(
                        question = question,
                        topK = 3
                    )

                // 2. Convert chunks into context
                val context =
                    relevantChunks.joinToString(
                        separator = "\n\n"
                    ) { chunk ->
                        chunk.text
                    }

                // 3. Send the same RAG context
                // to the model selected by the user
                _answer.value =
                    if (model == "Gemini") {

                        llmRepository.askWithContext(
                            question = question,
                            context = context
                        )

                    } else {

                        onDeviceLLMRepository.generateResponse(
                            """
                            You are a health document assistant.

                            Answer the user's question using ONLY
                            the information provided in the document context.

                            If the answer is not present in the context,
                            say that the information is not available
                            in the document.

                            Document context:
                            $context

                            User question:
                            $question
                            """.trimIndent()
                        )
                    }

            } catch (e: Exception) {

                _answer.value =
                    "Error: ${e.message}"

            } finally {

                _isLoading.value = false
            }
        }
    }
}