# Restaurant Management System

A comprehensive restaurant management system that handles restaurant onboarding, subscription management, and verification processes.

## Features

- **Restaurant Management**: Onboard and manage restaurant information
- **Subscription Plans**: Manage different subscription plans including free trials
- **Verification System**: Handle restaurant verification with document uploads
- **API Documentation**: Comprehensive API documentation using OpenAPI/Swagger

## Tech Stack

- **Backend**: Spring Boot 3.x
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA with Hibernate
- **API Documentation**: SpringDoc OpenAPI 3.0
- **Build Tool**: Maven
- **Java Version**: 17+

## Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- PostgreSQL 12 or higher
- Git (optional)

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/restaurant-management-system.git
   cd restaurant-management-system
   ```

2. **Set up the database**
   - Create a new PostgreSQL database named `restaurant_db`
   - Update the database configuration in `src/main/resources/application-dev.properties` if needed

3. **Build the application**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   The application will start on `http://localhost:8080/api`

## API Documentation

Once the application is running, you can access the following:

- **Swagger UI**: http://localhost:8080/api/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api/v3/api-docs

## Profiles

The application supports different profiles:

- **dev** (default): Development configuration with H2 in-memory database
- **prod**: Production configuration with PostgreSQL

To run with a specific profile:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

## Database Schema

The database schema is managed using Liquibase. The changelog files are located in `src/main/resources/db/changelog/`.

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Spring Boot Team
- Hibernate Team
- OpenAPI Community
