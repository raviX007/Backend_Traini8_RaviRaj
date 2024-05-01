# Training Center Management API

This project is a Spring Boot application that provides a RESTful API for managing training centers. It allows you to create, retrieve, update, and delete training center information.

## Features

- Create a new training center with details such as name, code, address, student capacity, courses offered, contact email, and phone number.
- Retrieve a list of all registered training centers.
- Update the details of an existing training center.
- Delete a training center.

## Technologies Used

- Java 8 or higher
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL (or any other relational database)
- Maven

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- MySQL or any other relational database

### Installation

1. Clone the repository:
```
git clone https://github.com/your-username/“Backend_Traini8_RaviRaj.git
```

2. Navigate to the project directory:

```
cd Backend_Traini8_RaviRaj
```

3. Configure the database connection in the `application.properties` file.

4. Build the project using Maven:
```
mvn clean
mvn install
```
5. Run the application:

```
mvn spring-boot:run
```
The application will start running on `http://localhost:8080`.

## API Endpoints

- `POST /api/training-centers/postmap`: Create a new training center.
- `GET /api/training-centers`: Retrieve a list of all training centers.

## Screenshots of working project:
![getspd](https://github.com/raviX007/Backend_Traini8_RaviRaj/assets/91694681/93c1074b-1a23-465f-b902-e9963e3e5ea1)

