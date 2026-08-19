# 🏥 HealthGuardAI

<p align="center">
  <strong>Privacy-Focused AI Health Assistant with RAG & On-Device LLM Inference</strong>
</p>

<p align="center">
  An Android application that combines Retrieval-Augmented Generation (RAG),
  semantic search, and on-device Large Language Model inference to generate
  grounded responses from user-provided medical documents — no data ever
  has to leave the device.
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

<p align="center">
  <img src="https://img.shields.io/badge/License-MIT-green.svg?style=flat-square" />
  <img src="https://img.shields.io/badge/Platform-Android-blue.svg?style=flat-square" />
  <img src="https://img.shields.io/badge/Status-In%20Development-yellow.svg?style=flat-square" />
</p>

---

## 📌 Overview

**HealthGuardAI** is a privacy-focused Android AI application that lets users upload medical documents (PDFs, reports, prescriptions) and ask natural-language questions about their content.

Instead of sending an entire document to a cloud AI model, HealthGuardAI uses a **Retrieval-Augmented Generation (RAG)** pipeline to retrieve only the relevant chunks first, then feeds that context to an **on-device LLM** — keeping sensitive health data private by default, with an optional cloud fallback for heavier queries.

```text
Document
   ↓
Text Extraction
   ↓
Chunking
   ↓
Embeddings (Universal Sentence Encoder)
   ↓
Semantic Retrieval (Vector Similarity)
   ↓
Relevant Context
   ↓
LLM (On-Device Gemma 3 1B via MediaPipe / Gemini Cloud fallback)
   ↓
Grounded Answer
```

---

## ✨ Features

- 📄 **Document Ingestion** — Upload and parse medical PDFs/reports directly on-device
- 🔍 **Semantic Search** — Retrieves only the most relevant chunks using vector similarity, not keyword matching
- 🤖 **On-Device LLM Inference** — Runs Google's Gemma 3 1B locally via MediaPipe, no internet required
- ☁️ **Cloud Fallback** — Optional Gemini Cloud API for more complex queries, user-selectable
- 🔒 **Privacy by Design** — Documents and embeddings never leave the device unless the user opts into cloud mode
- 💬 **Grounded Chat Interface** — Answers are grounded in the user's own documents, reducing hallucination
- 💾 **Persistent Local Storage** — Chat history and document embeddings cached via Room Database

---

## 🏗️ Architecture

HealthGuardAI follows **MVVM** with a clean separation between UI, business logic, and data:

```text
ChatScreen (Jetpack Compose)
      ↓
ChatViewModel (StateFlow, viewModelScope)
      ↓
Repository Layer
      ↓
RAG Engine (Chunking → Embedding → Retrieval)
      ↓
LLM Layer
   ├── On-Device: Gemma 3 1B (MediaPipe LLM Inference API)
   └── Cloud Fallback: Gemini Cloud API
      ↓
Room Database (Chat History, Document Embeddings)
```

**Why this architecture?**
- **Unidirectional data flow** keeps UI state predictable and testable
- **RAG before generation** ensures answers are grounded in retrieved context rather than the model's parametric memory, reducing hallucination on health-related queries
- **Pluggable LLM layer** lets the same ViewModel/Repository swap between on-device and cloud inference without UI changes

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose |
| Architecture | MVVM |
| On-Device LLM | Gemma 3 1B via MediaPipe LLM Inference API |
| Cloud LLM (fallback) | Gemini Cloud API |
| Embeddings | Universal Sentence Encoder |
| Local Storage | Room Database |
| Networking | Retrofit |
| Concurrency | Kotlin Coroutines, Dispatchers.IO |
| Auth/Backend (optional) | Firebase |

---

## 🚀 Getting Started

### Prerequisites
- Android Studio (latest stable)
- Minimum SDK: 26+ (check `build.gradle` for exact value)
- A physical device or emulator with sufficient RAM for on-device LLM inference (4GB+ recommended)

### Setup

```bash
git clone https://github.com/AromaAhmad/HealthGuardAI.git
cd HealthGuardAI
```

1. Open the project in Android Studio
2. Let Gradle sync complete
3. Download the Gemma 3 1B `.task` model file and place it as instructed in `/app/src/main/assets` (see [MediaPipe LLM Inference docs](https://ai.google.dev/edge/mediapipe/solutions/genai/llm_inference))
4. If using the Gemini Cloud fallback, add your API key to `local.properties`:
   ```properties
   GEMINI_API_KEY=your_key_here
   ```
5. Build and run on a physical device (recommended for realistic inference performance)

---

## 📱 Screenshots
add screen shots also  </p> <p align="center">   <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" />   <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />   <img src="https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" />   <img src="https://img.shields.io/badge/Gemma-8E75B2?style=for-the-badge" />   <img src="https://img.shields.io/badge/RAG-FF6F00?style=for-the-badge" />   <img src="https://img.shields.io/badge/MediaPipe-0097A7?style=for-the-badge" />   <img src="https://img.shields.io/badge/Room-6DB33F?style=for-the-badge" /> </p>

## 🗺️ Roadmap

- [x] On-device Gemma 3 1B inference via MediaPipe
- [x] Document chunking pipeline
- [ ] Full RAG pipeline wired into `ChatViewModel` and `ChatScreen`
- [ ] Multi-document support with source citation in answers
- [ ] Voice input for queries
- [ ] Export chat history as PDF

---

## ⚠️ Disclaimer

HealthGuardAI is an educational/research project and **does not provide medical advice**. Always consult a qualified healthcare professional for medical decisions.

---

## 📄 License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

---

## 👤 Author

**Aroma Ahmad**
Android Developer | BS Computer Science, University of Sargodha

- GitHub: [@AromaAhmad](https://github.com/AromaAhmad)
- LinkedIn: [aroma-a-378245342](https://linkedin.com/in/aroma-a-378245342)

---

<p align="center">Built with ❤️ and a lot of debugging</p>
