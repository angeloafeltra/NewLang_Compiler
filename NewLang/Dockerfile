FROM eclipse-temurin:17-jdk-jammy

RUN apt-get update
RUN apt-get -y install gcc

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY src ./src
COPY srcjflexcup ./srcjflexcup
COPY test_files ./test_files

EXPOSE 8080
CMD ["./mvnw", "test" , "spring-boot:run"]