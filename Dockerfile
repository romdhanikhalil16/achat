FROM openjdk:17-jdk
EXPOSE 8087
ADD target/achat-1.0.jar achat-1.0.jar
ENTRYPOINT ["java","-jar","/achat-1.0.jar"]
