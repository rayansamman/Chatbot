# Chatbot

## Overview

This project is a Java-based chatbot that demonstrates the implementation of various design patterns, including:

- **Factory Pattern** for dynamically creating response objects.
- **Decorator Pattern** for enhancing chatbot responses dynamically.
- **Singleton Pattern** for managing chatbot configurations globally.
- **Adapter Pattern** for connecting external functionalities.
- **Strategy Pattern** for dynamic chatbot behavior switching.
- **Observer Pattern** for event notifications and logging.

The chatbot allows users to interact with predefined commands such as `greeting`, `farewell`, `help`, `faq`, `Tell me jokes`, or `exit`. It dynamically generates responses and applies decorators for customized outputs.

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

    - Type commands like `greeting`, `farewell`, `help`, `faq`, `Tell me jokes` or `exit` in the console.

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
- Introduced mood-based responses (e.g., "happy", "grumpy", "neutral") to change the chatbot's tone dynamically. Additionally, integrated mood-sensitive text formatting through the `TextFormatterDecorator`, which adapts the chatbot's response presentation based on the current mood.

---

### Rayan's Contributions

#### 1. **Implemented Behavioral Patterns**

- **Adapter Pattern (Structural)**:

    - Created the `ExternalAPIAdapter` interface.
    - Implemented `JokeAPIAdapter` to simulate joke fetching from an external API.
    - Integrated the `JokeAPIAdapter` into `ChatbotApp` to handle the `jokeapi` command.

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
    - Integrated support for `jokeapi` using the `JokeAPIAdapter`.
    - Integrated the `EventManager` to notify observers (both `ConsoleLogger` and `FileLogger`) about user activities.

- Added functionality to log user interactions dynamically in both the console and a file (`chatbot_logs.txt`).

---

## Features and Design Patterns in Action

### **Creational Patterns**

## Abstract Factory Pattern in Chatbot Response System

### Description
The **Abstract Factory Pattern** is implemented in the chatbot response system to dynamically choose a mood-specific factory based on the chatbot's current mood. The `ResponseFactorySelector` selects one of the mood-specific factories: `HappyResponseFactory`, `GrumpyResponseFactory`, or `NeutralResponseFactory`, to create appropriate response objects.

### Code Example

```java
// Select the appropriate factory based on the chatbot's current mood
ResponseFactory responseFactory = ResponseFactorySelector.getFactory();

// Create a greeting response from the selected factory
Response response = responseFactory.createGreetingResponse();

// Output the response message
System.out.println(response.getMessage());
```

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
ExternalAPIAdapter jokeAPI = new JokeAPIAdapter();
System.out.println("Chatbot: " + jokeAPI.getResponse("jokeapi"));
```

- **Explanation**:
    - The `ExternalAPIAdapter` defines an interface for external API integration.
    - The `JokeAPIAdapter` simulates fetching jokes from an external API.

- **Interaction**:
    - **Input**: `I want something new`
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

## **Summary of Changes after feedback**
### **Creational patterns**
The key changes:

1. **Replacement of Simple Factory with Abstract Factory:**  
   The previous implementation relied on a simple factory (`ResponseFactory`) to create response objects dynamically. It was refactored to use an **Abstract Factory** pattern, but it still called as **ResponseFactory** in files. Was created factory folder for that as well you can check in types folder updated files. This change ensures better separation of concerns, promotes scalability, and adheres to design pattern principles like the **Open-Closed Principle (OCP)**.

2. **Retention of the Decorator Pattern for Dynamic Enhancements:**  
   The dynamic decorators (`EmojiDecorator`, `TextFormatterDecorator`, and `UppercaseDecorator`) were retained for enhancing base responses. These decorators continue to be applied dynamically at runtime based on the chatbot’s mood, providing flexible response customization.

3. **Improved Response Generation Flow:**  
   The chatbot’s response generation flow was refined by introducing **mood-specific factories** that are selected dynamically using the `ResponseFactorySelector` class.
    - The **base response objects** (e.g., `GreetingResponse`, `FarewellResponse`) are now created through mood-specific factories (`HappyResponseFactory`, `GrumpyResponseFactory`, `NeutralResponseFactory`).
    - Once created, the responses are **wrapped with decorators** dynamically within `ChatbotApp`, allowing mood-sensitive output formatting and emoji inclusion.

### **Structural patterns**
Updated Files for Jokes System:
1. **`ChatbotApp.java`**
    - **Added logic** to switch to **Jokes Mode**.
    - **Integrated user commands** for `'Tell me jokes'`, `'more'`, and `'I want something new'`.
    - **Handles switching** between **local jokes** and **API jokes** dynamically.

2. **`JokeStrategy.java`**
    - **Enhanced** to include the **local jokes store**.
    - **Dynamically selects jokes** based on user commands within **Jokes Mode**.

3. **`JokeAPIAdapter.java`**
    - **Connects** to the **`JokeAPI`** service.
    - **Fetches and adapts jokes** from the **external API** for the chatbot.

4. **`JokeAPI.java`**
    - Create the actual external API connection and response.
    - Can be replaced with a real external API in the future without affecting the chatbot's functionality.