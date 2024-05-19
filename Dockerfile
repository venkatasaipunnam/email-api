FROM openjdk:17
EXPOSE 8080
ADD build/libs/email-api-docker.jar email-api-docker.jar
ENTRYPOINT ["java", "-jar","/email-api-docker.jar"]