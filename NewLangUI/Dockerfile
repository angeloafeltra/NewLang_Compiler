FROM eclipse-temurin:17-jdk-jammy

WORKDIR /NewLangUi


COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src
COPY src/fileConvertiti ./fileConvertiti

RUN ./mvnw dependency:resolve
RUN ./mvnw package

EXPOSE 8081

CMD ["java", "-jar", "./target/NewLangUI-0.0.1-SNAPSHOT.jar"]
