# floor-layout
### Maven
Please, use mvn clean package with -DskipTests key, because database is in container and programm throw connect exception, so use
```
mvn clean package -DskipTests
```

### Docker
To run program use following commands
```
docker-compose build
docker-compose up
```

### Requests (Post)

To send new data, use the following request

#### Succesfull requests
```
http://localhost:8080/api/rooms/validateRoom
[
        {"x" : 1, "y" : 1},
        {"x" : 2, "y" : 1},
        {"x" : 2, "y" : 0},
        {"x" : 1, "y" : 0}
]

```
#### Bad request
```
http://localhost:8080/api/rooms/validateRoom
[
        {"x" : 1, "y" : 1},
        {"x" : 2, "y" : 2}
]
```
