# Movement Tracker
Introduce Movement Tracker service with user tracking and distance calculation features using Kafka and Spring Boot. Includes core functionalities, such as Haversine formula-based distance calculations, an in-memory cache, REST APIs for location updates, and integrated unit tests.
## Prerequisites
This project requires having Zookeeper and Kafka running on your local machine.

## Installation
Pull the project to your local machine using git:
bash git clone https://github.com/Karen3110/movement-tracker.git cd movement-tracker mvn clean install
## Usage
Run the application after navigating to the project directory with:
bash java -jar target/movement-tracker.jar
## Dependencies
This project is built with the following dependencies:

- [`spring-boot-starter-web`](https://spring.io/projects/spring-boot)
- [`spring-boot-starter-test`](https://spring.io/projects/spring-boot)
- [`lombok`](https://projectlombok.org/) 1.18.36
- [`spring-kafka`](https://spring.io/projects/spring-kafka)
- [`gson`](https://github.com/google/gson)

xml   .........  
## Endpoints

#### Add user location
`POST http://localhost:7070/controller/emit`
json { "userId": 0, "longitude": 0, "latitude": 0, "date": "" }

#### Calculate distance
`GET http://localhost:7070/controller/distance/{{userId}}`
{
"userId": 234543,
"dateStart": "2023-03-07T10:30:00.000+00:00",
"dateEnd": "2023-03-07T10:30:00.000+00:00",
"distanceInKm": 1111.9492664455872,
"distanceInMiles": 690.933027640561
}

