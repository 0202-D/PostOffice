FROM openjdk:17
EXPOSE 8090
ADD target/PostOffice-0.0.1-SNAPSHOT.jar post_office.jar
ENTRYPOINT ["java", "-jar", "post_office.jar"]