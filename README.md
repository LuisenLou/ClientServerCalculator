# Introduction

This type of application offers an excellent opportunity to learn several key concepts of programming in Java. By implementing client-server communication through sockets, one learns about data transfer over networks and communication protocols. 
The use of threads provides a better understanding of concurrent programming, which is essential for applications that require simultaneous operations without blocking execution.

Swing is chosen to develop user interfaces due to its robustness and flexibility in creating graphical applications. It provides hands-on experience in interface and event development, and is part of the core Java development toolkit.

These fundamentals demonstrate why Java is a favorable choice: it is a versatile language widely used in the industry, with a vast community and educational resources. 
It enables developers to acquire skills necessary for complex projects and lays the foundation for adapting to new technologies within the Java ecosystem.

## Steps

1. Open the server.
2. Open the client.
3. Enter two numbers in the corresponding sections.
4. Choose an operation to perform.
5. Click the "SEND" button.
6. Observe the data in the server panel.
7. Observe the data in the client panel in the corresponding space or section.
8. Repeat as needed.
9. If you need to clear the history, click the "RESET" button on the server.
10. If subtracting a smaller number from a larger number, the server will swap the numbers, subtracting the smaller one from the larger, and will notify in the server panel.


# Client-Server Mathematical Operations Application

The application I developed consists of a client-server interface where the client performs mathematical operations (such as addition, subtraction, and multiplication) between two numbers. The process is simple: the user enters two positive integers, selects the operation using radio buttons, and then clicks the "SEND" button. This data is sent to the server, which performs the calculation and returns the result to the client, which displays it on a graphical interface.

Data exchange between the client and server is done via sockets and threads. Sockets enable real-time communication, while threads allow multiple simultaneous connections, improving the system's efficiency.

## Benefits of Using Sockets and Threads

### 1. Real-Time Communication
Using sockets allows the client and server to communicate in real-time. Sockets create a bidirectional channel between the two, meaning that data can be sent and received immediately. This is a crucial aspect for applications like ours, where receiving results quickly and efficiently is essential.

### 2. Efficient Connection Handling
The use of threads on the server is key to handling multiple simultaneous connections without one interfering with another. This ensures that even if multiple users attempt to connect to the server at the same time, each will receive a response without the system becoming blocked. The ability to handle multiple threads improves the scalability of the application and allows it to adapt to a higher number of users or requests.

### 3. Scalability
The thread infrastructure allows the server to scale efficiently. As the demand for connections increases, we can add more threads to handle the traffic more smoothly without redesigning the entire system architecture.

## Current Limitations of the Application

### 1. Limited Mathematical Operations
Currently, the application only allows performing three basic operations: addition, subtraction, and multiplication. While these operations cover many use cases, it would be helpful to extend the functionality to include more advanced operations such as division, powers, or even working with fractions. This would improve the versatility of the application and make it more useful in different scenarios.

### 2. Basic Input Validation
The validation of the numbers entered in the interface is quite simple: it only checks that they are integers. It would be advisable to improve this validation to allow other types of numbers (such as decimals) or even add additional validations to handle negative numbers or very large values. This way, the application could be more robust and tolerant of different types of input.

### 3. No Calculation History
Another potential improvement is the addition of a calculation history system. Currently, the application only shows the last calculated result. It would be useful to allow the user to see a history of previous operations, which could make it easier to review past calculations and improve the overall user experience.

## Potential Interface Improvements: From Swing to JavaFX

### 1. Improved Aesthetics and Functionality
The current application is built with Swing, which is a stable and widely used technology for creating graphical interfaces in Java. However, Swing has some limitations, particularly in terms of modernizing user interfaces. The visual components can appear somewhat outdated compared to modern applications.

JavaFX could be an excellent alternative to enhance the appearance and functionality of the application. With JavaFX, I can create much more attractive and dynamic interfaces, offering more options for customizing the UI and making it more intuitive for the user. Additionally, JavaFX provides better support for animations, graphics, and transitions, which could improve the visual experience of the application.

### Benefits of Migrating to JavaFX:
- **More Modern and Attractive Interface**: JavaFX allows for creating visually appealing and modern designs, which can make the application look more professional.
- **More Interactivity**: With JavaFX, I can add animations and transitions that enhance the user experience, making the application more dynamic.
- **Advanced Components**: JavaFX offers additional components like graphics, tables, and advanced controls that could be useful for enriching the interface.
- **Support for Multimedia**: If in the future I want to include features such as audio or video (for example, to provide tutorials or audio feedback), JavaFX makes it easier to integrate these elements.

