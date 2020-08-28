FROM openjdk:11
FROM maven:latest
WORKDIR /app
ADD pom.xml /app
RUN mvn verify clean --fail-never

COPY . /app
RUN mvn -v


ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]