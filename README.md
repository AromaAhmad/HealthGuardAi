# 🏥 HealthGuardAI

<p align="center">
  <strong>Privacy-Focused AI Health Assistant with RAG & On-Device LLM Inference</strong>
</p>

<p align="center">
  An Android application that combines Retrieval-Augmented Generation (RAG), semantic search,
  and on-device Large Language Model inference to provide grounded AI responses from user-provided documents.
</p>

<p align="center">

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![Gemma](https://img.shields.io/badge/Gemma-8E75B2?style=for-the-badge)
![RAG](https://img.shields.io/badge/RAG-FF6F00?style=for-the-badge)
![MediaPipe](https://img.shields.io/badge/MediaPipe-0097A7?style=for-the-badge)
![Room](https://img.shields.io/badge/Room-6DB33F?style=for-the-badge)

</p>

---

## ✨ Overview

**HealthGuardAI** is a privacy-focused Android AI assistant designed to answer questions using information retrieved from user-provided health-related documents.

Instead of sending the entire document directly to an AI model, the application follows a **Retrieval-Augmented Generation (RAG)** pipeline:

**Document → Text Extraction → Chunking → Embeddings → Semantic Retrieval → Context → LLM → Answer**

The project also explores **on-device LLM inference**, allowing AI responses to be generated locally on supported Android devices.

This project was built to explore how modern AI techniques such as **RAG, embeddings, semantic search, and on-device LLMs** can be integrated into a real Android application.

---

# ✨ Features

### 📄 Document-Based AI

- Select and upload PDF documents
- Extract text from documents
- Split large documents into smaller semantic chunks
- Use document content as the knowledge source for AI responses
- Ask questions about uploaded documents

### 🧠 Retrieval-Augmented Generation

HealthGuardAI implements a RAG pipeline that retrieves the most relevant information before generating an answer.

The pipeline consists of:

1. PDF text extraction
2. Text chunking
3. Text embeddings
4. Semantic similarity search
5. Relevant context retrieval
6. Context injection into the LLM
7. Grounded response generation

This reduces the need for the model to rely entirely on its pretrained knowledge.

### 🤖 On-Device LLM

The project integrates **Gemma-based on-device inference** using Google's MediaPipe LLM Inference APIs.

Benefits include:

- Reduced dependency on cloud AI APIs
- Better privacy for sensitive documents
- Local inference
- Potential offline AI functionality
- Lower exposure of user-provided data

### 🔀 On-Device / API Choice

HealthGuardAI provides a choice between:

**On-Device AI**

> Run the LLM locally on a supported Android device.

**API-Based AI**

> Use a remote AI API when cloud inference is preferred or when the device cannot efficiently run the local model.

This makes the architecture flexible while keeping privacy as a core design goal.

---

# 📱 Screenshots

## Main Application

| Home | Upload Document | Chat |
|------|------------------|------|
| <img width="720" height="1612" alt="Home screen" src="https://github.com/user-attachments/assets/29dd0af1-73a4-4966-b81b-390f60e6af02" /> | <img width="720" height="1612" alt="Upload document" src="https://github.com/user-attachments/assets/d3b7210c-ed4c-4602-b41a-5464116d50ad" /> | <img width="720" height="1612" alt="Chat" src="https://github.com/user-attachments/assets/302b6535-a1bc-4be9-93a0-48def299f7a0" /> |

## AI Inference

| Gemma Test | On-Device / API |
|------------|-----------------|
| <img width="720" height="1612" alt="Gemma test" src="https://github.com/user-attachments/assets/db41790a-cb7a-416e-9d36-9b32787b56e0" /> | <img width="720" height="1612" alt="On-device and API choice" src="https://github.com/user-attachments/assets/047f9220-b0af-4e99-b75c-381c8ce73650" /> |

---

# 🏛 Architecture

HealthGuardAI follows an **MVVM + Repository architecture**.

```text
                    ┌──────────────────────┐
                    │     Jetpack Compose  │
                    │          UI          │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │      ViewModel       │
                    │   State Management   │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │      Repository      │
                    │   Business Logic     │
                    └──────────┬───────────┘
                               │
              ┌────────────────┼────────────────┐
              │                │                │
              ▼                ▼                ▼
       ┌─────────────┐  ┌─────────────┐  ┌──────────────┐
       │ PDF Parser  │  │ RAG Pipeline│  │ LLM Inference│
       └─────────────┘  └─────────────┘  └──────────────┘
                              │
                              ▼
                       ┌─────────────┐
                       │    Room     │
                       │ Local Data  │
                       └─────────────┘
