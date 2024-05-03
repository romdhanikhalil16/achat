#FROM openjdk:8-jdk-alpine
#EXPOSE 8082
#ADD target/timesheet-devops-1.0.jar timesheet-devops-1.0.jar
#ENTRYPOINT["java","-jar","/timesheet-devops-1.0.jar"]


FROM openjdk:17-jdk-alpine
EXPOSE 8089
ADD target/achat-1.0.jar achat-1.0.jar
ENTRYPOINT ["java", "-jar", "/achat-1.0.jar"]
