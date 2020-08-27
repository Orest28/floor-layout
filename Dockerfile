FROM openjdk:11
FROM maven:latest
WORKDIR /app
ADD pom.xml /app
RUN mvn verify clean --fail-never

COPY . /app
RUN mvn -v
RUN mvn clean install -DskipTests


ARG JAR_FILE=target/*.jar
#ADD ./target/*.jar /dev/
COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","app.jar"]



################33


#FROM openjdk:11
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
