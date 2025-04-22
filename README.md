# engine Android App ( Game Search )

## Table of contents
- <a href="#overview">overview</a>
- <a href="#screenshots">Screenshots</a>
- <a href="#run-the-project">Run the Project</a>
- <a href="#built-with">Built With</a>

## Overview
 
### About the App
    Android Game Search, built with Java or Kotlin by ByteDance, is a comprehensive app designed to provide users with detailed information about various video games, including game covers, ratings, release 
    dates, screenshots, genres, platforms, developers, and similar games. It also features a list of upcoming and ongoing gaming events, categorized game lists, and a robust search functionality.
    
### Key Features
  - Game Details

    -
        The app displays detailed information for each video game, including:
        - **Game Cover:** High-quality game cover images.
        - **Rating:** User and critic ratings for the game.
        - **Release Date:** Official release date of the game.
        - **Screenshots:** In-game screenshots for better visualization.
        - **Genres:** Categories and types of the game (e.g., Action, RPG, etc.).
        - **Platforms:** Supported platforms like PC, PlayStation, Xbox, etc.
        - **Developers:** Information about the game development studios.
        - **Similar Games:** Suggestions for games similar to the selected one.

     
- Gaming Events:

 
     - Lists upcoming and ongoing gaming events.
     - Includes event logos, descriptions, and associated games.
 - Game Categories

   
      - **Currently Popular:** Trending games based on 
          engagement.
      - **Recently Released:** Highly rated games from the 
         last three months.
      - **Top-Rated (All Time):** Games with a rating of 9+.
     - **Most Anticipated:** Upcoming games sorted by hype.
- IGDB API Integration
  
   - Integrates with the **IGDB API**, owned by Twitch, to fetch 
    comprehensive game data.
- Search Functionality
  
   - Provides robust search capabilities:
   - Users can search for any video games in the search screen.
   - All matching video games are fetched and displayed.
 -  Thread Architecture
       - 1. **Main Thread:**  
                 Handles all UI updates for smooth and responsive user interactions.
   
- Navigation:

    -  I have implemented fragment-to-fragment navigation using **Navigation Graph** in Java.
- Data Fetching

  - In this project, we have implemented efficient data fetching and caching mechanisms to ensure fast and reliable data management. The following techniques/tools were used:
    
       -  **Data Fetching**: We use **Retrofit** for making network calls to fetch data from remote APIs. It simplifies handling HTTP requests and parsing responses efficiently.
      
       -  **Efficient Data Management**: For managing large datasets and ensuring minimal memory usage, we have utilized  **Lazy Loading** techniques. Additionally, we use **Data Binding** and
## Screenshots 
<div style="display: grid; grid-template-columns: repeat(2, 1fr); gap: 10px;">
<img src="https://github.com/user-attachments/assets/a026876b-db62-40fd-ae41-d76873534db6" alt="screenshot 1" width="250" data-canonical-src="https://i.imgur.com/HEIBcYW.png" style="max-width: 100%;">
<img src="https://github.com/user-attachments/assets/3dbe0cc7-b3c0-48f4-9065-dea2fb3798b8" alt="screenshot 1" width="250" data-canonical-src="https://i.imgur.com/HEIBcYW.png" style="max-width: 100%;">
<img src="https://github.com/user-attachments/assets/47105ac6-f2bb-4c7e-b04b-8f1f07eeb2fb" alt="screenshot 1" width="250" data-canonical-src="https://i.imgur.com/HEIBcYW.png" style="max-width: 100%;">
<img src="https://github.com/user-attachments/assets/c509571e-6769-4275-8bd0-68ef31b00bb8" alt="screenshot 1" width="250" data-canonical-src="https://i.imgur.com/HEIBcYW.png" style="max-width: 100%;">
<img src="https://github.com/user-attachments/assets/88b505f9-7ab9-46b1-a22e-880f4a619a16" alt="screenshot 1" width="250" data-canonical-src="https://i.imgur.com/HEIBcYW.png" style="max-width: 100%;">
<img src="https://github.com/user-attachments/assets/c34c9472-689b-435a-9997-2a0b957eb5df" alt="screenshot 1" width="250" data-canonical-src="https://i.imgur.com/HEIBcYW.png" style="max-width: 100%;">
<img src="https://github.com/user-attachments/assets/a52b39e7-3720-4ae7-ae44-82f31d326ed3" alt="screenshot 1" width="250" data-canonical-src="https://i.imgur.com/HEIBcYW.png" style="max-width: 100%;">
<img src="https://github.com/user-attachments/assets/fcdcb49d-f820-4cdc-a930-67ed475531d3" alt="screenshot 1" width="250" data-canonical-src="https://i.imgur.com/HEIBcYW.png" style="max-width: 100%;">
<img src="https://github.com/user-attachments/assets/4d762456-551a-4153-9ec3-4436d1030545" alt="screenshot 1" width="250" data-canonical-src="https://i.imgur.com/HEIBcYW.png" style="max-width: 100%;">
<a target="_blank" rel="noopener noreferrer nofollow" href="https://github.com/user-attachments/assets/3dbe0cc7-b3c0-48f4-9065-dea2fb3798b8"><img src="https://github.com/user-attachments/assets/3dbe0cc7-b3c0-48f4-9065-dea2fb3798b8" alt="screenshot 1" width="250" data-canonical-src="https://i.imgur.com/HEIBcYW.png" style="max-width: 100%;"></a>

</div>



## Run the project
   -  Prerequisites
          - 1. **Java**: Install Java on your machine. You can download it [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
          - 2. **Clone the Project**: Run `git clone https://github.com/Manojraw/engine.git` to clone the project.
          - 3. **Code Editor/IDE**: Open the project in your preferred code editor (e.g., IntelliJ IDEA, Eclipse, or Visual Studio Code).

   - Set Up the Simulator

       -  Follow these instructions to set up the simulator for testing the app:   [Link to Setup Instructions](https://developer.android.com/studio/run/managing-avds)

   - Obtain IGDB API Key

       1. Visit [IGDB API Documentation](https://www.igdb.com/api).
       2. Follow the instructions to obtain your API key.

   - Install Dependencies

     Run the following command to install all project dependencies:

                ```bash
           ./gradlew build
         
     - In Short(On Android Studio)
         -  Clone this repository
       - Open with Android Studio
       - Sync Gradle
        - Run on emulator or real device
     ## Built with
       - **Java,kotlin** - Core development language
       - **Android Studio** - Used for building and testing the app
       - **Retrofit2** - Networking library to fetch data from the IGDB API([view](https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit))
       - **Glide** - Image loading and caching library([view](https://mvnrepository.com/artifact/com.github.bumptech.glide/glide))
       - **RecyclerView** - For displaying the fetched game list in a grid layout([view](https://mvnrepository.com/artifact/androidx.recyclerview/recyclerview)
       - **IGDB API** - Game data source[view](https://api-docs.igdb.com/#getting-started)


    

     
