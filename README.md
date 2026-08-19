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



Architecture Principles
MVVM for separation of UI and application logic
Repository pattern for data and AI operations
Jetpack Compose for declarative UI
Room for local persistence
Coroutines for asynchronous operations
Separation between document processing, retrieval, and inference
🧠 RAG Pipeline

The core of HealthGuardAI is its Retrieval-Augmented Generation pipeline.

          📄 PDF Document
                 │
                 ▼
        ┌─────────────────┐
        │ Text Extraction │
        └────────┬────────┘
                 │
                 ▼
        ┌─────────────────┐
        │    Chunking     │
        └────────┬────────┘
                 │
                 ▼
        ┌─────────────────┐
        │   Embeddings    │
        └────────┬────────┘
                 │
                 ▼
        ┌─────────────────┐
        │ Semantic Search │
        └────────┬────────┘
                 │
                 ▼
        ┌─────────────────┐
        │ Relevant Chunks │
        └────────┬────────┘
                 │
                 ▼
        ┌─────────────────┐
        │  Context + Query│
        └────────┬────────┘
                 │
                 ▼
        ┌─────────────────┐
        │      LLM        │
        └────────┬────────┘
                 │
                 ▼
             💬 Answer
1. Text Extraction

The application extracts readable text from the uploaded PDF.

2. Chunking

Large documents are divided into smaller chunks so that relevant sections can be retrieved efficiently.

3. Embeddings

Text chunks are converted into numerical vector representations using a text embedding model.

4. Retrieval

When the user asks a question, the query is also converted into an embedding.

The system compares the query representation with document chunk representations and retrieves the most relevant content.

5. Context Construction

The retrieved chunks are combined with the user's question to construct the context provided to the LLM.

6. Generation

The LLM generates the final response using the retrieved document context.

🤖 On-Device LLM

HealthGuardAI explores local Large Language Model inference using Gemma and MediaPipe.

User Question
      │
      ▼
Retrieved Context
      │
      ▼
Prompt Construction
      │
      ▼
┌──────────────────────┐
│   Gemma LLM          │
│   On Android Device  │
└──────────┬───────────┘
           │
           ▼
      AI Response

Running inference locally can provide important advantages for applications handling sensitive information:

🔒 Improved privacy
📱 Local processing
🌐 Reduced cloud dependency
⚡ Potentially lower latency
📴 Potential offline capability

Actual performance depends on the Android device and model configuration.

🛠 Tech Stack
Android
Kotlin
Android SDK
Jetpack Compose
Material 3
Navigation Compose
Kotlin Coroutines
Architecture
MVVM
Repository Pattern
Clean separation of responsibilities
AI / ML
Retrieval-Augmented Generation (RAG)
Text Embeddings
Semantic Search
Gemma
MediaPipe LLM Inference
Data
Room Database
Local document data
Local application state
Document Processing
PDF text extraction
Text chunking
Embedding generation
Context retrieval
🚀 Getting Started
Prerequisites

Before running the project, make sure you have:

Android Studio
JDK compatible with the project
Android SDK
A supported Android device or emulator
Sufficient device resources for on-device LLM inference
Installation
1. Clone the repository
git clone https://github.com/YOUR_USERNAME/HealthGuardAI.git
2. Open the project

Open the cloned project in Android Studio.

3. Sync Gradle

Allow Android Studio to download and configure the required dependencies.

4. Configure AI API

If using API-based inference, add the required API configuration according to the project setup.

Never commit API keys or other secrets to GitHub.

5. Run the application

Connect an Android device or start an emulator and run the application from Android Studio.

🔐 Privacy Considerations

HealthGuardAI is designed with privacy in mind.

The project explores local-first AI processing, particularly for documents that may contain sensitive information.

Privacy-focused design
Documents can be processed locally
On-device LLM inference reduces cloud dependency
Local persistence through Room
API-based inference is optional
API keys should never be stored directly in source code

Important: HealthGuardAI is an educational/technical project and is not a substitute for professional medical advice.

💡 What I Learned

Building HealthGuardAI helped me gain practical experience with:

Android Development
Building applications with Jetpack Compose
MVVM architecture
Repository pattern
State management
Navigation
Local persistence with Room
AI Engineering
Understanding Retrieval-Augmented Generation
Text chunking strategies
Embeddings
Semantic similarity search
Context retrieval
Prompt construction
LLM integration
On-Device AI
Running LLM inference on Android
Working with Gemma
MediaPipe LLM Inference
Understanding the limitations of mobile AI inference
Designing applications around device constraints
Privacy-Aware AI
Comparing cloud and local inference
Reducing unnecessary data transmission
Designing AI systems for sensitive document processing
🚀 Future Improvements

Planned improvements include:

 Better PDF parsing for complex documents
 Improved chunking strategies
 Vector database optimization
 More advanced semantic retrieval
 Retrieval ranking / reranking
 Streaming LLM responses
 Conversation history
 Multiple document collections
 Improved on-device model performance
 Better offline support
 Medical-document-specific retrieval evaluation
 RAG accuracy and latency benchmarking
📊 Project Goals

HealthGuardAI was built as a practical exploration of the intersection between:

Android Development + Generative AI + RAG + On-Device Machine Learning + Privacy

The main goal was not simply to integrate an AI API, but to understand how an end-to-end AI-powered Android system can be designed and implemented.

👩‍💻 Developer
Aroma Ahmad

BS Computer Science | Android Developer | AI/ML Enthusiast

Interested in building privacy-conscious mobile applications and exploring the integration of AI/ML technologies into real-world software systems.

Core Skills
Kotlin
Android Development
Jetpack Compose
MVVM
Clean Architecture
Room
REST APIs
RAG
LLM Integration
On-Device AI
⭐ Support

If you found HealthGuardAI interesting or useful, consider giving the repository a ⭐ Star!
                       │ Local Data  │
                       └─────────────┘
