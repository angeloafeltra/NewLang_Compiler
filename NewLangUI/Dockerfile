FROM eclipse-temurin:19-jdk-jammy

WORKDIR /NewLangUi


COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY src ./src
COPY fileConvertiti ./fileConvertiti

EXPOSE 8081

RUN ./mvnw package
COPY target/NewLangUI-0.0.1-SNAPSHOT.jar ./

CMD ["java", "-jar", "NewLangUI-0.0.1-SNAPSHOT.jar"]
