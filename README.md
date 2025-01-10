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

    - Type commands like `greeting`, `farewell`, `help`, `faq`, `joke` or `exit` in the console.

---

## Work Progress

### Collaboration and Learning

This project was developed collaboratively by **Alisiia** and **Rayan**. Throughout the development process, we:

- Learned new techniques from each other, such as debugging and implementing design patterns.
- Worked together to resolve errors in logic and functionality.
- Communicated effectively in university and via Teams for remote discussions.

---

### Alisiia's Contributions

#### 1. **Implemented Design Patterns**

- **Factory Pattern**:

    - Created a `ResponseFactory` class to dynamically generate responses based on user input.
    - Added response types like `GreetingResponse`, `FarewellResponse`, and `HelpResponse`.

- **Decorator Pattern**:

    - Added decorators like `UppercaseDecorator` `EmojiDecorator` and `TextFormatterDecorator` to enhance responses with emojis and formatting.

- **Singleton Pattern**:

    - Implemented a `ChatbotConfig` class to manage global chatbot configurations, such as the bot's name and mood.
    - Improved the singleton pattern to ensure proper deserialization and thread safety.
    - Added functionality to load and save preferences (e.g., name, mood) in a `config.properties` file.

#### 2. **Dynamic User Interaction**

- Added a user input loop in `ChatbotApp` to process commands and dynamically generate responses.
- Applied decorators to responses dynamically based on user input.
- Handled invalid commands gracefully with error messages.
- Allowed users to set the chatbot's name and mood dynamically during runtime.
- Saved user preferences across sessions using the `ChatbotConfig` class.

#### 3. **Enhanced Code in ChatbotApp**

- Refactored the chatbot's main logic into reusable methods.
- Dynamically applied decorators to all responses.
- Improved the user experience with enhanced messages and error handling.
- Introduced mood-based responses (e.g., "happy", "grumpy", "neutral", "formal", "casual") to change the chatbot's tone dynamically. Additionally, integrated mood-sensitive text formatting through the `TextFormatterDecorator`, which adapts the chatbot's response presentation based on the current mood.

---

### Rayan's Contributions

#### 1. **Implemented Behavioral Patterns**

- **Adapter Pattern (Structural)**:

    - Created the `ExternalAPIAdapter` interface.
    - Implemented `FakeJokeAPIAdapter` to simulate joke fetching from a fake external API.
    - Integrated the `FakeJokeAPIAdapter` into `ChatbotApp` to handle the `jokeapi` command.

- **Strategy Pattern (Behavioral)**:

    - Created the `ResponseStrategy` interface.
    - Implemented strategies like:
        - `SmallTalkStrategy`: Handles casual user input like "how are you."
        - `FAQStrategy`: Handles frequently asked questions like "help" or "pricing."
        - `JokeStrategy`: Delivers jokes based on user input.
    - Integrated strategy switching into `ChatbotApp` to dynamically change the chatbot's behavior.

- **Observer Pattern (Behavioral)**:

    - Created the `EventObserver` interface and `EventManager` class.
    - Implemented observers like:
        - `ConsoleLogger`: Logs events to the console.
        - `FileLogger`: Logs events to a file (`chatbot_logs.txt`).
    - Integrated observers into `ChatbotApp` to monitor events like:
        - User input processing.
        - Switching strategies.
        - Exiting the chatbot.

#### 2. **Integrated the Behavioral Patterns into ChatbotApp**

- Updated `ChatbotApp.java`:

    - Added commands for switching between strategies (`faq`, `joke`).
    - Integrated support for `jokeapi` using the `FakeJokeAPIAdapter`.
    - Integrated the `EventManager` to notify observers (both `ConsoleLogger` and `FileLogger`) about user activities.

- Added functionality to log user interactions dynamically in both the console and a file (`chatbot_logs.txt`).

---

## Features and Design Patterns in Action

### **Introduction**

This chatbot project showcases teamwork and the application of multiple design patterns. The project is hosted on GitHub with a clear file structure, and all relevant files are submitted in a zip archive. Both team members contributed equally to the project by collaborating in person and via remote tools like Teams, ensuring a balanced and coordinated effort.

---

### **Creational Patterns**

#### 1. Factory Pattern

- **Description**: The `ResponseFactory` class dynamically creates response objects based on user input.

- **Code Example**:
```java
Response response = ResponseFactory.createResponse("greeting");
System.out.println(response.getMessage());
```

- **Explanation**:
    - The `ResponseFactory` supports different response types such as `GreetingResponse`, `FarewellResponse`, and `HelpResponse`.
    - By centralizing response creation, the factory promotes extensibility and flexibility.

- **Interaction**:
    - **Input**: `greeting`
    - **Output**: `Hey there! So happy to see you! 😊`

#### 2. Singleton Pattern

- **Description**: The `ChatbotConfig` class ensures that only a single instance exists to manage the chatbot's global settings, such as name and mood.

- **File Content**:
```properties
botName=Kato
mood=grumpy
```

### **Structural Patterns**

#### 1. Decorator Pattern

- **Description**: Enhances responses dynamically with formatting and emojis based on mood.

- **Code Example**:
```java
Response decoratedResponse = new EmojiDecorator(new TextFormatterDecorator(response));
System.out.println(decoratedResponse.getMessage());
```

- **Explanation**:
    - The `EmojiDecorator` and `TextFormatterDecorator` add emojis and text formatting to the chatbot's responses.
    - The decorators support moods such as "happy", "grumpy", and "neutral", adjusting the tone of messages accordingly.

- **Example Interaction**:
    - **Neutral Mood**: `Hello! How can I assist you?`
    - **Happy Mood**: `Hey there! So happy to see you! 😊`
    - **Grumpy Mood**: `Oh... it's you. What do you want? 🙄`

#### 2. Adapter Pattern

- **Description**: Facilitates integration with external APIs (e.g., joke API).

- **Code Example**:
```java
ExternalAPIAdapter jokeAPI = new FakeJokeAPIAdapter();
System.out.println("Chatbot: " + jokeAPI.getResponse("jokeapi"));
```

- **Explanation**:
    - The `ExternalAPIAdapter` defines an interface for external API integration.
    - The `FakeJokeAPIAdapter` simulates fetching jokes from an external API.

- **Interaction**:
    - **Input**: `jokeapi`
    - **Output**: `"Why don’t skeletons fight each other? Because they don’t have the guts!"`

---

### **Behavioral Patterns**

#### 1. Strategy Pattern

- **Description**: Dynamically switches chatbot behavior based on user input.

- **Code Example**:
```java
ResponseStrategy currentStrategy = new SmallTalkStrategy();
String response = currentStrategy.generateResponse("how are you?");
System.out.println(response);
```

- **Explanation**:
    - Implemented strategies include `SmallTalkStrategy`, `FAQStrategy`, and `JokeStrategy`.
    - The chatbot switches between strategies at runtime to handle different user interactions.

- **Interaction**:
    - **Input**: `faq`
    - **Output**: `Here are some frequently asked questions.`

#### 2. Observer Pattern

- **Description**: Logs events (e.g., user actions, strategy switching) to the console and a file.

- **Explanation**:
    - `EventManager` broadcasts events to registered observers.
    - `ConsoleLogger` and `FileLogger` log events to the console and a file (`chatbot_logs.txt`), respectively.

- **Interaction**:
    - **Event**: User exits the chatbot.
    - **Log (Console)**: `[LOG]: User exited the chatbot.`
    - **Log (File)**: Written to `chatbot_logs.txt`.

---
