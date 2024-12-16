# Property Price Mockup
Just a Mockup project to detail City property prices along with currency value fluctuations


## 📜 Summary

This is a modern and intuitive Native Android app to show the listing of the Property prices of a city.
The development of this app was motivated and was part of a coding challenge described with a detailed practical example
that emboss my good practices, clean code principles and Android applied knowledge.


## 📌 Features

- Lists the rental properties with available prices per night.
- Calculates the currency rates for a list of selected currencies.
- Tracks the user's network calls for statistical metrics.
- Warns the user if connection or db problems occur.


### 🚀 Technical Highlights

- Android Native
- 100% Kotlin
- 100% Jetpack Compose
- Kotlin Coroutines, Flows and RxJava3 POC
- Unit tests applied to Property Ratings


### 🏛️ Architecture & Design Patterns

- Single module architecture
- Single Activity architecture
- MVVM presentation pattern with plenty of StateFlows visible to the UI layer
- MVI unidirectional data flow from Actions to Data and to State
- Sealed interfaces for Actions and Destinations files
- Dependency Injection applied with Hilt
- Dependency Inversion Principle (DIP) from data to presentation layers
- Single Source of Truth in a Room database
- Reactive Navigation (Flow listeners for navigation events)
- Reactive Error Handling: One Time Events from Data to Presentation
- Reactive stream of emission from Rates to keep prices in sync with currency rates


### 📲 UI

- Dark mode only
- Latest Splash API with animation
- Jetpack Compose No-type Args navigation
- NavigateToTop button
- PullToRefresh behaviour
- Error described with a Toast


### 🗄️ Local Persistence

- Room DB (on KSP)


### 📸 Screen Recording



### ☑ Project Requirements

- Java 17+
- The latest Android Studio Ladybug or above (for easy install use JetBrains Toolbox)
- Minimum SDK: 29 (Android 10)
- Target SDK: 35 (Android 15)
- Kotlin 2.1.0


### ⚙️ Configuration

Run ./gradlew build


## 🧾 License

Check LICENSE from the project.