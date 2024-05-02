
# Project "Basketball League"
The "Basketball League" project implements a system for managing players and teams in a basketball league. The project allows adding, updating, deleting, and retrieving information about players and teams, as well as creating reports for a specified team.

## Technologies
- Java
- Spring Framework
- Hibernate
- Maven
- Lombok
- Apache POI
- H2 Database
- RESTful API


## Functionality
1. Player Management:
* Add new players.
```json
  {
    "name": "Stephen Curry",
    "position": "Point guard",
    "team_id": 2,
    "age": 27
  }
```
* Update player information.
```json
  {
    "name": "Stephen Curry",
    "position": "Point guard",
    "team_id": 2,
    "age": 29
  }
  ```
* Retrieve player information by ID.
```http request
  localhost:8080/api/player/1
  
  ```
* Delete players by ID.
```http request
  localhost:8080/api/player/1
  ```
2. Team Management:
* Add new teams.
```json
{
    "name": "Mukachevo DSH",
    "city": "Mukachevo"
}
```
* Update team information.
```json
{
"name": "Mukachevo Bears",
"city": "Mukachevi"
}
```
* Retrieve team information by ID.
* Delete teams by ID.
* Reports:
* Generate Excel reports for players in a specified team.When saving, specify the xls format
```http request
localhost:8080/api/player/_report
```

How to Run
Clone this repository.
Navigate to the project directory.
Run mvn clean install to build the project.
Run the application using your IDE or by executing the generated JAR file.
Access the REST API endpoints to interact with the system.
API Documentation
The API documentation can be found in the codebase and is accessible through Swagger or other API documentation tools.

Contributors
[Dmytro Armianishyn]