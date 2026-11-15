# DineMaster – Restaurant & Chef Operations Platform

DineMaster is a Java Spring Boot backend project for managing restaurant and chef records through REST APIs. It uses Spring Data JPA and Hibernate to work with relational database entities.

## Features

- Create, view, update, and delete restaurant records.
- Create, view, update, and delete chef records.
- Retrieve the restaurant associated with a chef.
- Map Java model classes to database tables using JPA annotations.
- Handle missing records with HTTP `404 NOT_FOUND` responses.
- Use HTTP `204 NO_CONTENT` for successful delete operations.
- Define the restaurant and chef tables with a foreign-key relationship.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate ORM
- SQL
- Postman (for API testing)

## Project Structure

```text
03.DineMaster/
├── dinemaster/
│   ├── controller/
│   │   ├── ChefController.java
│   │   └── RestaurantController.java
│   ├── model/
│   │   ├── Chef.java
│   │   └── Restaurant.java
│   ├── repository/
│   │   ├── ChefJpaRepository.java
│   │   ├── ChefRepository.java
│   │   ├── RestaurantJpaRepository.java
│   │   └── RestaurantRepository.java
│   └── service/
│       ├── ChefJpaService.java
│       └── RestaurantJpaService.java
└── resources/
    └── schema.sql
```

## API Endpoints

### Restaurant APIs

| Method | Endpoint | Purpose |
|---|---|---|
| `GET` | `/restaurants` | Get all restaurants |
| `POST` | `/restaurants` | Add a restaurant |
| `GET` | `/restaurants/{restaurantId}` | Get a restaurant by ID |
| `PUT` | `/restaurants/{restaurantId}` | Update a restaurant |
| `DELETE` | `/restaurants/{restaurantId}` | Delete a restaurant |

### Chef APIs

| Method | Endpoint | Purpose |
|---|---|---|
| `GET` | `/restaurants/chefs` | Get all chefs |
| `POST` | `/restaurants/chefs` | Add a chef |
| `GET` | `/restaurants/chefs/{chefId}` | Get a chef by ID |
| `PUT` | `/restaurants/chefs/{chefId}` | Update a chef |
| `DELETE` | `/restaurants/chefs/{chefId}` | Delete a chef |
| `GET` | `/chefs/{chefId}/restaurant` | Get the restaurant associated with a chef |

## Data Model

### Restaurant

The `Restaurant` entity contains:

- `id`
- `name`
- `address`
- `cuisineType`
- `rating`

### Chef

The `Chef` entity contains:

- `id`
- `firstName`
- `lastName`
- `expertise`
- `experienceYears`
- `restaurant`

Each chef is associated with a restaurant through a `ManyToOne` relationship. The `restaurantid` column in the `chef` table references the `id` column in the `restaurant` table.

## Database Schema

The `schema.sql` file defines two tables:

- `restaurant` – stores restaurant details.
- `chef` – stores chef details and the restaurant reference.

Both tables use an auto-incrementing primary key. The `chef.restaurantid` column has a foreign-key reference to `restaurant.id`.

## API Testing

The REST endpoints can be tested using Postman. For create and update requests, send the required data as JSON in the request body.

Example request body for creating a restaurant:

```json
{
  "name": "Sample Restaurant",
  "address": "Sample Address",
  "cuisineType": "Indian",
  "rating": 4
}
```

Example request body for creating a chef:

```json
{
  "firstName": "Ravi",
  "lastName": "Kumar",
  "expertise": "Indian Cuisine",
  "experienceYears": 5,
  "restaurant": {
    "id": 1
  }
}
```

## Note

The shared project files include the Java source code and `schema.sql`. A build file and application configuration are not included in this archive, so the exact setup and run commands depend on the Spring Boot project configuration used with these source files.
