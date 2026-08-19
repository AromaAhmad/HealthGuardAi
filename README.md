# 🩺 HealthGuardAI

### A Privacy-Focused AI Health Assistant for Android

**HealthGuardAI** is an experimental Android application that combines **Retrieval-Augmented Generation (RAG)** with **on-device Large Language Models (LLMs)** to create a more private and context-aware AI assistant.

Instead of simply sending a user's question to an AI model, HealthGuardAI first retrieves relevant information from the user's documents and then uses that context to generate a grounded response.

The project explores an important question:

> **Can we build useful AI assistants directly on mobile devices while keeping sensitive user data as private as possible?**

---

## ✨ What HealthGuardAI Does

HealthGuardAI allows a user to provide a document and ask questions about its contents.

The application processes the document locally, retrieves the most relevant information, and provides that context to an AI model before generating an answer.

```text
                📄 User Document
                       │
                       ▼
              ┌─────────────────┐
              │  PDF Extraction  │
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
              │   Retrieval     │
              └────────┬────────┘
                       │
                Relevant Context
                       │
                       ▼
              ┌─────────────────┐
              │       LLM       │
              └────────┬────────┘
                       │
                       ▼
                💬 Grounded Answer
