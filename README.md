# Lexynapse: Your Personal Vocabulary Builder

**Unlock the Power of Words: Learn, Remember, and Expand Your Vocabulary Effortlessly.**

## Overview

Lexynapse is an Android application designed to help you build and expand your vocabulary in a personalized and engaging way. It leverages the power of local storage, intelligent AI, and user-friendly features to make learning new words an enjoyable experience.

## Key Features

* **Local Word Storage:** Save and manage your growing vocabulary with ease using a local Room database.
* **AI-Powered Definitions and Examples:** Get accurate meanings and contextual sample sentences for new words powered by Vertex AI.
* **Word of the Day:** Discover a new word daily, randomly selected from your local database, right on the home screen.
* **Categorization:** Organize your vocabulary by assigning categories (Easy, Medium, Hard, or custom) to each word for focused learning.
* **Add and Delete Words:** Seamlessly add new words and their details to your personal lexicon and remove words when needed.
* **Share Words:** Easily share interesting words and their definitions with friends and family.

## Tech Stack

* **Android Jetpack Compose:** Modern declarative UI toolkit for building native Android UIs.
* **Kotlin:** The expressive and concise programming language for Android development.
* **Room Persistence Library:** Provides an abstraction layer over SQLite for efficient local data storage.
* **Vertex AI (Standalone):** Utilized for fetching word meanings and sample sentences.
* **Hilt:** Dependency Injection library to manage dependencies efficiently.
* **Coroutines and Flow:** For asynchronous operations and managing data streams.

## Getting Started

1.  **Clone the repository:**
    ```bash
    
    git clone https://github.com/wgnofi/Lexynapse
    ```
2.  **Open the project in Android Studio.**
3.  **Set up Vertex AI API Key:**
    * Obtain an API key from the Google Cloud Console.
    * Create a `local.properties` file in the root of your project.
    * Add your API key to `local.properties`:
        ```properties
        
        GEN_AI_API_KEY=YOUR_API_KEY
        ```
    * Ensure `local.properties` is added to your `.gitignore` file.
4.  **Build and Run:** Build the project in Android Studio and run it on an emulator or a physical device.

## Libraries Used

* AndroidX Core KTX
* AndroidX Lifecycle KTX
* AndroidX Activity Compose
* AndroidX Compose UI, Graphics, Tooling, Material3
* JUnit
* AndroidX Test Ext JUnit
* AndroidX Test Espresso Core
* AndroidX Compose Test
* AndroidX Room Runtime, Compiler, KTX
* Hilt Android, Compiler, Navigation Compose
* Kotlin Coroutines Core, Android
* Kotlin Flow
* Vertex AI Generative Language API (Standalone)
