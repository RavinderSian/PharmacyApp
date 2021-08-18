FROM arm32v7/adoptopenjdk:11-jre-hotspot
RUN apt install mvn
COPY . /src
WORKDIR src/main/java/com/personal/pharmacy/
CMD mvn spring-boot:run