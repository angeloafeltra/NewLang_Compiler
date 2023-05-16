FROM eclipse-temurin:17-jdk-jammy

RUN apt-get update
RUN apt-get -y install gcc

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./


COPY src ./src
COPY srcjflexcup ./srcjflexcup
COPY test_files ./test_files

RUN ./mvnw dependency:resolve
RUN ./mvnw package

CMD ["java", "-jar", "./target/NewLang-1.0-SNAPSHOT.jar"]
