**Temperature Sensor Microservice**

This Spring Boot microservice is designed to manage temperature sensor data through REST API endpoints. It includes support for exception handling and validation of incoming sensor measurements and sensor registration forms. All measurements are persisted in a PostgreSQL database.

### Measurement Controller:

The `MeasurementController` manages endpoints related to sensor measurements.

- **POST /measurements/add**: Accepts a JSON representation of a measurement and adds it to the database after validation. If validation fails, it throws an `InvalidMeasurementDataException`. 
- **GET /measurements**: Retrieves all measurements stored in the database.
- **GET /measurements/rainyDays**: Retrieves measurements taken on rainy days.

### Sensor Controller:

The `SensorController` handles sensor registration endpoints.

- **POST /sensors/registration**: Registers a new sensor with the provided details. If validation fails, it throws an `InvalidSensorDataException`.

### Exception Handling:

Both controllers have exception handlers (`@ExceptionHandler`) to deal with invalid data exceptions. These handlers return appropriate error responses (`HttpStatus.BAD_REQUEST`) with error details.

### Validation:

Validation of incoming data is performed using `@Valid` annotation along with `BindingResult` to capture validation errors. Error messages are provided for each validation failure.

### Dependencies:

- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL JDBC Driver
- ModelMapper

### Contributors:

- [Your Name]
- [Contributor 1]
- [Contributor 2]

### License:

This project is licensed under the [license name]. See the LICENSE file for details.

### Support:

For any inquiries or issues, please contact [contact email]. Bug reports and feature requests are welcome.
