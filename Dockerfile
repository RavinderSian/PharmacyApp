FROM arm32v7/adoptopenjdk:11-jre-hotspot
RUN apt update && apt install mvn -y
COPY . /src
WORKDIR src/main/java/com/personal/pharmacy/
CMD mvn spring-boot:run