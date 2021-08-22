FROM eclipse-temurin:8-jdk-focal
RUN apt-get update && apt-get install maven -y
COPY . /src
WORKDIR src/main/java/com/personal/pharmacy/
CMD mvn spring-boot:run
