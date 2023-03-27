FROM eclipse-temurin:19-jdk-jammy

WORKDIR /NewLangUi


COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY src ./src
COPY fileConvertiti ./fileConvertiti

CMD ["./mvnw", "spring-boot:run"]