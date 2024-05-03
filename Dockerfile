FROM openjdk:17-jdk-alpine
EXPOSE 8091
ADD target/achat-1.0.jar achat-1.0.jar
ENTRYPOINT ["java", "-jar", "/achat-1.0.jar"]