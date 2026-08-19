# 🏥 HealthGuardAI

<p align="center">

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![Gemma](https://img.shields.io/badge/Gemma%203-8E75B2?style=for-the-badge&logo=google&logoColor=white)
![RAG](https://img.shields.io/badge/RAG-Retrieval%20Augmented%20Generation-orange?style=for-the-badge)
![MediaPipe](https://img.shields.io/badge/MediaPipe-FF6F00?style=for-the-badge&logo=google&logoColor=white)
![Room](https://img.shields.io/badge/Room-4285F4?style=for-the-badge&logo=sqlite&logoColor=white)

</p>

> **Privacy-focused AI health assistant for Android combining Retrieval-Augmented Generation (RAG), document understanding, semantic retrieval, and on-device LLM inference.**

HealthGuardAI is an experimental Android application designed to explore how **RAG and on-device Large Language Models (LLMs)** can be combined to build privacy-conscious AI applications.

Users can upload a health-related document, ask questions about its contents, retrieve relevant information from the document, and generate AI-powered responses using either a **cloud API** or an **on-device Gemma 3 model**.

---

# ✨ Features

### 📄 Document-Based AI

- Select and upload PDF documents
- Extract text from PDF files
- Process documents locally
- Split documents into smaller chunks
- Use document content as context for AI responses

### 🧠 RAG Pipeline

HealthGuardAI implements a Retrieval-Augmented Generation pipeline:

- Text extraction
- Document chunking
- Text embeddings
- Semantic retrieval
- Relevant context selection
- Context-aware LLM generation

This allows the application to retrieve information from the uploaded document before generating an answer.

### 🤖 On-Device AI

HealthGuardAI experiments with running **Gemma 3 1B** directly on Android.

- Local LLM inference
- Quantized Gemma model
- No API request required for on-device inference
- Designed for privacy-focused AI experimentation
- Uses MediaPipe LLM Inference

### 🔀 On-Device / API Choice

The application supports experimenting with two AI approaches:

```text
                    User Question
                          │
                          ▼
                  ┌───────────────┐
                  │  HealthGuardAI│
                  └───────┬───────┘
                          │
                ┌─────────┴─────────┐
                ▼                   ▼
        ☁️ Gemini API          📱 On-Device
                                Gemma 3 1B
