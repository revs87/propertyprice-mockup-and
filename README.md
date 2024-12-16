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


### 📸 Screenshots

| Splash               | Property List Refresh      | Property List GoToTop |
|----------------------|---------------------|-----------------------|
| ![propertyintro](https://github.com/user-attachments/assets/d5808a4f-6f87-4592-ac89-23d5110402f5) | ![propertylist2](https://github.com/user-attachments/assets/8774e42e-e38d-4337-a3bb-b75081a41177) | ![propertylist3](https://github.com/user-attachments/assets/7acf92a0-2035-467d-bd16-3ce4c81ee875) |

| Property Details   | Property Details Rates | Property Details NO_INTERNET |
|--------------------|------------------------|------------------------------|
| ![propertydetails](https://github.com/user-attachments/assets/046bd3af-cd1b-4fec-bb05-086a9efb404e) |  ![propertydetails2](https://github.com/user-attachments/assets/e458dac8-6d4f-435d-bf09-d33bcf2619ef)  |   ![propertydetails3](https://github.com/user-attachments/assets/7d6d918e-27ae-40c2-bc46-bbb1778f8cb3) |


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
