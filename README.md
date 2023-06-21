# Student-Teacher Connect Web Application

This is a web application developed using Spring Boot, Thymeleaf, and Bootstrap that connects students with teachers. The application provides user management for both students and teachers, allowing them to create accounts and manage their profiles. Students can send session requests to teachers, and they have the option to choose from a variety of teachers based on ratings and prices. Teachers have an online wallet to track their revenue.

## Prerequisites

To run this application, ensure that you have the following software installed:

- Java Development Kit (JDK) 17 or higher
- Maven
- MSSQL or any other supported database management system

## Getting Started

Follow the steps below to set up and run the application:

1. Clone the repository:

   ```bash
   git clone https://github.com/perrywalid/StudentApp.git

2. Navigate to the project directory:

   ```bash
   cd StudentApp

3. Build the application using Maven:
 
   ```bash
   mvn clean install

4. Configure the database:

- Create a MSSQL database with the name `student`.
- Update the database configuration in the `application.properties` file located in the `src/main/resources` directory. Replace `username` and `password` with your MSSQL credentials.

5. Run the application:
     ```bash
   mvn spring-boot:run

6. Open your web browser and access the application at [http://localhost:8080](http://localhost:8080).

## Features

### User Management

- Students and teachers can create accounts and log in to the application.
- Account information is securely stored and encrypted in the database.

### Student Features

- View a list of teachers available for sessions.
- Sort teachers by rating and price.
- Send session requests to teachers.
- Manage session requests (accept, decline, or cancel).
- View teacher profiles with detailed information.
- View past session history.
- Update profile information.

### Teacher Features

- View session requests from students.
- Accept or decline session requests.
- Manage accepted sessions (mark as completed or cancel).
- View student profiles with detailed information.
- View session history.
- Update profile information.
- Monitor online wallet to track revenue.

## Technologies Used

- Spring Boot 2.7.5
- Thymeleaf 3.0.11.RELEASE
- Bootstrap (version not specified)
- Maven
- MSSQL
- Lombok 1.18.24
- Spring Data JPA
- Spring Security
- JUnit (for testing)

## Directory Structure

```bash
.
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/
│ │ │ └── example/
│ │ │ └── studentapp/
│ │ │ ├── config/
│ │ │ │ └── ...
│ │ │ ├── controller/
│ │ │ │ └── ...
│ │ │ ├── model/
│ │ │ │ └── ...
│ │ │ ├── repository/
│ │ │ │ └── ...
│ │ │ ├── service/
│ │ │ │ └── ...
│ │ │ └── StudentAppApplication.java
│ │ └── resources/
│ │ ├── static/
│ │ │ └── ...
│ │ ├── templates/
│ │ │ └── ...
│ │ └── application.properties
│ └── test/
│ └── ...
├── pom.xml
└── README.md
```
## Conclusion

This web application provides an efficient way for students to connect with teachers, offering a user-friendly interface and essential features. By leveraging Spring Boot, Thymeleaf, and Bootstrap, the application ensures a smooth and responsive experience for users. Feel free to explore and enhance the application based on your specific requirements. If you encounter any issues, please refer to the documentation or reach out to the developer community for assistance. Happy teaching and learning!
