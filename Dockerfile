FROM maven:3.9.9-amazoncorretto-23 AS build

# Install Maven
RUN yum install -y maven

WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests -X

# Run stage
FROM amazoncorretto:23-alpine

ARG JAR_FILE=/build/target/*.jar
COPY --from=build ${JAR_FILE} /usr/local/docker-test/app.jar
ENTRYPOINT ["java","-jar","/usr/local/docker-test/app.jar"]
