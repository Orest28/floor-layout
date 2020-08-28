# floor-layout
### Maven
Please, use mvn clean package with -DskipTests key, because database is in container and programm throw connect exception, so use
```
mvn clean package -DskipTests
```

### Docker
For run program use following commands
```
docker-compose build
docker-compose up
```


