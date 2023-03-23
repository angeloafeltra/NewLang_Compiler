FROM eclipse-temurin:19-jdk-jammy

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY src ./src


CMD ["./mvnw", "spring-boot:run"]