FROM openjdk:22-slim
ADD /target/Library-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]

