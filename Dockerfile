FROM openjdk:17
ADD target/boot-example-k8s-mysql-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]