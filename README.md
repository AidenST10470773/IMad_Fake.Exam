# Game Log App

----------------------------------------------------------
## Project Overview

The Game Log App is an Android application designed for users to track their game playing activity. It allows users to log details such as the date played, minutes played, game genre, and a personal rating for each game session. The app stores a maximum of the 7 most recent entries and provides a review screen where users can see a summary, including the highest-rated game logged and the average minutes played across their sessions. The app utilizes `AppCompatActivity`, `EditText` for input, and an `object` (singleton) for managing the log data in memory.

------------------------------------------------------

## Purpose and Features

## Purpose:
The Game Log App aims to provide a simple and efficient way for gamers to keep a record of their playing habits and preferences. It helps users track time spent on games, identify favorite genres or highly-rated games, and reflect on their gaming activity over time. This can be useful for personal insight, time management, or simply remembering past gaming experiences.

## Key Features:

- **Log Entry Creation:**
    - **Description:** Users can input details for each game session.
    - **Functionality:** The app provides input fields for:
        - Date (String)
        - Minutes Played (Integer)
        - Game Genre (String)
        - Rating (Integer, e.g., 1-5 or 1-10, specify your scale)
    - **User Interaction:** Users fill in the `EditText` fields on the "Add Log" screen and tap an "Add" button to save the entry. Input validation ensures all fields are filled and that minutes/rating are valid numbers.

- **Log Storage (Max 7 Entries):**
    - **Description:** The app maintains a log of the 7 most recent game entries.
    - **Functionality:** When a new entry is added and the log is full (7 entries), the oldest entry is automatically removed to make space for the new one (FIFO - First-In, First-Out). Data is managed by the `inventory_manager` Kotlin object.
    - **User Interaction:** This process is automatic and transparent to the user after they submit a new log.

- **Review Log Data:**
    - **Description:** Users can navigate to a dedicated screen to review their logged data and calculated statistics.
    - **Functionality:** The "Review Log" screen displays:
        - **Highest Rated Game:** Shows details (date, genre, minutes, rating) of the game with the highest rating among the logged entries.
        - **Average Minutes Played:** Calculates and displays the average time spent per game session across all logged entries.
        - **List of All Logged Games (Optional but good):** Displays details for all current (up to 7) game logs.
    - **User Interaction:** Users access this screen via a "Review" button from the main input/navigation screen.

- **Navigation:**
    - **Description:** Simple navigation between the main input screen, the "Add Log" screen, and the "Review Log" screen.
    - **Functionality:** Uses Android `Intent`s to move between activities.
    - **User Interaction:** Buttons trigger navigation to the respective screens. The `finish()` method is used to close input screens after data submission.

------------------------------------------

## Design Considerations

The design of the Game Log App was based on the following key considerations:

- **User Experience (UX):**
    - **Simplicity:** Focused on making the logging and review process straightforward and quick.
    - **Clarity:** Clear labels for input fields and displayed data.
    - **Efficiency:** Minimized steps to log a game or view statistics.
- **Functionality:**
    - **Core Task Focus:** Prioritized the essential features of logging game data and reviewing key metrics.
    - **Data Management:** Implemented a simple in-memory data store (`inventory_manager` object) suitable for the scope of the project, with a clear rule for managing the 7-item limit.
- **Technical Implementation:**
    - **Standard Android Components:** Utilized `AppCompatActivity`, `EditText`, `Button`, `TextView`, and `Toast` messages for UI and user feedback.
    - **Data Encapsulation:** Centralized data storage and manipulation logic within the `inventory_manager` object.

-----------------------------------------------------
## GitHub and GitHub Actions (If you used them)

This project was managed using **GitHub** for version control. All code changes were committed and pushed regularly to the repository [Link to Your GitHub Repo Here]. GitHub facilitated tracking changes, managing versions, and maintaining project integrity.

### GitHub Actions (If you set this up):
I utilized **GitHub Actions** to automate parts of the development workflow. This included:
- [Describe your workflow, e.g., Running automated builds on push/pull request]
- [Describe your workflow, e.g., Generating APK/AAB files for testing or distribution]
[If you have a screenshot of your GitHub Actions workflow, include it here, similar to your example]
<!-- ![Screenshot of GitHub Actions Workflow](path_to_your_screenshot.png) -->

-------------------------------------------------------

### App Screenshots:
[Include screenshots of your app's main screens here. Good ones to include would be:]
- The main navigation screen (e.g., `MainInputActivity` with "Enter Log" and "Review Log" buttons)
- The "Add Log" screen (`Add_log` activity with input fields)
- The "Review Log" screen (`Review_log` activity showing highest rated, average minutes, and list of logs)

<!--
Example:
![Main Navigation Screen](path_to_main_screen_screenshot.png)
(Caption: The main screen allowing users to navigate to add or review logs.)

![Add Log Screen](path_to_add_log_screenshot.png)
(Caption: Screen for inputting game session details.)

![Review Log Screen](path_to_review_log_screenshot.png)
(Caption: Screen displaying the highest-rated game, average minutes, and all logged entries.)
-->

### Video Demo (If applicable):
A video showcasing the app's functionality can be viewed here: [Link to Your YouTube Demo Video Here].

-----------------------------------------------

## Challenges and Learnings

During the development of the Game Log App, I encountered several challenges, including:

- **Challenge 1: Managing a Fixed-Size Log:**
    - **Description:** Implementing the logic to store only the last 7 entries and discard the oldest when the limit is reached.
    - **Solution:** I used an `ArrayList` within the `inventory_manager` object. Before adding a new entry, I checked the list size. If it was at the maximum (`MAX_ITEMS`), I removed the element at index 0 from each parallel list (`dates`, `minutesPlayed`, etc.) before adding the new data. This ensured a FIFO queue behavior.

- **Challenge 2: Calculating Statistics:**
    - **Description:** Correctly finding the highest-rated game and calculating the average minutes played from the stored data.
    - **Solution:** For the highest-rated game, I iterated through the `ratings` list, keeping track of the maximum rating found and its index, then used that index to retrieve all details of that game. For the average minutes, I utilized the built-in `.average()` extension function on the `minutesPlayed` list after ensuring it wasn't empty.

- **Challenge 3: Data Persistence (or lack thereof and its implications):**
    - **Description:** Understanding that the current implementation uses in-memory storage (`inventory_manager` object), meaning data is lost when the app is fully closed.
    - **Solution/Learning:** While not implemented due to project scope, I learned that for persistent storage, technologies like SharedPreferences (for simple data), Room database (for structured data), or Firebase would be necessary. This project highlighted the difference between runtime data and persistent data.

- **Challenge 4: Passing Data and Updating UI Across Activities:**
    - **Description:** Ensuring data entered in `Add_log` was correctly stored and then retrieved and displayed in `Review_log`.
    - **Solution:** Data was added directly to the `inventory_manager` object from `Add_log`. The `Review_log` activity then read directly from this same shared object in its `onResume()` or `onCreate()` to display the current state of the logs and statistics. This avoided complex `Intent` extras for large datasets for this particular architecture.

## From these challenges, I learned:
- The importance of choosing appropriate data structures (`ArrayList`) for specific requirements (like FIFO queues).
- How to perform common data manipulation and calculation tasks in Kotlin.
- The lifecycle
