# 🏥 HealthGuardAI

<p align="center">
  <strong>Privacy-Focused AI Health Assistant with RAG & On-Device LLM Inference</strong>
</p>

<p align="center">
  An Android application that combines Retrieval-Augmented Generation (RAG),
  semantic search, and on-device Large Language Model inference to generate
  grounded responses from user-provided medical documents — on-device by default.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" />
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" />
  <img src="https://img.shields.io/badge/Gemma-8E75B2?style=for-the-badge" />
  <img src="https://img.shields.io/badge/RAG-FF6F00?style=for-the-badge" />
  <img src="https://img.shields.io/badge/MediaPipe-0097A7?style=for-the-badge" />
</p>

<p align="center">
  <img src="https://img.shields.io/badge/License-MIT-green.svg?style=flat-square" />
  <img src="https://img.shields.io/badge/Platform-Android-blue.svg?style=flat-square" />
  <img src="https://img.shields.io/badge/Status-In%20Development-yellow.svg?style=flat-square" />
</p>

---

## 📌 Overview

**HealthGuardAI** is a privacy-focused Android AI application that lets users upload medical documents (PDFs, reports, prescriptions) and ask natural-language questions about their content.

Instead of sending an entire document to a cloud AI model, HealthGuardAI uses a **Retrieval-Augmented Generation (RAG)** pipeline to retrieve only the relevant chunks first, then feeds that context to an **on-device LLM**. The user can optionally switch to a cloud model (Gemini).

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
LLM (On-Device Gemma 3 1B via MediaPipe / Gemini Cloud option)
   ↓
Grounded Answer
```

---

## 🎬 Demo

[Watch the demo video](https://drive.google.com/file/d/1_pZ8TebmiyeVRZ6I3ahDyRqiTskOyIPI/view?usp=drivesdk): PDF upload, chunking, and an on-device Gemma answer on a mid-range Android phone.

---

## ✨ Features

- 📄 **Document Ingestion**: Upload a PDF; text is extracted and chunked on-device
- 🔍 **Semantic Search**: Retrieves the most relevant chunks using vector similarity, not keyword matching
- 🤖 **On-Device LLM Inference**: Runs Google's Gemma 3 1B locally via MediaPipe, no internet required
- ☁️ **Cloud Option**: Gemini Cloud API, selectable by the user in the app
- 🔒 **Privacy by Design**: Documents stay on the device. In cloud mode, only the question and the retrieved text chunks are sent to Gemini
- 💬 **Grounded Answers**: Answers are generated from retrieved document context

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
   └── Cloud: Gemini API
```

**Why this architecture?**
- **Unidirectional data flow** keeps UI state predictable and testable
- **RAG before generation** grounds answers in retrieved context rather than the model's own memory
- **Pluggable LLM layer** lets the same ViewModel/Repository swap between on-device and cloud inference without UI changes

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose |
| Architecture | MVVM |
| On-Device LLM | Gemma 3 1B via MediaPipe LLM Inference API |
| Cloud LLM | Gemini API |
| Embeddings | Universal Sentence Encoder |
| Concurrency | Kotlin Coroutines, Dispatchers.IO |

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
4. If using the Gemini Cloud option, add your API key to `local.properties`:
   ```properties
   GEMINI_API_KEY=your_key_here
   ```
5. Build and run on a physical device (recommended for realistic inference performance)

---

# 📱 Screenshots

## 🏠 Main Application

| 🏠 Home | 📄 Upload Document | 💬 Chat |
|:---:|:---:|:---:|
| <img src="https://github.com/user-attachments/assets/29dd0af1-73a4-4966-b81b-390f60e6af02" width="250"/> | <img src="https://github.com/user-attachments/assets/d3b7210c-ed4c-4602-b41a-5464116d50ad" width="250"/> | <img src="https://github.com/user-attachments/assets/302b6535-a1bc-4be9-93a0-48def299f7a0" width="250"/> |

## 🤖 AI Inference

| 🤖 Gemma Test | 🔀 On-Device / API |
|:---:|:---:|
| <img src="https://github.com/user-attachments/assets/db41790a-cb7a-416e-9d36-9b32787b56e0" width="250"/> | <img src="https://github.com/user-attachments/assets/047f9220-b0af-4e99-b75c-381c8ce73650" width="250"/> |


## 🗺️ Roadmap

- [x] On-device Gemma 3 1B inference via MediaPipe
- [x] Document chunking pipeline
- [x] RAG pipeline wired into `ChatViewModel` and `ChatScreen`
- [ ] Retrieval quality evaluation on medical documents
- [ ] Multi-document support with source citation in answers
- [ ] Persistent chat history and cached embeddings
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
