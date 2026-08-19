# 🏥 HealthGuardAI

<p align="center">
  <strong>Privacy-Focused AI Health Assistant with RAG & On-Device LLM Inference</strong>
</p>

<p align="center">
  An Android application that combines Retrieval-Augmented Generation (RAG),
  semantic search, and on-device Large Language Model inference to generate
  grounded responses from user-provided documents.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" />
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" />
  <img src="https://img.shields.io/badge/Gemma-8E75B2?style=for-the-badge" />
  <img src="https://img.shields.io/badge/RAG-FF6F00?style=for-the-badge" />
  <img src="https://img.shields.io/badge/MediaPipe-0097A7?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Room-6DB33F?style=for-the-badge" />
</p>

---

## 📌 Overview

**HealthGuardAI** is a privacy-focused Android AI application that allows users to upload documents and ask questions about their content.

Instead of sending an entire document directly to an AI model, HealthGuardAI uses a **Retrieval-Augmented Generation (RAG)** pipeline to retrieve relevant information first and then provide that context to the language model.

```text
Document
   ↓
Text Extraction
   ↓
Chunking
   ↓
Embeddings
   ↓
Semantic Retrieval
   ↓
Relevant Context
   ↓
LLM
   ↓
Grounded Answer
