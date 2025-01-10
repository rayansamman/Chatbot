# Chatbot

## Overview

This project is a Java-based chatbot that demonstrates the implementation of various design patterns, including:

- **Factory Pattern** for dynamically creating response objects.
- **Decorator Pattern** for enhancing chatbot responses dynamically.
- **Singleton Pattern** for managing chatbot configurations globally.
- **Adapter Pattern** for connecting external functionalities.
- **Strategy Pattern** for dynamic chatbot behavior switching.
- **Observer Pattern** for event notifications and logging.

The chatbot allows users to interact with predefined commands such as `greeting`, `farewell`, `help`, `faq`, `joke`, or `exit`. It dynamically generates responses and applies decorators for customized outputs.


---

## How to Run

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/rayansamman/Chatbot.git
   ```

2. **Open the Project in IntelliJ**:

    - Ensure the project SDK is correctly configured.
    - Mark the `src` folder as "Sources Root."

3. **Run the Application**:

    - Navigate to `ChatbotApp.java`.
    - Right-click and select **Run 'ChatbotApp.main()'**.

4. **Interact with the Chatbot**:

    - Type commands like `greeting`, `farewell`, `help`,`faq`, `joke` or `exit` in the console.


---

# Work Progress

### This topic for keeping track and updating each other 


## My steps (Alisiia)
 behavioral-patterns

### 1. **Implemented Design Patterns**

- **Factory Pattern**:

    - Created a `ResponseFactory` class to dynamically generate responses based on user input.
    - Added response types like `GreetingResponse`, `FarewellResponse`, and `HelpResponse`.

- **Decorator Pattern**:

    - Added decorators like `EmojiDecorator` and `TextFormatterDecorator` to enhance responses with emojis and formatting.

- **Singleton Pattern**:

    - Implemented a `ChatbotConfig` class to manage global chatbot configurations, such as the bot's name and mood.
    - Improved the singleton pattern to ensure proper deserialization and thread safety.
    - Added functionality to load and save preferences (e.g., name, mood) in a `config.properties` file.

### 2. **Dynamic User Interaction**

- Added a user input loop in `ChatbotApp` to process commands and dynamically generate responses.
- Applied decorators to responses dynamically based on user input.
- Handled invalid commands gracefully with error messages.
- Allowed users to set the chatbot's name and mood dynamically during runtime.
- Saved user preferences across sessions using the `ChatbotConfig` class.

### 3. **Enhanced Code in ChatbotApp**

- Refactored the chatbot's main logic into reusable methods.
- Dynamically applied decorators to all responses.
- Improved the user experience with enhanced messages and error handling.
- Introduced mood-based responses (e.g., "happy", "grumpy", "neutral") to change the chatbot's tone dynamically.

---

## Features

### 1. **Commands**

- `greeting`: Returns a greeting message.
- `farewell`: Returns a farewell message.
- `help`: Displays a list of available commands.
- `exit`: Exits the chatbot application.
- `set name [name]`: Changes the chatbot's name (e.g., `set name Kato`).
- `set mood [mood]`: Changes the chatbot's mood (e.g., `set mood happy` or `set mood grumpy`).

### 2. **Dynamic Response Generation**

- Responses are generated dynamically using the `ResponseFactory`.
- All responses can be decorated with emojis and text formatting.
- Responses are adapted based on the chatbot's mood (e.g., "happy", "grumpy", "neutral").

### 3. **Singleton Configuration**

- The `ChatbotConfig` class ensures a single instance is used to manage global settings.
- Users can save preferences (name and mood) that persist across sessions using a `config.properties` file.

---

## Examples of Interaction

### Changing the Chatbot's Name
- **Input**: `set name Kato`
- **Response**: `Chatbot: You can now call me Kato!`

### Changing the Mood
- **Input**: `set mood happy`
- **Response**: `Chatbot: Mood changed to happy.`

### Greeting in Different Moods
- **Neutral Mood**:
    - **Response**: `Hello! How can I assist you?`
- **Happy Mood**:
    - **Response**: `Hey there! So happy to see you! 😊`
- **Grumpy Mood**:
    - **Response**: `Oh... it's you. What do you want? 🙄`

---

## Future Improvements

- Implement **Behavioral Patterns** like State or Observer for managing chatbot states or events.
- Add support for external APIs (e.g., jokes, weather updates).
- Enhance user input handling with NLP for smarter interactions.
- Add unit tests for better reliability using JUnit.
- Expand the chatbot's "mood" system with more dynamic behaviors (e.g., "curious", "excited").
- Add a logging system to track user interactions for analytics.

---

## Notes for Teammate 💕

- Refactored the file structure.

![img.png](img.png)


## Steps by Rayan

### 1. **Implemented Behavioral Patterns**

#### **Adapter Pattern (Structural)**

- **Purpose**: Connect the chatbot with external functionalities, such as joke generation.
- **What I Did**:
    - Created the `ExternalAPIAdapter` interface.
    - Implemented `FakeJokeAPIAdapter` to simulate joke fetching from a fake external API.
    - Integrated the `FakeJokeAPIAdapter` into `ChatbotApp` to handle the `jokeapi` command.
- **How It Works**:
    - When the user types `jokeapi`, the chatbot uses the adapter to fetch a joke dynamically.
    - Example: `"Why don't skeletons fight each other? Because they don’t have the guts!"`

#### **Strategy Pattern (Behavioral)**

- **Purpose**: Dynamically switch between chatbot strategies based on user input.
- **What I Did**:
    - Created the `ResponseStrategy` interface.
    - Implemented strategies like:
        - `SmallTalkStrategy`: Handles casual user input like "how are you."
        - `FAQStrategy`: Handles frequently asked questions like "help" or "pricing."
        - `JokeStrategy`: Delivers jokes based on user input.
    - Integrated strategy switching into `ChatbotApp` to dynamically change the chatbot's behavior.
- **How It Works**:
    - The chatbot starts with the `SmallTalkStrategy` by default.
    - Users can switch to `FAQStrategy` by typing `faq` or to `JokeStrategy` by typing `joke`.

#### **Observer Pattern (Behavioral)**

- **Purpose**: Notify modules when certain events occur in the chatbot.
- **What I Did**:
    - Created the `EventObserver` interface and `EventManager` class.
    - Implemented observers like:
        - `ConsoleLogger`: Logs events to the console.
        - `FileLogger`: Logs events to a file (`chatbot_logs.txt`).
    - Integrated observers into `ChatbotApp` to monitor events like:
        - User input processing.
        - Switching strategies.
        - Exiting the chatbot.
- **How It Works**:
    - The `ConsoleLogger` and `FileLogger` receive notifications whenever an event occurs, such as a user exiting the chatbot or switching conversation modes.

---

### 2. **Integrated the Behavioral Patterns into ChatbotApp**

#### Updated Code Files

1. **ChatbotApp.java**
    - Added commands for switching between strategies (`faq`, `joke`).
    - Integrated support for `jokeapi` using the `FakeJokeAPIAdapter`.
    - Integrated the `EventManager` to notify observers (both `ConsoleLogger` and `FileLogger`) about user activities.

2. **EventObserver.java**:
    - Interface for handling events within the chatbot system.

3. **EventManager.java**:
    - Manages all observers and broadcasts events.

4. **FakeJokeAPIAdapter.java**:
    - Simulates fetching jokes from an external API.

5. **ResponseStrategy Implementations**:
    - **SmallTalkStrategy**: Handles casual chat.
    - **FAQStrategy**: Responds to FAQs.
    - **JokeStrategy**: Provides jokes.

6. **FileLogger.java**:
    - Logs events to a file named `chatbot_logs.txt`.

#### Testing
- Verified all patterns by testing:
    - Switching between conversation modes (`faq`, `joke`).
    - Fetching jokes using `jokeapi`.
    - Logging events in both console and file.

---

## Features 

### 1. **Commands**

- `faq`: Switches to the FAQ mode.
- `joke`: Switches to the Joke mode.
- `jokeapi`: Fetches a joke using the `FakeJokeAPIAdapter`.

### 2. **Dynamic Strategy Switching**

- Chatbot changes its behavior dynamically based on user input (e.g., switching between Small Talk, FAQ, and Joke modes).

### 3. **Event Logging**

- All chatbot events are logged:
    - In the console (via `ConsoleLogger`).
    - In a file (`chatbot_logs.txt`, via `FileLogger`).

---

## Examples of Interaction

### Switching Strategies
- **Input**: `faq`
- **Response**: `Chatbot: Switched to FAQ mode.`

- **Input**: `joke`
- **Response**: `Chatbot: Switched to Joke mode.`

### Using the Fake Joke API
- **Input**: `jokeapi`
- **Response**: `"Why don't skeletons fight each other? Because they don’t have the guts!"`

---

### 3. **Improvements to the File Structure**

- Separated classes into appropriate packages:
    - **Behavioral**: Contains observer, strategy, and adapter patterns.
    - **App**: Core chatbot application.
    - **Response**: All response-related classes (factory and decorators).
    - **Decorator**: Emoji and text formatting decorators.

### 4. **Logging and Observer Enhancements**

- Added `FileLogger` to log user interactions into a file.
- Integrated both `ConsoleLogger` and `FileLogger` for comprehensive logging.
- Enhanced the `EventManager` to handle notifications seamlessly.

---
