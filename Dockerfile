FROM eclipse-temurin:11-jdk-focal
RUN apt-get update && apt-get install maven -y
COPY . /src
WORKDIR /src
CMD mvn org.springframework.boot:spring-boot-maven-plugin:run
