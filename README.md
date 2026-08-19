<div align="center">

# 🩺 HealthGuardAI

### Privacy-Focused AI Health Assistant for Android

**RAG • On-Device AI • Gemma 3 • Kotlin • Jetpack Compose**

<br/>

[![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Gemma](https://img.shields.io/badge/Gemma%203-8E75B2?style=for-the-badge)](https://ai.google.dev/gemma)
[![MediaPipe](https://img.shields.io/badge/MediaPipe-FF6F00?style=for-the-badge)](https://ai.google.dev/edge/mediapipe/solutions/guide)
[![Room](https://img.shields.io/badge/Room-4285F4?style=for-the-badge)](https://developer.android.com/training/data-storage/room)
[![MVVM](https://img.shields.io/badge/Architecture-MVVM-orange?style=for-the-badge)](#architecture)

<br/>

[**📱 Features**](#-features) •
[**🧠 RAG Pipeline**](#-rag-pipeline) •
[**🤖 On-Device AI**](#-on-device-ai) •
[**🏗️ Architecture**](#️-architecture) •
[**⚙️ Setup**](#️-getting-started) •
[**📸 Screenshots**](#-screenshots)

</div>

---

## 📌 About

**HealthGuardAI** is an experimental Android AI assistant designed to explore how **Retrieval-Augmented Generation (RAG)** and **on-device Large Language Models** can be combined to build more private and context-aware AI applications.

Users can provide a health-related document and ask questions about its contents.

Instead of sending the entire document directly to an LLM, HealthGuardAI:

```text
📄 Document
    ↓
📝 Text Extraction
    ↓
✂️ Chunking
    ↓
🧠 Embeddings
    ↓
🔎 Semantic Retrieval
    ↓
📚 Relevant Context
    ↓
🤖 LLM
    ↓
💬 Grounded Answer
