# Dental Lab Management Application Backend

This is a backend application for a dental lab management system written in Java, using Spring Boot, Spring Data, Hibernate, Spring Web, PostgreSQL, and JWT token authentication. It can perform CRUD (Create, Read, Update, and Delete) operations, as well as other endpoints.

## Technologies

* Java 17
* Spring Boot 2.7.9
* Spring Data
* Hibernate
* Spring Web
* Spring Security
* JWT token authentication
* Lombok
* PostgreSQL


## Controllers

### AuthController

This controller handles user authentication and registration.

Endpoints:

* POST /api/auth/signin: Authenticates user and returns a JWT token.
* POST /api/auth/signup: Registers a new user account.

### DentalWorkController

This controller handles CRUD operations for dental works.

Endpoints:

* GET /api/dental-works/all: Retrieves all dental works.
* GET /api/dental-works/id/{id}: Retrieves a dental work by ID.
* POST /api/dental-works: Creates a new dental work.
* DELETE /api/dental-works/id/{id}: Deletes a dental work by ID.
* GET /api/dental-works/status/{status}: Retrieves dental works by status.
* GET /api/dental-works/type/{type}: Retrieves dental works by type.
* GET /api/dental-works/color/{color}: Retrieves dental works by color.
* PUT /api/dental-works/id/{id}: Updates a dental work by ID.

### DentalTechnicianController

This controller handles CRUD operations for dental technicians.

Endpoints:

* GET /api/technicians/{id}: Retrieves a dental technician by ID.
* GET /api/technicians: Retrieves all dental technicians.
* POST /api/technicians: Creates a new dental technician.
* PUT /api/technicians/{id}: Updates a dental technician by ID.
* DELETE /api/technicians/{id}: Deletes a dental technician by ID.


### PatientController
* GET /api/patients/all 
* GET /api/patients/id/{id} 
* POST /api/patients
* DELETE /api/patients/id/{id} 
* PUT /api/patients/id/{id}


DentistController
* GET /api/dentists/{id}
* POST /api/dentists
* PUT /api/dentists/{id}
* DELETE /api/dentists/{id}
* GET /api/dentists
* GET /api/dentists?address={address}
* GET /api/dentists?clinic={clinic}

### PatientController:
* GET /api/patients/{id}
* GET /api/patients
* GET /api/patients/dentist/{name}
* POST /api/patients
* PUT /api/patients/{id}
* DELETE /api/patients/{id}

### TestController:
* GET /api/test/all
* GET /api/test/user
* GET /api/test/admin

