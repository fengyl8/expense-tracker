# Spring Boot Tutorial Notes

These notes summarize my understanding of the Spring Boot tutorial shared by my mentor.

module 2

- Spring Boot makes it easier to build Java web applications by providing starter dependencies that bundle the common tools and functionality needed for web development.
- Spring Web provides the basic features needed to receive HTTP requests, create controllers, and build web applications or REST APIs, while Spring Boot DevTools makes development easier by automatically restarting the application when the code changes.
- A dependency is a library added to a Spring project, and each dependency provides a set of features.
- important structure in a normal spring web project : main, java, resources, test, pom.xml
- A Spring bean is an instance of a class, with some metadata, that is managed by the Spring Application Context.
- The controller is one layer of the application, and if you organize your code by layers, something like a RunController often needs to talk to a RunService in another package.

module 3

- MVC means:
  - Model: the data types, here Run and Location.
  - View: how data is represented; in this REST API, it is JSON.
  - Controller: the system’s traffic cop. It receives a request, determines what should happen, delegates work, and returns a response.
- Controllers should stay simple: receive requests and return responses. They should not contain data-management or complex business logic.
- When a browser or Postman calls a URL, the controller matches that URL and HTTP method to a handler, takes in the request data,delegates work to a repository or service, and returns the result as a response like JSON in a REST API, or a proper status like 404 if not found.
- Besides testing in the browser, the instructor shows how to call the API from a terminal.
- In RunRepository
  - create(Run run): adds a run to the in-memory list.
  - update(...): finds the existing run and replaces it with the supplied run.
  - delete(Integer id): removes a run whose ID matches the supplied ID.

module 4
(too much things and need to review and memorize later)
- H2 is a fast in-memory database that supports JDBC and can run in embedded or server mode.
- Add the H2 Database dependency and Spring Boot Starter JDBC dependency to pom.xml so the application can connect to and work with a database.
- H2 is an in-memory database, so its tables and data are recreated or reloaded whenever the application restarts.
- data.sql can insert sample data automatically when the application starts.
- The JSON data loader needs @Component so Spring can detect, create, and run it.

module 5

- REST clients allow one application to call another service’s REST API, which is common in microservice architectures.
- Spring’s newer RestClient is a blocking, synchronous client with a simple fluent API, created as a cleaner alternative to RestTemplate.
- To consume an external API, create Java records that match its JSON response, then use RestClient to request and map that data into Java objects.
- HTTP interfaces let you define only method contracts and annotations such as @GetExchange; Spring creates the implementation at runtime and reduces boilerplate code.

module 6

- In real development, tests are written alongside the code, but this course postpones testing until after building the app features, and Spring Boot already includes testing tools by default.
- Didn't fully went through the content of module 6, only part of it, so I need more time to review it again.

Q and A(from my point)
-
- Is a package a type of data? What are its advantages, and what role does it play in Java?
- A(that I review and search):A package is not a data type. It is a namespace used to organize related Java classes and interfaces. Packages make a project easier to manage, prevent naming conflicts, and help control access between different parts of the application.

- When should I let Spring manage an object as a bean, and when is it appropriate to create an object with `new`?

- module 6 need more time to review